package com.tramp.idea.dom.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;


public interface Bean extends DomElement {

  @NotNull
  @SubTagList("property")
  public List<BeanProperty> getBeanProperties();

}
