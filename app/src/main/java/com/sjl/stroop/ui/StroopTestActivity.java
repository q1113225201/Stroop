package com.sjl.stroop.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.AppUtil;
import com.sjl.platform.util.JsonUtils;
import com.sjl.platform.util.LogUtil;
import com.sjl.stroop.R;
import com.sjl.stroop.db.ModelHelper;
import com.sjl.stroop.model.pojo.ColorBean;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.model.pojo.StroopData;
import com.sjl.stroop.mvpview.TestMvpView;
import com.sjl.stroop.presenter.TestPresenter;
import com.sjl.stroop.util.DialogUtil;
import com.sjl.stroop.util.StroopUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * 试验
 *
 * @author 林zero
 * @date 2018/4/18
 */
public class StroopTestActivity extends BaseActivity<TestMvpView, TestPresenter> implements TestMvpView {
    private static final String TAG = "StroopTestActivity";
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvHint)
    TextView tvHint;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTaskA)
    TextView tvTaskA;
    @BindView(R.id.tvTaskB)
    TextView tvTaskB;
    @BindView(R.id.tvTaskC)
    TextView tvTaskC;
    @BindView(R.id.btnRed)
    Button btnRed;
    @BindView(R.id.btnGreen)
    Button btnGreen;
    @BindView(R.id.btnYellow)
    Button btnYellow;
    @BindView(R.id.btnBlue)
    Button btnBlue;
    @BindView(R.id.btnStop)
    Button btnStop;
    @BindView(R.id.tvContent)
    TextView tvContent;

    //1 任务A 2 任务B 3 任务C
    private int test = 1;
    //每个任务中第几次
    private int index = 0;
    //是否练习模式
    private boolean isExercise = false;
    //是否测试中
    private boolean isTesting = false;
    private String content;
    /**
     * 单个Stroop数据
     */
    private StroopData.StroopDataItem stroopDataItem;
    //颜色列表
    private List<ColorBean> boxColorList = new ArrayList<>();
    private List<ColorBean> textColorList = new ArrayList<>();
    private List<ColorBean> textList = new ArrayList<>();
    private Random random = new Random();
    //位移起止横坐标
    private float startX;
    private float endX;
    //每次测试结果
    private List<StroopData.StroopDataItem> list = new ArrayList<>();

    private PersonData personData;
    private long startTime;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_stroop_test;
    }

    private TranslateAnimation translateAnimation;
    @Override
    protected void initView() {
        personData= JsonUtils.toObject(getIntent().getStringExtra("personData"),PersonData.class);
        tvContent.measure(0,0);
        startX = -tvContent.getMeasuredWidth();
        endX = AppUtil.getScreen(this).x+tvContent.getMeasuredWidth();

        translateAnimation = new TranslateAnimation(0, endX,tvContent.getY(),tvContent.getY());
        translateAnimation.setDuration(3000);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                LogUtil.i(TAG, "onAnimationStart" + index);
                isTesting = true;
                tvContent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(tvContent!=null) {
                    tvContent.setVisibility(View.GONE);
                    LogUtil.i(TAG, "onAnimationEnd" + (index + 1)+isTesting);
                }
                if(isTesting) {
                    list.add(stroopDataItem);
                    index++;
                    isTesting = false;
                    startTest();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        initTest();
        ivBack.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnYellow.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    /**
     * 初始化测试
     */
    private void initTest() {
        index=0;
        list.clear();
        if (test == 1) {
            tvTitle.setText(tvTaskA.getText());
            tvHint.setText("请选择出现的方块颜色");
            content = "请选择出现的方块颜色,是否开始任务A";
        } else if (test == 2) {
            tvTitle.setText(tvTaskB.getText());
            tvHint.setText("请选择出现的汉字颜色");
            content = "请选择出现的汉字颜色,是否开始任务B";
        } else if (test == 3) {
            tvTitle.setText(tvTaskC.getText());
            tvHint.setText("请选择出现的汉字颜色");
            content = "请选择出现的汉字颜色，是否开始任务C";
        }else{
            //测试结束
            DialogUtil.showDialog(this, "测试结束，查看测试结果。", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AppUtil.startActivity(activity,btnStop,StroopResultActivity.class,null);
                }
            });
            return;
        }
        isExercise = !isExercise;
        btnStop.setVisibility(isExercise ? View.VISIBLE : View.INVISIBLE);
        content += isExercise ? "(练习模式)?" : "?";
        tvTitle.append(isExercise ? "(练习模式)" : "");
        boxColorList.clear();
        boxColorList.addAll(StroopUtil.getColorBeanList());
        textColorList.clear();
        textColorList.addAll(StroopUtil.getColorBeanList());
        textList.clear();
        textList.addAll(StroopUtil.getColorBeanList());

        DialogUtil.showDialog(this, content, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTest();
            }
        });
    }

    /**
     * 开始测试
     */
    private void startTest() {
        if(index>=24){
            if(isExercise){
                initTest();
            }else{
                saveTestResult();
                test++;
                initTest();
            }
            return;
        }
        stroopDataItem = new StroopData.StroopDataItem();
        ColorBean boxColor = null;
        ColorBean textColor = null;
        ColorBean text = null;
        int randomIndex;
        if (test == 1) {
            randomIndex = boxColorList.size()==0?0:random.nextInt(boxColorList.size());
            boxColor = boxColorList.get(randomIndex);
            boxColorList.remove(randomIndex);
        } else if (test == 2) {
            randomIndex = textColorList.size()==0?0:random.nextInt(textColorList.size());
            textColor = textColorList.get(randomIndex);
            text = textColor;
        }else if(test==3){
            randomIndex = textColorList.size()==0?0:random.nextInt(textColorList.size());
            textColor = textColorList.get(randomIndex);
            randomIndex = textList.size()==0?0:random.nextInt(textList.size());
            text = textList.get(randomIndex);
        }
        stroopDataItem.setBoxColor(boxColor==null? Color.TRANSPARENT:boxColor.getColor());
        stroopDataItem.setTextColor(textColor==null?Color.TRANSPARENT:textColor.getColor());
        stroopDataItem.setText(text==null?"":text.getColorText());
        tvContent.setBackgroundColor(stroopDataItem.getBoxColor());
        tvContent.setTextColor(stroopDataItem.getTextColor());
        tvContent.setText(stroopDataItem.getText());
        if(translateAnimation!=null) {
            tvContent.startAnimation(translateAnimation);
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * 保存测试结果
     */
    private void saveTestResult() {
        int trueCount = 0;
        long totalTime = 0;
        for (int i=0;i<list.size();i++){
            StroopData.StroopDataItem item = new StroopData.StroopDataItem();
            trueCount+=item.isResult()?1:0;
            totalTime+=item.getTime();
        }
        StroopData stroopData = new StroopData();
        stroopData.setList(list);
        stroopData.setFalseCount(list.size()-trueCount);
        stroopData.setTrueCount(trueCount);
        stroopData.setTotalTime(totalTime);
        stroopData.setSvgTime(totalTime/list.size());
        String result = JsonUtils.toJson(stroopData);
        if(test==1){
            personData.setStroopA(result);
        }else if(test==2){
            personData.setStroopB(result);
        }else if(test==3){
            personData.setStroopC(result);
        }
        personData.setStroopState(!TextUtils.isEmpty(personData.getStroopA())&&!TextUtils.isEmpty(personData.getStroopB())&&!TextUtils.isEmpty(personData.getStroopC()));
        ModelHelper.updatePersonData(personData);
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
        switch (v.getId()){
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnRed:
                selectResult(new ColorBean(Color.RED,"红"));
                translateAnimation.cancel();
                break;
            case R.id.btnGreen:
                selectResult(new ColorBean(Color.GREEN,"绿"));
                translateAnimation.cancel();
                break;
            case R.id.btnBlue:
                selectResult(new ColorBean(Color.BLUE,"蓝"));
                translateAnimation.cancel();
                break;
            case R.id.btnYellow:
                selectResult(new ColorBean(Color.YELLOW,"黄"));
                translateAnimation.cancel();
                break;
            case R.id.btnStop:
                index = 24;
                translateAnimation.cancel();
                break;
        }
    }

    private void selectResult(ColorBean colorBean) {
        stroopDataItem.setSelectColorBean(colorBean);
        if(test==1) {
            stroopDataItem.setResult(colorBean.getColor()==stroopDataItem.getBoxColor());
        }else if(test==2){
            stroopDataItem.setResult(colorBean.getColor()==stroopDataItem.getTextColor());
        }else if(test==3){
            stroopDataItem.setResult(colorBean.getColor()==stroopDataItem.getTextColor());
        }
        stroopDataItem.setTime(System.currentTimeMillis()-startTime);
    }

    @Override
    protected void onDestroy() {
        isTesting = false;
        translateAnimation.cancel();
        translateAnimation = null;
        super.onDestroy();
    }
}
