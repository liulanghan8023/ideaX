package com.tramp.idea.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.SubTagList;
import com.tramp.idea.dom.converter.AliasConverter;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public interface ParameterMap extends IdDomElement {

	@NotNull
	@Attribute("type")
	@Convert(AliasConverter.class)
	public GenericAttributeValue<PsiClass> getType();

	@SubTagList("parameter")
	public List<Parameter> getParameters();

}
