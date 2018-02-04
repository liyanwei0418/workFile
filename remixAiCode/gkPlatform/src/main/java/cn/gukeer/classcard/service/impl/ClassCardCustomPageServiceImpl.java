package cn.gukeer.classcard.service.impl;

import cn.gukeer.classcard.modelView.ClassCardCustomPageView;
import cn.gukeer.classcard.persistence.dao.A_ClassCardCustomMapper;
import cn.gukeer.classcard.persistence.dao.ClassCardCustomPageMapper;
import cn.gukeer.classcard.persistence.dao.ClassCardCustomRefMapper;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomPage;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomPageExample;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomRef;
import cn.gukeer.classcard.persistence.entity.ClassCardCustomRefExample;
import cn.gukeer.classcard.service.ClassCardCustomPageService;
import cn.gukeer.classcard.service.ClassCardCustomRefService;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.platform.persistence.dao.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.disjoint;


/**
 * Created by leeyh on 2018/1/16.
 */
@Service
public class ClassCardCustomPageServiceImpl implements ClassCardCustomPageService {

    @Autowired
    A_ClassCardCustomMapper a_customMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassCardCustomPageMapper pageMapper;
    @Autowired
    ClassCardCustomRefService customRefService;
    @Autowired
    ClassCardCustomRefMapper customRefMapper;

    @Override
    public PageInfo<ClassCardCustomPageView> findAllPageBySchoolId(String schoolId, int pageNum, int pageSize) {
        //学校字段的问题,在发布班牌的多对多映射表中添加新的学校字段，不和classcard的school_id一起使用，拆分一次数据库的关联查询。
        //班牌开关机配置是一样的处理方式。
        if (pageSize != -1) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<ClassCardCustomPageView> pageViews = a_customMapper.findAllCustonPageBySchoolId(schoolId);
        PageInfo<ClassCardCustomPageView> pageInfo = new PageInfo<>(pageViews);
        return pageInfo;
    }

    @Override
    public List<ClassCardCustomPageView> transformPage(List<ClassCardCustomPageView> pageViews) {
        String sfm = "HH:mm";
        for (int i = 0; i < pageViews.size(); i++) {
            if (pageViews.get(i).getStartTime() != null && pageViews.get(i).getEndTime() != null) {
                pageViews.get(i).setStartTimeView(DateUtils.timestampToString(pageViews.get(i).getStartTime(), sfm));
                pageViews.get(i).setEndTimeView(DateUtils.timestampToString(pageViews.get(i).getEndTime(), sfm));
            } else {
                pageViews.get(i).setStartTimeView("未配置");
                pageViews.get(i).setEndTimeView("点击<b>发布</b>进行配置");
            }
            String createName = userMapper.selectByPrimaryKey(pageViews.get(i).getCreateBy()).getName();
            pageViews.get(i).setCreateByName(createName);
        }
        return pageViews;
    }

    @Override
    public Integer deletePageById(String id) {
        return pageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertPage(ClassCardCustomPage customPage) {
        return pageMapper.insertSelective(customPage);
    }

    @Override
    public Integer updatePage(ClassCardCustomPage customPage) {
        return pageMapper.updateByPrimaryKeySelective(customPage);
    }

    @Override
    public ClassCardCustomPage findOneById(String pageId) {
        return pageMapper.selectByPrimaryKey(pageId);
    }

    @Override
    public ClassCardCustomPageView findViewOneById(String pageId) {
        ClassCardCustomPageView pageView = a_customMapper.findViewOneById(pageId);
        String sfm = "HH:mm";
        if (pageView.getStartTime() != null && pageView.getEndTime() != null) {
            pageView.setStartTimeView(DateUtils.timestampToString(pageView.getStartTime(), sfm));
            pageView.setEndTimeView(DateUtils.timestampToString(pageView.getEndTime(), sfm));
        }
        String createName = userMapper.selectByPrimaryKey(pageView.getCreateBy()).getName();
        pageView.setCreateByName(createName);
        return pageView;
    }

    @Override
    public ClassCardCustomPage findOneReleaseById(String pageId,int status) {
//        ClassCardCustomPageExample example = new ClassCardCustomPageExample();
//        example.createCriteria().andIdEqualTo(pageId).andStatusEqualTo(status).andDelFlagEqualTo(0);
//        return pageMapper.selectByExample(example).get(0);
        ClassCardCustomPage customPage = pageMapper.selectByPrimaryKey(pageId);
        if (customPage.getStatus() != status) {
            return null;
        }
        return customPage;
    }

    @Override
    public Boolean verifyReleaseDate(String classCardList, String loopMark, String loopDate, Long startTime, Long endTime) {
        Boolean retFlag = false;
        //进行时间段的验证
        List<String> loopDates = Arrays.asList(loopDate.split(","));
        String[] classCards = classCardList.split(",");
        ClassCardCustomRefExample example = new ClassCardCustomRefExample();
        int countFlag = 0;
        for (String classCardId : classCards) {
            //优化：通过classcardId查询pageIds
            example.createCriteria().andClassCardIdEqualTo(classCardId);
            List<ClassCardCustomRef> pageIds = customRefMapper.selectByExample(example);
            //获取该设备已经在的发布pageId列表,继续循环获取pageId的时间段。
            int countFlagOther = 0;
            for (ClassCardCustomRef pageId : pageIds) {
                countFlagOther++;
                //优化：pageId查询page对象，重复数据多次查询，将查询数据放入缓冲中保存。//redis
                //查询已发布的，
                ClassCardCustomPage page = findOneReleaseById(pageId.getPageId(),1);
                //与customPage对象的时间段进行重复比较
                if (page != null) {
                    String verifyLoopMark = page.getLoopMark();
                    List<String> verifyLoopDates = Arrays.asList(page.getLoopDate().split(","));
                    Long verifyStartTime = page.getStartTime();
                    Long verifyEndTime = page.getEndTime();
                    //先判断时间段是否重复
                    if (verifyEndTime <= startTime || endTime <= verifyStartTime) {
                        //时间段不重复继续下个page判断
                        continue;
                    } else {
                        //时间段重复，判断循环的周期是否重复
                        if ("D".equals(loopMark) || "N".equals(loopMark)) {
                            return false;
                        }
                        //loopMark相同的情况比较,同为 W or M
                        if (verifyLoopMark.equals(loopMark)) {
                            //判断周和月的周期是否重复
                            if (!disjoint(loopDates, verifyLoopDates)) {
                                return false;
                            }
                            continue;
                        } else {
                            //M-W 或者 W-M 的情况肯定会重复
                            return false;
                        }
                    }
                } else {
                    //页面未发布。
                    continue;
                }
            }
            if (countFlagOther != pageIds.size()) {
                return false;
            } else {
                countFlag++;
            }
        }
        if (countFlag == classCards.length) {
            return true;
        }
        return retFlag;
    }
}
