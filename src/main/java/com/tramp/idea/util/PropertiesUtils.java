package com.tramp.idea.util;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * @author chenjm1
 * @since 2019/1/7
 */
public final class PropertiesUtils {
    private PropertiesUtils() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @NonNls
    public static Collection findProperties(@NotNull PsiElement psiElement) {
        Project project = psiElement.getProject();
        GlobalSearchScope scope = GlobalSearchScope.projectScope(project);
        String text = psiElement.getText();
        text = text.replace("\"", "");
        if (StringUtils.isNotBlank(text) && text.contains(".")) {
            String fileNamePre = text.split("\\.")[0];
            Collection<VirtualFile> allFiles = FilenameIndex.getAllFilesByExt(project, "properties", scope);
            List<VirtualFile> virtualFiles = Lists.newArrayList();
            allFiles.forEach(virtualFile -> {
                virtualFiles.add(virtualFile);
                /*if (virtualFile.getName().startsWith(fileNamePre)) {
                    virtualFiles.add(virtualFile);
                }*/
            });
            return virtualFiles;
        }
        return Lists.newArrayList();
    }

}
