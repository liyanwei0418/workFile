package cn.gukeer.classcard.service;

import cn.gukeer.classcard.persistence.entity.ClassSpaceVideo;

/**
 * Created by alpha on 18-1-10.
 */
public interface ClassSpaceVideoService {

    boolean deleteVideo(String id);

    boolean insertVideo(ClassSpaceVideo classSpaceVideo);

    ClassSpaceVideo findById(String id);

}
