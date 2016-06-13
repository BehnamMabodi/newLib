package com.newway.newlib.innerClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by Gold Phoenix on 06/13/2016.
 */
public class BasePreferences {

    protected static boolean mIsInitialized;
    protected static SharedPreferences mSPrefs;
    protected static boolean mForcePersian = false;
    protected static Typeface mPersianTypeFace;

    public BasePreferences(Context context, String fontAssetLocation) {
        if (!mIsInitialized) {
            mSPrefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            if(fontAssetLocation != null)
                mPersianTypeFace = Typeface.createFromAsset(context.getAssets(), fontAssetLocation);
            mIsInitialized = true;
        }
    }

    public Typeface getTypeface() {
        return mPersianTypeFace;
    }

    public boolean isForcedPersian() {
        return mForcePersian;
    }
}
