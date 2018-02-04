package cn.gukeer.classcard.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassIntroductionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassIntroductionExample() {
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

        public Criteria andSendWordIsNull() {
            addCriterion("send_word is null");
            return (Criteria) this;
        }

        public Criteria andSendWordIsNotNull() {
            addCriterion("send_word is not null");
            return (Criteria) this;
        }

        public Criteria andSendWordEqualTo(String value) {
            addCriterion("send_word =", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordNotEqualTo(String value) {
            addCriterion("send_word <>", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordGreaterThan(String value) {
            addCriterion("send_word >", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordGreaterThanOrEqualTo(String value) {
            addCriterion("send_word >=", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordLessThan(String value) {
            addCriterion("send_word <", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordLessThanOrEqualTo(String value) {
            addCriterion("send_word <=", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordLike(String value) {
            addCriterion("send_word like", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordNotLike(String value) {
            addCriterion("send_word not like", value, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordIn(List<String> values) {
            addCriterion("send_word in", values, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordNotIn(List<String> values) {
            addCriterion("send_word not in", values, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordBetween(String value1, String value2) {
            addCriterion("send_word between", value1, value2, "sendWord");
            return (Criteria) this;
        }

        public Criteria andSendWordNotBetween(String value1, String value2) {
            addCriterion("send_word not between", value1, value2, "sendWord");
            return (Criteria) this;
        }

        public Criteria andClassBackboneIsNull() {
            addCriterion("class_backbone is null");
            return (Criteria) this;
        }

        public Criteria andClassBackboneIsNotNull() {
            addCriterion("class_backbone is not null");
            return (Criteria) this;
        }

        public Criteria andClassBackboneEqualTo(String value) {
            addCriterion("class_backbone =", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneNotEqualTo(String value) {
            addCriterion("class_backbone <>", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneGreaterThan(String value) {
            addCriterion("class_backbone >", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneGreaterThanOrEqualTo(String value) {
            addCriterion("class_backbone >=", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneLessThan(String value) {
            addCriterion("class_backbone <", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneLessThanOrEqualTo(String value) {
            addCriterion("class_backbone <=", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneLike(String value) {
            addCriterion("class_backbone like", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneNotLike(String value) {
            addCriterion("class_backbone not like", value, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneIn(List<String> values) {
            addCriterion("class_backbone in", values, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneNotIn(List<String> values) {
            addCriterion("class_backbone not in", values, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneBetween(String value1, String value2) {
            addCriterion("class_backbone between", value1, value2, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassBackboneNotBetween(String value1, String value2) {
            addCriterion("class_backbone not between", value1, value2, "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionIsNull() {
            addCriterion("class_introduction is null");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionIsNotNull() {
            addCriterion("class_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionEqualTo(String value) {
            addCriterion("class_introduction =", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionNotEqualTo(String value) {
            addCriterion("class_introduction <>", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionGreaterThan(String value) {
            addCriterion("class_introduction >", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("class_introduction >=", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionLessThan(String value) {
            addCriterion("class_introduction <", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionLessThanOrEqualTo(String value) {
            addCriterion("class_introduction <=", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionLike(String value) {
            addCriterion("class_introduction like", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionNotLike(String value) {
            addCriterion("class_introduction not like", value, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionIn(List<String> values) {
            addCriterion("class_introduction in", values, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionNotIn(List<String> values) {
            addCriterion("class_introduction not in", values, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionBetween(String value1, String value2) {
            addCriterion("class_introduction between", value1, value2, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionNotBetween(String value1, String value2) {
            addCriterion("class_introduction not between", value1, value2, "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("class_id like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("class_id not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("class_id not between", value1, value2, "classId");
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

        public Criteria andPictureIdIsNull() {
            addCriterion("picture_id is null");
            return (Criteria) this;
        }

        public Criteria andPictureIdIsNotNull() {
            addCriterion("picture_id is not null");
            return (Criteria) this;
        }

        public Criteria andPictureIdEqualTo(String value) {
            addCriterion("picture_id =", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotEqualTo(String value) {
            addCriterion("picture_id <>", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdGreaterThan(String value) {
            addCriterion("picture_id >", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdGreaterThanOrEqualTo(String value) {
            addCriterion("picture_id >=", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLessThan(String value) {
            addCriterion("picture_id <", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLessThanOrEqualTo(String value) {
            addCriterion("picture_id <=", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdLike(String value) {
            addCriterion("picture_id like", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotLike(String value) {
            addCriterion("picture_id not like", value, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdIn(List<String> values) {
            addCriterion("picture_id in", values, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotIn(List<String> values) {
            addCriterion("picture_id not in", values, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdBetween(String value1, String value2) {
            addCriterion("picture_id between", value1, value2, "pictureId");
            return (Criteria) this;
        }

        public Criteria andPictureIdNotBetween(String value1, String value2) {
            addCriterion("picture_id not between", value1, value2, "pictureId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNull() {
            addCriterion("video_id is null");
            return (Criteria) this;
        }

        public Criteria andVideoIdIsNotNull() {
            addCriterion("video_id is not null");
            return (Criteria) this;
        }

        public Criteria andVideoIdEqualTo(String value) {
            addCriterion("video_id =", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotEqualTo(String value) {
            addCriterion("video_id <>", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThan(String value) {
            addCriterion("video_id >", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdGreaterThanOrEqualTo(String value) {
            addCriterion("video_id >=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThan(String value) {
            addCriterion("video_id <", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLessThanOrEqualTo(String value) {
            addCriterion("video_id <=", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLike(String value) {
            addCriterion("video_id like", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotLike(String value) {
            addCriterion("video_id not like", value, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdIn(List<String> values) {
            addCriterion("video_id in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotIn(List<String> values) {
            addCriterion("video_id not in", values, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdBetween(String value1, String value2) {
            addCriterion("video_id between", value1, value2, "videoId");
            return (Criteria) this;
        }

        public Criteria andVideoIdNotBetween(String value1, String value2) {
            addCriterion("video_id not between", value1, value2, "videoId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSendWordLikeInsensitive(String value) {
            addCriterion("upper(send_word) like", value.toUpperCase(), "sendWord");
            return (Criteria) this;
        }

        public Criteria andClassBackboneLikeInsensitive(String value) {
            addCriterion("upper(class_backbone) like", value.toUpperCase(), "classBackbone");
            return (Criteria) this;
        }

        public Criteria andClassIntroductionLikeInsensitive(String value) {
            addCriterion("upper(class_introduction) like", value.toUpperCase(), "classIntroduction");
            return (Criteria) this;
        }

        public Criteria andClassIdLikeInsensitive(String value) {
            addCriterion("upper(class_id) like", value.toUpperCase(), "classId");
            return (Criteria) this;
        }

        public Criteria andClassCardIdLikeInsensitive(String value) {
            addCriterion("upper(class_card_id) like", value.toUpperCase(), "classCardId");
            return (Criteria) this;
        }

        public Criteria andUpdateByLikeInsensitive(String value) {
            addCriterion("upper(update_by) like", value.toUpperCase(), "updateBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLikeInsensitive(String value) {
            addCriterion("upper(create_by) like", value.toUpperCase(), "createBy");
            return (Criteria) this;
        }

        public Criteria andPictureIdLikeInsensitive(String value) {
            addCriterion("upper(picture_id) like", value.toUpperCase(), "pictureId");
            return (Criteria) this;
        }

        public Criteria andVideoIdLikeInsensitive(String value) {
            addCriterion("upper(video_id) like", value.toUpperCase(), "videoId");
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