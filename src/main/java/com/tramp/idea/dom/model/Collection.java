package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.tramp.idea.dom.converter.AliasConverter;

public interface Collection extends GroupFour, ResultMapGroup, PropertyGroup {

	@NotNull
	@Attribute("ofType")
	@Convert(AliasConverter.class)
	public GenericAttributeValue<PsiClass> getOfType();

}