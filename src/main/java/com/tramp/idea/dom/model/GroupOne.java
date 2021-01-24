package com.tramp.idea.dom.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;


public interface GroupOne extends DomElement{

  @NotNull
  @SubTagList("include")
  public List<Include> getIncludes();

  @NotNull
  @SubTagList("trim")
  public List<Trim> getTrims();

  @NotNull
  @SubTagList("where")
  public List<Where> getWheres();

  @NotNull
  @SubTagList("set")
  public List<Set> getSets();

  @NotNull
  @SubTagList("foreach")
  public List<Foreach> getForeachs();

  @NotNull
  @SubTagList("choose")
  public List<Choose> getChooses();

  @NotNull
  @SubTagList("if")
  public List<If> getIfs();

}
