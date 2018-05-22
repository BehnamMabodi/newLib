package com.newway.newlib.design.widget.view.InputFilter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by goldm on 08/01/2018.
 */

public class InputFilterLength implements InputFilter {

    private int length;

    public InputFilterLength(int length) {
        this.length = length;
    }

    public InputFilterLength(String length) {
        this.length = Integer.parseInt(length);
    }

    @Override

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String input = dest.toString() + source.toString();
            if (isInRange(length, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(int length, String input) {
        return input.length() <= length;
    }
}