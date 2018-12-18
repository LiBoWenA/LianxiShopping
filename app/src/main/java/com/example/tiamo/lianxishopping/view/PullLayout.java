package com.example.tiamo.lianxishopping.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class PullLayout extends LinearLayout {
    private int mchildMaxHeight;
    private int mHSpace = 20;
    private int mVSpace = 20;

    public PullLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到父容器的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heigh = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //测量子孩子的宽高
        fingMaxChildHeigh();
        int left = 0,top = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (left != 0){
                if ((left + view.getMeasuredWidth())>width){
                    top += mchildMaxHeight;
                    left = 0;
                }
            }
            left += view.getMeasuredWidth()+mHSpace;
        }
        setMeasuredDimension(width,(top+mchildMaxHeight)>heigh ? heigh :top +mchildMaxHeight);

    }

    private void fingMaxChildHeigh() {
        mchildMaxHeight = 0;
        //得到子孩子的数量
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view.getMeasuredHeight() > mchildMaxHeight){
                mchildMaxHeight = view.getMeasuredHeight();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        fingMaxChildHeigh();
        int left = 0,top = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (left != 0){
                if ((left + view.getMeasuredWidth())>getWidth()){
                    top += mchildMaxHeight + mVSpace;
                    left = 0;
                }
            }

            view.layout(left,top,left+view.getMeasuredWidth(),top+mchildMaxHeight);;
            left += view.getMeasuredWidth()+mHSpace;
        }
    }


}
