package com.tramp.idea.dom.converter;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.psi.ElementManipulators;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.*;
import com.tramp.idea.reference.ResultPropertyReferenceSet;

public class PropertyConverter extends ConverterAdaptor<XmlAttributeValue> implements CustomReferenceConverter<XmlAttributeValue> {

	@NotNull
	@Override
	public PsiReference[] createReferences(GenericDomValue<XmlAttributeValue> value, PsiElement element, ConvertContext context) {
		final String s = value.getStringValue();
		if (s == null) {
			return PsiReference.EMPTY_ARRAY;
		}
		return new ResultPropertyReferenceSet(s, element, ElementManipulators.getOffsetInElement(element)).getPsiReferences();
	}

	@Nullable
	@Override
	public XmlAttributeValue fromString(@Nullable @NonNls String s, ConvertContext context) {
		DomElement ctxElement = context.getInvocationElement();
		return ctxElement instanceof GenericAttributeValue ? ((GenericAttributeValue) ctxElement).getXmlAttributeValue() : null;
	}

}
