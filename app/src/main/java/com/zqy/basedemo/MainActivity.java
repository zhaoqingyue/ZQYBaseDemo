package com.zqy.basedemo;

import android.widget.TextView;

import com.zqy.basedemo.base.BaseActivity;

import java.math.BigDecimal;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_text0)
    TextView textView0;

    @BindView(R.id.tv_text1)
    TextView textView1;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        double dis0 = 1852.22;
        double dis1 = 52.22;

        textView0.setText("textView0" + getDistance(dis0));
        textView1.setText("textView1" + getDistance(dis1));
    }

    private String getDistance(double dis) {
        String distance = "";
        if (dis >= 1000) {
            BigDecimal bg = new BigDecimal(dis / 1000.0);
            double f1 = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            distance = " (差距" + getPrettyNumber(f1) + "公里)";
        } else {
            distance = " (差距" + (int)dis + "米)";
        }
        return distance;
    }

    public static String getPrettyNumber(double number) {
        return BigDecimal.valueOf(number)
                .stripTrailingZeros().toPlainString();
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
