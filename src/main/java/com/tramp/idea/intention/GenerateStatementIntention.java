package com.tramp.idea.intention;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import com.tramp.idea.generate.StatementGenerator;

public class GenerateStatementIntention extends GenericIntention {

	public GenerateStatementIntention() {
		super(GenerateStatementChooser.INSTANCE);
	}

	@NotNull
	@Override
	public String getText() {
		return "[Mybatis] Generate new statement";
	}

	@Override
	public void invoke(@NotNull final Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
		StatementGenerator.applyGenerate(PsiTreeUtil.getParentOfType(element, PsiMethod.class));
	}

}
