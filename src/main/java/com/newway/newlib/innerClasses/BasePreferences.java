package com.newway.newlib.innerClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by Gold Phoenix on 06/13/2016.
 */
public class BasePreferences {

    private static boolean mIsInitialized;
    private static SharedPreferences mSPrefs;
    private static boolean mForcePersian = false;
    private static Typeface mPersianTypeFace;

    public BasePreferences(Context context){
        if(!mIsInitialized) {
            mSPrefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            mPersianTypeFace = Typeface.createFromAsset(context.getAssets() , "fonts/")
        }
    }


}
