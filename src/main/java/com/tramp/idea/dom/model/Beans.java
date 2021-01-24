package com.tramp.idea.dom.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;


public interface Beans extends DomElement {

  @NotNull
  @SubTagList("bean")
  public List<Bean> getBeans();

}
