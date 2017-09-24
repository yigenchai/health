package com.olosom.health.widget.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.olosom.health.R;


/**
 * title bar
 */
public class NormalTitleBar extends RelativeLayout {

    private View mLeftLayout;
    protected ImageView mLeftImage;
    protected ImageView mRightImage;

    protected TextView mTitleTv;
    protected TextView mRightTxtTv;

    public NormalTitleBar(Context context) {
        this(context, null);
    }

    public NormalTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NormalTitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this);
        mLeftLayout = findViewById(R.id.layout_left);
        mLeftImage = (ImageView) findViewById(R.id.img_left);
        mRightImage = (ImageView) findViewById(R.id.img_right);
        mRightTxtTv = (TextView) findViewById(R.id.tv_right);
        mTitleTv = (TextView) findViewById(R.id.tv_title);

        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            String title = ta.getString(R.styleable.TitleBar_titleBarTitle);
            mTitleTv.setText(title);

            Drawable rightDrawable = ta.getDrawable(R.styleable.TitleBar_titleBarRightImage);
            if (null != rightDrawable) {
                mRightImage.setImageDrawable(rightDrawable);
            }

            String rightTxt = ta.getString(R.styleable.TitleBar_titleBarRightTxt);
            if (!TextUtils.isEmpty(rightTxt)) {
                mRightImage.setVisibility(GONE);
                mRightTxtTv.setVisibility(VISIBLE);
                mRightTxtTv.setText(rightTxt);
            }

            boolean leftLayoutGone = ta.getBoolean(R.styleable.TitleBar_titleBarLeftLayoutGone, false);
            if (leftLayoutGone) {
                mLeftImage.setVisibility(GONE);
            }

            ta.recycle();
        }
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public void setLeftLayoutClickListener(OnClickListener listener) {
        mLeftLayout.setOnClickListener(listener);
    }

    public void setRightLayoutClickListener(OnClickListener listener) {
        if (mRightImage.getVisibility() == VISIBLE) {
            mRightImage.setOnClickListener(listener);
        }
        if (mRightTxtTv.getVisibility() == VISIBLE) {
            mRightTxtTv.setOnClickListener(listener);
        }
    }

    public void setTitleLayoutClickListener(OnClickListener listener) {
        mTitleTv.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public void setRightTxt(String content) {
        mRightImage.setVisibility(GONE);
        mRightTxtTv.setVisibility(VISIBLE);
        mRightTxtTv.setText(content);
    }

    public void setRightImage(Drawable drawable) {
        mRightImage.setVisibility(VISIBLE);
        mRightTxtTv.setVisibility(GONE);
        mRightImage.setImageDrawable(drawable);
    }

}
