package cn.gukeer.platform.service;

import cn.gukeer.platform.persistence.entity.Patriarch;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/9/7.
 */
public interface PatriarchService {

    PageInfo<Patriarch> findAllList(int pageNum, int pageSize);

    Patriarch findPatriarchById(String id);

    int updatePatriarch(Patriarch patriarch);

    int insertPatriarch(Patriarch patriarch);

    Patriarch findPatriarchByStudentId(String studentId, int patriarchFlag);

    int batchInsetPatriarch(List<Patriarch> patriarchList);

    Map<Object, Object> getParList(Map<String, String> param, boolean flag, String schoolId);
}
