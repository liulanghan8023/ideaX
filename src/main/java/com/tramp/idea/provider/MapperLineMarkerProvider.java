package com.tramp.idea.provider;

import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.CommonProcessors;
import com.intellij.util.xml.DomElement;
import com.tramp.idea.dom.model.Mapper;
import com.tramp.idea.service.JavaService;
import com.tramp.idea.util.Icons;
import com.tramp.idea.util.JavaUtils;

/**
 * @author chenjm1
 * @since 2017/12/26
 */
public class MapperLineMarkerProvider extends RelatedItemLineMarkerProvider {

	private static final Function<DomElement, XmlTag> FUN = domElement -> domElement.getXmlTag();

	@Override
	protected void collectNavigationMarkers(PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiNameIdentifierOwner && JavaUtils.isElementWithinInterface(element)) {
			CommonProcessors.CollectProcessor<Mapper> processor = new CommonProcessors.CollectProcessor<Mapper>();
			JavaService.getInstance(element.getProject()).process(element, processor);
			Collection<Mapper> results = processor.getResults();
			if (!results.isEmpty()) {
				NavigationGutterIconBuilder<PsiElement> builder = NavigationGutterIconBuilder.create(Icons.MAPPER_LINE_MARKER_ICON)
						.setAlignment(GutterIconRenderer.Alignment.CENTER).setTargets(Collections2.transform(results, FUN))
						.setTooltipTitle("Navigation to target in mapper xml");
				result.add(builder.createLineMarkerInfo(((PsiNameIdentifierOwner) element).getNameIdentifier()));
			}
		}

	}

}