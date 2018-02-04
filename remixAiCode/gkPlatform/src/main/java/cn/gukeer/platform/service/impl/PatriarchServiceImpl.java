package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.NumberConvertUtil;
import cn.gukeer.platform.persistence.dao.A_StudentExtensionMapper;
import cn.gukeer.platform.persistence.dao.PatriarchMapper;
import cn.gukeer.platform.persistence.entity.Patriarch;
import cn.gukeer.platform.persistence.entity.PatriarchExample;
import cn.gukeer.platform.service.PatriarchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/9/7.
 */
@Service
public class PatriarchServiceImpl extends BasicService implements PatriarchService {


    @Autowired
    PatriarchMapper patriarchMapper;

    @Autowired
    A_StudentExtensionMapper studentExtensionMapper;

    @Override
    public PageInfo<Patriarch> findAllList(int pageNum, int pageSize) {

        PatriarchExample example = new PatriarchExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Patriarch> list = patriarchMapper.selectByExample(example);

        PageInfo<Patriarch> pageInfo = new PageInfo<Patriarch>(list);

        return pageInfo;
    }

    @Override
    public Patriarch findPatriarchById(String id) {

        Patriarch patriarch = patriarchMapper.selectByPrimaryKey(id);
        return patriarch;
    }

    @Override
    public int updatePatriarch(Patriarch patriarch) {
//        PatriarchExample example = new PatriarchExample();
//        example.createCriteria().andIdEqualTo(patriarch.getId());
//        int count = patriarchMapper.updateByExampleSelective(patriarch, example);
        int count = patriarchMapper.updateByPrimaryKeySelective(patriarch);
        return count;
    }

    @Override
    public int insertPatriarch(Patriarch patriarch) {
        int count = patriarchMapper.insertSelective(patriarch);
        return count;
    }

    @Override
    public Patriarch findPatriarchByStudentId(String studentId, int patriarchFlag) {

        PatriarchExample example = new PatriarchExample();
        example.createCriteria().andPatriarchFlagEqualTo(patriarchFlag).
                andStudentIdEqualTo(studentId);

        List<Patriarch> patriarches = patriarchMapper.selectByExample(example);

        Patriarch patriarch = null;
        if (null != patriarches && patriarches.size() > 0) {
            patriarch = patriarches.get(0);
        }

        return patriarch;
    }

    @Override
    public int batchInsetPatriarch(List<Patriarch> patriarchList) {
        int count = studentExtensionMapper.batchInsetPatriarch(patriarchList);
        return count;
    }

    @Override
    public Map<Object, Object> getParList(Map<String, String> param, boolean flag, String schoolId) {
        String name = param.get("name");
        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");//解决非post访问的中文乱码问题。
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<Object, Object> map = new HashMap<Object, Object>();
        Map paramMap = new HashMap();
        paramMap.put("schoolId", schoolId);
        paramMap.put("name", "%" + name + "%");
        /* PatriarchExample example = new PatriarchExample();
        PatriarchExample.Criteria criteria = example.createCriteria();
        criteria.andStudentSchoolIdEqualTo(schoolId).andDelFlagEqualTo(0);
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }*/
        int pageSize = 0;
        int pageNum = 0;
        if (flag) {
            pageSize = NumberConvertUtil.convertS2I(param.get("pageSizeHave")) == 0 ? 10 : NumberConvertUtil.convertS2I(param.get("pageSizeHave"));
            pageNum = NumberConvertUtil.convertS2I(param.get("pageNumHave"));
            // criteria.andAccountIsNotNull();
            paramMap.put("flag", "no null");
        } else {
            pageSize = NumberConvertUtil.convertS2I(param.get("pageSizeNo")) == 0 ? 10 : NumberConvertUtil.convertS2I(param.get("pageSizeNo"));
            pageNum = NumberConvertUtil.convertS2I(param.get("pageNumNo"));
            //criteria.andAccountIsNull();
            paramMap.put("flag", null);
        }
        PageHelper.startPage(pageNum, pageSize);
        //List<Patriarch> parlist = patriarchMapper.selectByExample(example);
        //PageInfo<Patriarch> pageInfo = new PageInfo<Patriarch>(parlist);
        List<Map> parlist = studentExtensionMapper.getParList(paramMap);
        PageInfo<Map> pageInfo = new PageInfo<Map>(parlist);
        map.put("parlist", parlist);
        map.put("pageInfo", pageInfo);

        return map;
    }
}
