package com.zqy.basedemo.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zqy.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhaoqy on 2018/5/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ImmersionBar mImmersionBar;
    private Unbinder mUnbinder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.iv_right)
    ImageView iv_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        mUnbinder = ButterKnife.bind(this);

        initImmersionBar();
        initToolbar();
        initData();
    }

    protected abstract int getLayoutResID();

    protected abstract void initData();

    protected abstract String getTitleName();

    protected abstract String getRightName();

    protected abstract int getRightResID();

    /**
     * 是否可以使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 是否有Toolbar
     */
    protected boolean isHasToolbar() {
        return true;
    }

    /**
     * 是否有导航图标
     */
    protected boolean isHasNaviIcon() {
        return true;
    }

    /**
     * 标题是否居中
     */
    protected boolean isTitleCenterInParent() {
        return true;
    }

    /**
     * 初始化mImmersionBar
     */
    protected void initImmersionBar() {
        if (isImmersionBarEnabled()) {
            mImmersionBar = ImmersionBar.with(this);
            if (ImmersionBar.isSupportStatusBarDarkFont()) {
                // 状态栏字体颜色为深色(android6.0以上或者miuiv6以上或者flymeOS4+)
                mImmersionBar.statusBarDarkFont(true);
            }
            mImmersionBar.init();
            if (isHasToolbar()) {
                mImmersionBar.titleBar(toolbar).init();
            }
        }
    }

    private void initToolbar() {
        if (isHasToolbar()) {
            if (toolbar != null) {
                if (isTitleCenterInParent()) {
                    toolbar.setTitle("");
                    tv_title.setText(getTitleName());
                } else {
                    toolbar.setTitle(getTitleName());
                    tv_title.setVisibility(View.GONE);
                }

                if (isHasNaviIcon()) {
                    // 设置导航按钮图标
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                }

                if (!TextUtils.isEmpty(getRightName())) {
                    tv_right.setText(getRightName());
                    tv_right.setVisibility(View.VISIBLE);
                }

                if (getRightResID() > 0) {
                    iv_right.setImageResource(getRightResID());
                    iv_right.setVisibility(View.VISIBLE);
                }

                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null && isHasNaviIcon()) {
                    // 设置导航按钮enable
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeButtonEnabled(true);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Toolbar导航按钮的按键事件
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (mImmersionBar != null) {
            // 销毁mImmersionBar
            mImmersionBar.destroy();
        }
    }
}
