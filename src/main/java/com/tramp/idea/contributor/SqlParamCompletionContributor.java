package com.tramp.idea.contributor;

import com.google.common.base.Optional;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.injected.editor.DocumentWindow;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.tramp.idea.dom.model.IdDomElement;
import com.tramp.idea.util.DomUtils;
import com.tramp.idea.util.MapperUtils;

/**
 * @author yanglin
 */
public class SqlParamCompletionContributor extends CompletionContributor {

    @Override
    public void fillCompletionVariants(CompletionParameters parameters, final CompletionResultSet result) {
        if (parameters.getCompletionType() != CompletionType.BASIC) {
            return;
        }

        PsiElement position = parameters.getPosition();
        PsiFile topLevelFile = InjectedLanguageUtil.getTopLevelFile(position);
        if (DomUtils.isMybatisFile(topLevelFile)) {
            if (shouldAddParamElement(position.getContainingFile(), parameters.getOffset())) {
                paramProcess(topLevelFile, result, position);
            } else if (shouldAddDbPropertyElement(position.getContainingFile(), parameters.getOffset())) {
                dbPropertyProcess(topLevelFile, result, position);
            }
        }
    }

    private void dbPropertyProcess(PsiFile xmlFile, CompletionResultSet result, PsiElement position) {

        DocumentWindow documentWindow = InjectedLanguageUtil.getDocumentWindow(position);
        if (null != documentWindow) {
            int offset = documentWindow.injectedToHost(position.getTextOffset());
            Optional<IdDomElement> idDomElement = MapperUtils.findParentIdDomElement(xmlFile.findElementAt(offset));
            if (idDomElement.isPresent()) {
                DbPropertyContributor.addElementForPsiParameter(position, result, idDomElement.get());
                result.stopHere();
            }
        }
    }

    private void paramProcess(PsiFile xmlFile, CompletionResultSet result, PsiElement position) {
        DocumentWindow documentWindow = InjectedLanguageUtil.getDocumentWindow(position);
        if (null != documentWindow) {
            int offset = documentWindow.injectedToHost(position.getTextOffset());
            Optional<IdDomElement> idDomElement = MapperUtils.findParentIdDomElement(xmlFile.findElementAt(offset));
            if (idDomElement.isPresent()) {
                TestParamContributor.addElementForPsiParameter(position.getProject(), result, idDomElement.get());
                result.stopHere();
            }
        }
    }

    /**
     * 判断是否添加参数
     *
     * @param file
     * @param offset
     * @return
     */
    private boolean shouldAddParamElement(PsiFile file, int offset) {
        String allText = file.getText();
        for (int i = offset; i >= offset - 2; i--) {
            if (allText.charAt(i) == '{'
                    && (allText.charAt(i - 1) == '#' || allText.charAt(i - 1) == '$')) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否添加数据库的属性名
     *
     * @param file
     * @param offset
     * @return
     */
    private boolean shouldAddDbPropertyElement(PsiFile file, int offset) {
        String allText = file.getText();
        for (int i = offset; i >= offset - 2; i--) {
            if (allText.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }
}