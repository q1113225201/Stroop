package com.sjl.stroop.db;

import android.text.TextUtils;

import com.sjl.stroop.dao.PersonDataDao;
import com.sjl.stroop.model.GlobalData;
import com.sjl.stroop.model.pojo.PersonData;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * ModelHelper
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class ModelHelper {
    /**
     * 获取所有用户数据列表
     */
    public static List<PersonData> fetchAllPersonDataList() {
        PersonDataDao personDataDao = GlobalData.getDbHelper().getDaoSession().getPersonDataDao();
        Query<PersonData> query = personDataDao.queryBuilder().build();
        return query.list();
    }

    /**
     * 获取所有用户数据列表
     */
    public static List<PersonData> fetchPersonDataList(String key) {
        PersonDataDao personDataDao = GlobalData.getDbHelper().getDaoSession().getPersonDataDao();
        Query<PersonData> query = personDataDao.queryBuilder()
                .whereOr(PersonDataDao.Properties.Idcard.like(key),
                        PersonDataDao.Properties.Name.like(key),
                        PersonDataDao.Properties.Birth.like(key),
                        PersonDataDao.Properties.Gender.like(key),
                        PersonDataDao.Properties.Job.like(key),
                        PersonDataDao.Properties.Education.like(key))
                .build();
        return query.list();
    }

    /**
     * 查找用户信息
     */
    public static PersonData findPersonData(PersonData personData) {
        PersonDataDao personDataDao = GlobalData.getDbHelper().getDaoSession().getPersonDataDao();
        Query<PersonData> query = personDataDao.queryBuilder()
                .where(PersonDataDao.Properties.Idcard.eq(personData.getIdcard()))
                .build();
        if(query.list().size()>0){
            return query.list().get(0);
        }
        query = personDataDao.queryBuilder()
                .where(PersonDataDao.Properties.Name.eq(personData.getName()),
                PersonDataDao.Properties.Birth.eq(personData.getBirth()),
                PersonDataDao.Properties.Gender.eq(personData.getGender()),
                PersonDataDao.Properties.Job.eq(personData.getJob()),
                PersonDataDao.Properties.Education.eq(personData.getEducation()))
                .build();
        return query.list().size()>0?query.list().get(0):null;
    }

    /**
     * 保存用户信息
     */
    public static PersonData savePersonData(PersonData personData) {
        PersonDataDao personDataDao = GlobalData.getDbHelper().getDaoSession().getPersonDataDao();
        personDataDao.save(personData);
        return findPersonData(personData);
    }

    public static void updatePersonData(PersonData personData){
        PersonDataDao personDataDao = GlobalData.getDbHelper().getDaoSession().getPersonDataDao();
        PersonData updateData = findPersonData(personData);
        updateData.setStroopA(personData.getStroopA());
        updateData.setStroopB(personData.getStroopB());
        updateData.setStroopC(personData.getStroopC());
        updateData.setStroopState(personData.getStroopState());
        personDataDao.update(updateData);
    }
}
