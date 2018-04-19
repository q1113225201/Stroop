package com.sjl.stroop.presenter;

import com.sjl.platform.base.BasePresenter;
import com.sjl.stroop.db.ModelHelper;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.mvpview.AddPersonMvpView;

/**
 * 人员添加
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class AddPersonPresenter extends BasePresenter<AddPersonMvpView> {
    /**
     * 保存用户信息
     */
    public void savePerson(PersonData personData) {
        if (ModelHelper.findPersonData(personData)) {
            getMvpView().onAddPersonFailure("用户已存在");
        } else {
            ModelHelper.savePersonData(personData);
            getMvpView().onAddPersonSuccess();
        }
    }
}
