package com.newway.newlib.innerClasses.Tools;

import android.view.View;
import android.view.animation.Interpolator;

public class MyAnimator {
    private int mAnimDelay;
    private Interpolator mInterpolator;

    public MyAnimator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void fadeIn(View view, float finalAlpha, boolean incrementDelay) {
        int delay = mAnimDelay;
        view.setTranslationY(-20f);
        view.setAlpha(0);
        view.animate().setDuration(150).setInterpolator(mInterpolator).translationY(0).setStartDelay(delay).alpha(finalAlpha).start();
        if (incrementDelay)
            mAnimDelay += 50;
    }

    public void fadeIn(int startDelay, boolean incrementDelay, View... views) {
        for (View v : views) {
            v.setAlpha(0);
            v.setTranslationY(-20f);
            v.animate().setDuration(150).setInterpolator(mInterpolator).translationY(0).setStartDelay(startDelay).alpha(1).start();
            if (incrementDelay)
                startDelay += 50;
        }
    }

    public void fadeOut(int startDelay, int duration, boolean incrementDelay, View... views) {
        for (View v : views) {
            v.animate().setInterpolator(mInterpolator).setDuration(duration).setStartDelay(startDelay).alpha(0).start();
            if (incrementDelay)
                startDelay += 50;
        }


    }

    public void setAlpha(float alpha, View... views) {
        for (View v : views) {
            v.setAlpha(alpha);
        }
    }

    public void resetDelay() {
        mAnimDelay = 0;
    }
}
