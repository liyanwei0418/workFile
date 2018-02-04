package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncTeacherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SyncTeacherExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSyncIdIsNull() {
            addCriterion("sync_id is null");
            return (Criteria) this;
        }

        public Criteria andSyncIdIsNotNull() {
            addCriterion("sync_id is not null");
            return (Criteria) this;
        }

        public Criteria andSyncIdEqualTo(String value) {
            addCriterion("sync_id =", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotEqualTo(String value) {
            addCriterion("sync_id <>", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdGreaterThan(String value) {
            addCriterion("sync_id >", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdGreaterThanOrEqualTo(String value) {
            addCriterion("sync_id >=", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdLessThan(String value) {
            addCriterion("sync_id <", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdLessThanOrEqualTo(String value) {
            addCriterion("sync_id <=", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdLike(String value) {
            addCriterion("sync_id like", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotLike(String value) {
            addCriterion("sync_id not like", value, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdIn(List<String> values) {
            addCriterion("sync_id in", values, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotIn(List<String> values) {
            addCriterion("sync_id not in", values, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdBetween(String value1, String value2) {
            addCriterion("sync_id between", value1, value2, "syncId");
            return (Criteria) this;
        }

        public Criteria andSyncIdNotBetween(String value1, String value2) {
            addCriterion("sync_id not between", value1, value2, "syncId");
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

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(String value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(String value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(String value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(String value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(String value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLike(String value) {
            addCriterion("department_id like", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotLike(String value) {
            addCriterion("department_id not like", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<String> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<String> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(String value1, String value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(String value1, String value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andIdentityIsNull() {
            addCriterion("identity is null");
            return (Criteria) this;
        }

        public Criteria andIdentityIsNotNull() {
            addCriterion("identity is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityEqualTo(String value) {
            addCriterion("identity =", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotEqualTo(String value) {
            addCriterion("identity <>", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityGreaterThan(String value) {
            addCriterion("identity >", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("identity >=", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLessThan(String value) {
            addCriterion("identity <", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLessThanOrEqualTo(String value) {
            addCriterion("identity <=", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLike(String value) {
            addCriterion("identity like", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotLike(String value) {
            addCriterion("identity not like", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityIn(List<String> values) {
            addCriterion("identity in", values, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotIn(List<String> values) {
            addCriterion("identity not in", values, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityBetween(String value1, String value2) {
            addCriterion("identity between", value1, value2, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotBetween(String value1, String value2) {
            addCriterion("identity not between", value1, value2, "identity");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andIsManageIsNull() {
            addCriterion("is_manage is null");
            return (Criteria) this;
        }

        public Criteria andIsManageIsNotNull() {
            addCriterion("is_manage is not null");
            return (Criteria) this;
        }

        public Criteria andIsManageEqualTo(Integer value) {
            addCriterion("is_manage =", value, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageNotEqualTo(Integer value) {
            addCriterion("is_manage <>", value, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageGreaterThan(Integer value) {
            addCriterion("is_manage >", value, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_manage >=", value, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageLessThan(Integer value) {
            addCriterion("is_manage <", value, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageLessThanOrEqualTo(Integer value) {
            addCriterion("is_manage <=", value, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageIn(List<Integer> values) {
            addCriterion("is_manage in", values, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageNotIn(List<Integer> values) {
            addCriterion("is_manage not in", values, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageBetween(Integer value1, Integer value2) {
            addCriterion("is_manage between", value1, value2, "isManage");
            return (Criteria) this;
        }

        public Criteria andIsManageNotBetween(Integer value1, Integer value2) {
            addCriterion("is_manage not between", value1, value2, "isManage");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNull() {
            addCriterion("title_id is null");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNotNull() {
            addCriterion("title_id is not null");
            return (Criteria) this;
        }

        public Criteria andTitleIdEqualTo(String value) {
            addCriterion("title_id =", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotEqualTo(String value) {
            addCriterion("title_id <>", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThan(String value) {
            addCriterion("title_id >", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThanOrEqualTo(String value) {
            addCriterion("title_id >=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThan(String value) {
            addCriterion("title_id <", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThanOrEqualTo(String value) {
            addCriterion("title_id <=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLike(String value) {
            addCriterion("title_id like", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotLike(String value) {
            addCriterion("title_id not like", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdIn(List<String> values) {
            addCriterion("title_id in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotIn(List<String> values) {
            addCriterion("title_id not in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdBetween(String value1, String value2) {
            addCriterion("title_id between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotBetween(String value1, String value2) {
            addCriterion("title_id not between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andStartWorkIsNull() {
            addCriterion("start_work is null");
            return (Criteria) this;
        }

        public Criteria andStartWorkIsNotNull() {
            addCriterion("start_work is not null");
            return (Criteria) this;
        }

        public Criteria andStartWorkEqualTo(Long value) {
            addCriterion("start_work =", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotEqualTo(Long value) {
            addCriterion("start_work <>", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkGreaterThan(Long value) {
            addCriterion("start_work >", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkGreaterThanOrEqualTo(Long value) {
            addCriterion("start_work >=", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkLessThan(Long value) {
            addCriterion("start_work <", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkLessThanOrEqualTo(Long value) {
            addCriterion("start_work <=", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkIn(List<Long> values) {
            addCriterion("start_work in", values, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotIn(List<Long> values) {
            addCriterion("start_work not in", values, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkBetween(Long value1, Long value2) {
            addCriterion("start_work between", value1, value2, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotBetween(Long value1, Long value2) {
            addCriterion("start_work not between", value1, value2, "startWork");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIsNull() {
            addCriterion("head_url is null");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIsNotNull() {
            addCriterion("head_url is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUrlEqualTo(String value) {
            addCriterion("head_url =", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotEqualTo(String value) {
            addCriterion("head_url <>", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlGreaterThan(String value) {
            addCriterion("head_url >", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlGreaterThanOrEqualTo(String value) {
            addCriterion("head_url >=", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLessThan(String value) {
            addCriterion("head_url <", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLessThanOrEqualTo(String value) {
            addCriterion("head_url <=", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLike(String value) {
            addCriterion("head_url like", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotLike(String value) {
            addCriterion("head_url not like", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIn(List<String> values) {
            addCriterion("head_url in", values, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotIn(List<String> values) {
            addCriterion("head_url not in", values, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlBetween(String value1, String value2) {
            addCriterion("head_url between", value1, value2, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotBetween(String value1, String value2) {
            addCriterion("head_url not between", value1, value2, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHighTimeIsNull() {
            addCriterion("high_time is null");
            return (Criteria) this;
        }

        public Criteria andHighTimeIsNotNull() {
            addCriterion("high_time is not null");
            return (Criteria) this;
        }

        public Criteria andHighTimeEqualTo(Long value) {
            addCriterion("high_time =", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotEqualTo(Long value) {
            addCriterion("high_time <>", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeGreaterThan(Long value) {
            addCriterion("high_time >", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("high_time >=", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeLessThan(Long value) {
            addCriterion("high_time <", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeLessThanOrEqualTo(Long value) {
            addCriterion("high_time <=", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeIn(List<Long> values) {
            addCriterion("high_time in", values, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotIn(List<Long> values) {
            addCriterion("high_time not in", values, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeBetween(Long value1, Long value2) {
            addCriterion("high_time between", value1, value2, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotBetween(Long value1, Long value2) {
            addCriterion("high_time not between", value1, value2, "highTime");
            return (Criteria) this;
        }

        public Criteria andZcIsNull() {
            addCriterion("zc is null");
            return (Criteria) this;
        }

        public Criteria andZcIsNotNull() {
            addCriterion("zc is not null");
            return (Criteria) this;
        }

        public Criteria andZcEqualTo(String value) {
            addCriterion("zc =", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcNotEqualTo(String value) {
            addCriterion("zc <>", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcGreaterThan(String value) {
            addCriterion("zc >", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcGreaterThanOrEqualTo(String value) {
            addCriterion("zc >=", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcLessThan(String value) {
            addCriterion("zc <", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcLessThanOrEqualTo(String value) {
            addCriterion("zc <=", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcLike(String value) {
            addCriterion("zc like", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcNotLike(String value) {
            addCriterion("zc not like", value, "zc");
            return (Criteria) this;
        }

        public Criteria andZcIn(List<String> values) {
            addCriterion("zc in", values, "zc");
            return (Criteria) this;
        }

        public Criteria andZcNotIn(List<String> values) {
            addCriterion("zc not in", values, "zc");
            return (Criteria) this;
        }

        public Criteria andZcBetween(String value1, String value2) {
            addCriterion("zc between", value1, value2, "zc");
            return (Criteria) this;
        }

        public Criteria andZcNotBetween(String value1, String value2) {
            addCriterion("zc not between", value1, value2, "zc");
            return (Criteria) this;
        }

        public Criteria andSfzrjsIsNull() {
            addCriterion("sfzrjs is null");
            return (Criteria) this;
        }

        public Criteria andSfzrjsIsNotNull() {
            addCriterion("sfzrjs is not null");
            return (Criteria) this;
        }

        public Criteria andSfzrjsEqualTo(String value) {
            addCriterion("sfzrjs =", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsNotEqualTo(String value) {
            addCriterion("sfzrjs <>", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsGreaterThan(String value) {
            addCriterion("sfzrjs >", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsGreaterThanOrEqualTo(String value) {
            addCriterion("sfzrjs >=", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsLessThan(String value) {
            addCriterion("sfzrjs <", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsLessThanOrEqualTo(String value) {
            addCriterion("sfzrjs <=", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsLike(String value) {
            addCriterion("sfzrjs like", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsNotLike(String value) {
            addCriterion("sfzrjs not like", value, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsIn(List<String> values) {
            addCriterion("sfzrjs in", values, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsNotIn(List<String> values) {
            addCriterion("sfzrjs not in", values, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsBetween(String value1, String value2) {
            addCriterion("sfzrjs between", value1, value2, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSfzrjsNotBetween(String value1, String value2) {
            addCriterion("sfzrjs not between", value1, value2, "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andJgIsNull() {
            addCriterion("jg is null");
            return (Criteria) this;
        }

        public Criteria andJgIsNotNull() {
            addCriterion("jg is not null");
            return (Criteria) this;
        }

        public Criteria andJgEqualTo(String value) {
            addCriterion("jg =", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgNotEqualTo(String value) {
            addCriterion("jg <>", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgGreaterThan(String value) {
            addCriterion("jg >", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgGreaterThanOrEqualTo(String value) {
            addCriterion("jg >=", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgLessThan(String value) {
            addCriterion("jg <", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgLessThanOrEqualTo(String value) {
            addCriterion("jg <=", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgLike(String value) {
            addCriterion("jg like", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgNotLike(String value) {
            addCriterion("jg not like", value, "jg");
            return (Criteria) this;
        }

        public Criteria andJgIn(List<String> values) {
            addCriterion("jg in", values, "jg");
            return (Criteria) this;
        }

        public Criteria andJgNotIn(List<String> values) {
            addCriterion("jg not in", values, "jg");
            return (Criteria) this;
        }

        public Criteria andJgBetween(String value1, String value2) {
            addCriterion("jg between", value1, value2, "jg");
            return (Criteria) this;
        }

        public Criteria andJgNotBetween(String value1, String value2) {
            addCriterion("jg not between", value1, value2, "jg");
            return (Criteria) this;
        }

        public Criteria andZzmmIsNull() {
            addCriterion("zzmm is null");
            return (Criteria) this;
        }

        public Criteria andZzmmIsNotNull() {
            addCriterion("zzmm is not null");
            return (Criteria) this;
        }

        public Criteria andZzmmEqualTo(String value) {
            addCriterion("zzmm =", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotEqualTo(String value) {
            addCriterion("zzmm <>", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmGreaterThan(String value) {
            addCriterion("zzmm >", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmGreaterThanOrEqualTo(String value) {
            addCriterion("zzmm >=", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLessThan(String value) {
            addCriterion("zzmm <", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLessThanOrEqualTo(String value) {
            addCriterion("zzmm <=", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLike(String value) {
            addCriterion("zzmm like", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotLike(String value) {
            addCriterion("zzmm not like", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmIn(List<String> values) {
            addCriterion("zzmm in", values, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotIn(List<String> values) {
            addCriterion("zzmm not in", values, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmBetween(String value1, String value2) {
            addCriterion("zzmm between", value1, value2, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotBetween(String value1, String value2) {
            addCriterion("zzmm not between", value1, value2, "zzmm");
            return (Criteria) this;
        }

        public Criteria andRjxkIsNull() {
            addCriterion("rjxk is null");
            return (Criteria) this;
        }

        public Criteria andRjxkIsNotNull() {
            addCriterion("rjxk is not null");
            return (Criteria) this;
        }

        public Criteria andRjxkEqualTo(String value) {
            addCriterion("rjxk =", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkNotEqualTo(String value) {
            addCriterion("rjxk <>", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkGreaterThan(String value) {
            addCriterion("rjxk >", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkGreaterThanOrEqualTo(String value) {
            addCriterion("rjxk >=", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkLessThan(String value) {
            addCriterion("rjxk <", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkLessThanOrEqualTo(String value) {
            addCriterion("rjxk <=", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkLike(String value) {
            addCriterion("rjxk like", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkNotLike(String value) {
            addCriterion("rjxk not like", value, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkIn(List<String> values) {
            addCriterion("rjxk in", values, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkNotIn(List<String> values) {
            addCriterion("rjxk not in", values, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkBetween(String value1, String value2) {
            addCriterion("rjxk between", value1, value2, "rjxk");
            return (Criteria) this;
        }

        public Criteria andRjxkNotBetween(String value1, String value2) {
            addCriterion("rjxk not between", value1, value2, "rjxk");
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

        public Criteria andZgxlIsNull() {
            addCriterion("zgxl is null");
            return (Criteria) this;
        }

        public Criteria andZgxlIsNotNull() {
            addCriterion("zgxl is not null");
            return (Criteria) this;
        }

        public Criteria andZgxlEqualTo(String value) {
            addCriterion("zgxl =", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlNotEqualTo(String value) {
            addCriterion("zgxl <>", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlGreaterThan(String value) {
            addCriterion("zgxl >", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlGreaterThanOrEqualTo(String value) {
            addCriterion("zgxl >=", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlLessThan(String value) {
            addCriterion("zgxl <", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlLessThanOrEqualTo(String value) {
            addCriterion("zgxl <=", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlLike(String value) {
            addCriterion("zgxl like", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlNotLike(String value) {
            addCriterion("zgxl not like", value, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlIn(List<String> values) {
            addCriterion("zgxl in", values, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlNotIn(List<String> values) {
            addCriterion("zgxl not in", values, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlBetween(String value1, String value2) {
            addCriterion("zgxl between", value1, value2, "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgxlNotBetween(String value1, String value2) {
            addCriterion("zgxl not between", value1, value2, "zgxl");
            return (Criteria) this;
        }

        public Criteria andLwxsjIsNull() {
            addCriterion("lwxsj is null");
            return (Criteria) this;
        }

        public Criteria andLwxsjIsNotNull() {
            addCriterion("lwxsj is not null");
            return (Criteria) this;
        }

        public Criteria andLwxsjEqualTo(Long value) {
            addCriterion("lwxsj =", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotEqualTo(Long value) {
            addCriterion("lwxsj <>", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjGreaterThan(Long value) {
            addCriterion("lwxsj >", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjGreaterThanOrEqualTo(Long value) {
            addCriterion("lwxsj >=", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjLessThan(Long value) {
            addCriterion("lwxsj <", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjLessThanOrEqualTo(Long value) {
            addCriterion("lwxsj <=", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjIn(List<Long> values) {
            addCriterion("lwxsj in", values, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotIn(List<Long> values) {
            addCriterion("lwxsj not in", values, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjBetween(Long value1, Long value2) {
            addCriterion("lwxsj between", value1, value2, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotBetween(Long value1, Long value2) {
            addCriterion("lwxsj not between", value1, value2, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andSfhqIsNull() {
            addCriterion("sfhq is null");
            return (Criteria) this;
        }

        public Criteria andSfhqIsNotNull() {
            addCriterion("sfhq is not null");
            return (Criteria) this;
        }

        public Criteria andSfhqEqualTo(String value) {
            addCriterion("sfhq =", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqNotEqualTo(String value) {
            addCriterion("sfhq <>", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqGreaterThan(String value) {
            addCriterion("sfhq >", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqGreaterThanOrEqualTo(String value) {
            addCriterion("sfhq >=", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqLessThan(String value) {
            addCriterion("sfhq <", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqLessThanOrEqualTo(String value) {
            addCriterion("sfhq <=", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqLike(String value) {
            addCriterion("sfhq like", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqNotLike(String value) {
            addCriterion("sfhq not like", value, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqIn(List<String> values) {
            addCriterion("sfhq in", values, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqNotIn(List<String> values) {
            addCriterion("sfhq not in", values, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqBetween(String value1, String value2) {
            addCriterion("sfhq between", value1, value2, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfhqNotBetween(String value1, String value2) {
            addCriterion("sfhq not between", value1, value2, "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfbzrIsNull() {
            addCriterion("sfbzr is null");
            return (Criteria) this;
        }

        public Criteria andSfbzrIsNotNull() {
            addCriterion("sfbzr is not null");
            return (Criteria) this;
        }

        public Criteria andSfbzrEqualTo(String value) {
            addCriterion("sfbzr =", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrNotEqualTo(String value) {
            addCriterion("sfbzr <>", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrGreaterThan(String value) {
            addCriterion("sfbzr >", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrGreaterThanOrEqualTo(String value) {
            addCriterion("sfbzr >=", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrLessThan(String value) {
            addCriterion("sfbzr <", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrLessThanOrEqualTo(String value) {
            addCriterion("sfbzr <=", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrLike(String value) {
            addCriterion("sfbzr like", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrNotLike(String value) {
            addCriterion("sfbzr not like", value, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrIn(List<String> values) {
            addCriterion("sfbzr in", values, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrNotIn(List<String> values) {
            addCriterion("sfbzr not in", values, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrBetween(String value1, String value2) {
            addCriterion("sfbzr between", value1, value2, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andSfbzrNotBetween(String value1, String value2) {
            addCriterion("sfbzr not between", value1, value2, "sfbzr");
            return (Criteria) this;
        }

        public Criteria andWyyzIsNull() {
            addCriterion("wyyz is null");
            return (Criteria) this;
        }

        public Criteria andWyyzIsNotNull() {
            addCriterion("wyyz is not null");
            return (Criteria) this;
        }

        public Criteria andWyyzEqualTo(String value) {
            addCriterion("wyyz =", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzNotEqualTo(String value) {
            addCriterion("wyyz <>", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzGreaterThan(String value) {
            addCriterion("wyyz >", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzGreaterThanOrEqualTo(String value) {
            addCriterion("wyyz >=", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzLessThan(String value) {
            addCriterion("wyyz <", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzLessThanOrEqualTo(String value) {
            addCriterion("wyyz <=", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzLike(String value) {
            addCriterion("wyyz like", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzNotLike(String value) {
            addCriterion("wyyz not like", value, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzIn(List<String> values) {
            addCriterion("wyyz in", values, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzNotIn(List<String> values) {
            addCriterion("wyyz not in", values, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzBetween(String value1, String value2) {
            addCriterion("wyyz between", value1, value2, "wyyz");
            return (Criteria) this;
        }

        public Criteria andWyyzNotBetween(String value1, String value2) {
            addCriterion("wyyz not between", value1, value2, "wyyz");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflIsNull() {
            addCriterion("zyjsgwfl is null");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflIsNotNull() {
            addCriterion("zyjsgwfl is not null");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflEqualTo(String value) {
            addCriterion("zyjsgwfl =", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflNotEqualTo(String value) {
            addCriterion("zyjsgwfl <>", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflGreaterThan(String value) {
            addCriterion("zyjsgwfl >", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflGreaterThanOrEqualTo(String value) {
            addCriterion("zyjsgwfl >=", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflLessThan(String value) {
            addCriterion("zyjsgwfl <", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflLessThanOrEqualTo(String value) {
            addCriterion("zyjsgwfl <=", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflLike(String value) {
            addCriterion("zyjsgwfl like", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflNotLike(String value) {
            addCriterion("zyjsgwfl not like", value, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflIn(List<String> values) {
            addCriterion("zyjsgwfl in", values, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflNotIn(List<String> values) {
            addCriterion("zyjsgwfl not in", values, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflBetween(String value1, String value2) {
            addCriterion("zyjsgwfl between", value1, value2, "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflNotBetween(String value1, String value2) {
            addCriterion("zyjsgwfl not between", value1, value2, "zyjsgwfl");
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

        public Criteria andEventIsNull() {
            addCriterion("event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(String value) {
            addCriterion("event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(String value) {
            addCriterion("event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(String value) {
            addCriterion("event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(String value) {
            addCriterion("event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(String value) {
            addCriterion("event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(String value) {
            addCriterion("event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLike(String value) {
            addCriterion("event like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotLike(String value) {
            addCriterion("event not like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<String> values) {
            addCriterion("event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<String> values) {
            addCriterion("event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(String value1, String value2) {
            addCriterion("event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(String value1, String value2) {
            addCriterion("event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSyncDateIsNull() {
            addCriterion("sync_date is null");
            return (Criteria) this;
        }

        public Criteria andSyncDateIsNotNull() {
            addCriterion("sync_date is not null");
            return (Criteria) this;
        }

        public Criteria andSyncDateEqualTo(Date value) {
            addCriterion("sync_date =", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotEqualTo(Date value) {
            addCriterion("sync_date <>", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateGreaterThan(Date value) {
            addCriterion("sync_date >", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateGreaterThanOrEqualTo(Date value) {
            addCriterion("sync_date >=", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLessThan(Date value) {
            addCriterion("sync_date <", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateLessThanOrEqualTo(Date value) {
            addCriterion("sync_date <=", value, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateIn(List<Date> values) {
            addCriterion("sync_date in", values, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotIn(List<Date> values) {
            addCriterion("sync_date not in", values, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateBetween(Date value1, Date value2) {
            addCriterion("sync_date between", value1, value2, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDateNotBetween(Date value1, Date value2) {
            addCriterion("sync_date not between", value1, value2, "syncDate");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagIsNull() {
            addCriterion("sync_del_flag is null");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagIsNotNull() {
            addCriterion("sync_del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagEqualTo(String value) {
            addCriterion("sync_del_flag =", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotEqualTo(String value) {
            addCriterion("sync_del_flag <>", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagGreaterThan(String value) {
            addCriterion("sync_del_flag >", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("sync_del_flag >=", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLessThan(String value) {
            addCriterion("sync_del_flag <", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLessThanOrEqualTo(String value) {
            addCriterion("sync_del_flag <=", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLike(String value) {
            addCriterion("sync_del_flag like", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotLike(String value) {
            addCriterion("sync_del_flag not like", value, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagIn(List<String> values) {
            addCriterion("sync_del_flag in", values, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotIn(List<String> values) {
            addCriterion("sync_del_flag not in", values, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagBetween(String value1, String value2) {
            addCriterion("sync_del_flag between", value1, value2, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagNotBetween(String value1, String value2) {
            addCriterion("sync_del_flag not between", value1, value2, "syncDelFlag");
            return (Criteria) this;
        }

        public Criteria andSyncIdLikeInsensitive(String value) {
            addCriterion("upper(sync_id) like", value.toUpperCase(), "syncId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLikeInsensitive(String value) {
            addCriterion("upper(department_id) like", value.toUpperCase(), "departmentId");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andIdentityLikeInsensitive(String value) {
            addCriterion("upper(identity) like", value.toUpperCase(), "identity");
            return (Criteria) this;
        }

        public Criteria andAccountLikeInsensitive(String value) {
            addCriterion("upper(account) like", value.toUpperCase(), "account");
            return (Criteria) this;
        }

        public Criteria andTitleIdLikeInsensitive(String value) {
            addCriterion("upper(title_id) like", value.toUpperCase(), "titleId");
            return (Criteria) this;
        }

        public Criteria andNoLikeInsensitive(String value) {
            addCriterion("upper(no) like", value.toUpperCase(), "no");
            return (Criteria) this;
        }

        public Criteria andPhoneLikeInsensitive(String value) {
            addCriterion("upper(phone) like", value.toUpperCase(), "phone");
            return (Criteria) this;
        }

        public Criteria andMobileLikeInsensitive(String value) {
            addCriterion("upper(mobile) like", value.toUpperCase(), "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLikeInsensitive(String value) {
            addCriterion("upper(head_url) like", value.toUpperCase(), "headUrl");
            return (Criteria) this;
        }

        public Criteria andZcLikeInsensitive(String value) {
            addCriterion("upper(zc) like", value.toUpperCase(), "zc");
            return (Criteria) this;
        }

        public Criteria andSfzrjsLikeInsensitive(String value) {
            addCriterion("upper(sfzrjs) like", value.toUpperCase(), "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andJgLikeInsensitive(String value) {
            addCriterion("upper(jg) like", value.toUpperCase(), "jg");
            return (Criteria) this;
        }

        public Criteria andZzmmLikeInsensitive(String value) {
            addCriterion("upper(zzmm) like", value.toUpperCase(), "zzmm");
            return (Criteria) this;
        }

        public Criteria andRjxkLikeInsensitive(String value) {
            addCriterion("upper(rjxk) like", value.toUpperCase(), "rjxk");
            return (Criteria) this;
        }

        public Criteria andXqLikeInsensitive(String value) {
            addCriterion("upper(xq) like", value.toUpperCase(), "xq");
            return (Criteria) this;
        }

        public Criteria andZgxlLikeInsensitive(String value) {
            addCriterion("upper(zgxl) like", value.toUpperCase(), "zgxl");
            return (Criteria) this;
        }

        public Criteria andSfhqLikeInsensitive(String value) {
            addCriterion("upper(sfhq) like", value.toUpperCase(), "sfhq");
            return (Criteria) this;
        }

        public Criteria andSfbzrLikeInsensitive(String value) {
            addCriterion("upper(sfbzr) like", value.toUpperCase(), "sfbzr");
            return (Criteria) this;
        }

        public Criteria andWyyzLikeInsensitive(String value) {
            addCriterion("upper(wyyz) like", value.toUpperCase(), "wyyz");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflLikeInsensitive(String value) {
            addCriterion("upper(zyjsgwfl) like", value.toUpperCase(), "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andEventLikeInsensitive(String value) {
            addCriterion("upper(event) like", value.toUpperCase(), "event");
            return (Criteria) this;
        }

        public Criteria andSourceLikeInsensitive(String value) {
            addCriterion("upper(source) like", value.toUpperCase(), "source");
            return (Criteria) this;
        }

        public Criteria andSyncDelFlagLikeInsensitive(String value) {
            addCriterion("upper(sync_del_flag) like", value.toUpperCase(), "syncDelFlag");
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