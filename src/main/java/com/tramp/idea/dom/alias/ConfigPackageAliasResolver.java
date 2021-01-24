package com.tramp.idea.dom.alias;

import java.util.ArrayList;
import java.util.Collection;

import com.tramp.idea.dom.model.Package;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.tramp.idea.util.MapperUtils;


public class ConfigPackageAliasResolver extends PackageAliasResolver {

	public ConfigPackageAliasResolver(Project project) {
		super(project);
	}

	@NotNull
	@Override
	public Collection<String> getPackages(@Nullable PsiElement element) {
		final ArrayList<String> result = Lists.newArrayList();
		MapperUtils.processConfiguredPackage(project, new Processor<Package>() {
			@Override
			public boolean process(Package pkg) {
				result.add(pkg.getName().getStringValue());
				return true;
			}
		});
		return result;
	}

}
