package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.newway.newlib.R;
import com.newway.newlib.design.widget.view.CheckBoxStyleable;

/**
 * Created by goldm on 10/09/2017.
 */

public class NumberPickerDialogActivity extends DialogActivity {

    public static final String KEY_VALUE = "KEY_VALUE";
    public static final String KEY_MIN = "KEY_MIN";
    public static final String KEY_MAX = "KEY_MAX";


    protected NumberPicker mNumberPicker;

    CheckBoxStyleable.OnCheckedChangeListener mOnCheckedChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init(Bundle SavedBundle) {
        super.init(SavedBundle);
        int min = getIntent().getExtras().getInt(KEY_MIN);
        int max = getIntent().getExtras().getInt(KEY_MAX);
        int valueCurrent = getIntent().getExtras().getInt(KEY_VALUE);
        if (SavedBundle != null)
            valueCurrent = SavedBundle.getInt(KEY_VALUE);

        mNumberPicker = new NumberPicker(this);
        mNumberPicker.setMinValue(min);
        mNumberPicker.setMaxValue(max);
        mNumberPicker.setValue(valueCurrent);
        NumberPicker.LayoutParams params = new NumberPicker.LayoutParams(
                (int) getResources().getDimension(R.dimen.dialog_activity_min_width),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mNumberPicker.setLayoutParams(params);
        mFrameLayout.addView(mNumberPicker);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_VALUE, mNumberPicker.getValue());
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void commitChanges(Intent resultIntent) {
        if (resultIntent == null)
            resultIntent = new Intent();
        resultIntent.putExtra(KEY_VALUE, mNumberPicker.getValue());
        super.commitChanges(resultIntent);
    }

}
