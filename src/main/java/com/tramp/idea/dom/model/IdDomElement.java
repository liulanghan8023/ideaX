package com.tramp.idea.dom.model;

import com.intellij.util.xml.*;


public interface IdDomElement extends DomElement{

  @Required
  @NameValue
  @Attribute("id")
  public GenericAttributeValue<String> getId();

  public void setValue(String content);
}
