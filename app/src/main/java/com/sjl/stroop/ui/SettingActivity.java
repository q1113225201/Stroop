package com.sjl.stroop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.FileUtil;
import com.sjl.platform.util.JsonUtils;
import com.sjl.platform.util.PermisstionUtil;
import com.sjl.platform.util.ToastUtil;
import com.sjl.platform.util.UriUtil;
import com.sjl.stroop.R;
import com.sjl.stroop.db.ModelHelper;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.mvpview.SettingMvpView;
import com.sjl.stroop.presenter.SettingPresenter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;

/**
  * 设置
  *
  * @author 林zero
  * @date 2018/4/22
  */
public class SettingActivity extends BaseActivity<SettingMvpView,SettingPresenter> implements SettingMvpView {

    @BindView(R.id.tvShare)
    TextView tvShare;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        tvShare.setOnClickListener(this);
    }

    @Override
    protected SettingMvpView obtainMvpView() {
        return this;
    }

    @Override
    protected SettingPresenter obtainPresenter() {
        mPresenter = new SettingPresenter();
        return (SettingPresenter) mPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvShare:
                share();
                break;
        }
    }

    private void share() {
        PermisstionUtil.requestPermissions(this, PermisstionUtil.STORAGE, 100, "导出数据和分享数据需要读写权限", new PermisstionUtil.OnPermissionResult() {
            @Override
            public void granted(int requestCode) {
                List<PersonData> list = ModelHelper.fetchAllPersonDataList();
                String result = JsonUtils.toJson(list);
                    String path = Environment.getExternalStorageDirectory()+"/Stroop/stroop.dat";
                    try {
                        FileUtil.writeFile(path,result,true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ToastUtil.showToast("数据已导出，文件路径："+path);
            }

            @Override
            public void denied(int requestCode) {

            }
        });
    }

    /**
     * 文件分享
     * @param context
     * @param file
     */
    public void shareFile(Context context, File file){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, UriUtil.getFileUri(context,file));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermisstionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
