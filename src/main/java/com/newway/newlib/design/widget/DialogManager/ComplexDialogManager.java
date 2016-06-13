package com.newway.newlib.design.widget.DialogManager;

import android.support.v7.app.AlertDialog;

/**
 * Created by Golden Phoenix on 02/04/2016.
 */
public class ComplexDialogManager extends DialogManager {


    @Override
    protected void initViews() {
        if (mAlertDialog == null)
            mAlertDialog = new AlertDialog.Builder(mContext).create();
    }
}
