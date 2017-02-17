package com.newway.newlib.design.viewGroup.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goldm on 17/02/2017.
 */

public class ActivityRTL extends AppCompatActivity {

    protected List<OnSavedInstanceBundleListener> mOnSavedInstanceBundleListeners;

    public interface OnSavedInstanceBundleListener {
        void onSavedInstanceBundle(Bundle bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        mOnSavedInstanceBundleListeners = new ArrayList<>(1);
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mOnSavedInstanceBundleListeners = new ArrayList<>(1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        fireOnSavedInstanceBundle(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        fireOnSavedInstanceBundle(outState);
        super.onSaveInstanceState(outState);
    }

    public void addOnSavedInstanceBundleListener(OnSavedInstanceBundleListener listener) {
        mOnSavedInstanceBundleListeners.add(listener);
    }

    public void removeOnSavedInstanceBundleListener(OnSavedInstanceBundleListener listener) {
        mOnSavedInstanceBundleListeners.remove(listener);
    }

    protected void fireOnSavedInstanceBundle(Bundle bundle) {
        for (int i = 0; i < mOnSavedInstanceBundleListeners.size(); i++)
            mOnSavedInstanceBundleListeners.get(i).onSavedInstanceBundle(bundle);
    }
}
