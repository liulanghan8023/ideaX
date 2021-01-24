package com.tramp.idea.dom.description;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.module.Module;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileDescription;
import com.tramp.idea.dom.model.Mapper;
import com.tramp.idea.util.DomUtils;

public class MapperDescription extends DomFileDescription<Mapper> {

	public MapperDescription() {
		super(Mapper.class, "mapper");
	}

	@Override
	public boolean isMyFile(@NotNull XmlFile file, @Nullable Module module) {
		return DomUtils.isMybatisFile(file);
	}

}
