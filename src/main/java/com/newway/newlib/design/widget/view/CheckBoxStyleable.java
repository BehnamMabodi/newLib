package com.newway.newlib.design.widget.view;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by goldm on 11/03/2017.
 */

public class CheckBoxStyleable extends AppCompatCheckBox {
    public CheckBoxStyleable(Context context) {
        super(context);
        doOnCreate();
    }

    public CheckBoxStyleable(Context context, AttributeSet attrs) {
        super(context, attrs);
        doOnCreate();
    }

    public CheckBoxStyleable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doOnCreate();
    }

    private void doOnCreate() {
    }


    public void setMultiStyleText(String part1, String part2, int style1, int style2) {
        SpannableString formattedSpan = WidgetSharedComponents.CreateMultiStyleText(getContext(), part1, part2, style1, style2);
        setText(formattedSpan, TextView.BufferType.SPANNABLE);

    }
}
