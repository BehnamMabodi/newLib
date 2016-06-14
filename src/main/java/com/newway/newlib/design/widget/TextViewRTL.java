package com.newway.newlib.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import com.newway.newlib.R;
import com.newway.newlib.innerClasses.BasePreferences;
import com.newway.newlib.innerClasses.PersianConverter;

import java.util.Locale;

/**
 * Created by Golden Phoenix on 04/09/2015.
 */


public abstract class TextViewRTL extends android.support.v7.widget.AppCompatTextView {

    protected static BasePreferences mPrefs;

    public TextViewRTL(Context context) {
        super(context);
    }

    public TextViewRTL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewRTL(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void doOnCreate(Context context) {

    }

    public void setPersianText(String text) {
        if (mPrefs.isForcedPersian() || Locale.getDefault().getDisplayLanguage().equals("فارسی"))
            setText(PersianConverter.NumberToPersian(text));
        else
            setText(text);
    }
}
