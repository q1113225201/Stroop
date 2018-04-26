package com.sjl.stroop.ui;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.FileUtil;
import com.sjl.platform.util.JsonUtils;
import com.sjl.platform.util.PermisstionUtil;
import com.sjl.platform.util.ShareUtil;
import com.sjl.platform.util.ToastUtil;
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
public class SettingActivity extends BaseActivity<SettingMvpView, SettingPresenter> implements SettingMvpView {

    @BindView(R.id.llExport)
    LinearLayout llExport;
    @BindView(R.id.llDelete)
    LinearLayout llDelete;
    @BindView(R.id.llShare)
    LinearLayout llShare;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        llExport.setOnClickListener(this);
        llDelete.setOnClickListener(this);
        llShare.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.llExport:
                fileAction(1);
                break;
            case R.id.llDelete:
                fileAction(2);
                break;
            case R.id.llShare:
                fileAction(3);
                break;
        }
    }

    private void fileAction(final int type) {
        PermisstionUtil.requestPermissions(this, PermisstionUtil.STORAGE, 100, "导出数据和分享数据需要读写权限", new PermisstionUtil.OnPermissionResult() {
            @Override
            public void granted(int requestCode) {
                List<PersonData> list = ModelHelper.fetchAllPersonDataList();
                String result = JsonUtils.toJson(list);
                String path = Environment.getExternalStorageDirectory() + "/Stroop/stroop.db";
                try {
                    FileUtil.writeFileByNio(path, result, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(type==1){
                    ToastUtil.showToast("数据已导出，文件路径：" + path);
                }else if(type==2){
                    FileUtil.deleteFile(path);
                    ToastUtil.showToast("文件已删除");
                }else if(type==3){
                    ShareUtil.shareFile(activity, new File(path));
                }
            }

            @Override
            public void denied(int requestCode) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermisstionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
