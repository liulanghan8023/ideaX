package com.tramp.idea.dom.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;


public interface TypeAliases extends DomElement {

  @NotNull
  @SubTagList("typeAlias")
  public List<TypeAlias> getTypeAlias();

  @NotNull
  @SubTagList("package")
  public List<Package> getPackages();

}
