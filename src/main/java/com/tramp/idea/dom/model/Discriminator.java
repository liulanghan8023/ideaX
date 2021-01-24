package com.tramp.idea.dom.model;

import java.util.List;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Required;
import com.intellij.util.xml.SubTagList;


public interface Discriminator extends DomElement {

  @Required
  @SubTagList("case")
  public List<Case> getCases();

}
