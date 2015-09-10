package com.ideal.tagportrait.web.framework.binder;

import java.beans.PropertyEditorSupport;

/**
 * Email: xingsen@join-cn.com
 * User: 邢森
 */
public class EnumeratePropertyEditor extends PropertyEditorSupport {

    private Class clazz;

    public EnumeratePropertyEditor(Class clazz) {
        this.clazz = clazz;
    }

    public String StringAsText() {
        return (getValue() == null ? "" : ((Enum<?>) getValue()).name());
    }

    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Enum.valueOf(clazz, text));
    }
}
