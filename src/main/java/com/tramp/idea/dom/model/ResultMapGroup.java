package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

import com.tramp.idea.dom.converter.ResultMapConverter;


public interface ResultMapGroup extends DomElement {

	@NotNull
	@Attribute("resultMap")
	@Convert(ResultMapConverter.class)
	GenericAttributeValue<XmlTag> getResultMap();
}
