package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.newway.newlib.R;

/**
 * Created by goldm on 12/03/2017.
 */

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    View mMainLayout;
    FrameLayout mFrameLayout;
    View mBtnOK;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity);
        init(savedInstanceState);
    }

    protected void init(Bundle SavedBundle) {
        mMainLayout = findViewById(R.id.layout_main);
        mBtnOK = findViewById(R.id.btn_ok);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_frame);

        mBtnOK.setOnClickListener(this);
        mMainLayout.setOnClickListener(this);
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
