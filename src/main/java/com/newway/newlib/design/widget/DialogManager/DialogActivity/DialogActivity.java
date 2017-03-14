package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.newway.newlib.R;
import com.newway.newlib.design.widget.view.TextViewStyleable;

/**
 * Created by goldm on 12/03/2017.
 */

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_TITLE_TEXT = "title_text";
    public static final String KEY_DESCRIPTION_TEXT = "description_text";
    public static final String KEY_BUTTON_OK_TEXT = "button_ok_text";
    public static final String KEY_BUTTON_CANCEL_TEXT = "button_cancel_text";

    String mStrTitle;
    String mStrDescription;
    View mMainLayout;
    TextViewStyleable mTvTitle;
    FrameLayout mFrameLayout;
    Button mBtnOK;
    TextViewStyleable mTvDescription;
    Button mBtnCancel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity);
        init(savedInstanceState);
    }

    protected void init(Bundle SavedBundle) {
        mMainLayout = findViewById(R.id.layout_main);
        mTvTitle = (TextViewStyleable) findViewById(R.id.tv_title);
        mTvDescription = (TextViewStyleable) findViewById(R.id.tv_description);
        mBtnOK = (Button) findViewById(R.id.btn_ok);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_frame);
        setTitle(getIntent().getStringExtra(KEY_TITLE_TEXT));
        setDescription(getIntent().getStringExtra(KEY_DESCRIPTION_TEXT));
        setButtonText(getIntent().getStringExtra(KEY_BUTTON_OK_TEXT), getIntent().getStringExtra(KEY_BUTTON_CANCEL_TEXT), null);

        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mMainLayout.setOnClickListener(this);
    }

    private void setButtonText(String buttonTextOK, String buttonTextCancel, String buttonTextNatural) {
        if (buttonTextOK != null)
            mBtnOK.setText(buttonTextOK);
        if (buttonTextCancel != null)
            mBtnCancel.setText(buttonTextCancel);
    }

    public void setTitle(String title) {
        mStrTitle = title;
        if (mStrTitle != null) {
            mTvTitle.setText(mStrTitle);
            mTvTitle.setVisibility(View.VISIBLE);
        } else
            mTvTitle.setVisibility(View.GONE);

    }

    public void setDescription(String description) {
        mStrDescription = description;
        if (mStrDescription != null) {
            mTvDescription.setText(mStrDescription);
            mTvDescription.setVisibility(View.VISIBLE);
        } else
            mTvDescription.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View view) {
        if (view == mMainLayout || view == mBtnCancel)
            discardChanges(null);
        else if (view == mBtnOK)
            commitChanges(null);

    }

    protected void commitChanges(@Nullable Intent intent) {
        if (intent == null)
            intent = new Intent();
        setResult(RESULT_OK, intent);

        onBackPressed();
    }

    protected void discardChanges(@Nullable Intent intent) {
        if (intent == null)
            intent = new Intent();
        setResult(RESULT_CANCELED, intent);

        onBackPressed();
    }

}
