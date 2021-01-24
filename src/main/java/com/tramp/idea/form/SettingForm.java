package com.tramp.idea.form;

import javax.swing.*;

/**
 * @author chenjm1
 * @since 2019/7/2
 */
public class SettingForm {

    private JTextField entitySuffixs;
    private JTextField tablePrefixs;
    private JPanel mainPanel;

    public JTextField getEntitySuffixs() {
        return entitySuffixs;
    }

    public void setEntitySuffixs(JTextField entitySuffixs) {
        this.entitySuffixs = entitySuffixs;
    }

    public JTextField getTablePrefixs() {
        return tablePrefixs;
    }

    public void setTablePrefixs(JTextField tablePrefixs) {
        this.tablePrefixs = tablePrefixs;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
