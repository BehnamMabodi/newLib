package com.newway.newlib.design.widget.ViewSelectorCoordinator;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * A class, that can be used as a TouchListener on any view (e.g. a Button).
 * It cyclically runs a clickListener, emulating keyboard-like behaviour. First
 * click is fired immediately, next one after the mInitialInterval, and subsequent
 * ones after the mNormalInterval.
 * <p/>
 * <p>Interval is scheduled after the onClick completes, so it has to run fast.
 * If it runs slow, it does not generate skipped onClicks. Can be rewritten to
 * achieve this.
 */
public class RepeatListener implements OnTouchListener {

    private static Handler handler = new Handler();

    private static int MODE_MULTICLICK = 10;
    private static int MODE_TIME_INTERPOLATOR = 20;
    private int mClickMod;
    private int mStepsIncreaseRate;
    private int mDefaultNormalInterval;
    private int mInitialInterval;
    private int mNormalInterval;
    private int mDefaultSteps = 1;
    private int mSteps = mDefaultSteps;
    private int mRepeatCount;
    private View.OnLongClickListener longClickListener;

    private View downView;

    private boolean hasStartedRepeating;

    private Runnable handlerRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                handler.postDelayed(this, mNormalInterval);
                for (int i = 0; i < mSteps; i++)
                    longClickListener.onLongClick(downView);
                flashRipple();
                hasStartedRepeating = true;
                decreaseIntervalTime();
                increaseSteps();
            } catch (Exception e) {
            }
        }
    };

    private void flashRipple() {
        Drawable drawable = downView.getBackground();
        if (isPressed(downView)) {
            if (downView.isEnabled())
                drawable.setState(new int[]{android.R.attr.state_enabled});
            else
                drawable.setState(new int[]{});
        } else
            drawable.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
    }

    private boolean isPressed(View view) {
        Drawable drawable = view.getBackground();
        int[] stateList = drawable.getState();
        for (int aStateList : stateList)
            if (aStateList == android.R.attr.state_pressed)
                return true;
        return false;
    }

    /**
     * @param initialInterval   The interval after first click event
     * @param normalInterval    The interval after second and subsequent click
     *                          events
     * @param longClickListener The OnClickListener, that will be called
     *                          periodically
     */
    public RepeatListener(int initialInterval, int normalInterval,
                          View.OnLongClickListener longClickListener) {
        if (longClickListener == null)
            throw new IllegalArgumentException("null runnable");
        if (initialInterval < 0 || normalInterval < 0)
            throw new IllegalArgumentException("negative interval");

        this.mInitialInterval = initialInterval;
        this.mNormalInterval = normalInterval;
        this.longClickListener = longClickListener;
        this.mDefaultNormalInterval = normalInterval;
        this.mClickMod = MODE_TIME_INTERPOLATOR;

    }

    /**
     * @param initialInterval   The interval after first click event
     * @param normalInterval    The interval after second and subsequent click
     *                          events
     * @param longClickListener The OnClickListener, that will be called
     *                          periodically
     * @param stepsIncreaseRate Increase rate for each steps
     */
    public RepeatListener(int initialInterval, int normalInterval, int stepsIncreaseRate,
                          View.OnLongClickListener longClickListener) {
        if (longClickListener == null)
            throw new IllegalArgumentException("null runnable");
        if (initialInterval < 0 || normalInterval < 0)
            throw new IllegalArgumentException("negative interval");

        this.mInitialInterval = initialInterval;
        this.mNormalInterval = normalInterval;
        this.longClickListener = longClickListener;
        this.mDefaultNormalInterval = normalInterval;
        this.mStepsIncreaseRate = stepsIncreaseRate;
        this.mClickMod = MODE_MULTICLICK;

    }


    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return actionDown(view);
            case MotionEvent.ACTION_UP:
                actionUp(view);
            case MotionEvent.ACTION_CANCEL:
                return actionCancel();
        }

        return false;
    }

    private boolean actionDown(View view) {
        try {
            handler.removeCallbacks(handlerRunnable);
            handler.postDelayed(handlerRunnable, mInitialInterval);
            downView = view;
            downView.setPressed(true);
        } finally {
            return true;
        }
    }


    private void actionUp(View view) {
        if (!hasStartedRepeating) {
            longClickListener.onLongClick(view);
        }
    }

    private boolean actionCancel() {
        try {
            handler.removeCallbacks(handlerRunnable);
            downView.setPressed(false);
            downView = null;
            hasStartedRepeating = false;
        } finally {
            resetIntervalTime();
            resetSteps();
            return true;
        }
    }

    private void increaseSteps() {
        if (mClickMod == MODE_MULTICLICK) {
            if (mRepeatCount > 8)
                mSteps = 2 * mStepsIncreaseRate;
            if (mRepeatCount > 10)
                mSteps = 3 * mStepsIncreaseRate;
            if (mRepeatCount > 13)
                mSteps = 4 * mStepsIncreaseRate;
            if (mRepeatCount > 15)
                mSteps = 5 * mStepsIncreaseRate;

            mRepeatCount++;
        }
    }

    private void resetSteps() {
        mSteps = mDefaultSteps;
        mRepeatCount = 0;
    }

    private void decreaseIntervalTime() {
        if (mClickMod == MODE_TIME_INTERPOLATOR && mNormalInterval > 40)
            mNormalInterval -= 8;
    }

    private void resetIntervalTime() {
        mNormalInterval = mDefaultNormalInterval;
    }
}
