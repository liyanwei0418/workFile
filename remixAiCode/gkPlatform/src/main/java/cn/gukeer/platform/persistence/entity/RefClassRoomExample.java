package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class RefClassRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RefClassRoomExample() {
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

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(String value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(String value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(String value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(String value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(String value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(String value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLike(String value) {
            addCriterion("room_id like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotLike(String value) {
            addCriterion("room_id not like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<String> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<String> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(String value1, String value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(String value1, String value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andGradeClassIsNull() {
            addCriterion("grade_class is null");
            return (Criteria) this;
        }

        public Criteria andGradeClassIsNotNull() {
            addCriterion("grade_class is not null");
            return (Criteria) this;
        }

        public Criteria andGradeClassEqualTo(String value) {
            addCriterion("grade_class =", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassNotEqualTo(String value) {
            addCriterion("grade_class <>", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassGreaterThan(String value) {
            addCriterion("grade_class >", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassGreaterThanOrEqualTo(String value) {
            addCriterion("grade_class >=", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassLessThan(String value) {
            addCriterion("grade_class <", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassLessThanOrEqualTo(String value) {
            addCriterion("grade_class <=", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassLike(String value) {
            addCriterion("grade_class like", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassNotLike(String value) {
            addCriterion("grade_class not like", value, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassIn(List<String> values) {
            addCriterion("grade_class in", values, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassNotIn(List<String> values) {
            addCriterion("grade_class not in", values, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassBetween(String value1, String value2) {
            addCriterion("grade_class between", value1, value2, "gradeClass");
            return (Criteria) this;
        }

        public Criteria andGradeClassNotBetween(String value1, String value2) {
            addCriterion("grade_class not between", value1, value2, "gradeClass");
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

        public Criteria andSchoolTypeIdIsNull() {
            addCriterion("school_type_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdIsNotNull() {
            addCriterion("school_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdEqualTo(String value) {
            addCriterion("school_type_id =", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotEqualTo(String value) {
            addCriterion("school_type_id <>", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdGreaterThan(String value) {
            addCriterion("school_type_id >", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_type_id >=", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLessThan(String value) {
            addCriterion("school_type_id <", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLessThanOrEqualTo(String value) {
            addCriterion("school_type_id <=", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLike(String value) {
            addCriterion("school_type_id like", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotLike(String value) {
            addCriterion("school_type_id not like", value, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdIn(List<String> values) {
            addCriterion("school_type_id in", values, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotIn(List<String> values) {
            addCriterion("school_type_id not in", values, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdBetween(String value1, String value2) {
            addCriterion("school_type_id between", value1, value2, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdNotBetween(String value1, String value2) {
            addCriterion("school_type_id not between", value1, value2, "schoolTypeId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andRoomIdLikeInsensitive(String value) {
            addCriterion("upper(room_id) like", value.toUpperCase(), "roomId");
            return (Criteria) this;
        }

        public Criteria andGradeClassLikeInsensitive(String value) {
            addCriterion("upper(grade_class) like", value.toUpperCase(), "gradeClass");
            return (Criteria) this;
        }

        public Criteria andCycleIdLikeInsensitive(String value) {
            addCriterion("upper(cycle_id) like", value.toUpperCase(), "cycleId");
            return (Criteria) this;
        }

        public Criteria andSchoolTypeIdLikeInsensitive(String value) {
            addCriterion("upper(school_type_id) like", value.toUpperCase(), "schoolTypeId");
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