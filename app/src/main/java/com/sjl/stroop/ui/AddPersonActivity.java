package com.sjl.stroop.ui;

import android.view.View;

import com.sjl.platform.base.BaseActivity;
import com.sjl.stroop.R;
import com.sjl.stroop.mvpview.AddPersonMvpView;
import com.sjl.stroop.presenter.AddPersonPresenter;

/**
 * 人员添加
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class AddPersonActivity extends BaseActivity<AddPersonMvpView, AddPersonPresenter> implements AddPersonMvpView {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_person;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected AddPersonMvpView obtainMvpView() {
        return this;
    }

    @Override
    protected AddPersonPresenter obtainPresenter() {
        mPresenter = new AddPersonPresenter();
        return (AddPersonPresenter) mPresenter;
    }

    @Override
    public void onClick(View v) {

    }
}
