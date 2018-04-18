package com.sjl.stroop.ui;

import android.view.View;

import com.sjl.platform.base.BaseActivity;
import com.sjl.stroop.R;
import com.sjl.stroop.mvpview.TestMvpView;
import com.sjl.stroop.presenter.TestPresenter;

/**
 * 试验
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class TestActivity extends BaseActivity<TestMvpView, TestPresenter> implements TestMvpView {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected TestMvpView obtainMvpView() {
        return this;
    }

    @Override
    protected TestPresenter obtainPresenter() {
        mPresenter = new TestPresenter();
        return (TestPresenter) mPresenter;
    }

    @Override
    public void onClick(View v) {

    }
}
