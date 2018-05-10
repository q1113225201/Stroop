package com.sjl.stroop.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.sjl.platform.PlatformInit;
import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.AppUtil;
import com.sjl.platform.util.JsonUtils;
import com.sjl.platform.widget.PopWindow;
import com.sjl.stroop.App;
import com.sjl.stroop.R;
import com.sjl.stroop.model.GlobalData;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.mvpview.AddPersonMvpView;
import com.sjl.stroop.presenter.AddPersonPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 人员添加
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class AddPersonActivity extends BaseActivity<AddPersonMvpView, AddPersonPresenter> implements AddPersonMvpView {
    @BindView(R.id.viewParent)
    View viewParent;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etIDCard)
    EditText etIDCard;
    @BindView(R.id.rbMan)
    RadioButton rbMan;
    @BindView(R.id.etBirth)
    EditText etBirth;
    @BindView(R.id.etEducation)
    EditText etEducation;
    @BindView(R.id.etJob)
    EditText etJob;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_person;
    }

    @Override
    protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ivBack.setOnClickListener(this);
        etBirth.setOnClickListener(this);
        etEducation.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
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
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        switch (v.getId()) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.etBirth:
                showDatePicker();
                break;
            case R.id.etEducation:
                showEducationPop();
                break;
            case R.id.btnConfirm:
                savePerson();
                break;
        }
    }

    private void savePerson() {
        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名");
            return;
        }
        String idcard = etIDCard.getText().toString();
        String gender = rbMan.isChecked() ? "男" : "女";
        String birth = etBirth.getText().toString();
        if (TextUtils.isEmpty(birth)) {
            showToast("请选择出生年月");
            return;
        }
        String education = etEducation.getText().toString();
        if (TextUtils.isEmpty(birth)) {
            showToast("请选择学历");
            return;
        }
        String job = etJob.getText().toString();
        if (TextUtils.isEmpty(job)) {
            showToast("请输入职业");
            return;
        }
        PersonData personData = new PersonData();
        personData.setTime(System.currentTimeMillis());
        personData.setName(name);
        personData.setIdcard(idcard);
        personData.setGender(gender);
        personData.setBirth(birth);
        personData.setEducation(education);
        personData.setJob(job);
        ((AddPersonPresenter) mPresenter).savePerson(personData);
    }

    private PopWindow datePop;

    private void showDatePicker() {
        if (datePop == null) {
            datePop = new PopWindow(this, R.layout.layout_date_picker, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.PopAnimBottom, new PopWindow.PopWindowListener() {
                @Override
                public void onInit(View view) {
                    final WheelDatePicker wheelPicker = view.findViewById(R.id.wheelDatePicker);
                    view.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            datePop.dismiss();
                        }
                    });
                    view.findViewById(R.id.tvConfirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etBirth.setText(String.format("%04d-%02d-%02d",
                                    wheelPicker.getCurrentYear(),
                                    wheelPicker.getCurrentMonth(),
                                    wheelPicker.getCurrentDay()));
                            datePop.dismiss();
                        }
                    });
                }

                @Override
                public void onDismiss() {

                }
            });
        }
        datePop.showAtLocation(viewParent, Gravity.BOTTOM, 0, 0);
    }

    private PopWindow educationPop;
    private List<String> educationList;

    private void showEducationPop() {
        if (educationPop == null) {
            educationList = new ArrayList<>();
            educationList.add("初中及以下");
            educationList.add("高中");
            educationList.add("大专");
            educationList.add("本科");
            educationList.add("硕士");
            educationList.add("博士及以上");
            educationPop = new PopWindow(this, R.layout.layout_education_picker, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.PopAnimBottom, new PopWindow.PopWindowListener() {
                @Override
                public void onInit(View view) {
                    final WheelPicker wheelPicker = view.findViewById(R.id.wpEducation);
                    wheelPicker.setData(educationList);
                    view.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            educationPop.dismiss();
                        }
                    });
                    view.findViewById(R.id.tvConfirm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etEducation.setText(educationList.get(wheelPicker.getCurrentItemPosition()));
                            educationPop.dismiss();
                        }
                    });
                }

                @Override
                public void onDismiss() {

                }
            });
        }
        educationPop.showAtLocation(viewParent, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onAddPersonSuccess(PersonData personData) {
        showToast("资料已添加，下面进行测试。。。");
        Bundle bundle = new Bundle();
        bundle.putString("personData", JsonUtils.toJson(personData));
        AppUtil.startActivity(this, btnConfirm, StroopTestActivity.class, bundle);
        finish();
    }

    @Override
    public void onAddPersonFailure(PersonData personData, String msg) {
        showToast(msg);
        Bundle bundle = new Bundle();
        bundle.putString("personData", JsonUtils.toJson(personData));
        AppUtil.startActivity(this, btnConfirm, StroopTestActivity.class, bundle);
        finish();
    }
}
