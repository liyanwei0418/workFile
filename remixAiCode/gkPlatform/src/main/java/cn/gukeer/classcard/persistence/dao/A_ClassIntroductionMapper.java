package cn.gukeer.classcard.persistence.dao;


import cn.gukeer.classcard.modelView.ClassIntroductionView;

/**
 * Created by alpha on 17-7-11.
 */
public interface A_ClassIntroductionMapper {
    //班级简介view查询
    ClassIntroductionView findIntroductionViewByClasscardId(String classCardId);
}
