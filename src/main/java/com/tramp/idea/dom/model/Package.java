package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;


public interface Package extends DomElement {

  @NotNull
  @Attribute("name")
  public GenericAttributeValue<String> getName();

}
