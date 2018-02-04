package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class TeachCycleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeachCycleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(String value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(String value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(String value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(String value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLike(String value) {
            addCriterion("school_id like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotLike(String value) {
            addCriterion("school_id not like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<String> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<String> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(String value1, String value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(String value1, String value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andCycleNameIsNull() {
            addCriterion("cycle_name is null");
            return (Criteria) this;
        }

        public Criteria andCycleNameIsNotNull() {
            addCriterion("cycle_name is not null");
            return (Criteria) this;
        }

        public Criteria andCycleNameEqualTo(String value) {
            addCriterion("cycle_name =", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameNotEqualTo(String value) {
            addCriterion("cycle_name <>", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameGreaterThan(String value) {
            addCriterion("cycle_name >", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_name >=", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameLessThan(String value) {
            addCriterion("cycle_name <", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameLessThanOrEqualTo(String value) {
            addCriterion("cycle_name <=", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameLike(String value) {
            addCriterion("cycle_name like", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameNotLike(String value) {
            addCriterion("cycle_name not like", value, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameIn(List<String> values) {
            addCriterion("cycle_name in", values, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameNotIn(List<String> values) {
            addCriterion("cycle_name not in", values, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameBetween(String value1, String value2) {
            addCriterion("cycle_name between", value1, value2, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleNameNotBetween(String value1, String value2) {
            addCriterion("cycle_name not between", value1, value2, "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleYearIsNull() {
            addCriterion("cycle_year is null");
            return (Criteria) this;
        }

        public Criteria andCycleYearIsNotNull() {
            addCriterion("cycle_year is not null");
            return (Criteria) this;
        }

        public Criteria andCycleYearEqualTo(String value) {
            addCriterion("cycle_year =", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotEqualTo(String value) {
            addCriterion("cycle_year <>", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearGreaterThan(String value) {
            addCriterion("cycle_year >", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_year >=", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearLessThan(String value) {
            addCriterion("cycle_year <", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearLessThanOrEqualTo(String value) {
            addCriterion("cycle_year <=", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearLike(String value) {
            addCriterion("cycle_year like", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotLike(String value) {
            addCriterion("cycle_year not like", value, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearIn(List<String> values) {
            addCriterion("cycle_year in", values, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotIn(List<String> values) {
            addCriterion("cycle_year not in", values, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearBetween(String value1, String value2) {
            addCriterion("cycle_year between", value1, value2, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleYearNotBetween(String value1, String value2) {
            addCriterion("cycle_year not between", value1, value2, "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterIsNull() {
            addCriterion("cycle_semester is null");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterIsNotNull() {
            addCriterion("cycle_semester is not null");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterEqualTo(Integer value) {
            addCriterion("cycle_semester =", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterNotEqualTo(Integer value) {
            addCriterion("cycle_semester <>", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterGreaterThan(Integer value) {
            addCriterion("cycle_semester >", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterGreaterThanOrEqualTo(Integer value) {
            addCriterion("cycle_semester >=", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterLessThan(Integer value) {
            addCriterion("cycle_semester <", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterLessThanOrEqualTo(Integer value) {
            addCriterion("cycle_semester <=", value, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterIn(List<Integer> values) {
            addCriterion("cycle_semester in", values, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterNotIn(List<Integer> values) {
            addCriterion("cycle_semester not in", values, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterBetween(Integer value1, Integer value2) {
            addCriterion("cycle_semester between", value1, value2, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andCycleSemesterNotBetween(Integer value1, Integer value2) {
            addCriterion("cycle_semester not between", value1, value2, "cycleSemester");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeIsNull() {
            addCriterion("term_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeIsNotNull() {
            addCriterion("term_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeEqualTo(Long value) {
            addCriterion("term_begin_time =", value, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeNotEqualTo(Long value) {
            addCriterion("term_begin_time <>", value, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeGreaterThan(Long value) {
            addCriterion("term_begin_time >", value, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("term_begin_time >=", value, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeLessThan(Long value) {
            addCriterion("term_begin_time <", value, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeLessThanOrEqualTo(Long value) {
            addCriterion("term_begin_time <=", value, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeIn(List<Long> values) {
            addCriterion("term_begin_time in", values, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeNotIn(List<Long> values) {
            addCriterion("term_begin_time not in", values, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeBetween(Long value1, Long value2) {
            addCriterion("term_begin_time between", value1, value2, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andTermBeginTimeNotBetween(Long value1, Long value2) {
            addCriterion("term_begin_time not between", value1, value2, "termBeginTime");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Long value) {
            addCriterion("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Long value) {
            addCriterion("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Long value) {
            addCriterion("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Long value) {
            addCriterion("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Long value) {
            addCriterion("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Long value) {
            addCriterion("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Long> values) {
            addCriterion("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Long> values) {
            addCriterion("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Long value1, Long value2) {
            addCriterion("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Long value1, Long value2) {
            addCriterion("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Long value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Long value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Long value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Long value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Long value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Long value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Long> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Long> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Long value1, Long value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Long value1, Long value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andWeekCountIsNull() {
            addCriterion("week_count is null");
            return (Criteria) this;
        }

        public Criteria andWeekCountIsNotNull() {
            addCriterion("week_count is not null");
            return (Criteria) this;
        }

        public Criteria andWeekCountEqualTo(Integer value) {
            addCriterion("week_count =", value, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountNotEqualTo(Integer value) {
            addCriterion("week_count <>", value, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountGreaterThan(Integer value) {
            addCriterion("week_count >", value, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("week_count >=", value, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountLessThan(Integer value) {
            addCriterion("week_count <", value, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountLessThanOrEqualTo(Integer value) {
            addCriterion("week_count <=", value, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountIn(List<Integer> values) {
            addCriterion("week_count in", values, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountNotIn(List<Integer> values) {
            addCriterion("week_count not in", values, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountBetween(Integer value1, Integer value2) {
            addCriterion("week_count between", value1, value2, "weekCount");
            return (Criteria) this;
        }

        public Criteria andWeekCountNotBetween(Integer value1, Integer value2) {
            addCriterion("week_count not between", value1, value2, "weekCount");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Long value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Long value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Long value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Long value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Long value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Long> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Long> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Long value1, Long value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Long value1, Long value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Long value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Long value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Long value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Long value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Long value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Long> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Long> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Long value1, Long value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Long value1, Long value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andCycleNameLikeInsensitive(String value) {
            addCriterion("upper(cycle_name) like", value.toUpperCase(), "cycleName");
            return (Criteria) this;
        }

        public Criteria andCycleYearLikeInsensitive(String value) {
            addCriterion("upper(cycle_year) like", value.toUpperCase(), "cycleYear");
            return (Criteria) this;
        }

        public Criteria andCreateByLikeInsensitive(String value) {
            addCriterion("upper(create_by) like", value.toUpperCase(), "createBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLikeInsensitive(String value) {
            addCriterion("upper(update_by) like", value.toUpperCase(), "updateBy");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}