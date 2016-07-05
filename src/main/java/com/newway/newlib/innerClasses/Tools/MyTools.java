package com.newway.newlib.innerClasses.Tools;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;


/**
 * Created by Golden Phoenix on 05/08/2015.
 */
public abstract class MyTools {

    public static abstract class Convert {

        public static int[] StringArrayToInt(String[] mStringArray) {

            int[] intArray = new int[mStringArray.length];
            for (int i = 0; i < mStringArray.length; i++) {
                intArray[i] = Integer.parseInt(mStringArray[i].trim());
            }
            return intArray;
        }

        public static int getIntLength(int number) {
            int length = 0;
            while (number > 1) {
                number = number / 10;
                length++;
            }
            return length;
        }
    }


    public static abstract class SystemTools {

        public static float dpToPx(float dp) {
            /*// Reference http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
            float scale = context.getResources().getDisplayMetrics().density;
            return (int) ((dp * scale) + 0.5f);*/
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
        }

        /*        public static float dpToPxByFormula(float dp) {
                    return (dp * Resources.getSystem().getDisplayMetrics().density);
                }*/
        public static float pxToDp(float px) {
            return (px / Resources.getSystem().getDisplayMetrics().density);
        }

        public static int getSDKVersionNumber() {
            return Build.VERSION.SDK_INT;
        }

        public static boolean isSDKGreaterThan(int sdk) {
            return getSDKVersionNumber() >= sdk;
        }


        public static boolean isTablet() {
            boolean isTablet = false;
            DisplayMetrics metrics = new DisplayMetrics();

            float widthInches = metrics.widthPixels / metrics.xdpi;
            float heightInches = metrics.heightPixels / metrics.ydpi;
            double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
            if (diagonalInches >= 7.0) {
                isTablet = true;
            }
            return isTablet;
        }

        /**
         * @param context
         * @return Screen width in dp
         */
        public static float getScreenWidth(Context context) {
            int widthOfscreen = 0;
            DisplayMetrics dm = new DisplayMetrics();
            try {
                ((Activity) context).getWindowManager().getDefaultDisplay()
                        .getMetrics(dm);
            } catch (Exception ex) {
            }
            widthOfscreen = dm.widthPixels;
            dm = null;
            return pxToDp(widthOfscreen);
        }

        public static float getScreenHeight(Context context) {
            int heightOfscreen = 0;
            DisplayMetrics dm = new DisplayMetrics();
            try {
                ((Activity) context).getWindowManager().getDefaultDisplay()
                        .getMetrics(dm);
            } catch (Exception ex) {
            }
            heightOfscreen = dm.heightPixels;
            dm = null;
            return pxToDp(heightOfscreen);
        }

        public static void print(String s) {
            System.out.println(s);
        }

        public static boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }


       /* public int getMaxProperMemoryUsage(Context context)
        {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            int memoryClass = am.getMemoryClass();
            Log.v("onCreate", "memoryClass:" + Integer.toString(memoryClass));
            //Bytes
            return memoryClass;
        }

        public int getMaxAllowedMemoryUsage()
        {
            Runtime rt = Runtime.getRuntime();
            long maxMemory = rt.maxMemory();
            Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory));

            //Megabytes
            return (int) maxMemory;
        }*/


        /**
         * Create an intent for sending email.
         * Use startActivity(Intent.createChooser(emailIntent, "Send email...")); to start intent
         *
         * @param body            Email body
         * @param subject         Email Subject
         * @param receiverAddress Email Receiver Address
         * @return emailIntent
         */
        public static Intent sendMail(String receiverAddress, String subject, String body) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", receiverAddress, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
            return emailIntent;

        }
    }

    public static abstract class MarketTools {
        public static void goToMarketPage(Context context) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
        }

        /**
         * Crash if bazaar not installed , need to use try catch
         *
         * @param context
         */
        public static void sendCommentBazaar(Context context) throws Exception {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setData(Uri.parse("bazaar://details?id=" + context.getPackageName()));
            intent.setPackage("com.farsitel.bazaar");
            context.startActivity(intent);
        }

        public static void sendCommentInternational(Context context) {
            try {
                sendCommentBazaar(context);
            } catch (Exception e) {
                goToMarketPage(context);
            }
        }
    }

/*    public static abstract class MyAnimationBuilder {
        public static int STANDARD_ANIM_DURATION = 1500;

        public static SupportAnimator createCircularReveal(View view, int CX, int CY, float startRadius, float endRadius, int duration, Interpolator interpolator) {
            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, CX, CY, startRadius, endRadius);
            animator.setInterpolator(interpolator);
            animator.setDuration(duration);
            return animator;
        }
    }*/
}
