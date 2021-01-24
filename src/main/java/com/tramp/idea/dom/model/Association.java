package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.tramp.idea.dom.converter.AliasConverter;

public interface Association extends GroupFour, ResultMapGroup, PropertyGroup {

	@NotNull
	@Attribute("javaType")
	@Convert(AliasConverter.class)
	public GenericAttributeValue<PsiClass> getJavaType();
}
