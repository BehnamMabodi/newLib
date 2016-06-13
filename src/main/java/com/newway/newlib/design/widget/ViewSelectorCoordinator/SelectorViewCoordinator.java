package com.newway.newlib.design.widget.ViewSelectorCoordinator;

import android.view.View;

import java.util.List;

/**
 * Created by Golden Phoenix on 17/03/2016.
 */
public abstract class SelectorViewCoordinator {
    protected List<View> mViews;
    protected List<onSelectChangedListener> mOnSelectChangedListeners;
    protected List<View> mSelectedView;
    private View.OnClickListener mOnViewClickListener;

    public interface onSelectChangedListener {
        void OnSelectChanged(View newSelectedView , boolean changedByUser);
    }


    /**
     * set views that must coordinate
     * @param views array of views to coordinate selection.
     */
    protected void setViews(View... views)  {
        mOnViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedView(view , true);
            }
        };
        for (View v : views) {
            mViews.add(v);
            v.setOnClickListener(mOnViewClickListener);
        }

    }


    /**
     * Sets the new selected view
     * @param newSelectedView New view to be selected
     */
    public void setSelectedView(View newSelectedView , boolean changedByUser) {
            onSelectedChanged(newSelectedView);
            for (onSelectChangedListener listener : mOnSelectChangedListeners)
                listener.OnSelectChanged(newSelectedView , changedByUser);
    }


    /**
     * @return the current selected view(s)
     */
    public View[] getSelectedView() {
        return mSelectedView.toArray(new View[mSelectedView.size()]);
    }

    public View[] getAllViews(){
        return mViews.toArray(new View[mViews.size()]);
    }


    /**
     * sets new selected view and take care of old selected view (if necessary)
     * @param newSelectedView new view to be selected
     */
    protected abstract void onSelectedChanged(View newSelectedView);
/*    protected void onSelectedChanged(View newSelectedView) {
        mSelectedView.setSelected(false);
        mSelectedView = newSelectedView;
        mSelectedView.setSelected(true);

        for (onSelectChangedListener listener : mOnSelectChangedListeners)
            listener.OnSelectChanged(newSelectedView);
    }*/


    /**
     * When selected view(s) changes.
     * @param onSelectedChangedListener The view select state change listener
     */
    public void addOnSelectedChangedListener(onSelectChangedListener onSelectedChangedListener) {
        mOnSelectChangedListeners.add(onSelectedChangedListener);
    }


}
