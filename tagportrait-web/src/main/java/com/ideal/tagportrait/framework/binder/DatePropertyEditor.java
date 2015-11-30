package com.ideal.tagportrait.framework.binder;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * Email: xingsen@join-cn.com
 * User: 邢森
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private final String outFmt;
    private String fmt[];

    public DatePropertyEditor(String outFmt, String fmt[]) {
        this.fmt = fmt;
        this.outFmt = outFmt;
    }

    public DatePropertyEditor(String outFmt, String format) {
        this(outFmt, new String[]{format});
    }

    public DatePropertyEditor(String outFmt) {
        this(outFmt, new String[]{outFmt});
    }

    public String getAsText() {
        if (getValue() instanceof Date) {
            return DateFormatUtils.format((Date) getValue(), outFmt);
        }
        return super.getAsText();
    }

    public void setAsText(String value) {
        try {
            if (value != null && !value.equals("")) {
                setValue(DateUtils.parseDate(value, fmt));
            } else {
                setValue(null);
            }
        } catch (ParseException e) {
            setValue(null);
        }
    }
}
