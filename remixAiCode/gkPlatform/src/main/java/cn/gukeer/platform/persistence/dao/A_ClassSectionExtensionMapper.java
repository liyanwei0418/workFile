package cn.gukeer.platform.persistence.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface A_ClassSectionExtensionMapper {
	List<Map> parentInfoList(Map param);

	Map selectParentByPrim(@Param("prim")String prim);
}
