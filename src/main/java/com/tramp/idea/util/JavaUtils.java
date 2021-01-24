package com.tramp.idea.util;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PropertyUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.tramp.idea.dom.model.IdDomElement;

public final class JavaUtils {

	private JavaUtils() {
		throw new UnsupportedOperationException();
	}

	public static boolean isModelClazz(@Nullable PsiClass clazz) {
		return null != clazz && !clazz.isAnnotationType() && !clazz.isInterface() && !clazz.isEnum() && clazz.isValid();
	}

	@NotNull
	public static Optional<PsiField> findSettablePsiField(@NotNull PsiClass clazz, @Nullable String propertyName) {
		PsiMethod propertySetter = PropertyUtil.findPropertySetter(clazz, propertyName, false, true);
		return null == propertySetter ? Optional.<PsiField> absent() : Optional.fromNullable(PropertyUtil.findPropertyFieldByMember(propertySetter));
	}
	@NotNull
	public static Optional<PsiField> findPropertyField(@NotNull PsiClass clazz, @Nullable String propertyName) {
		PsiField propertyField = PropertyUtil.findPropertyField(clazz, propertyName, false);
		return Optional.fromNullable(propertyField);
	}
	@NotNull
	public static PsiField[] findSettablePsiFields(@NotNull PsiClass clazz) {
		PsiMethod[] methods = clazz.getAllMethods();
		List<PsiField> fields = Lists.newArrayList();
		for (PsiMethod method : methods) {
			if (PropertyUtil.isSimplePropertySetter(method)) {
				Optional<PsiField> psiField = findSettablePsiField(clazz, PropertyUtil.getPropertyName(method));
				if (psiField.isPresent()) {
					fields.add(psiField.get());
				}
			}
		}
		return fields.toArray(new PsiField[fields.size()]);
	}

	public static boolean isElementWithinInterface(@Nullable PsiElement element) {
		if (element instanceof PsiClass && ((PsiClass) element).isInterface()) {
			return true;
		}
		PsiClass type = PsiTreeUtil.getParentOfType(element, PsiClass.class);
		return Optional.fromNullable(type).isPresent() && type.isInterface();
	}

	@NotNull
	public static Optional<PsiClass> findClazz(@NotNull Project project, @NotNull String clazzName) {
		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(clazzName, GlobalSearchScope.allScope(project));
		if (aClass != null) {
			return Optional.fromNullable(aClass);
		}
		return null;
	}

	@NotNull
	public static Optional<PsiMethod> findMethod(@NotNull Project project, @Nullable String clazzName, @Nullable String methodName) {
		if (StringUtils.isBlank(clazzName) && StringUtils.isBlank(methodName)) {
			return Optional.absent();
		}
		Optional<PsiClass> clazz = findClazz(project, clazzName);
		if (clazz.isPresent()) {
			PsiMethod[] methods = clazz.get().findMethodsByName(methodName, true);
			return ArrayUtils.isEmpty(methods) ? Optional.<PsiMethod> absent() : Optional.of(methods[0]);
		}
		return Optional.absent();
	}

	@NotNull
	public static Optional<PsiMethod> findMethod(@NotNull Project project, @NotNull IdDomElement element) {
		return findMethod(project, MapperUtils.getNamespace(element), MapperUtils.getId(element));
	}

	public static boolean isAnnotationPresent(@NotNull PsiModifierListOwner target, @NotNull Annotation annotation) {
		PsiModifierList modifierList = target.getModifierList();
		return null != modifierList && null != modifierList.findAnnotation(annotation.getQualifiedName());
	}

	@NotNull
	public static Optional<PsiAnnotation> getPsiAnnotation(@NotNull PsiModifierListOwner target, @NotNull Annotation annotation) {
		PsiModifierList modifierList = target.getModifierList();
		return null == modifierList ? Optional.<PsiAnnotation> absent()
				: Optional.fromNullable(modifierList.findAnnotation(annotation.getQualifiedName()));
	}

	@NotNull
	public static Optional<PsiAnnotationMemberValue> getAnnotationAttributeValue(@NotNull PsiModifierListOwner target, @NotNull Annotation annotation,
			@NotNull String attrName) {
		if (!isAnnotationPresent(target, annotation)) {
			return Optional.absent();
		}
		Optional<PsiAnnotation> psiAnnotation = getPsiAnnotation(target, annotation);
		return psiAnnotation.isPresent() ? Optional.fromNullable(psiAnnotation.get().findAttributeValue(attrName))
				: Optional.<PsiAnnotationMemberValue> absent();
	}

	@NotNull
	public static Optional<PsiAnnotationMemberValue> getAnnotationValue(@NotNull PsiModifierListOwner target, @NotNull Annotation annotation) {
		return getAnnotationAttributeValue(target, annotation, "value");
	}

	public static Optional<String> getAnnotationValueText(@NotNull PsiModifierListOwner target, @NotNull Annotation annotation) {
		Optional<PsiAnnotationMemberValue> annotationValue = getAnnotationValue(target, annotation);
		return annotationValue.isPresent() ? Optional.of(annotationValue.get().getText().replaceAll("\"", "")) : Optional.<String> absent();
	}

	public static boolean isAnyAnnotationPresent(@NotNull PsiModifierListOwner target, @NotNull Set<Annotation> annotations) {
		for (Annotation annotation : annotations) {
			if (isAnnotationPresent(target, annotation)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAllParameterWithAnnotation(@NotNull PsiMethod method, @NotNull Annotation annotation) {
		PsiParameter[] parameters = method.getParameterList().getParameters();
		for (PsiParameter parameter : parameters) {
			if (!isAnnotationPresent(parameter, annotation)) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasImportClazz(@NotNull PsiJavaFile file, @NotNull String clazzName) {
		PsiImportList importList = file.getImportList();
		if (null == importList) {
			return false;
		}
		PsiImportStatement[] statements = importList.getImportStatements();
		for (PsiImportStatement tmp : statements) {
			if (null != tmp && tmp.getQualifiedName().equals(clazzName)) {
				return true;
			}
		}
		return false;
	}

}
