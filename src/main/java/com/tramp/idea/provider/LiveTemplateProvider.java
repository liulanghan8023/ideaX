package com.tramp.idea.provider;

import org.jetbrains.annotations.Nullable;

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;

/**
 * @author chenjm1
 * @since 2017/11/7
 */
public class LiveTemplateProvider implements DefaultLiveTemplatesProvider {

    @Override
    public String[] getDefaultLiveTemplateFiles() {
        //模板
        return new String[]{"liveTemplates/xmlTemplate"};
    }

    @Nullable
    @Override
    public String[] getHiddenLiveTemplateFiles() {
        return new String[0];
    }
}
