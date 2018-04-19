package com.sjl.stroop.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.AppUtil;
import com.sjl.stroop.R;
import com.sjl.stroop.adapter.PersonDataAdapter;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.mvpview.MainMvpView;
import com.sjl.stroop.presenter.MainPresenter;
import com.sjl.stroop.ui.widget.CustomItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView {

    @BindView(R.id.ivSetting)
    ImageView ivSetting;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.etKey)
    EditText etKey;
    @BindView(R.id.rv)
    RecyclerView rv;

    private PersonDataAdapter personDataAdapter;
    private List<PersonData> personDataList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        personDataAdapter = new PersonDataAdapter(this,personDataList);
        rv.setAdapter(personDataAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new CustomItemDecoration(this));

        etKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fetchPersonDataList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivSetting.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPersonDataList(etKey.getText().toString());
    }

    private void fetchPersonDataList(String key) {
        ((MainPresenter) mPresenter).fetchPersonDataList(key);
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
        switch (v.getId()) {
            case R.id.ivSetting:

                break;
            case R.id.ivAdd:
                AppUtil.startActivity(this, v, AddPersonActivity.class, null);
                break;
        }
    }

    @Override
    public void onPersonDataListSuccess(List<PersonData> list) {
        personDataList.clear();
        personDataList.addAll(list);
        personDataAdapter.notifyDataSetChanged();
    }
}
