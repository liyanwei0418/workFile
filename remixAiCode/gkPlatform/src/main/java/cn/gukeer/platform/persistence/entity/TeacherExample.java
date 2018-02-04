package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class TeacherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeacherExample() {
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

        public Criteria andQuanPinIsNull() {
            addCriterion("quan_pin is null");
            return (Criteria) this;
        }

        public Criteria andQuanPinIsNotNull() {
            addCriterion("quan_pin is not null");
            return (Criteria) this;
        }

        public Criteria andQuanPinEqualTo(String value) {
            addCriterion("quan_pin =", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinNotEqualTo(String value) {
            addCriterion("quan_pin <>", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinGreaterThan(String value) {
            addCriterion("quan_pin >", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinGreaterThanOrEqualTo(String value) {
            addCriterion("quan_pin >=", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinLessThan(String value) {
            addCriterion("quan_pin <", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinLessThanOrEqualTo(String value) {
            addCriterion("quan_pin <=", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinLike(String value) {
            addCriterion("quan_pin like", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinNotLike(String value) {
            addCriterion("quan_pin not like", value, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinIn(List<String> values) {
            addCriterion("quan_pin in", values, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinNotIn(List<String> values) {
            addCriterion("quan_pin not in", values, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinBetween(String value1, String value2) {
            addCriterion("quan_pin between", value1, value2, "quanPin");
            return (Criteria) this;
        }

        public Criteria andQuanPinNotBetween(String value1, String value2) {
            addCriterion("quan_pin not between", value1, value2, "quanPin");
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

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("role_id like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("role_id not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
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

        public Criteria andStartWorkEqualTo(String value) {
            addCriterion("start_work =", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotEqualTo(String value) {
            addCriterion("start_work <>", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkGreaterThan(String value) {
            addCriterion("start_work >", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkGreaterThanOrEqualTo(String value) {
            addCriterion("start_work >=", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkLessThan(String value) {
            addCriterion("start_work <", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkLessThanOrEqualTo(String value) {
            addCriterion("start_work <=", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkLike(String value) {
            addCriterion("start_work like", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotLike(String value) {
            addCriterion("start_work not like", value, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkIn(List<String> values) {
            addCriterion("start_work in", values, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotIn(List<String> values) {
            addCriterion("start_work not in", values, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkBetween(String value1, String value2) {
            addCriterion("start_work between", value1, value2, "startWork");
            return (Criteria) this;
        }

        public Criteria andStartWorkNotBetween(String value1, String value2) {
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

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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

        public Criteria andHighTimeEqualTo(String value) {
            addCriterion("high_time =", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotEqualTo(String value) {
            addCriterion("high_time <>", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeGreaterThan(String value) {
            addCriterion("high_time >", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeGreaterThanOrEqualTo(String value) {
            addCriterion("high_time >=", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeLessThan(String value) {
            addCriterion("high_time <", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeLessThanOrEqualTo(String value) {
            addCriterion("high_time <=", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeLike(String value) {
            addCriterion("high_time like", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotLike(String value) {
            addCriterion("high_time not like", value, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeIn(List<String> values) {
            addCriterion("high_time in", values, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotIn(List<String> values) {
            addCriterion("high_time not in", values, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeBetween(String value1, String value2) {
            addCriterion("high_time between", value1, value2, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighTimeNotBetween(String value1, String value2) {
            addCriterion("high_time not between", value1, value2, "highTime");
            return (Criteria) this;
        }

        public Criteria andHighJobIsNull() {
            addCriterion("high_job is null");
            return (Criteria) this;
        }

        public Criteria andHighJobIsNotNull() {
            addCriterion("high_job is not null");
            return (Criteria) this;
        }

        public Criteria andHighJobEqualTo(String value) {
            addCriterion("high_job =", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobNotEqualTo(String value) {
            addCriterion("high_job <>", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobGreaterThan(String value) {
            addCriterion("high_job >", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobGreaterThanOrEqualTo(String value) {
            addCriterion("high_job >=", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobLessThan(String value) {
            addCriterion("high_job <", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobLessThanOrEqualTo(String value) {
            addCriterion("high_job <=", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobLike(String value) {
            addCriterion("high_job like", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobNotLike(String value) {
            addCriterion("high_job not like", value, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobIn(List<String> values) {
            addCriterion("high_job in", values, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobNotIn(List<String> values) {
            addCriterion("high_job not in", values, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobBetween(String value1, String value2) {
            addCriterion("high_job between", value1, value2, "highJob");
            return (Criteria) this;
        }

        public Criteria andHighJobNotBetween(String value1, String value2) {
            addCriterion("high_job not between", value1, value2, "highJob");
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

        public Criteria andPzxxIsNull() {
            addCriterion("pzxx is null");
            return (Criteria) this;
        }

        public Criteria andPzxxIsNotNull() {
            addCriterion("pzxx is not null");
            return (Criteria) this;
        }

        public Criteria andPzxxEqualTo(String value) {
            addCriterion("pzxx =", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxNotEqualTo(String value) {
            addCriterion("pzxx <>", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxGreaterThan(String value) {
            addCriterion("pzxx >", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxGreaterThanOrEqualTo(String value) {
            addCriterion("pzxx >=", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxLessThan(String value) {
            addCriterion("pzxx <", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxLessThanOrEqualTo(String value) {
            addCriterion("pzxx <=", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxLike(String value) {
            addCriterion("pzxx like", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxNotLike(String value) {
            addCriterion("pzxx not like", value, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxIn(List<String> values) {
            addCriterion("pzxx in", values, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxNotIn(List<String> values) {
            addCriterion("pzxx not in", values, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxBetween(String value1, String value2) {
            addCriterion("pzxx between", value1, value2, "pzxx");
            return (Criteria) this;
        }

        public Criteria andPzxxNotBetween(String value1, String value2) {
            addCriterion("pzxx not between", value1, value2, "pzxx");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andGgjsjbIsNull() {
            addCriterion("ggjsjb is null");
            return (Criteria) this;
        }

        public Criteria andGgjsjbIsNotNull() {
            addCriterion("ggjsjb is not null");
            return (Criteria) this;
        }

        public Criteria andGgjsjbEqualTo(String value) {
            addCriterion("ggjsjb =", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbNotEqualTo(String value) {
            addCriterion("ggjsjb <>", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbGreaterThan(String value) {
            addCriterion("ggjsjb >", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbGreaterThanOrEqualTo(String value) {
            addCriterion("ggjsjb >=", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbLessThan(String value) {
            addCriterion("ggjsjb <", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbLessThanOrEqualTo(String value) {
            addCriterion("ggjsjb <=", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbLike(String value) {
            addCriterion("ggjsjb like", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbNotLike(String value) {
            addCriterion("ggjsjb not like", value, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbIn(List<String> values) {
            addCriterion("ggjsjb in", values, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbNotIn(List<String> values) {
            addCriterion("ggjsjb not in", values, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbBetween(String value1, String value2) {
            addCriterion("ggjsjb between", value1, value2, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andGgjsjbNotBetween(String value1, String value2) {
            addCriterion("ggjsjb not between", value1, value2, "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andHtkssjIsNull() {
            addCriterion("htkssj is null");
            return (Criteria) this;
        }

        public Criteria andHtkssjIsNotNull() {
            addCriterion("htkssj is not null");
            return (Criteria) this;
        }

        public Criteria andHtkssjEqualTo(String value) {
            addCriterion("htkssj =", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjNotEqualTo(String value) {
            addCriterion("htkssj <>", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjGreaterThan(String value) {
            addCriterion("htkssj >", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjGreaterThanOrEqualTo(String value) {
            addCriterion("htkssj >=", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjLessThan(String value) {
            addCriterion("htkssj <", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjLessThanOrEqualTo(String value) {
            addCriterion("htkssj <=", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjLike(String value) {
            addCriterion("htkssj like", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjNotLike(String value) {
            addCriterion("htkssj not like", value, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjIn(List<String> values) {
            addCriterion("htkssj in", values, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjNotIn(List<String> values) {
            addCriterion("htkssj not in", values, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjBetween(String value1, String value2) {
            addCriterion("htkssj between", value1, value2, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtkssjNotBetween(String value1, String value2) {
            addCriterion("htkssj not between", value1, value2, "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjIsNull() {
            addCriterion("htjssj is null");
            return (Criteria) this;
        }

        public Criteria andHtjssjIsNotNull() {
            addCriterion("htjssj is not null");
            return (Criteria) this;
        }

        public Criteria andHtjssjEqualTo(String value) {
            addCriterion("htjssj =", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjNotEqualTo(String value) {
            addCriterion("htjssj <>", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjGreaterThan(String value) {
            addCriterion("htjssj >", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjGreaterThanOrEqualTo(String value) {
            addCriterion("htjssj >=", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjLessThan(String value) {
            addCriterion("htjssj <", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjLessThanOrEqualTo(String value) {
            addCriterion("htjssj <=", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjLike(String value) {
            addCriterion("htjssj like", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjNotLike(String value) {
            addCriterion("htjssj not like", value, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjIn(List<String> values) {
            addCriterion("htjssj in", values, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjNotIn(List<String> values) {
            addCriterion("htjssj not in", values, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjBetween(String value1, String value2) {
            addCriterion("htjssj between", value1, value2, "htjssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjNotBetween(String value1, String value2) {
            addCriterion("htjssj not between", value1, value2, "htjssj");
            return (Criteria) this;
        }

        public Criteria andCymIsNull() {
            addCriterion("cym is null");
            return (Criteria) this;
        }

        public Criteria andCymIsNotNull() {
            addCriterion("cym is not null");
            return (Criteria) this;
        }

        public Criteria andCymEqualTo(String value) {
            addCriterion("cym =", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotEqualTo(String value) {
            addCriterion("cym <>", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymGreaterThan(String value) {
            addCriterion("cym >", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymGreaterThanOrEqualTo(String value) {
            addCriterion("cym >=", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymLessThan(String value) {
            addCriterion("cym <", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymLessThanOrEqualTo(String value) {
            addCriterion("cym <=", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymLike(String value) {
            addCriterion("cym like", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotLike(String value) {
            addCriterion("cym not like", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymIn(List<String> values) {
            addCriterion("cym in", values, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotIn(List<String> values) {
            addCriterion("cym not in", values, "cym");
            return (Criteria) this;
        }

        public Criteria andCymBetween(String value1, String value2) {
            addCriterion("cym between", value1, value2, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotBetween(String value1, String value2) {
            addCriterion("cym not between", value1, value2, "cym");
            return (Criteria) this;
        }

        public Criteria andJtybIsNull() {
            addCriterion("jtyb is null");
            return (Criteria) this;
        }

        public Criteria andJtybIsNotNull() {
            addCriterion("jtyb is not null");
            return (Criteria) this;
        }

        public Criteria andJtybEqualTo(String value) {
            addCriterion("jtyb =", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybNotEqualTo(String value) {
            addCriterion("jtyb <>", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybGreaterThan(String value) {
            addCriterion("jtyb >", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybGreaterThanOrEqualTo(String value) {
            addCriterion("jtyb >=", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybLessThan(String value) {
            addCriterion("jtyb <", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybLessThanOrEqualTo(String value) {
            addCriterion("jtyb <=", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybLike(String value) {
            addCriterion("jtyb like", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybNotLike(String value) {
            addCriterion("jtyb not like", value, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybIn(List<String> values) {
            addCriterion("jtyb in", values, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybNotIn(List<String> values) {
            addCriterion("jtyb not in", values, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybBetween(String value1, String value2) {
            addCriterion("jtyb between", value1, value2, "jtyb");
            return (Criteria) this;
        }

        public Criteria andJtybNotBetween(String value1, String value2) {
            addCriterion("jtyb not between", value1, value2, "jtyb");
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

        public Criteria andSalaryIsNull() {
            addCriterion("salary is null");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNotNull() {
            addCriterion("salary is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryEqualTo(String value) {
            addCriterion("salary =", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotEqualTo(String value) {
            addCriterion("salary <>", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThan(String value) {
            addCriterion("salary >", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThanOrEqualTo(String value) {
            addCriterion("salary >=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThan(String value) {
            addCriterion("salary <", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThanOrEqualTo(String value) {
            addCriterion("salary <=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLike(String value) {
            addCriterion("salary like", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotLike(String value) {
            addCriterion("salary not like", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryIn(List<String> values) {
            addCriterion("salary in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotIn(List<String> values) {
            addCriterion("salary not in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryBetween(String value1, String value2) {
            addCriterion("salary between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotBetween(String value1, String value2) {
            addCriterion("salary not between", value1, value2, "salary");
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

        public Criteria andCjgzsjIsNull() {
            addCriterion("cjgzsj is null");
            return (Criteria) this;
        }

        public Criteria andCjgzsjIsNotNull() {
            addCriterion("cjgzsj is not null");
            return (Criteria) this;
        }

        public Criteria andCjgzsjEqualTo(String value) {
            addCriterion("cjgzsj =", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjNotEqualTo(String value) {
            addCriterion("cjgzsj <>", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjGreaterThan(String value) {
            addCriterion("cjgzsj >", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjGreaterThanOrEqualTo(String value) {
            addCriterion("cjgzsj >=", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjLessThan(String value) {
            addCriterion("cjgzsj <", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjLessThanOrEqualTo(String value) {
            addCriterion("cjgzsj <=", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjLike(String value) {
            addCriterion("cjgzsj like", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjNotLike(String value) {
            addCriterion("cjgzsj not like", value, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjIn(List<String> values) {
            addCriterion("cjgzsj in", values, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjNotIn(List<String> values) {
            addCriterion("cjgzsj not in", values, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjBetween(String value1, String value2) {
            addCriterion("cjgzsj between", value1, value2, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andCjgzsjNotBetween(String value1, String value2) {
            addCriterion("cjgzsj not between", value1, value2, "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andYsbysjIsNull() {
            addCriterion("ysbysj is null");
            return (Criteria) this;
        }

        public Criteria andYsbysjIsNotNull() {
            addCriterion("ysbysj is not null");
            return (Criteria) this;
        }

        public Criteria andYsbysjEqualTo(String value) {
            addCriterion("ysbysj =", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjNotEqualTo(String value) {
            addCriterion("ysbysj <>", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjGreaterThan(String value) {
            addCriterion("ysbysj >", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjGreaterThanOrEqualTo(String value) {
            addCriterion("ysbysj >=", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjLessThan(String value) {
            addCriterion("ysbysj <", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjLessThanOrEqualTo(String value) {
            addCriterion("ysbysj <=", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjLike(String value) {
            addCriterion("ysbysj like", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjNotLike(String value) {
            addCriterion("ysbysj not like", value, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjIn(List<String> values) {
            addCriterion("ysbysj in", values, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjNotIn(List<String> values) {
            addCriterion("ysbysj not in", values, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjBetween(String value1, String value2) {
            addCriterion("ysbysj between", value1, value2, "ysbysj");
            return (Criteria) this;
        }

        public Criteria andYsbysjNotBetween(String value1, String value2) {
            addCriterion("ysbysj not between", value1, value2, "ysbysj");
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

        public Criteria andSfIsNull() {
            addCriterion("sf is null");
            return (Criteria) this;
        }

        public Criteria andSfIsNotNull() {
            addCriterion("sf is not null");
            return (Criteria) this;
        }

        public Criteria andSfEqualTo(String value) {
            addCriterion("sf =", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotEqualTo(String value) {
            addCriterion("sf <>", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThan(String value) {
            addCriterion("sf >", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThanOrEqualTo(String value) {
            addCriterion("sf >=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThan(String value) {
            addCriterion("sf <", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThanOrEqualTo(String value) {
            addCriterion("sf <=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLike(String value) {
            addCriterion("sf like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotLike(String value) {
            addCriterion("sf not like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfIn(List<String> values) {
            addCriterion("sf in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotIn(List<String> values) {
            addCriterion("sf not in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfBetween(String value1, String value2) {
            addCriterion("sf between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotBetween(String value1, String value2) {
            addCriterion("sf not between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andWyspIsNull() {
            addCriterion("wysp is null");
            return (Criteria) this;
        }

        public Criteria andWyspIsNotNull() {
            addCriterion("wysp is not null");
            return (Criteria) this;
        }

        public Criteria andWyspEqualTo(String value) {
            addCriterion("wysp =", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotEqualTo(String value) {
            addCriterion("wysp <>", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspGreaterThan(String value) {
            addCriterion("wysp >", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspGreaterThanOrEqualTo(String value) {
            addCriterion("wysp >=", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspLessThan(String value) {
            addCriterion("wysp <", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspLessThanOrEqualTo(String value) {
            addCriterion("wysp <=", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspLike(String value) {
            addCriterion("wysp like", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotLike(String value) {
            addCriterion("wysp not like", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspIn(List<String> values) {
            addCriterion("wysp in", values, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotIn(List<String> values) {
            addCriterion("wysp not in", values, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspBetween(String value1, String value2) {
            addCriterion("wysp between", value1, value2, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotBetween(String value1, String value2) {
            addCriterion("wysp not between", value1, value2, "wysp");
            return (Criteria) this;
        }

        public Criteria andZgxzIsNull() {
            addCriterion("zgxz is null");
            return (Criteria) this;
        }

        public Criteria andZgxzIsNotNull() {
            addCriterion("zgxz is not null");
            return (Criteria) this;
        }

        public Criteria andZgxzEqualTo(String value) {
            addCriterion("zgxz =", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzNotEqualTo(String value) {
            addCriterion("zgxz <>", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzGreaterThan(String value) {
            addCriterion("zgxz >", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzGreaterThanOrEqualTo(String value) {
            addCriterion("zgxz >=", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzLessThan(String value) {
            addCriterion("zgxz <", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzLessThanOrEqualTo(String value) {
            addCriterion("zgxz <=", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzLike(String value) {
            addCriterion("zgxz like", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzNotLike(String value) {
            addCriterion("zgxz not like", value, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzIn(List<String> values) {
            addCriterion("zgxz in", values, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzNotIn(List<String> values) {
            addCriterion("zgxz not in", values, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzBetween(String value1, String value2) {
            addCriterion("zgxz between", value1, value2, "zgxz");
            return (Criteria) this;
        }

        public Criteria andZgxzNotBetween(String value1, String value2) {
            addCriterion("zgxz not between", value1, value2, "zgxz");
            return (Criteria) this;
        }

        public Criteria andXwslIsNull() {
            addCriterion("xwsl is null");
            return (Criteria) this;
        }

        public Criteria andXwslIsNotNull() {
            addCriterion("xwsl is not null");
            return (Criteria) this;
        }

        public Criteria andXwslEqualTo(String value) {
            addCriterion("xwsl =", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslNotEqualTo(String value) {
            addCriterion("xwsl <>", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslGreaterThan(String value) {
            addCriterion("xwsl >", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslGreaterThanOrEqualTo(String value) {
            addCriterion("xwsl >=", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslLessThan(String value) {
            addCriterion("xwsl <", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslLessThanOrEqualTo(String value) {
            addCriterion("xwsl <=", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslLike(String value) {
            addCriterion("xwsl like", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslNotLike(String value) {
            addCriterion("xwsl not like", value, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslIn(List<String> values) {
            addCriterion("xwsl in", values, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslNotIn(List<String> values) {
            addCriterion("xwsl not in", values, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslBetween(String value1, String value2) {
            addCriterion("xwsl between", value1, value2, "xwsl");
            return (Criteria) this;
        }

        public Criteria andXwslNotBetween(String value1, String value2) {
            addCriterion("xwsl not between", value1, value2, "xwsl");
            return (Criteria) this;
        }

        public Criteria andRjxkjbIsNull() {
            addCriterion("rjxkjb is null");
            return (Criteria) this;
        }

        public Criteria andRjxkjbIsNotNull() {
            addCriterion("rjxkjb is not null");
            return (Criteria) this;
        }

        public Criteria andRjxkjbEqualTo(String value) {
            addCriterion("rjxkjb =", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbNotEqualTo(String value) {
            addCriterion("rjxkjb <>", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbGreaterThan(String value) {
            addCriterion("rjxkjb >", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbGreaterThanOrEqualTo(String value) {
            addCriterion("rjxkjb >=", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbLessThan(String value) {
            addCriterion("rjxkjb <", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbLessThanOrEqualTo(String value) {
            addCriterion("rjxkjb <=", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbLike(String value) {
            addCriterion("rjxkjb like", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbNotLike(String value) {
            addCriterion("rjxkjb not like", value, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbIn(List<String> values) {
            addCriterion("rjxkjb in", values, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbNotIn(List<String> values) {
            addCriterion("rjxkjb not in", values, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbBetween(String value1, String value2) {
            addCriterion("rjxkjb between", value1, value2, "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andRjxkjbNotBetween(String value1, String value2) {
            addCriterion("rjxkjb not between", value1, value2, "rjxkjb");
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

        public Criteria andGwflfIsNull() {
            addCriterion("gwflf is null");
            return (Criteria) this;
        }

        public Criteria andGwflfIsNotNull() {
            addCriterion("gwflf is not null");
            return (Criteria) this;
        }

        public Criteria andGwflfEqualTo(String value) {
            addCriterion("gwflf =", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfNotEqualTo(String value) {
            addCriterion("gwflf <>", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfGreaterThan(String value) {
            addCriterion("gwflf >", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfGreaterThanOrEqualTo(String value) {
            addCriterion("gwflf >=", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfLessThan(String value) {
            addCriterion("gwflf <", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfLessThanOrEqualTo(String value) {
            addCriterion("gwflf <=", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfLike(String value) {
            addCriterion("gwflf like", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfNotLike(String value) {
            addCriterion("gwflf not like", value, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfIn(List<String> values) {
            addCriterion("gwflf in", values, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfNotIn(List<String> values) {
            addCriterion("gwflf not in", values, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfBetween(String value1, String value2) {
            addCriterion("gwflf between", value1, value2, "gwflf");
            return (Criteria) this;
        }

        public Criteria andGwflfNotBetween(String value1, String value2) {
            addCriterion("gwflf not between", value1, value2, "gwflf");
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

        public Criteria andZgbyxxIsNull() {
            addCriterion("zgbyxx is null");
            return (Criteria) this;
        }

        public Criteria andZgbyxxIsNotNull() {
            addCriterion("zgbyxx is not null");
            return (Criteria) this;
        }

        public Criteria andZgbyxxEqualTo(String value) {
            addCriterion("zgbyxx =", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxNotEqualTo(String value) {
            addCriterion("zgbyxx <>", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxGreaterThan(String value) {
            addCriterion("zgbyxx >", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxGreaterThanOrEqualTo(String value) {
            addCriterion("zgbyxx >=", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxLessThan(String value) {
            addCriterion("zgbyxx <", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxLessThanOrEqualTo(String value) {
            addCriterion("zgbyxx <=", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxLike(String value) {
            addCriterion("zgbyxx like", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxNotLike(String value) {
            addCriterion("zgbyxx not like", value, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxIn(List<String> values) {
            addCriterion("zgbyxx in", values, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxNotIn(List<String> values) {
            addCriterion("zgbyxx not in", values, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxBetween(String value1, String value2) {
            addCriterion("zgbyxx between", value1, value2, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andZgbyxxNotBetween(String value1, String value2) {
            addCriterion("zgbyxx not between", value1, value2, "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andYzyIsNull() {
            addCriterion("yzy is null");
            return (Criteria) this;
        }

        public Criteria andYzyIsNotNull() {
            addCriterion("yzy is not null");
            return (Criteria) this;
        }

        public Criteria andYzyEqualTo(String value) {
            addCriterion("yzy =", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyNotEqualTo(String value) {
            addCriterion("yzy <>", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyGreaterThan(String value) {
            addCriterion("yzy >", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyGreaterThanOrEqualTo(String value) {
            addCriterion("yzy >=", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyLessThan(String value) {
            addCriterion("yzy <", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyLessThanOrEqualTo(String value) {
            addCriterion("yzy <=", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyLike(String value) {
            addCriterion("yzy like", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyNotLike(String value) {
            addCriterion("yzy not like", value, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyIn(List<String> values) {
            addCriterion("yzy in", values, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyNotIn(List<String> values) {
            addCriterion("yzy not in", values, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyBetween(String value1, String value2) {
            addCriterion("yzy between", value1, value2, "yzy");
            return (Criteria) this;
        }

        public Criteria andYzyNotBetween(String value1, String value2) {
            addCriterion("yzy not between", value1, value2, "yzy");
            return (Criteria) this;
        }

        public Criteria andPzsjIsNull() {
            addCriterion("pzsj is null");
            return (Criteria) this;
        }

        public Criteria andPzsjIsNotNull() {
            addCriterion("pzsj is not null");
            return (Criteria) this;
        }

        public Criteria andPzsjEqualTo(String value) {
            addCriterion("pzsj =", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjNotEqualTo(String value) {
            addCriterion("pzsj <>", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjGreaterThan(String value) {
            addCriterion("pzsj >", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjGreaterThanOrEqualTo(String value) {
            addCriterion("pzsj >=", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjLessThan(String value) {
            addCriterion("pzsj <", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjLessThanOrEqualTo(String value) {
            addCriterion("pzsj <=", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjLike(String value) {
            addCriterion("pzsj like", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjNotLike(String value) {
            addCriterion("pzsj not like", value, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjIn(List<String> values) {
            addCriterion("pzsj in", values, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjNotIn(List<String> values) {
            addCriterion("pzsj not in", values, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjBetween(String value1, String value2) {
            addCriterion("pzsj between", value1, value2, "pzsj");
            return (Criteria) this;
        }

        public Criteria andPzsjNotBetween(String value1, String value2) {
            addCriterion("pzsj not between", value1, value2, "pzsj");
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

        public Criteria andLwxsjEqualTo(String value) {
            addCriterion("lwxsj =", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotEqualTo(String value) {
            addCriterion("lwxsj <>", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjGreaterThan(String value) {
            addCriterion("lwxsj >", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjGreaterThanOrEqualTo(String value) {
            addCriterion("lwxsj >=", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjLessThan(String value) {
            addCriterion("lwxsj <", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjLessThanOrEqualTo(String value) {
            addCriterion("lwxsj <=", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjLike(String value) {
            addCriterion("lwxsj like", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotLike(String value) {
            addCriterion("lwxsj not like", value, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjIn(List<String> values) {
            addCriterion("lwxsj in", values, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotIn(List<String> values) {
            addCriterion("lwxsj not in", values, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjBetween(String value1, String value2) {
            addCriterion("lwxsj between", value1, value2, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjNotBetween(String value1, String value2) {
            addCriterion("lwxsj not between", value1, value2, "lwxsj");
            return (Criteria) this;
        }

        public Criteria andZzdhIsNull() {
            addCriterion("zzdh is null");
            return (Criteria) this;
        }

        public Criteria andZzdhIsNotNull() {
            addCriterion("zzdh is not null");
            return (Criteria) this;
        }

        public Criteria andZzdhEqualTo(String value) {
            addCriterion("zzdh =", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhNotEqualTo(String value) {
            addCriterion("zzdh <>", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhGreaterThan(String value) {
            addCriterion("zzdh >", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhGreaterThanOrEqualTo(String value) {
            addCriterion("zzdh >=", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhLessThan(String value) {
            addCriterion("zzdh <", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhLessThanOrEqualTo(String value) {
            addCriterion("zzdh <=", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhLike(String value) {
            addCriterion("zzdh like", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhNotLike(String value) {
            addCriterion("zzdh not like", value, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhIn(List<String> values) {
            addCriterion("zzdh in", values, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhNotIn(List<String> values) {
            addCriterion("zzdh not in", values, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhBetween(String value1, String value2) {
            addCriterion("zzdh between", value1, value2, "zzdh");
            return (Criteria) this;
        }

        public Criteria andZzdhNotBetween(String value1, String value2) {
            addCriterion("zzdh not between", value1, value2, "zzdh");
            return (Criteria) this;
        }

        public Criteria andGzgwIsNull() {
            addCriterion("gzgw is null");
            return (Criteria) this;
        }

        public Criteria andGzgwIsNotNull() {
            addCriterion("gzgw is not null");
            return (Criteria) this;
        }

        public Criteria andGzgwEqualTo(String value) {
            addCriterion("gzgw =", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwNotEqualTo(String value) {
            addCriterion("gzgw <>", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwGreaterThan(String value) {
            addCriterion("gzgw >", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwGreaterThanOrEqualTo(String value) {
            addCriterion("gzgw >=", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwLessThan(String value) {
            addCriterion("gzgw <", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwLessThanOrEqualTo(String value) {
            addCriterion("gzgw <=", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwLike(String value) {
            addCriterion("gzgw like", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwNotLike(String value) {
            addCriterion("gzgw not like", value, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwIn(List<String> values) {
            addCriterion("gzgw in", values, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwNotIn(List<String> values) {
            addCriterion("gzgw not in", values, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwBetween(String value1, String value2) {
            addCriterion("gzgw between", value1, value2, "gzgw");
            return (Criteria) this;
        }

        public Criteria andGzgwNotBetween(String value1, String value2) {
            addCriterion("gzgw not between", value1, value2, "gzgw");
            return (Criteria) this;
        }

        public Criteria andBgsdhIsNull() {
            addCriterion("bgsdh is null");
            return (Criteria) this;
        }

        public Criteria andBgsdhIsNotNull() {
            addCriterion("bgsdh is not null");
            return (Criteria) this;
        }

        public Criteria andBgsdhEqualTo(String value) {
            addCriterion("bgsdh =", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhNotEqualTo(String value) {
            addCriterion("bgsdh <>", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhGreaterThan(String value) {
            addCriterion("bgsdh >", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhGreaterThanOrEqualTo(String value) {
            addCriterion("bgsdh >=", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhLessThan(String value) {
            addCriterion("bgsdh <", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhLessThanOrEqualTo(String value) {
            addCriterion("bgsdh <=", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhLike(String value) {
            addCriterion("bgsdh like", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhNotLike(String value) {
            addCriterion("bgsdh not like", value, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhIn(List<String> values) {
            addCriterion("bgsdh in", values, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhNotIn(List<String> values) {
            addCriterion("bgsdh not in", values, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhBetween(String value1, String value2) {
            addCriterion("bgsdh between", value1, value2, "bgsdh");
            return (Criteria) this;
        }

        public Criteria andBgsdhNotBetween(String value1, String value2) {
            addCriterion("bgsdh not between", value1, value2, "bgsdh");
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

        public Criteria andYxzIsNull() {
            addCriterion("yxz is null");
            return (Criteria) this;
        }

        public Criteria andYxzIsNotNull() {
            addCriterion("yxz is not null");
            return (Criteria) this;
        }

        public Criteria andYxzEqualTo(String value) {
            addCriterion("yxz =", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzNotEqualTo(String value) {
            addCriterion("yxz <>", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzGreaterThan(String value) {
            addCriterion("yxz >", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzGreaterThanOrEqualTo(String value) {
            addCriterion("yxz >=", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzLessThan(String value) {
            addCriterion("yxz <", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzLessThanOrEqualTo(String value) {
            addCriterion("yxz <=", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzLike(String value) {
            addCriterion("yxz like", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzNotLike(String value) {
            addCriterion("yxz not like", value, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzIn(List<String> values) {
            addCriterion("yxz in", values, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzNotIn(List<String> values) {
            addCriterion("yxz not in", values, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzBetween(String value1, String value2) {
            addCriterion("yxz between", value1, value2, "yxz");
            return (Criteria) this;
        }

        public Criteria andYxzNotBetween(String value1, String value2) {
            addCriterion("yxz not between", value1, value2, "yxz");
            return (Criteria) this;
        }

        public Criteria andZgxwIsNull() {
            addCriterion("zgxw is null");
            return (Criteria) this;
        }

        public Criteria andZgxwIsNotNull() {
            addCriterion("zgxw is not null");
            return (Criteria) this;
        }

        public Criteria andZgxwEqualTo(String value) {
            addCriterion("zgxw =", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwNotEqualTo(String value) {
            addCriterion("zgxw <>", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwGreaterThan(String value) {
            addCriterion("zgxw >", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwGreaterThanOrEqualTo(String value) {
            addCriterion("zgxw >=", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwLessThan(String value) {
            addCriterion("zgxw <", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwLessThanOrEqualTo(String value) {
            addCriterion("zgxw <=", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwLike(String value) {
            addCriterion("zgxw like", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwNotLike(String value) {
            addCriterion("zgxw not like", value, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwIn(List<String> values) {
            addCriterion("zgxw in", values, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwNotIn(List<String> values) {
            addCriterion("zgxw not in", values, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwBetween(String value1, String value2) {
            addCriterion("zgxw between", value1, value2, "zgxw");
            return (Criteria) this;
        }

        public Criteria andZgxwNotBetween(String value1, String value2) {
            addCriterion("zgxw not between", value1, value2, "zgxw");
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

        public Criteria andSzjbIsNull() {
            addCriterion("szjb is null");
            return (Criteria) this;
        }

        public Criteria andSzjbIsNotNull() {
            addCriterion("szjb is not null");
            return (Criteria) this;
        }

        public Criteria andSzjbEqualTo(String value) {
            addCriterion("szjb =", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbNotEqualTo(String value) {
            addCriterion("szjb <>", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbGreaterThan(String value) {
            addCriterion("szjb >", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbGreaterThanOrEqualTo(String value) {
            addCriterion("szjb >=", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbLessThan(String value) {
            addCriterion("szjb <", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbLessThanOrEqualTo(String value) {
            addCriterion("szjb <=", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbLike(String value) {
            addCriterion("szjb like", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbNotLike(String value) {
            addCriterion("szjb not like", value, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbIn(List<String> values) {
            addCriterion("szjb in", values, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbNotIn(List<String> values) {
            addCriterion("szjb not in", values, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbBetween(String value1, String value2) {
            addCriterion("szjb between", value1, value2, "szjb");
            return (Criteria) this;
        }

        public Criteria andSzjbNotBetween(String value1, String value2) {
            addCriterion("szjb not between", value1, value2, "szjb");
            return (Criteria) this;
        }

        public Criteria andGzgwfIsNull() {
            addCriterion("gzgwf is null");
            return (Criteria) this;
        }

        public Criteria andGzgwfIsNotNull() {
            addCriterion("gzgwf is not null");
            return (Criteria) this;
        }

        public Criteria andGzgwfEqualTo(String value) {
            addCriterion("gzgwf =", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfNotEqualTo(String value) {
            addCriterion("gzgwf <>", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfGreaterThan(String value) {
            addCriterion("gzgwf >", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfGreaterThanOrEqualTo(String value) {
            addCriterion("gzgwf >=", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfLessThan(String value) {
            addCriterion("gzgwf <", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfLessThanOrEqualTo(String value) {
            addCriterion("gzgwf <=", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfLike(String value) {
            addCriterion("gzgwf like", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfNotLike(String value) {
            addCriterion("gzgwf not like", value, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfIn(List<String> values) {
            addCriterion("gzgwf in", values, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfNotIn(List<String> values) {
            addCriterion("gzgwf not in", values, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfBetween(String value1, String value2) {
            addCriterion("gzgwf between", value1, value2, "gzgwf");
            return (Criteria) this;
        }

        public Criteria andGzgwfNotBetween(String value1, String value2) {
            addCriterion("gzgwf not between", value1, value2, "gzgwf");
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

        public Criteria andDepartmentIdLikeInsensitive(String value) {
            addCriterion("upper(department_id) like", value.toUpperCase(), "departmentId");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andQuanPinLikeInsensitive(String value) {
            addCriterion("upper(quan_pin) like", value.toUpperCase(), "quanPin");
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

        public Criteria andRoleIdLikeInsensitive(String value) {
            addCriterion("upper(role_id) like", value.toUpperCase(), "roleId");
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

        public Criteria andStartWorkLikeInsensitive(String value) {
            addCriterion("upper(start_work) like", value.toUpperCase(), "startWork");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLikeInsensitive(String value) {
            addCriterion("upper(head_url) like", value.toUpperCase(), "headUrl");
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

        public Criteria andRemarksLikeInsensitive(String value) {
            addCriterion("upper(remarks) like", value.toUpperCase(), "remarks");
            return (Criteria) this;
        }

        public Criteria andHighTimeLikeInsensitive(String value) {
            addCriterion("upper(high_time) like", value.toUpperCase(), "highTime");
            return (Criteria) this;
        }

        public Criteria andHighJobLikeInsensitive(String value) {
            addCriterion("upper(high_job) like", value.toUpperCase(), "highJob");
            return (Criteria) this;
        }

        public Criteria andZcLikeInsensitive(String value) {
            addCriterion("upper(zc) like", value.toUpperCase(), "zc");
            return (Criteria) this;
        }

        public Criteria andPzxxLikeInsensitive(String value) {
            addCriterion("upper(pzxx) like", value.toUpperCase(), "pzxx");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andGgjsjbLikeInsensitive(String value) {
            addCriterion("upper(ggjsjb) like", value.toUpperCase(), "ggjsjb");
            return (Criteria) this;
        }

        public Criteria andHtkssjLikeInsensitive(String value) {
            addCriterion("upper(htkssj) like", value.toUpperCase(), "htkssj");
            return (Criteria) this;
        }

        public Criteria andHtjssjLikeInsensitive(String value) {
            addCriterion("upper(htjssj) like", value.toUpperCase(), "htjssj");
            return (Criteria) this;
        }

        public Criteria andCymLikeInsensitive(String value) {
            addCriterion("upper(cym) like", value.toUpperCase(), "cym");
            return (Criteria) this;
        }

        public Criteria andJtybLikeInsensitive(String value) {
            addCriterion("upper(jtyb) like", value.toUpperCase(), "jtyb");
            return (Criteria) this;
        }

        public Criteria andSfzrjsLikeInsensitive(String value) {
            addCriterion("upper(sfzrjs) like", value.toUpperCase(), "sfzrjs");
            return (Criteria) this;
        }

        public Criteria andSalaryLikeInsensitive(String value) {
            addCriterion("upper(salary) like", value.toUpperCase(), "salary");
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

        public Criteria andCjgzsjLikeInsensitive(String value) {
            addCriterion("upper(cjgzsj) like", value.toUpperCase(), "cjgzsj");
            return (Criteria) this;
        }

        public Criteria andYsbysjLikeInsensitive(String value) {
            addCriterion("upper(ysbysj) like", value.toUpperCase(), "ysbysj");
            return (Criteria) this;
        }

        public Criteria andRjxkLikeInsensitive(String value) {
            addCriterion("upper(rjxk) like", value.toUpperCase(), "rjxk");
            return (Criteria) this;
        }

        public Criteria andSfLikeInsensitive(String value) {
            addCriterion("upper(sf) like", value.toUpperCase(), "sf");
            return (Criteria) this;
        }

        public Criteria andWyspLikeInsensitive(String value) {
            addCriterion("upper(wysp) like", value.toUpperCase(), "wysp");
            return (Criteria) this;
        }

        public Criteria andZgxzLikeInsensitive(String value) {
            addCriterion("upper(zgxz) like", value.toUpperCase(), "zgxz");
            return (Criteria) this;
        }

        public Criteria andXwslLikeInsensitive(String value) {
            addCriterion("upper(xwsl) like", value.toUpperCase(), "xwsl");
            return (Criteria) this;
        }

        public Criteria andRjxkjbLikeInsensitive(String value) {
            addCriterion("upper(rjxkjb) like", value.toUpperCase(), "rjxkjb");
            return (Criteria) this;
        }

        public Criteria andXqLikeInsensitive(String value) {
            addCriterion("upper(xq) like", value.toUpperCase(), "xq");
            return (Criteria) this;
        }

        public Criteria andGwflfLikeInsensitive(String value) {
            addCriterion("upper(gwflf) like", value.toUpperCase(), "gwflf");
            return (Criteria) this;
        }

        public Criteria andZgxlLikeInsensitive(String value) {
            addCriterion("upper(zgxl) like", value.toUpperCase(), "zgxl");
            return (Criteria) this;
        }

        public Criteria andZgbyxxLikeInsensitive(String value) {
            addCriterion("upper(zgbyxx) like", value.toUpperCase(), "zgbyxx");
            return (Criteria) this;
        }

        public Criteria andYzyLikeInsensitive(String value) {
            addCriterion("upper(yzy) like", value.toUpperCase(), "yzy");
            return (Criteria) this;
        }

        public Criteria andPzsjLikeInsensitive(String value) {
            addCriterion("upper(pzsj) like", value.toUpperCase(), "pzsj");
            return (Criteria) this;
        }

        public Criteria andLwxsjLikeInsensitive(String value) {
            addCriterion("upper(lwxsj) like", value.toUpperCase(), "lwxsj");
            return (Criteria) this;
        }

        public Criteria andZzdhLikeInsensitive(String value) {
            addCriterion("upper(zzdh) like", value.toUpperCase(), "zzdh");
            return (Criteria) this;
        }

        public Criteria andGzgwLikeInsensitive(String value) {
            addCriterion("upper(gzgw) like", value.toUpperCase(), "gzgw");
            return (Criteria) this;
        }

        public Criteria andBgsdhLikeInsensitive(String value) {
            addCriterion("upper(bgsdh) like", value.toUpperCase(), "bgsdh");
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

        public Criteria andYxzLikeInsensitive(String value) {
            addCriterion("upper(yxz) like", value.toUpperCase(), "yxz");
            return (Criteria) this;
        }

        public Criteria andZgxwLikeInsensitive(String value) {
            addCriterion("upper(zgxw) like", value.toUpperCase(), "zgxw");
            return (Criteria) this;
        }

        public Criteria andZyjsgwflLikeInsensitive(String value) {
            addCriterion("upper(zyjsgwfl) like", value.toUpperCase(), "zyjsgwfl");
            return (Criteria) this;
        }

        public Criteria andSzjbLikeInsensitive(String value) {
            addCriterion("upper(szjb) like", value.toUpperCase(), "szjb");
            return (Criteria) this;
        }

        public Criteria andGzgwfLikeInsensitive(String value) {
            addCriterion("upper(gzgwf) like", value.toUpperCase(), "gzgwf");
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