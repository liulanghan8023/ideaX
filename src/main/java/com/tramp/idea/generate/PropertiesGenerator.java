package com.tramp.idea.generate;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.util.CommonProcessors;
import com.tramp.idea.ui.ListSelectionListener;
import com.tramp.idea.ui.UiComponentFacade;
import com.tramp.idea.util.PropertiesUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * @author chenjm1
 * @since 2019/1/7
 */
public abstract class PropertiesGenerator {

    private static final Function<VirtualFile, String> FUN = virtualFile -> {
        if (null == virtualFile)
            return "";
        return virtualFile.getName();
    };

    public static void applyGenerate(@Nullable final PsiElement psiElement) {
        if (null == psiElement) {
            return;
        }
        execute(psiElement);
    }

    public static void execute(@NotNull final PsiElement psiElement) {
        if (null == psiElement)
            return;
        CommonProcessors.CollectProcessor processor = new CommonProcessors.CollectProcessor();
        Collection properties = PropertiesUtils.findProperties(psiElement);
        properties.forEach(o -> {
            processor.process(o);
        });
        final List<VirtualFile> propertieList = Lists.newArrayList(processor.getResults());

        Collection<String> paths = Collections2.transform(propertieList, FUN);
        UiComponentFacade.getInstance(psiElement.getProject()).showListPopup("Choose target properties to generate", new ListSelectionListener() {
            @Override
            public void selected(int index) {
                setupProperty(psiElement, propertieList.get(index));
            }

            @Override
            public boolean isWriteAction() {
                return true;
            }
        }, paths.toArray(new String[paths.size()]));


    }

    private static void setupProperty(PsiElement psiElement, VirtualFile virtualFile) {
        Project project = psiElement.getProject();
        String text = psiElement.getText();
        if (StringUtils.isNotBlank(text)) {
            text = text.replace("\"", "");
        }
        Editor editor = FileEditorManager.getInstance(project).openTextEditor(new OpenFileDescriptor(project, virtualFile.getCanonicalFile()),
                true);
        if (null != editor) {
            Document document = editor.getDocument();
            document.setText(text + "=" + "\n" + document.getText());
            //NavigationUtil.activateFileWithPsiElement(element, true);
            int offset = text.length() + 1;
            editor.getCaretModel().moveToOffset(offset);
            editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
        }
    }

}
