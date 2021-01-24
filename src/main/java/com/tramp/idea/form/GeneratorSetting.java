package com.tramp.idea.form;

import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author chenjm1
 * @since 2019/7/2
 */
public class GeneratorSetting implements SearchableConfigurable {
    private Setting setting = Setting.getInstance();
    public static final String PLUGIN_NAME = "ideaX";

    SettingForm settingForm = new SettingForm();

    @NotNull
    @Override
    public String getId() {
        return PLUGIN_NAME;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return PLUGIN_NAME;
    }

    @Override
    public void reset() {
        setting.setEntitySuffixsValue("#Entity,Usr#Entity,Smm#Entity");
        setting.setTablePrefixsValue("usr_,smm_,gcsp_,ci_");
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        String entitySuffixsValue = setting.getEntitySuffixsValue() != null ? setting.getEntitySuffixsValue() : "";
        String tablePrefixsValue = setting.getTablePrefixsValue() != null ? setting.getTablePrefixsValue() : "";
        settingForm.getEntitySuffixs().setText(entitySuffixsValue);
        settingForm.getTablePrefixs().setText(tablePrefixsValue);
        return settingForm.getMainPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        setting.setEntitySuffixsValue(settingForm.getEntitySuffixs().getText());
        setting.setTablePrefixsValue(settingForm.getTablePrefixs().getText());
    }

}
