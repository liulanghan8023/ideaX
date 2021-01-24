package com.tramp.idea.dom.model;

import java.util.List;

import com.intellij.util.xml.SubTagList;


public interface GroupThree extends GroupTwo{

  @SubTagList("selectKey")
  public List<SelectKey> getSelectKey();

}
