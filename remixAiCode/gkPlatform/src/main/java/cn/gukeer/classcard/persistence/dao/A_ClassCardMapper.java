package cn.gukeer.classcard.persistence.dao;

import cn.gukeer.classcard.modelView.ClassCardView;
import cn.gukeer.classcard.persistence.entity.ClassCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alpha on 17-7-3.
 */
public interface A_ClassCardMapper {
    //班牌首页根据树搜索班牌
    List<ClassCardView> selectByClassIdAndNj(@Param("classId") List<String> classId, @Param("schoolId") String schoolId, @Param("xd") String xd,
                                             @Param("nj") int nj, @Param("xq") String xq);

    //通知公告，选择班牌设备
    List<ClassCardView> selectAllClassCardView(@Param("classIds") List<String> classIds, @Param("schoolId") String schoolId);


    int insertClassCardBatch(List<ClassCard> classCards);

    List< ClassCardView> selectClassCardToSync();

    List< ClassCardView>  selectFreeClass(String schoolId);
}



