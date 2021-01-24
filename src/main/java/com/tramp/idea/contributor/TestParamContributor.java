package com.tramp.idea.contributor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.XmlPatterns;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.util.ProcessingContext;
import com.tramp.idea.dom.model.IdDomElement;
import com.tramp.idea.enums.TypeEnum;
import com.tramp.idea.util.Icons;
import com.tramp.idea.util.JavaUtils;
import com.tramp.idea.util.MapperUtils;
import com.tramp.idea.util.MybatisConstants;

public class TestParamContributor extends CompletionContributor {

	private static final Logger logger = LoggerFactory.getLogger(TestParamContributor.class);

	public TestParamContributor() {
		extend(CompletionType.BASIC,
				XmlPatterns.psiElement().inside(XmlPatterns.xmlAttributeValue().inside(XmlPatterns.xmlAttribute().withName("test"))),
				new CompletionProvider<CompletionParameters>() {
					@Override
					protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context,
							@NotNull CompletionResultSet result) {
						PsiElement position = parameters.getPosition();
						addElementForPsiParameter(position.getProject(), result, MapperUtils.findParentIdDomElement(position).orNull());
					}
				});
	}

	public static void addElementForPsiParameter(@NotNull Project project, @NotNull CompletionResultSet result, @Nullable IdDomElement element) {
		if (null == element) {
			return;
		}
		PsiMethod psiMethod = JavaUtils.findMethod(project, element).orNull();
		if (null == psiMethod) {
			logger.info("psiMethod null");
			return;

		}
		PsiParameter[] psiParameters = psiMethod.getParameterList().getParameters();

		for (PsiParameter parameter : psiParameters) {
			LookupElementBuilder builder;
			PsiType type = parameter.getType();
			String canonicalText = type.getCanonicalText();
			if (TypeEnum.isExit(canonicalText)) {
				builder = LookupElementBuilder.create(parameter.getName()).withIcon(Icons.PARAM_COMPLETION_ICON);
				result.addElement(PrioritizedLookupElement.withPriority(builder, MybatisConstants.PRIORITY));
			}
			else {
				PsiClass psiClass = PsiTypesUtil.getPsiClass(type);
				PsiField[] settablePsiFields = JavaUtils.findSettablePsiFields(psiClass);
				for (PsiField psiField : settablePsiFields) {
					String text = psiField.getText();
					String[] docs = text.split("//");
					if (docs.length > 1) {
						String doc = "(" + docs[1]+")";
						builder = LookupElementBuilder.create(psiField.getName()).withTailText(doc).withIcon(Icons.PARAM_COMPLETION_ICON);
					}
					else {
						builder = LookupElementBuilder.create(psiField.getName()).withIcon(Icons.PARAM_COMPLETION_ICON);
					}
					result.addElement(PrioritizedLookupElement.withPriority(builder, MybatisConstants.PRIORITY));
				}
			}
		}
	}
}
