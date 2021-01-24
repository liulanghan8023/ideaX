package com.tramp.idea.reference;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.xml.XmlAttributeValue;


public final class ReferenceSetResolverFactory {

  private ReferenceSetResolverFactory() {
    throw new UnsupportedOperationException();
  }

  public static <F extends XmlAttributeValue> ContextReferenceSetResolver createPsiFieldResolver(@NotNull F target) {
    return new PsiFieldReferenceSetResolver(target);
  }

}
