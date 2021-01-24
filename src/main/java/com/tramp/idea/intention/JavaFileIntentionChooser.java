package com.tramp.idea.intention;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.tramp.idea.service.JavaService;
import com.tramp.idea.util.JavaUtils;

public abstract class JavaFileIntentionChooser implements IntentionChooser {

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		if (!(file instanceof PsiJavaFile))
			return false;
		PsiElement element = file.findElementAt(editor.getCaretModel().getOffset());
		return null != element && JavaUtils.isElementWithinInterface(element) && isAvailable(element);
	}

	public abstract boolean isAvailable(@NotNull PsiElement element);

	public boolean isPositionOfParameterDeclaration(@NotNull PsiElement element) {
		return element.getParent() instanceof PsiParameter;
	}

	public boolean isPositionOfMethodDeclaration(@NotNull PsiElement element) {
		return element.getParent() instanceof PsiMethod;
	}

	public boolean isPositionOfInterfaceDeclaration(@NotNull PsiElement element) {
		return element.getParent().getParent() instanceof PsiClass;
	}

	public boolean isTargetPresentInXml(@NotNull PsiElement element) {
		return JavaService.getInstance(element.getProject()).findWithFindFirstProcessor(element).isPresent();
	}

}
