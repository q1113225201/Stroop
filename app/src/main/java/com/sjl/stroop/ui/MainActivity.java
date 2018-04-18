package com.sjl.stroop.ui;

import android.view.View;

import com.sjl.platform.base.BaseActivity;
import com.sjl.stroop.R;
import com.sjl.stroop.mvpview.MainMvpView;
import com.sjl.stroop.presenter.MainPresenter;

/**
 * 首页
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainMvpView obtainMvpView() {
        return this;
    }

    @Override
    protected MainPresenter obtainPresenter() {
        mPresenter = new MainPresenter();
        return (MainPresenter) mPresenter;
    }

    @Override
    public void onClick(View v) {

    }
}
