package com.newway.newlib.design.viewGroup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by goldm on 30/01/2017.
 */

public class FragmentComplex extends Fragment {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private AppCompatActivity mActivity;
    private int mToolbarId;
    private int mOpenDrawerString;
    private int mCloseDrawerString;

    public FragmentComplex() {
        // It's recommended to be singleton
    }

    public void initialize(DrawerLayout drawerLayout, int toolbarId, int openDrawerString, int closeDrawerString) {
        mDrawerLayout = drawerLayout;
        mToolbarId = toolbarId;
        mOpenDrawerString = openDrawerString;
        mCloseDrawerString = closeDrawerString;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mActivity = (AppCompatActivity) getActivity();
        mToolbar = (Toolbar) getView().findViewById(mToolbarId);
        mActivity.setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawerLayout, mToolbar, mOpenDrawerString, mCloseDrawerString);
        mDrawerLayout.addDrawerListener(toggle);
        super.onViewCreated(view, savedInstanceState);
        toggle.syncState();
        Log.d("Ejra shod" , "true");
    }

    public void syncDrawerAndToolbar(int toolbarId, DrawerLayout drawerLayout, int openDrawerString, int closeDrawerString) {
        /*mActivity = (AppCompatActivity) getActivity();
        mDrawerLayout = drawerLayout;
        mToolbarId = toolbarId;
        mOpenDrawerString = openDrawerString;
        mCloseDrawerString = closeDrawerString;
        mToolbar = (Toolbar) getView().findViewById(toolbarId);
        mActivity.setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawerLayout, mToolbar, mOpenDrawerString, mCloseDrawerString);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/
    }
}
