package com.tramp.idea.dom.model;

import java.util.List;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;


public interface Constructor extends DomElement {

  @SubTagList("arg")
  public List<Arg> getArgs();

  @SubTagList("idArg")
  public List<IdArg> getIdArgs();
}
