package com.tramp.idea.generate;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Optional;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.tramp.idea.dom.model.GroupTwo;
import com.tramp.idea.dom.model.Mapper;
import com.tramp.idea.dom.model.Select;

public class SelectGenerator extends StatementGenerator {

	public SelectGenerator(@NotNull String... patterns) {
		super(patterns);
	}

	@NotNull
	@Override
	protected GroupTwo getTarget(@NotNull Mapper mapper, @NotNull PsiMethod method) {
		Select select = mapper.addSelect();
		setupResultType(method, select);
		return select;
	}

	private void setupResultType(PsiMethod method, Select select) {
		Optional<PsiClass> clazz = StatementGenerator.getSelectResultType(method);
		if (clazz.isPresent()) {
			// TODO: 2018/1/3
			//select.getResultType().setValue(clazz.get());
		}
	}

	@NotNull
	@Override
	public String getId() {
		return "SelectGenerator";
	}

	@NotNull
	@Override
	public String getDisplayText() {
		return "Select Statement";
	}
}
