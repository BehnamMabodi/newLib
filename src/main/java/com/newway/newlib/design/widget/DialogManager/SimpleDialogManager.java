package com.newway.newlib.design.widget.DialogManager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.newway.newlib.R;

import java.util.ArrayList;
import java.util.List;

import static com.newway.newlib.innerClasses.Tools.MyAnimationUtils.enterReveal;

/**
 * Created by Golden Phoenix on 02/04/2016.
 */
public class SimpleDialogManager extends DialogManager {

    private LayoutInflater mInflater;
    private View mLayoutMain;
    private TextView mLayoutTitle;
    private Button mLayoutButtonOK;
    private Button mLayoutButtonCancel;
    private ViewGroup mLayoutContent;
    private View mLayoutContentView;


    private List<OnClickListener> mOnClickListener;
    private String mTextTitle;
    private String mTextOk;
    private String mTextCancel;
    private String mTextContent;
    private View.OnClickListener mViewClickListener;

    private TextView mLayoutContentTextView;

    //TODO: Apply Material Theme and properties Automatically
    public interface OnClickListener {
        void onOK(View DialogView, String id);

        void onCancel(View DialogView, String id);
    }


    public SimpleDialogManager(Context context, String title, String buttonOK_Text, String buttonCancel_Text, String content) {
        initVars(context, title, buttonOK_Text, buttonCancel_Text, content);
    }

    public SimpleDialogManager(Context context, View view, String id, String title, String buttonOK_Text, String buttonCancel_Text, String content) {

        initVars(context, title, buttonOK_Text, buttonCancel_Text, content);
        setView(view, id);
    }

    protected void initVars(Context context, String title, String buttonOK_Text, String buttonCancel_Text, String content) {
        mContext = context;
        mOnClickListener = new ArrayList<>();
        mTextTitle = title;
        mTextOk = buttonOK_Text;
        mTextCancel = buttonCancel_Text;
        mTextContent = content;
        initViews();
    }

    @Override
    public void showDialog() {
        initViews();
        super.showDialog();
        mAlertDialog.getWindow().getDecorView().setAlpha(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAlertDialog.getWindow().getDecorView().setAlpha(1);
                enterReveal(mAlertDialog.getWindow().getDecorView(), 300, 0);
            }
        }, 20);
    }

    @Override
    public void setView(View view, String id) {
        setDialogID(id);
        initViews();
        if (view != null && mLayoutContentView != view) {
            mLayoutContent.removeAllViews();
            mLayoutContent.addView(view);
            mLayoutContentView = view;
        }
    }

    @Override
    protected void initViews() {
        if (mInflater == null)
            mInflater = LayoutInflater.from(mContext);
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(mContext).create();
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
                mAlertDialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
            else
                mAlertDialog.getWindow().setWindowAnimations(R.style.DialogNonAnimation);
            mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    onCancel();
                }
            });
            setupDefaultView();
            setWidthAndHeight(DIALOG_WIDTH_DEFAULT, DIALOG_HEIGHT_DEFAULT);
        }
    }

    /**
     * Setup default views for first time
     */
    protected void setupDefaultView() {
        mViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == mLayoutButtonOK) {
                    onOK();
                } else if (view == mLayoutButtonCancel) {
                    onCancel();
                }
            }
        };
        mLayoutMain = mInflater.inflate(R.layout.dialog_main_layout, null);
        mLayoutTitle = (TextView) mLayoutMain.findViewById(R.id.Dialog_Title);
        mLayoutContentTextView = (TextView) mLayoutMain.findViewById(R.id.Dialog_ContentTextView);
        mLayoutButtonOK = (Button) mLayoutMain.findViewById(R.id.DialogButton_OK);
        mLayoutButtonOK.setOnClickListener(mViewClickListener);
        mLayoutButtonCancel = (Button) mLayoutMain.findViewById(R.id.DialogButton_Cancel);
        mLayoutButtonCancel.setOnClickListener(mViewClickListener);
        mLayoutContent = (ViewGroup) mLayoutMain.findViewById(R.id.Dialog_ContentLayout);


        mLayoutTitle.setText(mTextTitle);
        mLayoutButtonOK.setText(mTextOk);
        mLayoutButtonCancel.setText(mTextCancel);
        mLayoutContentTextView.setText(mTextContent);

        mAlertDialog.setView(mLayoutMain);
        mLayoutContentView = mLayoutContentTextView;
    }

    /**
     * Set Color For Default Dialog View
     *
     * @param background      Background color
     * @param title           Title text color
     * @param titleBackground Title background color
     * @param buttons         Button text color
     */
    public void setColor(int background, int title, int titleBackground, int buttons, int content) {
        try {
            mLayoutMain.setBackgroundColor(background);
            mLayoutTitle.setBackgroundColor(titleBackground);
            mLayoutTitle.setTextColor(title);
            mLayoutButtonOK.setTextColor(buttons);
            mLayoutButtonCancel.setTextColor(buttons);
            mLayoutContentTextView.setTextColor(content);
        } catch (Exception e) {
            System.out.println("Dialog Manager: Use setColor for coloring default dialog view only");
        }
    }

    public void setTitleText(String title) {
        mTextTitle = title;
        mLayoutTitle.setText(mTextTitle);
    }

    public void addOnClickListenerListener(OnClickListener listener) {
        mOnClickListener.add(listener);
    }

    protected void onOK() {
        for (OnClickListener listener : mOnClickListener)
            listener.onOK(mLayoutContentView, mDialogID);
    }

    protected void onCancel() {
        for (OnClickListener listener : mOnClickListener)
            listener.onCancel(mLayoutContentView, mDialogID);
    }
}
