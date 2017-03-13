package com.newway.newlib.design.widget.DialogManager.DialogActivity;

import android.content.Intent;
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

    public static final String KEY_CHECKED_ITEMS = "checked_items";
    public static final String KEY_ITEM_TEXTS = "item_texts";
    public static final String KEY_DEFAULT_ITEM_STATES = "default_item_states";

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
        mItemText = getIntent().getExtras().getStringArray(KEY_ITEM_TEXTS);
        if (SavedBundle == null)
            mCheckListValues = getIntent().getExtras().getBooleanArray(KEY_DEFAULT_ITEM_STATES);
        else
            mCheckListValues = SavedBundle.getBooleanArray(KEY_CHECKED_ITEMS);

        mRecyclerView = new RecyclerView(this);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                (int) getResources().getDimension(R.dimen.dialog_activity_min_width),
                ViewGroup.LayoutParams.WRAP_CONTENT);
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
        outState.putBooleanArray(KEY_CHECKED_ITEMS, mCheckListValues);
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

    @Override
    protected void commitChanges() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_CHECKED_ITEMS, mCheckListValues);
        setResult(RESULT_OK, resultIntent);
        super.commitChanges();
    }

    @Override
    protected void discardChanges() {
        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
        super.discardChanges();
    }
}
