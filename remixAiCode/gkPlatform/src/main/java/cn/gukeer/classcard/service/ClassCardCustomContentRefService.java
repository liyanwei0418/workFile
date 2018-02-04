package cn.gukeer.classcard.service;

import cn.gukeer.classcard.persistence.entity.ClassCardCustomContentRef;

import java.util.List;

/**
 * Created by leeyh on 2018/1/18.
 */
public interface ClassCardCustomContentRefService {
    Integer deleteCustomContentById(String pageId);

    Integer insertContent(String pageId,String names,String contents);

    Integer updateContent(String pageId,String names,String contents);

    List<ClassCardCustomContentRef> findContentByPageId(String pageId);
}
