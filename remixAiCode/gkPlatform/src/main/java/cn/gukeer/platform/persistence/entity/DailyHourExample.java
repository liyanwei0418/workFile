package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class DailyHourExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyHourExample() {
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

        public Criteria andGradeClassIdIsNull() {
            addCriterion("grade_class_id is null");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdIsNotNull() {
            addCriterion("grade_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdEqualTo(String value) {
            addCriterion("grade_class_id =", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdNotEqualTo(String value) {
            addCriterion("grade_class_id <>", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdGreaterThan(String value) {
            addCriterion("grade_class_id >", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("grade_class_id >=", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdLessThan(String value) {
            addCriterion("grade_class_id <", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdLessThanOrEqualTo(String value) {
            addCriterion("grade_class_id <=", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdLike(String value) {
            addCriterion("grade_class_id like", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdNotLike(String value) {
            addCriterion("grade_class_id not like", value, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdIn(List<String> values) {
            addCriterion("grade_class_id in", values, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdNotIn(List<String> values) {
            addCriterion("grade_class_id not in", values, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdBetween(String value1, String value2) {
            addCriterion("grade_class_id between", value1, value2, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdNotBetween(String value1, String value2) {
            addCriterion("grade_class_id not between", value1, value2, "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andSktsIsNull() {
            addCriterion("skts is null");
            return (Criteria) this;
        }

        public Criteria andSktsIsNotNull() {
            addCriterion("skts is not null");
            return (Criteria) this;
        }

        public Criteria andSktsEqualTo(Integer value) {
            addCriterion("skts =", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsNotEqualTo(Integer value) {
            addCriterion("skts <>", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsGreaterThan(Integer value) {
            addCriterion("skts >", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsGreaterThanOrEqualTo(Integer value) {
            addCriterion("skts >=", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsLessThan(Integer value) {
            addCriterion("skts <", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsLessThanOrEqualTo(Integer value) {
            addCriterion("skts <=", value, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsIn(List<Integer> values) {
            addCriterion("skts in", values, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsNotIn(List<Integer> values) {
            addCriterion("skts not in", values, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsBetween(Integer value1, Integer value2) {
            addCriterion("skts between", value1, value2, "skts");
            return (Criteria) this;
        }

        public Criteria andSktsNotBetween(Integer value1, Integer value2) {
            addCriterion("skts not between", value1, value2, "skts");
            return (Criteria) this;
        }

        public Criteria andSwksIsNull() {
            addCriterion("swks is null");
            return (Criteria) this;
        }

        public Criteria andSwksIsNotNull() {
            addCriterion("swks is not null");
            return (Criteria) this;
        }

        public Criteria andSwksEqualTo(Integer value) {
            addCriterion("swks =", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksNotEqualTo(Integer value) {
            addCriterion("swks <>", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksGreaterThan(Integer value) {
            addCriterion("swks >", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksGreaterThanOrEqualTo(Integer value) {
            addCriterion("swks >=", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksLessThan(Integer value) {
            addCriterion("swks <", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksLessThanOrEqualTo(Integer value) {
            addCriterion("swks <=", value, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksIn(List<Integer> values) {
            addCriterion("swks in", values, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksNotIn(List<Integer> values) {
            addCriterion("swks not in", values, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksBetween(Integer value1, Integer value2) {
            addCriterion("swks between", value1, value2, "swks");
            return (Criteria) this;
        }

        public Criteria andSwksNotBetween(Integer value1, Integer value2) {
            addCriterion("swks not between", value1, value2, "swks");
            return (Criteria) this;
        }

        public Criteria andXwksIsNull() {
            addCriterion("xwks is null");
            return (Criteria) this;
        }

        public Criteria andXwksIsNotNull() {
            addCriterion("xwks is not null");
            return (Criteria) this;
        }

        public Criteria andXwksEqualTo(Integer value) {
            addCriterion("xwks =", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksNotEqualTo(Integer value) {
            addCriterion("xwks <>", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksGreaterThan(Integer value) {
            addCriterion("xwks >", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksGreaterThanOrEqualTo(Integer value) {
            addCriterion("xwks >=", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksLessThan(Integer value) {
            addCriterion("xwks <", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksLessThanOrEqualTo(Integer value) {
            addCriterion("xwks <=", value, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksIn(List<Integer> values) {
            addCriterion("xwks in", values, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksNotIn(List<Integer> values) {
            addCriterion("xwks not in", values, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksBetween(Integer value1, Integer value2) {
            addCriterion("xwks between", value1, value2, "xwks");
            return (Criteria) this;
        }

        public Criteria andXwksNotBetween(Integer value1, Integer value2) {
            addCriterion("xwks not between", value1, value2, "xwks");
            return (Criteria) this;
        }

        public Criteria andKjcIsNull() {
            addCriterion("kjc is null");
            return (Criteria) this;
        }

        public Criteria andKjcIsNotNull() {
            addCriterion("kjc is not null");
            return (Criteria) this;
        }

        public Criteria andKjcEqualTo(Integer value) {
            addCriterion("kjc =", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcNotEqualTo(Integer value) {
            addCriterion("kjc <>", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcGreaterThan(Integer value) {
            addCriterion("kjc >", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcGreaterThanOrEqualTo(Integer value) {
            addCriterion("kjc >=", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcLessThan(Integer value) {
            addCriterion("kjc <", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcLessThanOrEqualTo(Integer value) {
            addCriterion("kjc <=", value, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcIn(List<Integer> values) {
            addCriterion("kjc in", values, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcNotIn(List<Integer> values) {
            addCriterion("kjc not in", values, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcBetween(Integer value1, Integer value2) {
            addCriterion("kjc between", value1, value2, "kjc");
            return (Criteria) this;
        }

        public Criteria andKjcNotBetween(Integer value1, Integer value2) {
            addCriterion("kjc not between", value1, value2, "kjc");
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

        public Criteria andXnIsNull() {
            addCriterion("xn is null");
            return (Criteria) this;
        }

        public Criteria andXnIsNotNull() {
            addCriterion("xn is not null");
            return (Criteria) this;
        }

        public Criteria andXnEqualTo(String value) {
            addCriterion("xn =", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotEqualTo(String value) {
            addCriterion("xn <>", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnGreaterThan(String value) {
            addCriterion("xn >", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnGreaterThanOrEqualTo(String value) {
            addCriterion("xn >=", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnLessThan(String value) {
            addCriterion("xn <", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnLessThanOrEqualTo(String value) {
            addCriterion("xn <=", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnLike(String value) {
            addCriterion("xn like", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotLike(String value) {
            addCriterion("xn not like", value, "xn");
            return (Criteria) this;
        }

        public Criteria andXnIn(List<String> values) {
            addCriterion("xn in", values, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotIn(List<String> values) {
            addCriterion("xn not in", values, "xn");
            return (Criteria) this;
        }

        public Criteria andXnBetween(String value1, String value2) {
            addCriterion("xn between", value1, value2, "xn");
            return (Criteria) this;
        }

        public Criteria andXnNotBetween(String value1, String value2) {
            addCriterion("xn not between", value1, value2, "xn");
            return (Criteria) this;
        }

        public Criteria andXqIsNull() {
            addCriterion("xq is null");
            return (Criteria) this;
        }

        public Criteria andXqIsNotNull() {
            addCriterion("xq is not null");
            return (Criteria) this;
        }

        public Criteria andXqEqualTo(String value) {
            addCriterion("xq =", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotEqualTo(String value) {
            addCriterion("xq <>", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThan(String value) {
            addCriterion("xq >", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqGreaterThanOrEqualTo(String value) {
            addCriterion("xq >=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThan(String value) {
            addCriterion("xq <", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLessThanOrEqualTo(String value) {
            addCriterion("xq <=", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqLike(String value) {
            addCriterion("xq like", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotLike(String value) {
            addCriterion("xq not like", value, "xq");
            return (Criteria) this;
        }

        public Criteria andXqIn(List<String> values) {
            addCriterion("xq in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotIn(List<String> values) {
            addCriterion("xq not in", values, "xq");
            return (Criteria) this;
        }

        public Criteria andXqBetween(String value1, String value2) {
            addCriterion("xq between", value1, value2, "xq");
            return (Criteria) this;
        }

        public Criteria andXqNotBetween(String value1, String value2) {
            addCriterion("xq not between", value1, value2, "xq");
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

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIdLikeInsensitive(String value) {
            addCriterion("upper(grade_class_id) like", value.toUpperCase(), "gradeClassId");
            return (Criteria) this;
        }

        public Criteria andCycleIdLikeInsensitive(String value) {
            addCriterion("upper(cycle_id) like", value.toUpperCase(), "cycleId");
            return (Criteria) this;
        }

        public Criteria andXnLikeInsensitive(String value) {
            addCriterion("upper(xn) like", value.toUpperCase(), "xn");
            return (Criteria) this;
        }

        public Criteria andXqLikeInsensitive(String value) {
            addCriterion("upper(xq) like", value.toUpperCase(), "xq");
            return (Criteria) this;
        }

        public Criteria andUpdateByLikeInsensitive(String value) {
            addCriterion("upper(update_by) like", value.toUpperCase(), "updateBy");
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