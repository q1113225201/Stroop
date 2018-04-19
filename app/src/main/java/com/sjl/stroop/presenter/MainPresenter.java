package com.sjl.stroop.presenter;

import com.sjl.platform.base.BasePresenter;
import com.sjl.stroop.db.ModelHelper;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.mvpview.MainMvpView;

import java.util.List;

/**
 * MainPresenter
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class MainPresenter extends BasePresenter<MainMvpView> {
    /**
     * 获取人员列表
     */
    public void fetchPersonDataList(String key) {
        List<PersonData> list = ModelHelper.fetchPersonDataList(key);
        /*list.add(new PersonData("","name0","19930504","男","工作","教育","","","",true));
        list.add(new PersonData("","name1","19930504","男","工作","教育","","","",true));
        list.add(new PersonData("","name2","19930504","男","工作","教育","","","",false));
        list.add(new PersonData("","name3","19930504","男","工作","教育","","","",false));
        list.add(new PersonData("","name4","19930504","男","工作","教育","","","",false));
        list.add(new PersonData("","name5","19930504","男","工作","教育","","","",true));
        list.add(new PersonData("","name6","19930504","男","工作","教育","","","",false));
        list.add(new PersonData("","name7","19930504","男","工作","教育","","","",true));
        list.add(new PersonData("","name8","19930504","男","工作","教育","","","",true));
        list.add(new PersonData("","name9","19930504","男","工作","教育","","","",true));
        list.add(new PersonData("","name10","19930504","男","工作","教育","","","",true));
        */
        getMvpView().onPersonDataListSuccess(list);
    }
}
