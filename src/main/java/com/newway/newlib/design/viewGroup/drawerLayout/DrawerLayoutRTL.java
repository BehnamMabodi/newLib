package com.newway.newlib.design.viewGroup.drawerLayout;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

/**
 * Created by goldm on 17/02/2017.
 */

public class DrawerLayoutRTL extends DrawerLayout {
    protected boolean mHasPendingTransaction;


    protected String mTransactionTag;

    public DrawerLayoutRTL(Context context) {
        super(context);
    }

    public DrawerLayoutRTL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerLayoutRTL(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setHasPendingTransaction(boolean hasPendingTransaction) {
        mHasPendingTransaction = hasPendingTransaction;
    }

    public boolean hasPendingTransaction() {
        return mHasPendingTransaction;
    }

    public String getTransactionTag() {
        return mTransactionTag;
    }

    public void setTransactionTag(String transactionTag) {
        setHasPendingTransaction(true);
        mTransactionTag = transactionTag;
    }

}
