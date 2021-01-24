package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;

import com.tramp.idea.dom.converter.AliasConverter;
import com.tramp.idea.dom.converter.ResultMapConverter;


public interface ResultMap extends GroupFour, IdDomElement {

	@NotNull
	@Attribute("extends")
	@Convert(ResultMapConverter.class)
	public GenericAttributeValue<XmlAttributeValue> getExtends();

	@NotNull
	@Attribute("type")
	@Convert(AliasConverter.class)
	public GenericAttributeValue<PsiClass> getType();

}