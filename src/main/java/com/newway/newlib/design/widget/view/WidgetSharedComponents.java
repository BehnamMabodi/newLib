package com.newway.newlib.design.widget.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;

/**
 * Created by goldm on 11/03/2017.
 */

public class WidgetSharedComponents {
    static public SpannableString CreateMultiStyleText(Context context, String part1, String part2, int style1, int style2) {
        return formatStyles(context, part1, part2, style1, style2);

    }

    static public SpannableString formatStyles(Context context, String part1, String part2, int style1, int style2) {
        // This method can convert to two arrays of strings and styles. This way can support infinite text parts and styles
        // However , this is not necessary for now
        SpannableString styledText = new SpannableString(part1 + part2);
        styledText.setSpan(new TextAppearanceSpan(context, style1), 0, part1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, style2), part1.length(), part1.length() + part2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return styledText;
    }
}
