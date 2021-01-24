package com.tramp.idea.dom.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Required;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;


public interface Choose extends DomElement {

  @NotNull
  @Required
  @SubTagList("when")
  public List<When> getWhens();

  @SubTag("otherwise")
  public Otherwise getOtherwise();

}
