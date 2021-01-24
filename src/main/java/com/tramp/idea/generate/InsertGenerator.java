package com.tramp.idea.generate;

import org.jetbrains.annotations.NotNull;

import com.intellij.psi.PsiMethod;
import com.tramp.idea.dom.model.GroupTwo;
import com.tramp.idea.dom.model.Mapper;

public class InsertGenerator extends StatementGenerator {

	public InsertGenerator(@NotNull String... patterns) {
		super(patterns);
	}

	@NotNull
	@Override
	protected GroupTwo getTarget(@NotNull Mapper mapper, @NotNull PsiMethod method) {
		return mapper.addInsert();
	}

	@NotNull
	@Override
	public String getId() {
		return "InsertGenerator";
	}

	@NotNull
	@Override
	public String getDisplayText() {
		return "Insert Statement";
	}
}
