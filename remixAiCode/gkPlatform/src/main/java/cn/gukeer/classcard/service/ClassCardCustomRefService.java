package cn.gukeer.classcard.service;

/**
 * Created by leeyh on 2018/1/18.
 */
public interface ClassCardCustomRefService {
    Integer deleteCustomRefById(String pageId);

    Integer updatePageRelease(String classCardList,String pageId);

    String findCheckIds(String pageId);
}
