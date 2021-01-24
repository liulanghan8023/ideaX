package com.tramp.idea.dom.model;

import java.util.List;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTag;
import com.intellij.util.xml.SubTagList;


public interface GroupFour extends DomElement {

  @SubTag("constructor")
  public Constructor getConstructor();

  @SubTagList("id")
  public List<Id> getIds();

  @SubTagList("result")
  public List<Result> getResults();

  @SubTagList("association")
  public List<Association> getAssociations();

  @SubTagList("collection")
  public List<Collection> getCollections();

  @SubTag("discriminator")
  public Discriminator getDiscriminator();
}
