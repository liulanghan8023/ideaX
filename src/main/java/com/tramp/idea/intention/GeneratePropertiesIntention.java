package com.tramp.idea.intention;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.tramp.idea.generate.PropertiesGenerator;
import com.tramp.idea.generate.StatementGenerator;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * @author chenjm1
 * @since 2019/1/7
 */
public class GeneratePropertiesIntention extends GenericIntention {
    public GeneratePropertiesIntention() {
        super(PropertiesChooser.INSTANCE);
    }

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "[Properties] Generate new property";
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
        PropertiesGenerator.applyGenerate(element);
    }
}
