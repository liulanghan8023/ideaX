package com.tramp.idea.dom.converter;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.ConvertContext;
import com.tramp.idea.dom.model.Mapper;
import com.tramp.idea.util.JavaUtils;
import com.tramp.idea.util.MapperUtils;


public class DaoMethodConverter extends ConverterAdaptor<PsiMethod> {

	@Nullable
	@Override
	public PsiMethod fromString(@Nullable @NonNls String id, ConvertContext context) {
		Mapper mapper = MapperUtils.getMapper(context.getInvocationElement());
		return JavaUtils.findMethod(context.getProject(), MapperUtils.getNamespace(mapper), id).orNull();
	}

}