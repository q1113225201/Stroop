package com.sjl.stroop.ui;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.sjl.platform.base.BaseActivity;
import com.sjl.platform.util.AppUtil;
import com.sjl.stroop.R;
import com.sjl.stroop.model.pojo.ColorBean;
import com.sjl.stroop.model.pojo.StroopData;
import com.sjl.stroop.mvpview.TestMvpView;
import com.sjl.stroop.presenter.TestPresenter;
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

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvHint)
    TextView tvHint;
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

    /**
     * 1 任务A 2 任务B 3 任务C
     */
    private int test = 1;
    private boolean isExercise = true;
    /**
     * 单个Stroop数据
     */
    private StroopData.StroopDataItem stroopDataItem;
    private List<ColorBean> boxColorList = new ArrayList<>();
    private List<ColorBean> textColorList = new ArrayList<>();
    private List<ColorBean> textList = new ArrayList<>();
    private Random random = new Random();
    private float startX;
    private float endX;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_stroop_test;
    }

    //参数意义：X坐标参照类型，X开始坐标参照点，X坐标参照类型，X结束坐标参照点，Y坐标参照类型，Y开始坐标参照点，Y坐标参照类型，Y结束坐标参照点
    TranslateAnimation translateAnimation;
    @Override
    protected void initView() {
        tvContent.measure(0,0);
        startX = -tvContent.getMeasuredWidth();
        tvContent.setX(startX);
        endX = AppUtil.getScreen(this).x;
        initTest(test);
    }

    private void initTest(int test) {
        if (test == 1) {
            tvTitle.setText(tvTaskA.getText());
            tvHint.setText("请选择出现的方块颜色");
        } else if (test == 2) {
            tvTitle.setText(tvTaskA.getText());
            tvHint.setText("请选择出现的汉字颜色");
        } else if (test == 3) {
            tvTitle.setText(tvTaskA.getText());
            tvHint.setText("请选择出现的汉字颜色");
        }else{
            //测试结束

            return;
        }
        btnStop.setVisibility(isExercise ? View.VISIBLE : View.INVISIBLE);
        tvTitle.append(isExercise ? "(练习模式)" : "");
        boxColorList.clear();
        boxColorList.addAll(StroopUtil.getColorBeanList());
        textColorList.clear();
        textColorList.addAll(StroopUtil.getColorBeanList());
        textList.clear();
        textList.addAll(StroopUtil.getColorBeanList());
        startTest(test, 0);
    }

    private void startTest(int test, int index) {
        if(index>=24){
            initTest(test+1);
            return;
        }
        stroopDataItem = new StroopData.StroopDataItem();
        ColorBean boxColor = null;
        ColorBean textColor = null;
        ColorBean text = null;
        int randomIndex;
        if (test == 1) {
            randomIndex = random.nextInt(boxColorList.size());
            boxColor = boxColorList.get(randomIndex);
            boxColorList.remove(randomIndex);
        } else if (test == 2) {
            randomIndex = random.nextInt(textColorList.size());
            textColor = textColorList.get(randomIndex);
            text = textColor;
        }else if(test==3){
            randomIndex = random.nextInt(textColorList.size());
            textColor = textColorList.get(randomIndex);
            randomIndex = random.nextInt(textList.size());
            text = textList.get(randomIndex);
        }
        stroopDataItem.setBoxColor(boxColor==null? Color.TRANSPARENT:boxColor.getColor());
        stroopDataItem.setTextColor(textColor==null?Color.TRANSPARENT:textColor.getColor());
        stroopDataItem.setText(text==null?"":text.getColorText());
        tvContent.setBackgroundColor(stroopDataItem.getBoxColor());
        tvContent.setTextColor(stroopDataItem.getTextColor());
        tvContent.setText(stroopDataItem.getText());

        if(translateAnimation==null) {
            translateAnimation = new TranslateAnimation(startX, endX,tvContent.getY(),tvContent.getY());
            translateAnimation.setDuration(5000);
            translateAnimation.setInterpolator(new LinearInterpolator());
        }
        tvContent.startAnimation(translateAnimation);
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
