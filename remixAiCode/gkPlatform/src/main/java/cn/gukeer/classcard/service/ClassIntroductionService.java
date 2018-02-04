package cn.gukeer.classcard.service;


import cn.gukeer.classcard.persistence.entity.ClassIntroduction;

/**
 * Created by alpha on 2017/7/16.
 */

public interface ClassIntroductionService {

    ClassIntroduction findClassIntroductionById(String id);

    int updateClassIntroduction(ClassIntroduction classIntroduction);

    boolean  insertClassIntroduction(ClassIntroduction classIntroduction);

}
