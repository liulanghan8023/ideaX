package com.tramp.idea.dom.alias;

import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Sets;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.tramp.idea.dom.model.TypeAlias;
import com.tramp.idea.util.MapperUtils;


public class SingleAliasResolver extends AliasResolver {

	public SingleAliasResolver(Project project) {
		super(project);
	}

	@NotNull
	@Override
	public Set<AliasDesc> getClassAliasDescriptions(@Nullable PsiElement element) {
		final Set<AliasDesc> result = Sets.newHashSet();
		MapperUtils.processConfiguredTypeAliases(project, new Processor<TypeAlias>() {
			@Override
			public boolean process(TypeAlias typeAlias) {
				addAliasDesc(result, typeAlias.getType().getValue(), typeAlias.getAlias().getStringValue());
				return true;
			}
		});
		return result;
	}

}
