package com.vsshv.doitlater.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;

import com.vsshv.doitlater.R;

public class CustomButton extends AppCompatButton{

    private int mBackgroundResource = -1;
    private int mGravity = -1;

    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        int minWidth = (int) getResources().getDimension(R.dimen.min_blue_button_min_width);
        if(attrs != null) {
            TypedArray bgrndArray = context.getTheme().obtainStyledAttributes(attrs, new int[]{android.R.attr.background}, defStyle, 0);
            TypedArray gravityArray = context.getTheme().obtainStyledAttributes(attrs, new int[]{android.R.attr.gravity}, defStyle, 0);
            TypedArray minWidthArray = context.getTheme().obtainStyledAttributes(attrs, new int[]{android.R.attr.minWidth}, defStyle, 0);

            mBackgroundResource = bgrndArray.getResourceId(0, mBackgroundResource);
            mGravity = gravityArray.getInt(0, mGravity);
            minWidth = (int) minWidthArray.getDimension(0, minWidth);

            bgrndArray.recycle();
            gravityArray.recycle();
            minWidthArray.recycle();
        }
        if (mGravity == -1) {
            mGravity = Gravity.CENTER;
        }
        setGravity(mGravity);
        if(mBackgroundResource == R.drawable.btn_selector) {
            setMinimumWidth(minWidth);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        requestLayout();
    }
}
