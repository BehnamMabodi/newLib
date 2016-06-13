package com.newway.newlib.design.widget.ViewSelectorCoordinator;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Golden Phoenix on 17/03/2016.
 */
public class SingleSelectViewCoordinator extends SelectorViewCoordinator {

    private View.OnClickListener mOnViewClickListener;


    public SingleSelectViewCoordinator(View... views) {
        mSelectedView = new ArrayList<>();
        mOnSelectChangedListeners = new ArrayList<>();
        mViews = new ArrayList<>();
        setViews(views);
    }

    @Override
    protected void onSelectedChanged(View newSelectedView) {
        if (!mSelectedView.isEmpty()) {
            for (View v : mSelectedView)
                v.setSelected(false);
            mSelectedView.clear();
        }
        newSelectedView.setSelected(true);
        mSelectedView.add(newSelectedView);
    }
}
