package com.newway.newlib.innerClasses.Tools;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by Gold Phoenix on 06/28/2016.
 */
public abstract class MyAnimationUtils {

    /**
     * Create circular reveal for Post Lollipop
     *
     * @param view
     * @param duration
     * @param delay
     */
    public static void enterReveal(View view, int duration, int delay, Integer cx, Integer cy) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                // get the center for the clipping circle
                if (cx == null || cy == null) {
                    cx = view.getMeasuredWidth() / 2;
                    cy = view.getMeasuredHeight() / 2;
                }

                // get the final radius for the clipping circle
                // int finalRadius = 125;
                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, getFinalRadius(cx, cy, view));
                anim.setInterpolator(new FastOutSlowInInterpolator());
                anim.setDuration(duration);
                anim.setStartDelay(delay);

                // make the view visible and start the animation
                view.setVisibility(View.VISIBLE);
                anim.start();
            } catch (Exception e) {
            }
        } else
            view.setVisibility(View.VISIBLE);
    }

    private static int getFinalRadius(int cx, int cy, View view) {

        //int finalRadius = (int) (Math.max(Math.sqrt(Math.pow(cx, 2) + Math.pow(view.getMeasuredWidth(), 2)), Math.sqrt(Math.pow(cy, 2) + Math.pow(view.getMeasuredHeight(), 2)))) / 2;
        int finalRadius1 = (int) (Math.sqrt(Math.pow(cx, 2) + Math.pow(cy, 2)));
        int finalRadius2 = (int) (Math.sqrt(Math.pow(view.getMeasuredWidth() - cx, 2) + Math.pow(cy, 2)));
        int finalRadius3 = (int) (Math.sqrt(Math.pow(cx, 2) + Math.pow(view.getMeasuredHeight() - cy, 2)));
        int finalRadius4 = (int) (Math.sqrt(Math.pow(view.getMeasuredWidth() - cx, 2) + Math.pow(view.getMeasuredHeight() - cy, 2)));

        return Math.max(Math.max(finalRadius1, finalRadius2), Math.max(finalRadius3, finalRadius4));
    }

    public static void forceRippleAnimation(View view, Float cX, Float cY) {
        Drawable background = view.getBackground();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && background instanceof RippleDrawable) {
            final RippleDrawable rippleDrawable = (RippleDrawable) background;

            rippleDrawable.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
            if (cX != null && cY != null)
                rippleDrawable.setHotspot(cX, cY);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rippleDrawable.setState(new int[]{});
                }
            }, 200);
        }
    }


}
