package com.newway.newlib.design.widget.DialogManager;

import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;

import com.newway.newlib.R;

/**
 * Created by Golden Phoenix on 02/04/2016.
 */


public abstract class DialogManager {

    protected AlertDialog mAlertDialog;
    protected Context mContext;

    protected String mDialogID;

    public static int DIALOG_WIDTH_DEFAULT = -1;
    public static int DIALOG_HEIGHT_DEFAULT = -1;

    protected int mDialogWidth;
    protected int mDialogHeight;


    /**
     * Set new dialog view
     *
     * @param view New dialog view
     */
    public void setView(View view, String id) {
        initViews();
        mAlertDialog.setView(view);
        setDialogID(id);
    }

    protected void setDialogID(String id) {
        mDialogID = id;
    }

    /**
     * Set new size for dialog
     *
     * @param width  Width of dialog , use DIALOG_WIDTH_DEFAULT for default width
     * @param height Height of dialog , DIALOG_HEIGHT_DEFAULT for default height
     */
    public void setWidthAndHeight(int width, int height) {
        //TODO: Calculate Correct Value for tablets  , Constant size for width and variable size for height (if bigger than content , then should also be constant) , anyway should set after finishing main layout
        //         if(MyTools.SystemTools.getScreenWidth(mContext) >= 820 || MyTools.SystemTools.getScreenHeight(mContext) >= 820  )

        if (width == DIALOG_WIDTH_DEFAULT)
            width = (int) mContext.getResources().getDimension(R.dimen.UnitDialog_DialogMinMaxWidth);
        if (height == DIALOG_HEIGHT_DEFAULT)
            height = (int) mContext.getResources().getDimension(R.dimen.UnitDialog_DialogMinMaxHeight);

        mDialogWidth = width;
        mDialogHeight = height;
        mAlertDialog.getWindow().setLayout(width, height);



    }

    /**
     * Setup main views for the first time
     */
    protected abstract void initViews();

    public void hideDialog() {
        mAlertDialog.hide();
    }

    public void showDialog() {
        mAlertDialog.show();
        View view = mAlertDialog.getWindow().getDecorView();
        view.setScaleX(0.2f);
        view.setScaleY(0.2f);
        view.setAlpha(0f);

        view.animate().scaleX(1f).scaleY(1f).setDuration(400).alpha(1f).setInterpolator(new FastOutSlowInInterpolator()).start();
    }
}
