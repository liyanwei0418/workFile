package cn.gukeer.classcard.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassCardConfigRefExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassCardConfigRefExample() {
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

        public Criteria andClassCardConfigIdIsNull() {
            addCriterion("class_card_config_id is null");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdIsNotNull() {
            addCriterion("class_card_config_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdEqualTo(String value) {
            addCriterion("class_card_config_id =", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdNotEqualTo(String value) {
            addCriterion("class_card_config_id <>", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdGreaterThan(String value) {
            addCriterion("class_card_config_id >", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_card_config_id >=", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdLessThan(String value) {
            addCriterion("class_card_config_id <", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdLessThanOrEqualTo(String value) {
            addCriterion("class_card_config_id <=", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdLike(String value) {
            addCriterion("class_card_config_id like", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdNotLike(String value) {
            addCriterion("class_card_config_id not like", value, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdIn(List<String> values) {
            addCriterion("class_card_config_id in", values, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdNotIn(List<String> values) {
            addCriterion("class_card_config_id not in", values, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdBetween(String value1, String value2) {
            addCriterion("class_card_config_id between", value1, value2, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardConfigIdNotBetween(String value1, String value2) {
            addCriterion("class_card_config_id not between", value1, value2, "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdIsNull() {
            addCriterion("class_card_id is null");
            return (Criteria) this;
        }

        public Criteria andClassCardIdIsNotNull() {
            addCriterion("class_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassCardIdEqualTo(String value) {
            addCriterion("class_card_id =", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdNotEqualTo(String value) {
            addCriterion("class_card_id <>", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdGreaterThan(String value) {
            addCriterion("class_card_id >", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_card_id >=", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdLessThan(String value) {
            addCriterion("class_card_id <", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdLessThanOrEqualTo(String value) {
            addCriterion("class_card_id <=", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdLike(String value) {
            addCriterion("class_card_id like", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdNotLike(String value) {
            addCriterion("class_card_id not like", value, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdIn(List<String> values) {
            addCriterion("class_card_id in", values, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdNotIn(List<String> values) {
            addCriterion("class_card_id not in", values, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdBetween(String value1, String value2) {
            addCriterion("class_card_id between", value1, value2, "classCardId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdNotBetween(String value1, String value2) {
            addCriterion("class_card_id not between", value1, value2, "classCardId");
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

        public Criteria andClassCardConfigIdLikeInsensitive(String value) {
            addCriterion("upper(class_card_config_id) like", value.toUpperCase(), "classCardConfigId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdLikeInsensitive(String value) {
            addCriterion("upper(class_card_id) like", value.toUpperCase(), "classCardId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
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