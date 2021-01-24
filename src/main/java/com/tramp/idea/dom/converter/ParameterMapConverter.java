package com.tramp.idea.dom.converter;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.util.xml.ConvertContext;
import com.tramp.idea.dom.model.IdDomElement;
import com.tramp.idea.dom.model.Mapper;

public class ParameterMapConverter extends IdBasedTagConverter {

	@NotNull
	@Override
	public Collection<? extends IdDomElement> getComparisons(@Nullable Mapper mapper, ConvertContext context) {
		return mapper.getParameterMaps();
	}

}
