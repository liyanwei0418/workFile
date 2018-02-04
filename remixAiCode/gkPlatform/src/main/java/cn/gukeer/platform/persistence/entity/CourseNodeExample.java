package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseNodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseNodeExample() {
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

        public Criteria andCourseNodeInitIdIsNull() {
            addCriterion("course_node_init_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdIsNotNull() {
            addCriterion("course_node_init_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdEqualTo(String value) {
            addCriterion("course_node_init_id =", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotEqualTo(String value) {
            addCriterion("course_node_init_id <>", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdGreaterThan(String value) {
            addCriterion("course_node_init_id >", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_node_init_id >=", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLessThan(String value) {
            addCriterion("course_node_init_id <", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLessThanOrEqualTo(String value) {
            addCriterion("course_node_init_id <=", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdLike(String value) {
            addCriterion("course_node_init_id like", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotLike(String value) {
            addCriterion("course_node_init_id not like", value, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdIn(List<String> values) {
            addCriterion("course_node_init_id in", values, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotIn(List<String> values) {
            addCriterion("course_node_init_id not in", values, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdBetween(String value1, String value2) {
            addCriterion("course_node_init_id between", value1, value2, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCourseNodeInitIdNotBetween(String value1, String value2) {
            addCriterion("course_node_init_id not between", value1, value2, "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCycleIdIsNull() {
            addCriterion("cycle_id is null");
            return (Criteria) this;
        }

        public Criteria andCycleIdIsNotNull() {
            addCriterion("cycle_id is not null");
            return (Criteria) this;
        }

        public Criteria andCycleIdEqualTo(String value) {
            addCriterion("cycle_id =", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotEqualTo(String value) {
            addCriterion("cycle_id <>", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdGreaterThan(String value) {
            addCriterion("cycle_id >", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_id >=", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLessThan(String value) {
            addCriterion("cycle_id <", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLessThanOrEqualTo(String value) {
            addCriterion("cycle_id <=", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLike(String value) {
            addCriterion("cycle_id like", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotLike(String value) {
            addCriterion("cycle_id not like", value, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdIn(List<String> values) {
            addCriterion("cycle_id in", values, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotIn(List<String> values) {
            addCriterion("cycle_id not in", values, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdBetween(String value1, String value2) {
            addCriterion("cycle_id between", value1, value2, "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleIdNotBetween(String value1, String value2) {
            addCriterion("cycle_id not between", value1, value2, "cycleId");
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

        public Criteria andNodeIsNull() {
            addCriterion("node is null");
            return (Criteria) this;
        }

        public Criteria andNodeIsNotNull() {
            addCriterion("node is not null");
            return (Criteria) this;
        }

        public Criteria andNodeEqualTo(Integer value) {
            addCriterion("node =", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotEqualTo(Integer value) {
            addCriterion("node <>", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThan(Integer value) {
            addCriterion("node >", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("node >=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThan(Integer value) {
            addCriterion("node <", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThanOrEqualTo(Integer value) {
            addCriterion("node <=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeIn(List<Integer> values) {
            addCriterion("node in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotIn(List<Integer> values) {
            addCriterion("node not in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeBetween(Integer value1, Integer value2) {
            addCriterion("node between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotBetween(Integer value1, Integer value2) {
            addCriterion("node not between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNull() {
            addCriterion("node_name is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("node_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("node_name =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("node_name <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("node_name >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("node_name >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("node_name <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("node_name <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("node_name like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("node_name not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("node_name in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("node_name not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("node_name between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("node_name not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Long value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Long value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Long value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Long value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Long> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Long> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Long value1, Long value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Long value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Long value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Long value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Long value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Long> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Long> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Long value1, Long value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonIsNull() {
            addCriterion("morning_afternoon is null");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonIsNotNull() {
            addCriterion("morning_afternoon is not null");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonEqualTo(String value) {
            addCriterion("morning_afternoon =", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotEqualTo(String value) {
            addCriterion("morning_afternoon <>", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonGreaterThan(String value) {
            addCriterion("morning_afternoon >", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonGreaterThanOrEqualTo(String value) {
            addCriterion("morning_afternoon >=", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLessThan(String value) {
            addCriterion("morning_afternoon <", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLessThanOrEqualTo(String value) {
            addCriterion("morning_afternoon <=", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLike(String value) {
            addCriterion("morning_afternoon like", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotLike(String value) {
            addCriterion("morning_afternoon not like", value, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonIn(List<String> values) {
            addCriterion("morning_afternoon in", values, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotIn(List<String> values) {
            addCriterion("morning_afternoon not in", values, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonBetween(String value1, String value2) {
            addCriterion("morning_afternoon between", value1, value2, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonNotBetween(String value1, String value2) {
            addCriterion("morning_afternoon not between", value1, value2, "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(Integer value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(Integer value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(Integer value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(Integer value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(Integer value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<Integer> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<Integer> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(Integer value1, Integer value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("week not between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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

        public Criteria andSummerWinterTimeIsNull() {
            addCriterion("summer_winter_time is null");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeIsNotNull() {
            addCriterion("summer_winter_time is not null");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeEqualTo(String value) {
            addCriterion("summer_winter_time =", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotEqualTo(String value) {
            addCriterion("summer_winter_time <>", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeGreaterThan(String value) {
            addCriterion("summer_winter_time >", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeGreaterThanOrEqualTo(String value) {
            addCriterion("summer_winter_time >=", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLessThan(String value) {
            addCriterion("summer_winter_time <", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLessThanOrEqualTo(String value) {
            addCriterion("summer_winter_time <=", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLike(String value) {
            addCriterion("summer_winter_time like", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotLike(String value) {
            addCriterion("summer_winter_time not like", value, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeIn(List<String> values) {
            addCriterion("summer_winter_time in", values, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotIn(List<String> values) {
            addCriterion("summer_winter_time not in", values, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeBetween(String value1, String value2) {
            addCriterion("summer_winter_time between", value1, value2, "summerWinterTime");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeNotBetween(String value1, String value2) {
            addCriterion("summer_winter_time not between", value1, value2, "summerWinterTime");
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

        public Criteria andCourseNodeInitIdLikeInsensitive(String value) {
            addCriterion("upper(course_node_init_id) like", value.toUpperCase(), "courseNodeInitId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLikeInsensitive(String value) {
            addCriterion("upper(cycle_id) like", value.toUpperCase(), "cycleId");
            return (Criteria) this;
        }

        public Criteria andCycleYearLikeInsensitive(String value) {
            addCriterion("upper(cycle_year) like", value.toUpperCase(), "cycleYear");
            return (Criteria) this;
        }

        public Criteria andNodeNameLikeInsensitive(String value) {
            addCriterion("upper(node_name) like", value.toUpperCase(), "nodeName");
            return (Criteria) this;
        }

        public Criteria andMorningAfternoonLikeInsensitive(String value) {
            addCriterion("upper(morning_afternoon) like", value.toUpperCase(), "morningAfternoon");
            return (Criteria) this;
        }

        public Criteria andUpdateByLikeInsensitive(String value) {
            addCriterion("upper(update_by) like", value.toUpperCase(), "updateBy");
            return (Criteria) this;
        }

        public Criteria andSummerWinterTimeLikeInsensitive(String value) {
            addCriterion("upper(summer_winter_time) like", value.toUpperCase(), "summerWinterTime");
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