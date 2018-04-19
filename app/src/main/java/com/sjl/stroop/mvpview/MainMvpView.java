package com.sjl.stroop.mvpview;

import com.sjl.platform.base.MvpView;
import com.sjl.stroop.model.pojo.PersonData;

import java.util.List;

/**
 * 首页
 *
 * @author 林zero
 * @date 2018/4/18
 */

public interface MainMvpView extends MvpView {
    void onPersonDataListSuccess(List<PersonData> list);
}
