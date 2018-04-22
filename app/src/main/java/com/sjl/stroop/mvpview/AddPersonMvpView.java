package com.sjl.stroop.mvpview;

import com.sjl.platform.base.MvpView;
import com.sjl.stroop.model.pojo.PersonData;

/**
 * 人员添加
 *
 * @author 林zero
 * @date 2018/4/18
 */

public interface AddPersonMvpView extends MvpView {
    void onAddPersonSuccess(PersonData personData);
    void onAddPersonFailure(PersonData personData,String msg);
}
