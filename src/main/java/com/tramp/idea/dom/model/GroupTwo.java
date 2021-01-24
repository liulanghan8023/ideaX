package com.tramp.idea.dom.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.SubTagList;

import com.tramp.idea.dom.converter.AliasConverter;
import com.tramp.idea.dom.converter.DaoMethodConverter;
import com.tramp.idea.dom.converter.ParameterMapConverter;


public interface GroupTwo extends GroupOne, IdDomElement {

	@SubTagList("bind")
	public List<Bind> getBinds();

	@NotNull
	@Attribute("parameterMap")
	@Convert(ParameterMapConverter.class)
	GenericAttributeValue<XmlTag> getParameterMap();

	@Attribute("id")
	@Convert(DaoMethodConverter.class)
	GenericAttributeValue<String> getId();

	@NotNull
	@Attribute("parameterType")
	@Convert(AliasConverter.class)
	GenericAttributeValue<PsiClass> getParameterType();
}
