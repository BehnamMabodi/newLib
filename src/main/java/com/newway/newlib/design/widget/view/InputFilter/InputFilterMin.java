package com.newway.newlib.design.widget.view.InputFilter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by goldm on 08/01/2018.
 */

public class InputFilterMin implements InputFilter {

    private int min;

    public InputFilterMin(int min) {
        this.min = min;
    }

    public InputFilterMin(String min) {
        this.min = Integer.parseInt(min);
    }

    @Override

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(int a, int c) {
        return c >= a;
    }
}