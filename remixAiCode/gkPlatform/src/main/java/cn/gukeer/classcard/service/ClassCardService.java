package cn.gukeer.classcard.service;

import cn.gukeer.classcard.modelView.ClassCardView;
import cn.gukeer.classcard.modelView.ClassIntroductionView;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import cn.gukeer.classcard.persistence.entity.ClassCardMode;
import cn.gukeer.classcard.persistence.entity.SchoolCulture;
import cn.gukeer.platform.modelView.SchoolView;
import cn.gukeer.classcard.persistence.entity.ClassIntroduction;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by alpha on 17-6-26.
 */
public interface ClassCardService {
    //----------------班牌------------------
    int insertClassCard(ClassCard classCard);

    int updateClassCard(ClassCard classCard);

    ClassCard selectClassCardById(String id);

    PageInfo<ClassCardView> selectClassCardByChoose(String sid, String cid, String xd, int nj, String xq, int pageNum, int pageSize);

    Map<String, Object> selectCascadeClassRoom(String condition, String schoolId);

    List<Map<String, String>> selectXdBySchool(String schoolId);

    Map<String, Object> selectCascadeClass(String condition, String schoolId);

    int deleteClassCard(List<String> ids);

    int deleteClassCardById(String classCardId);

    //根据教室id查询班牌
    ClassCard selectClassCardByTeachClassRoomId(String teachClassRoomId);

    int selectClassCardByClassIdAndCardId(String classId, String classCardId);

    int selectClassCardByRoomIdAndCardId(String roomId, String classCardId);
    //根据班级id查询班牌
    ClassCard selectClassCardByClassId(String ClassId);

    ClassCard selectClassCardByMacId(String macId);

    Integer batchInsertClassCard(List<ClassCard> classCardList);

    //查询未绑定班级和教室的ｍａｃ

    List<ClassCard> findUnboundClassCard();

    //查询班级对应的班牌
    List<ClassCard> findClassCardByClassIds(List<String> classIds);

    //根据班级id查询被逻辑删除的班牌
    List<ClassCard> findDelClassCardByClassId(String classId);

    //查询走班的班牌
    PageInfo<ClassCardView> findFreeClass(String schoolId, int pageNum, int pageSize);


    //---------------------班牌模式-------------------
    PageInfo<ClassCardMode> selectAllUnDelMode();

    ClassCardMode selectModeById(String id);

    int updateMode(ClassCardMode classCardMode);

    int insertMode(ClassCardMode classCardMode);

    //逻辑删除当前时间在班牌模式结束时间之后的记录
    List<ClassCardMode> delOutDateMode(List<ClassCardMode> classCardModes);


    //----------------------通知公告------------------
    //选择设备
    Map<String, Map<String, List<ClassCardView>>> selectEquipmentForNotify();


    //----------------------班级空间-----------------------
    SchoolView selectAndMakeTree(String schoolId, String[] judge,boolean showOther);

    ClassIntroduction selectClassIntroductionByCardId(String classCardId);

    ClassIntroductionView selectViewByclassCardId(String classCardId);

    ClassCard findClassCardByClassCardId(String classCardId);

    //----------------------校园文化-----------------------
    SchoolCulture findSchoolCultureBySchoolId(String schoolId);

    boolean insertSchoolCulture(SchoolCulture schoolCulture);

    boolean updateSchoolCulture(SchoolCulture schoolCulture);

}
