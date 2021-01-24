package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;

import com.tramp.idea.dom.converter.AliasConverter;


public interface Select extends GroupTwo, ResultMapGroup {

	@NotNull
	@Attribute("resultType")
	@Convert(AliasConverter.class)
	GenericAttributeValue<PsiClass> getResultType();
}
