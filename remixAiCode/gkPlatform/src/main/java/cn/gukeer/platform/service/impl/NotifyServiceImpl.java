package cn.gukeer.platform.service.impl;

import cn.gukeer.common.service.BasicService;
import cn.gukeer.common.utils.DateUtils;
import cn.gukeer.common.utils.PrimaryKey;
import cn.gukeer.platform.persistence.dao.A_NotifyExtensionMapper;
import cn.gukeer.platform.persistence.dao.NotifyColumnMapper;
import cn.gukeer.platform.persistence.dao.NotifyMapper;
import cn.gukeer.platform.persistence.dao.NotifyRecordMapper;
import cn.gukeer.platform.persistence.entity.*;
import cn.gukeer.platform.service.NotifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by conn on 2016/8/8.
 */
@Service
public class NotifyServiceImpl extends BasicService implements NotifyService {

    @Autowired
    NotifyMapper notifyMapper;

    @Autowired
    NotifyColumnMapper notifyColumnMapper;

    @Autowired
    NotifyRecordMapper notifyRecordMapper;

    @Autowired
    A_NotifyExtensionMapper notifyExtentsionMapper;

    @Override
    public PageInfo<Notify> findAllList(int pageNum, int pageSize) {

        NotifyExample example = new NotifyExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("create_date desc");

        PageHelper.startPage(pageNum, pageSize);
        List<Notify> list = notifyMapper.selectByExample(example);
        PageInfo<Notify> pageInfo = new PageInfo<Notify>(list);

        return pageInfo;
    }

    @Override
    public Notify findNotifyById(String id) {

        Notify notify = notifyMapper.selectByPrimaryKey(id);
        return notify;
    }

    @Override
    public int saveNotify(Notify notify) {
        int count = 0;

        if (null == notify.getId() || notify.getId() == "") {
            count = notifyMapper.insertSelective(notify);
        } else {
            NotifyExample example = new NotifyExample();
            example.createCriteria().andIdEqualTo(notify.getId()).andSchoolIdEqualTo(notify.getSchoolId());
            count = notifyMapper.updateByExampleSelective(notify, example);
        }

        return count;
    }

    @Override
    public PageInfo<Map<Object, Object>> findNotifyView(Map<Object, Object> param, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<Object, Object>> list = notifyExtentsionMapper.selectNotifyView(param);
        PageInfo<Map<Object, Object>> pageInfo = new PageInfo<Map<Object, Object>>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Notify> getNotifyByCriteria(Notify notify, String beginTime, String endTime, Integer pageSize, Integer pageNum) {
        String columId = notify.getColumnId().toString();
        NotifyExample example = new NotifyExample();
        example.setOrderByClause("create_date desc");
        NotifyExample.Criteria criteria = example.createCriteria();
        if (!columId.equals("0")) {
            criteria.andColumnIdEqualTo(columId);
        }
        if (!beginTime.equals("")) {
            try {
                criteria.andPublishTimeBetween(DateUtils.yyyyMMddToMillis(beginTime), DateUtils.yyyyMMddToMillis(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        criteria.andDelFlagEqualTo(0).andSchoolIdEqualTo(notify.getSchoolId());
        PageHelper.startPage(pageNum, pageSize);
        List<Notify> list = notifyMapper.selectByExample(example);
        PageInfo<Notify> pageInfo = new PageInfo<Notify>(list);
        return pageInfo;
    }

    /**
     * 查询所有公告栏目
     *
     * @return
     */
    @Override
    public List<NotifyColumn> findAllColumn(String schoolId) {

        NotifyColumnExample example = new NotifyColumnExample();
        example.createCriteria().andDelFlagEqualTo(0).andSchoolIdEqualTo(schoolId);
        example.setOrderByClause("create_date");

        List<NotifyColumn> list = notifyColumnMapper.selectByExample(example);

        return list;
    }

    @Override
    public NotifyColumn findNotifyColumnById(String id) {
        NotifyColumn notifyColumn = notifyColumnMapper.selectByPrimaryKey(id);
        return notifyColumn;
    }

    @Override
    public int saveNotifyColumn(NotifyColumn notifyColumn) {

        int count = 0;

        if (StringUtils.isEmpty(notifyColumn.getId())) {
            notifyColumn.setId(PrimaryKey.get());
            count = notifyColumnMapper.insertSelective(notifyColumn);
        } else {
            NotifyColumnExample example = new NotifyColumnExample();
            example.createCriteria().andIdEqualTo(notifyColumn.getId());
            count = notifyColumnMapper.updateByExampleSelective(notifyColumn, example);
        }

        return count;
    }

    @Override
    public int deleteNotifyByColumnId(Notify notify, String columId) {
        NotifyExample notifyExample = new NotifyExample();
        notifyExample.createCriteria().andDelFlagEqualTo(0).andColumnIdEqualTo(columId);
        notifyMapper.updateByExampleSelective(notify, notifyExample);
        return 0;
    }

    @Override
    public int saveNotifyRecord(NotifyRecord notifyRecord) {
        int count = 0;
        if (null == notifyRecord.getUserId() || notifyRecord.getUserId() == "") {

        } else {
            NotifyRecordExample example = new NotifyRecordExample();
            example.createCriteria().andUserIdEqualTo(notifyRecord.getUserId()).andNotifyIdEqualTo(notifyRecord.getNotifyId());
            count = notifyRecordMapper.updateByExampleSelective(notifyRecord, example);
        }

        return count;
    }

    @Override
    public int addNotifyRecord(NotifyRecord notifyRecord) {
        return notifyRecordMapper.insertSelective(notifyRecord);
    }

    @Override
    public int deleteNotifyRecord(NotifyRecord record) {
        NotifyRecordExample notifyRecordExample = new NotifyRecordExample();
        notifyRecordExample.createCriteria().andNotifyIdEqualTo(record.getNotifyId());
        return notifyRecordMapper.deleteByExample(notifyRecordExample);
    }

    @Override
    public int deleteNotifyRecord(List<String> idList) {
        NotifyRecordExample example = new NotifyRecordExample();
        example.createCriteria().andNotifyIdIn(idList);

        return notifyRecordMapper.deleteByExample(example);
    }

    @Override
    public List<Map<Object, Object>> getRecordList(String id) {
        return notifyExtentsionMapper.selectNotifyRecord(id);
    }

    @Override
    public int addNotifyBackId(Notify notify) {
        return notifyExtentsionMapper.insertNotifyBackId(notify);
    }

    @Override
    public int selectRemindNotifyCount(String refId) {
        return notifyExtentsionMapper.selectRemindNotifyCount(refId);
    }

}
