package com.newway.newlib.design.widget.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by goldm on 16/02/2017.
 */

public class TextViewMultiStyle extends AppCompatTextView {

    public TextViewMultiStyle(Context context) {
        super(context);
        doOnCreate();
    }

    public TextViewMultiStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
        doOnCreate();
    }

    public TextViewMultiStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doOnCreate();
    }

    protected void doOnCreate() {

    }

    public void setMultiStyleText(String part1, String part2, int style1, int style2) {
        SpannableString formattedSpan = formatStyles(part1, part2, style1, style2);
        setText(formattedSpan, TextView.BufferType.SPANNABLE);

    }

    public SpannableString formatStyles(String part1, String part2, int style1, int style2) {
        // This method can convert to two arrays of strings and styles. This way can support infinite text parts and styles
        // However , this is not necessary for now
        SpannableString styledText = new SpannableString(part1 + part2);
        styledText.setSpan(new TextAppearanceSpan(getContext(), style1), 0, part1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(getContext(), style2), part1.length(), part1.length() + part2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return styledText;
    }
}
