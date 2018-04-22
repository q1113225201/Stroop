package com.sjl.stroop.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.JsonUtils;
import com.sjl.stroop.R;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.model.pojo.StroopData;
import com.sjl.stroop.mvpview.StroopResultMvpView;
import com.sjl.stroop.presenter.StroopResultPresenter;

import java.util.List;

import butterknife.BindView;

/**
  * 结果
  *
  * @author 林zero
  * @date 2018/4/22
  */
public class StroopResultActivity extends BaseActivity<StroopResultMvpView,StroopResultPresenter> implements StroopResultMvpView {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.tvTaskA)
    TextView tvTaskA;
    @BindView(R.id.tvTaskB)
    TextView tvTaskB;
    @BindView(R.id.tvTaskC)
    TextView tvTaskC;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDisturbTime)
    TextView tvDisturbTime;
    @BindView(R.id.tvDisturbNum)
    TextView tvDisturbNum;

    private PersonData personData;
    private int trueNumA;
    private int trueNumB;
    private int trueNumC;
    private int trueNum;
    private int falseNum;
    private int noNum;
    private long avgTime;
    private StroopData stroopDataA;
    private StroopData stroopDataB;
    private StroopData stroopDataC;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_stroop_result;
    }

    @Override
    protected void initView() {
        personData = JsonUtils.toObject(getIntent().getStringExtra("personData"),PersonData.class);

        stroopDataA = JsonUtils.toObject(personData.getStroopA(),StroopData.class);
        stroopDataB = JsonUtils.toObject(personData.getStroopB(),StroopData.class);
        stroopDataC = JsonUtils.toObject(personData.getStroopC(),StroopData.class);
        trueNumA =setStroopData(stroopDataA,"A",tvTaskA);
        trueNumB = setStroopData(stroopDataB,"B",tvTaskB);
        trueNumC = setStroopData(stroopDataC,"C",tvTaskC);
        tvTime.setText(String.format("反应时间：%d毫秒",avgTime/3));
        tvDisturbTime.setText(String.format("时间干扰量：%.0f毫秒",stroopDataC.getSvgTime()-stroopDataA.getSvgTime()));
        tvDisturbNum.setText(String.format("正确数干扰量：%d",trueNumC-trueNumA));
        int resultNum = trueNumA+trueNumB+trueNumC;
        if(resultNum==24*3){
            tvResult.setText("优");
        }else if(resultNum==24*3-1){
            tvResult.setText("良");
        }else if(resultNum>=24*3-5){
            tvResult.setText("继续努力");
        }else {
            tvResult.setText("快找医生");
        }
        ivBack.setOnClickListener(this);
    }

    private int setStroopData(StroopData stroopData,String task,TextView tv) {
        trueNum = 0;
        falseNum = 0;
        noNum = 0;
        List<StroopData.StroopDataItem> list = stroopData.getList();
        for (int i=0;i<list.size();i++){
            if(list.get(i).getSelectColorBean()==null){
                noNum++;
            }else{
                trueNum+=list.get(i).isResult()?1:0;
                falseNum+=list.get(i).isResult()?0:1;
            }
        }
        tv.setText(String.format("任务%s：正确%d，错误%d，未应答%d",task,trueNum,falseNum,noNum));
        avgTime+=stroopData.getSvgTime();
        return trueNum;
    }

    @Override
    protected StroopResultMvpView obtainMvpView() {
        return this;
    }

    @Override
    protected StroopResultPresenter obtainPresenter() {
        mPresenter = new StroopResultPresenter();
        return (StroopResultPresenter) mPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                finish();
                break;
        }
    }
}
