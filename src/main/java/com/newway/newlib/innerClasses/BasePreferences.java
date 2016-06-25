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


    public BasePreferences(Context context, String fontAssetLocationPersian) {
        if (!mIsInitialized) {
            mSPrefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

            if (fontAssetLocationPersian != null)
                mTypeFaceMediumPersian = Typeface.createFromAsset(context.getAssets(), fontAssetLocationPersian);
            mTypeFaceMediumEnglish = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
            mTypeFaceRegularEnglish = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");

            //        Log.d("Display LANG", Locale.getDefault().getDisplayLanguage());
            mIsInitialized = true;
        }
    }

    public Typeface getTypeface(int typeFacesize) {
        if (isForcedPersian() || Locale.getDefault().getDisplayLanguage().equals("فارسی"))
            return mTypeFaceMediumPersian;
        else {
            if (typeFacesize == TYPEFACE_REGULAR)
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
}
