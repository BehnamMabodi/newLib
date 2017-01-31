package com.newway.newlib.design.viewGroup.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by goldm on 30/01/2017.
 */

public class FragmentComplex extends Fragment {

    public FragmentComplex() {
    }

    protected void addDrawerSyncToggle(DrawerLayout drawerLayout, Toolbar toolbar, int id_string_drawer_open, int id_string_drawer_closed) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawerLayout, toolbar, id_string_drawer_open, id_string_drawer_closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

}
