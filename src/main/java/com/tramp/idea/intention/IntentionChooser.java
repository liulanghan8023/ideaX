package com.tramp.idea.intention;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;


public interface IntentionChooser {

    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file);

}
