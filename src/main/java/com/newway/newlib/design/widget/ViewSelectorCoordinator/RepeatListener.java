package com.newway.newlib.design.widget.ViewSelectorCoordinator;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * A class, that can be used as a TouchListener on any view (e.g. a Button).
 * It cyclically runs a clickListener, emulating keyboard-like behaviour. First
 * click is fired immediately, next one after the initialInterval, and subsequent
 * ones after the normalInterval.
 * <p/>
 * <p>Interval is scheduled after the onClick completes, so it has to run fast.
 * If it runs slow, it does not generate skipped onClicks. Can be rewritten to
 * achieve this.
 */
public class RepeatListener implements OnTouchListener {

    private static Handler handler = new Handler();

    private int initialInterval;
    private int normalInterval;
    private int defaultNormalInterval;
    private View.OnLongClickListener longClickListener;

    private View downView;

    private boolean hasStartedRepeating;

    private Runnable handlerRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                handler.postDelayed(this, normalInterval);
                longClickListener.onLongClick(downView);
                hasStartedRepeating = true;
                decreaseIntervalTime();
            } catch (Exception e) {

            }
        }
    };

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

        this.initialInterval = initialInterval;
        this.normalInterval = normalInterval;
        this.longClickListener = longClickListener;
        defaultNormalInterval = normalInterval;

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
            handler.postDelayed(handlerRunnable, initialInterval);
            downView = view;
            downView.setPressed(true);
        }
        finally {
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
        }
        finally {
            resetIntervalTime();
            return true;
        }
    }

    private void decreaseIntervalTime() {
        if (normalInterval > 40)
            normalInterval -= 8;
    }

    private void resetIntervalTime() {
        normalInterval = defaultNormalInterval;
    }

}
