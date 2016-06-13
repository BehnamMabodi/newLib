package com.newway.newlib.design.widget.ViewSelectorCoordinator;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Golden Phoenix on 17/03/2016.
 */
public class MultiSelectViewCoordinator extends SelectorViewCoordinator {

    public MultiSelectViewCoordinator(View... views) {
        mSelectedView = new ArrayList<>();
        mOnSelectChangedListeners = new ArrayList<>();
        mViews = new ArrayList<>();
        setViews(views);
    }

    @Override
    protected void onSelectedChanged(View newSelectedView) {

        if (!mSelectedView.contains(newSelectedView)) {
            newSelectedView.setSelected(true);
            mSelectedView.add(newSelectedView);
        } else {
            newSelectedView.setSelected(false);
            mSelectedView.remove(newSelectedView);
        }
    }
}
