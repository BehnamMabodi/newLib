package com.newway.newlib.innerClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import java.util.Locale;

/**
 * Created by Gold Phoenix on 06/13/2016.
 */
public class BasePreferences {

    protected static boolean mIsInitialized;
    protected static SharedPreferences mSPrefs;
    protected static boolean mForcePersian = false;
    protected static Typeface mTypeFaceMediumPersian;
    protected static Typeface mTypeFaceMediumEnglish;
    protected static Typeface mTypeFaceRegularEnglish;

    public static int TYPEFACE_PERSIAN = 10;
    public static int TYPEFACE_REGULAR = 10;
    public static int TYPEFACE_MEDIUM = 20;
    public static int TYPEFACE_BOLD = 30;


    protected BasePreferences(Context context, String fontAssetLocationPersian) {
        if (!mIsInitialized) {
            Context applicationContext = context.getApplicationContext();
            mSPrefs = applicationContext.getSharedPreferences(applicationContext.getPackageName(), Context.MODE_PRIVATE);

            if (fontAssetLocationPersian != null)
                mTypeFaceMediumPersian = Typeface.createFromAsset(applicationContext.getAssets(), fontAssetLocationPersian);
            mTypeFaceMediumEnglish = Typeface.createFromAsset(applicationContext.getAssets(), "fonts/Roboto-Medium.ttf");
            mTypeFaceRegularEnglish = Typeface.createFromAsset(applicationContext.getAssets(), "fonts/Roboto-Regular.ttf");

            //        Log.d("Display LANG", Locale.getDefault().getDisplayLanguage());
            mIsInitialized = true;
        }
    }

    public Typeface getTypeface(int typeFaceSize) {
        if (isForcedPersian() || Locale.getDefault().getDisplayLanguage().equals("فارسی"))
            return mTypeFaceMediumPersian;
        else {
            if (typeFaceSize == TYPEFACE_REGULAR)
                return mTypeFaceRegularEnglish;
            else
                return mTypeFaceMediumEnglish;
        }
    }

    public Typeface getTypeface(int typeFaceLangID, int typeFacesize) {
        if (typeFaceLangID == TYPEFACE_PERSIAN)
            return mTypeFaceMediumPersian;
        else {
            if (typeFacesize == TYPEFACE_REGULAR)
                return mTypeFaceRegularEnglish;
            else
                return mTypeFaceMediumEnglish;
        }
    }

    public boolean isForcedPersian() {
        return mForcePersian;
    }

    public boolean isFirstUse(String uniqueID) {
        return mSPrefs.getBoolean("IS_FIRST_USE" + uniqueID, true);
    }

    public void setFirstUse(String uniqueID, boolean firstUse) {
        mSPrefs.edit().putBoolean("IS_FIRST_USE" + uniqueID, firstUse).apply();
    }

    public void putInt(String name, int value) {
        mSPrefs.edit().putInt(name, value).apply();
    }

    public int getInt(String name, int defaultValue) {
        return mSPrefs.getInt(name, defaultValue);
    }

    public void putLong(String name, long value) {
        mSPrefs.edit().putLong(name, value).apply();
    }

    public long getLong(String name, long defaultValue) {
        return mSPrefs.getLong(name, defaultValue);
    }


    public void putString(String name, String value) {
        mSPrefs.edit().putString(name, value).apply();
    }

    public String getString(String name, String defaultValue) {
        return mSPrefs.getString(name, defaultValue);
    }

    public void putBoolean(String name, boolean value) {
        mSPrefs.edit().putBoolean(name, value).apply();
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        return mSPrefs.getBoolean(name, defaultValue);
    }

}
