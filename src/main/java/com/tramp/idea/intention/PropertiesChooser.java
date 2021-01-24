package com.tramp.idea.intention;

import com.intellij.psi.PsiElement;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author chenjm1
 * @since 2019/1/7
 */
public class PropertiesChooser extends PropertiesFileIntentionChooser {
    public static final PropertiesFileIntentionChooser INSTANCE = new PropertiesChooser();

    @Override
    public boolean isAvailable(@NotNull PsiElement element) {
        String text = element.getText();
        if (StringUtils.isNotBlank(text) && text.contains(".") && !text.startsWith(".") && !text.endsWith(".")) {
            return true;
        }
        return false;
    }
}
