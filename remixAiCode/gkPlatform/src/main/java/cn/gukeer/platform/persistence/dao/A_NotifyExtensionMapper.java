package cn.gukeer.platform.persistence.dao;

import cn.gukeer.platform.modelView.RoleView;
import cn.gukeer.platform.persistence.entity.Notify;
import cn.gukeer.platform.persistence.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/19.
 */
public interface A_NotifyExtensionMapper {

    List<Map<Object, Object>> selectNotifyView(Map<Object, Object> paramMap);

    int insertNotifyBackId(Notify notify);

    int selectRemindNotifyCount(@Param("userRefId") String userRefId);

    List<Map<Object, Object>> selectNotifyRecord(@Param("notifyId") String notifyId);
}