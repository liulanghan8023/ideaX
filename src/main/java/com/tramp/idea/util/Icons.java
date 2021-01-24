package com.tramp.idea.util;

import javax.swing.*;

import com.intellij.openapi.util.IconLoader;
import com.intellij.util.PlatformIcons;


public interface Icons {

  Icon MYBATIS_LOGO = IconLoader.getIcon("/javaee/persistenceId.png");

  Icon MAPPER_LINE_MARKER_ICON = IconLoader.getIcon("/images/mapper_method.png");

  Icon SPRING_INJECTION_ICON = IconLoader.getIcon("/images/injection.png");

  Icon STATEMENT_LINE_MARKER_ICON = IconLoader.getIcon("/images/statement.png");

  Icon PARAM_COMPLETION_ICON = PlatformIcons.PARAMETER_ICON;
  Icon VARIABLE_ICON = PlatformIcons.VARIABLE_ICON;
  //Icon PARAM_COMPLETION_ICON = IconLoader.getIcon("/images/injection.png");


}