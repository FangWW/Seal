/*
 * mail:1065680448@qq.com
 */
package com.seal;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @Author FangJW
 * @Date 12/8/16
 */
public class SealView extends TextView {
    private int mLeftSize = 24;
    private int mRightSize = 50;
    private String mRightString;
    private String mLeftString;
    private float mR = 350f;

    public SealView(Context context) {
        super(context);
    }

    public SealView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.sealstyle);
        setGravity(Gravity.CENTER);
        setTextColor(getResources().getColor(android.R.color.holo_red_light));
        setBackground(getResources().getDrawable(R.drawable.list_icon_bzj));
        setRotationSeal(mR);


        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.sealstyle_left_text:
                    mLeftString = typedArray.getString(index);
                    break;
                case R.styleable.sealstyle_right_text:
                    mRightString = typedArray.getString(index);
                    break;
                case R.styleable.sealstyle_left_text_size:
                    mLeftSize = typedArray.getDimensionPixelSize(index, 12);
                    break;
                case R.styleable.sealstyle_right_text_size:
                    mRightSize = typedArray.getDimensionPixelSize(index, 15);
                    break;

            }
        }
        typedArray.recycle();


        reDraw();
    }

    private void setRotationSeal(float r) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(this, "rotation", 0f, r);
        rotation.setDuration(0);
        rotation.start();
    }

    private void reDraw() {
        StringBuilder sb = new StringBuilder();

        int leftIndex = 0;
        if (!TextUtils.isEmpty(mLeftString)) {
            leftIndex = mLeftString.length();
            sb.append(mLeftString);
        }

        int rightIndex = 0;
        if (!TextUtils.isEmpty(mRightString)) {
            rightIndex = mRightString.length();
            sb.append(mRightString);
        }
        setText(sb);
        Spannable span = new SpannableString(getText());
        span.setSpan(new AbsoluteSizeSpan(mRightSize), leftIndex, getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new AbsoluteSizeSpan(mLeftSize), 0, leftIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(span);
    }

    public void setRightString(String rightString) {
        mRightString = rightString;
        reDraw();
    }

    public void setLeftString(String leftString) {
        mLeftString = leftString;
        reDraw();
    }

    public void setRightSize(int rightSize) {
        mRightSize = rightSize;
        reDraw();
    }

    public void setLeftSize(int leftSize) {
        mLeftSize = leftSize;
        reDraw();
    }
}
