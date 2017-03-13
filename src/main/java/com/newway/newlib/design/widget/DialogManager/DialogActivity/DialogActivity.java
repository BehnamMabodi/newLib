package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.newway.newlib.R;
import com.newway.newlib.design.widget.view.TextViewStyleable;

/**
 * Created by goldm on 12/03/2017.
 */

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_TITLE_TEXT = "title_text";
    public static final String KEY_DESCRIPTION_TEXT = "description_text";
    String mStrTitle;
    String mStrDescription;
    View mMainLayout;
    TextViewStyleable mTvTitle;
    FrameLayout mFrameLayout;
    View mBtnOK;
    TextViewStyleable mTvDescription;

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
        mBtnOK = findViewById(R.id.btn_ok);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_frame);
        setTitle(getIntent().getStringExtra(KEY_TITLE_TEXT));
        setDescription(getIntent().getStringExtra(KEY_DESCRIPTION_TEXT));

        mBtnOK.setOnClickListener(this);
        mMainLayout.setOnClickListener(this);
    }

    public void setTitle(String title) {
        mStrTitle = title;
        if (mStrTitle != null) {
            mTvTitle.setText(mStrTitle);
            mTvTitle.setVisibility(View.VISIBLE);
        } else
            mTvTitle.setVisibility(View.GONE);

    }

    public void setDescription(String title) {
        mStrDescription = title;
        if (mStrDescription != null) {
            mTvDescription.setText(mStrTitle);
            mTvDescription.setVisibility(View.VISIBLE);
        } else
            mTvDescription.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View view) {
        if (view == mMainLayout)
            discardChanges();
        else if (view == mBtnOK)
            commitChanges();

    }

    protected void discardChanges() {
        onBackPressed();
    }

    protected void commitChanges() {
        onBackPressed();
    }

}
