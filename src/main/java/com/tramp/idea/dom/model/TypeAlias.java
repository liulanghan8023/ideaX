package com.tramp.idea.dom.model;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;


public interface TypeAlias extends DomElement {

  @NotNull
  @Attribute("type")
  public GenericAttributeValue<PsiClass> getType();

  @NotNull
  @Attribute("alias")
  public GenericAttributeValue<String> getAlias();

}
