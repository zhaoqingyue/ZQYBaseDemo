package com.zqy.basedemo;

import com.zqy.basedemo.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String getTitleName() {
        return getResources().getString(R.string.app_name);
    }

    @Override
    protected String getRightName() {
        return "";
    }

    @Override
    protected int getRightResID() {
        return R.drawable.ic_add_black_24dp;
    }

    @Override
    protected boolean isHasNaviIcon() {
        return true;
    }

    @Override
    protected boolean isTitleCenterInParent() {
        return false;
    }
}
