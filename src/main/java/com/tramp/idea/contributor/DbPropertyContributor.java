package com.tramp.idea.contributor;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.XmlPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.ProcessingContext;
import com.tramp.idea.dom.model.IdDomElement;
import com.tramp.idea.form.Setting;
import com.tramp.idea.util.Icons;
import com.tramp.idea.util.JavaUtils;
import com.tramp.idea.util.MapperUtils;
import com.tramp.idea.util.MybatisConstants;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjm1
 * @since 2019/1/14
 */
public class DbPropertyContributor extends CompletionContributor {
    private static final Logger logger = LoggerFactory.getLogger(DbPropertyContributor.class);

    public DbPropertyContributor() {
        extend(CompletionType.BASIC,
                XmlPatterns.psiElement().inside(XmlPatterns.xmlAttributeValue().inside(XmlPatterns.xmlAttribute().withName("test"))),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
                                                  @NotNull CompletionResultSet result) {
                        PsiElement position = parameters.getPosition();
                        addElementForPsiParameter(position, result, MapperUtils.findParentIdDomElement(position).orNull());
                    }
                });
    }

    public static void addElementForPsiParameter(@NotNull PsiElement position, @NotNull CompletionResultSet result, @Nullable IdDomElement element) {
        if (null == element) {
            return;
        }
        String text = element.getXmlElement().getText();
        if (StringUtils.isBlank(text)) {
            return;
        }
        String abbr;
        if (position.getPrevSibling() != null && position.getPrevSibling().getPrevSibling() != null) {
            abbr = position.getPrevSibling().getPrevSibling().getText();
        } else {
            abbr = position.getParent().getPrevSibling().getPrevSibling().getText();
        }
        if (StringUtils.isBlank(abbr)) {
            return;
        }
        Project project = position.getProject();
        //获取表及其简称
        Map<String, String> tables = getTableMap(text);
        String tableName = tables.get(abbr.toLowerCase());
        //获取对应实体类的属性
        String name = getTableNoPre(tableName);
        String upperName = upperName(name);
        //根据设置获取class
        PsiClass psiClass = getPsiClass(project, upperName);
        if (psiClass == null) {
            return;
        }
        PsiField[] fields = psiClass.getFields();
        LookupElementBuilder builder;
        for (PsiField psiField : fields) {
            String fieldText = psiField.getText();
            String[] docs = fieldText.split("//");
            String proName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, psiField.getName()).toUpperCase() + " ";

            if (docs.length > 1) {
                String doc = "(" + docs[1] + ")";
                builder = LookupElementBuilder.create(proName).withTailText(doc).withIcon(Icons.VARIABLE_ICON);
            } else {
                builder = LookupElementBuilder.create(proName).withIcon(Icons.VARIABLE_ICON);
            }
            result.addElement(PrioritizedLookupElement.withPriority(builder, MybatisConstants.PRIORITY));
        }
    }

    @Nullable
    private static PsiClass getPsiClass(Project project, String upperName) {
        Setting setting = Setting.getInstance();
        List<String> entityRuleList = setting.listEntityRule();
        for (String rule : entityRuleList) {
            String className = rule.replace("#", upperName);
            PsiFile[] filesByName = FilenameIndex.getFilesByName(project, className + ".java", GlobalSearchScope.allScope(project));
            if (filesByName.length == 0) {
                continue;
            }
            String classPath = ((PsiJavaFileImpl) filesByName[0].getContainingFile()).getPackageName() + "." + className;
            return JavaUtils.findClazz(project, classPath).get();
        }
        return null;
    }

    private static Map<String, String> getTableMap(String sql) {
        Map<String, String> resultMap = new HashMap<>();
        String[] froms = sql.split("\\s+");
        for (String from : froms) {
            if (StringUtils.isBlank(from)) {
                continue;
            }
            String tableName = from.toLowerCase();
            if (isTable(tableName)) {
                resultMap.put(initialStrToAbbreviation(tableName), tableName);
            }
        }
        return resultMap;
    }

    private static String getTableNoPre(String tableName) {
        List<String> tablePreList = Setting.getInstance().listTablePre();
        for (String tablePre : tablePreList) {
            tableName = tableName.replace(tablePre, "");
        }
        return tableName;
    }

    private static boolean isTable(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        List<String> tablePreList = Setting.getInstance().listTablePre();
        for (String tablePre : tablePreList) {
            if (name.startsWith(tablePre)) {
                return true;
            }
        }
        return false;
    }

    public static String upperName(String tableName) {
        String[] strs = tableName.split("_");
        String str = "";
        if (strs.length >= 0) {
            for (String string : strs) {
                str += initialStrToUpper(string);
            }
        }

        return str;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String initialStrToUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    /**
     * 转为缩写
     *
     * @param str
     * @return
     */
    public static String initialStrToAbbreviation(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        List<String> stringList = Splitter.on("_").splitToList(str);
        String result = "";
        for (String s : stringList) {
            if (StringUtils.isNotBlank(s)) {
                result = result + s.substring(0, 1);
            }
        }
        return result;
    }


}
