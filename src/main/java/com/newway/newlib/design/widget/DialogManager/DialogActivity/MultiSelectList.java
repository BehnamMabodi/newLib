package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.newway.newlib.R;
import com.newway.newlib.design.widget.view.CheckBoxStyleable;


public class MultiSelectList extends DialogActivity {

    RecyclerView mRecyclerView;
    boolean[] mCheckListValues;
    String[] mItemText;
    CheckBoxStyleable.OnCheckedChangeListener mOnCheckedChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init(Bundle SavedBundle) {
        super.init(SavedBundle);
        mItemText = getIntent().getExtras().getStringArray("item_texts");
        if (SavedBundle == null)
            mCheckListValues = getIntent().getExtras().getBooleanArray("default_item_states");
        else
            mCheckListValues = SavedBundle.getBooleanArray("checked_items");

        mRecyclerView = new RecyclerView(this);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                (int) getResources().getDimension(R.dimen.dialog_activity_min_width),
                (int) getResources().getDimension(R.dimen.dialog_activity_min_height));
        mRecyclerView.setLayoutParams(params);
        mFrameLayout.addView(mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(new Adapter());

        mOnCheckedChangeListener = new CheckBoxStyleable.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ViewHolder holder = (ViewHolder) mRecyclerView.findContainingViewHolder(compoundButton);
                if (holder != null)
                    mCheckListValues[holder.getAdapterPosition()] = b;
            }
        };
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBooleanArray("checked_items", mCheckListValues);
        super.onSaveInstanceState(outState);
    }


    private class Adapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CheckBoxStyleable checkBoxStyleable = (CheckBoxStyleable) LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_activity_multi_select_item_layout, parent, false);
            checkBoxStyleable.setOnCheckedChangeListener(mOnCheckedChangeListener);
            return new ViewHolder(checkBoxStyleable);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mCheckBoxStyleable.setChecked(mCheckListValues[position]);
            holder.mCheckBoxStyleable.setText(mItemText[position]);
        }

        @Override
        public int getItemCount() {
            return mCheckListValues.length;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        CheckBoxStyleable mCheckBoxStyleable;


        ViewHolder(View v) {
            super(v);
            mCheckBoxStyleable = (CheckBoxStyleable) v;

        }
    }

}
