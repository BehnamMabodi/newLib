package com.newway.newlib.design.widget.view;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by goldm on 17/09/2017.
 */

public class CheckedTextViewStyleable extends android.support.v7.widget.AppCompatCheckedTextView {


    public CheckedTextViewStyleable(Context context) {
        super(context);
        doOnCreate();
    }

    public CheckedTextViewStyleable(Context context, AttributeSet attrs) {
        super(context, attrs);
        doOnCreate();
    }

    public CheckedTextViewStyleable(Context context, AttributeSet attrs, int defStyleAttr) {
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
