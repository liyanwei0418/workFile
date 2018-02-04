package cn.gukeer.sync.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2017/2/7.
 */
public interface SyncBaseMapper {
    List<String> getTableNames(@Param("schema") String schema,
                               @Param("tableNamePrefix") String tableNamePrefix,
                               @Param("specialNamePrefix") String specialNamePrefix,
                               @Param("specialNamePrefix1") String specialNamePrefix1);

    List<Map<String, Object>> getDatas(String tableName, String notLileMark);

    int identityDeletion(String tableName, String mark,
                         @Param("ids") List<String> ids, String notLikeMark);
}
