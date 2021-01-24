package com.tramp.idea.generate;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiMethod;
import com.tramp.idea.dom.model.GroupTwo;
import com.tramp.idea.dom.model.Mapper;

public class DeleteGenerator extends StatementGenerator {

	public DeleteGenerator(@NotNull String... patterns) {
		super(patterns);
	}

	@NotNull
	@Override
	protected GroupTwo getTarget(@NotNull Mapper mapper, @NotNull PsiMethod method) {
		return mapper.addDelete();
	}

	@NotNull
	@Override
	public String getId() {
		return "DeleteGenerator";
	}

	@NotNull
	@Override
	public String getDisplayText() {
		return "Delete Statement";
	}

}
