package com.tramp.idea.dom.model;

import java.util.List;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;


public interface Cache extends DomElement {

  @SubTagList("property")
  public List<Property> getProperties();

}
