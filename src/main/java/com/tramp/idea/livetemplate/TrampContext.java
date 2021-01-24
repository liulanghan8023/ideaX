package com.tramp.idea.livetemplate;

import org.jetbrains.annotations.NotNull;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiFile;

/**
 * @author chenjm1
 * @since 2017/11/7
 */
public class TrampContext extends TemplateContextType {

    protected TrampContext() {
        super("XML", "xml");
    }

    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        return file.getName().endsWith(".xml");
    }
}
