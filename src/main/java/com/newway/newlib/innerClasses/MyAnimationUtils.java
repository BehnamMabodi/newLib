package com.newway.newlib.innerClasses;

import android.animation.Animator;
import android.os.Build;
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
    public static void enterReveal(View view, int duration, int delay) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                // get the center for the clipping circle
                int cx = view.getMeasuredWidth() / 2;
                int cy = view.getMeasuredHeight() / 2;

                // get the final radius for the clipping circle
                // int finalRadius = 125;
                int finalRadius = (int) (Math.max(Math.sqrt(Math.pow(cx, 2) + Math.pow(view.getMeasuredWidth(), 2)), Math.sqrt(Math.pow(cy, 2) + Math.pow(view.getMeasuredHeight(), 2)))) / 2;
                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
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
}
