package com.tramp.idea.form;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author chenjm1
 * @since 2019/7/2
 */
@State(
        name = "com.tramp.idea.form.Setting",
        storages = {@Storage(
                file = "$APP_CONFIG$/ideaX.xml"
        )}
)
public class Setting implements PersistentStateComponent<Element> {

    private String entitySuffixsValue;
    private String tablePrefixsValue;

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element(GeneratorSetting.class.getSimpleName());
        element.setAttribute("entitySuffixsValue", this.getEntitySuffixsValue() == null ? "" : this.getEntitySuffixsValue());
        element.setAttribute("tablePrefixsValue", this.getTablePrefixsValue() == null ? "" : this.getTablePrefixsValue());
        return element;
    }

    @Override
    public void loadState(@NotNull Element element) {

    }

    public String getEntitySuffixsValue() {
        return entitySuffixsValue;
    }

    public void setEntitySuffixsValue(String entitySuffixsValue) {
        this.entitySuffixsValue = entitySuffixsValue;
    }

    public String getTablePrefixsValue() {
        return tablePrefixsValue;
    }

    public void setTablePrefixsValue(String tablePrefixsValue) {
        this.tablePrefixsValue = tablePrefixsValue;
    }

    public static Setting getInstance() {
        return ServiceManager.getService(Setting.class);
    }

    public List<String> listTablePre() {
        if (StringUtils.isBlank(this.tablePrefixsValue)) {
            return Lists.newArrayList();
        }
        return Splitter.on(",").splitToList(this.tablePrefixsValue);
    }

    public List<String> listEntityRule() {
        if (StringUtils.isBlank(this.entitySuffixsValue)) {
            return Lists.newArrayList();
        }
        return Splitter.on(",").splitToList(this.entitySuffixsValue);
    }
}
