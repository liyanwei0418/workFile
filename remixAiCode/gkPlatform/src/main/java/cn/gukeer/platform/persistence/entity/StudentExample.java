package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andXsxmIsNull() {
            addCriterion("xsxm is null");
            return (Criteria) this;
        }

        public Criteria andXsxmIsNotNull() {
            addCriterion("xsxm is not null");
            return (Criteria) this;
        }

        public Criteria andXsxmEqualTo(String value) {
            addCriterion("xsxm =", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmNotEqualTo(String value) {
            addCriterion("xsxm <>", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmGreaterThan(String value) {
            addCriterion("xsxm >", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmGreaterThanOrEqualTo(String value) {
            addCriterion("xsxm >=", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmLessThan(String value) {
            addCriterion("xsxm <", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmLessThanOrEqualTo(String value) {
            addCriterion("xsxm <=", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmLike(String value) {
            addCriterion("xsxm like", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmNotLike(String value) {
            addCriterion("xsxm not like", value, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmIn(List<String> values) {
            addCriterion("xsxm in", values, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmNotIn(List<String> values) {
            addCriterion("xsxm not in", values, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmBetween(String value1, String value2) {
            addCriterion("xsxm between", value1, value2, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXsxmNotBetween(String value1, String value2) {
            addCriterion("xsxm not between", value1, value2, "xsxm");
            return (Criteria) this;
        }

        public Criteria andXmpyIsNull() {
            addCriterion("xmpy is null");
            return (Criteria) this;
        }

        public Criteria andXmpyIsNotNull() {
            addCriterion("xmpy is not null");
            return (Criteria) this;
        }

        public Criteria andXmpyEqualTo(String value) {
            addCriterion("xmpy =", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyNotEqualTo(String value) {
            addCriterion("xmpy <>", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyGreaterThan(String value) {
            addCriterion("xmpy >", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyGreaterThanOrEqualTo(String value) {
            addCriterion("xmpy >=", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyLessThan(String value) {
            addCriterion("xmpy <", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyLessThanOrEqualTo(String value) {
            addCriterion("xmpy <=", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyLike(String value) {
            addCriterion("xmpy like", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyNotLike(String value) {
            addCriterion("xmpy not like", value, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyIn(List<String> values) {
            addCriterion("xmpy in", values, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyNotIn(List<String> values) {
            addCriterion("xmpy not in", values, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyBetween(String value1, String value2) {
            addCriterion("xmpy between", value1, value2, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXmpyNotBetween(String value1, String value2) {
            addCriterion("xmpy not between", value1, value2, "xmpy");
            return (Criteria) this;
        }

        public Criteria andXszpIsNull() {
            addCriterion("xszp is null");
            return (Criteria) this;
        }

        public Criteria andXszpIsNotNull() {
            addCriterion("xszp is not null");
            return (Criteria) this;
        }

        public Criteria andXszpEqualTo(String value) {
            addCriterion("xszp =", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpNotEqualTo(String value) {
            addCriterion("xszp <>", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpGreaterThan(String value) {
            addCriterion("xszp >", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpGreaterThanOrEqualTo(String value) {
            addCriterion("xszp >=", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpLessThan(String value) {
            addCriterion("xszp <", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpLessThanOrEqualTo(String value) {
            addCriterion("xszp <=", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpLike(String value) {
            addCriterion("xszp like", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpNotLike(String value) {
            addCriterion("xszp not like", value, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpIn(List<String> values) {
            addCriterion("xszp in", values, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpNotIn(List<String> values) {
            addCriterion("xszp not in", values, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpBetween(String value1, String value2) {
            addCriterion("xszp between", value1, value2, "xszp");
            return (Criteria) this;
        }

        public Criteria andXszpNotBetween(String value1, String value2) {
            addCriterion("xszp not between", value1, value2, "xszp");
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

        public Criteria andCsrqIsNull() {
            addCriterion("csrq is null");
            return (Criteria) this;
        }

        public Criteria andCsrqIsNotNull() {
            addCriterion("csrq is not null");
            return (Criteria) this;
        }

        public Criteria andCsrqEqualTo(Long value) {
            addCriterion("csrq =", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotEqualTo(Long value) {
            addCriterion("csrq <>", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqGreaterThan(Long value) {
            addCriterion("csrq >", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqGreaterThanOrEqualTo(Long value) {
            addCriterion("csrq >=", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLessThan(Long value) {
            addCriterion("csrq <", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLessThanOrEqualTo(Long value) {
            addCriterion("csrq <=", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqIn(List<Long> values) {
            addCriterion("csrq in", values, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotIn(List<Long> values) {
            addCriterion("csrq not in", values, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqBetween(Long value1, Long value2) {
            addCriterion("csrq between", value1, value2, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotBetween(Long value1, Long value2) {
            addCriterion("csrq not between", value1, value2, "csrq");
            return (Criteria) this;
        }

        public Criteria andRxrqIsNull() {
            addCriterion("rxrq is null");
            return (Criteria) this;
        }

        public Criteria andRxrqIsNotNull() {
            addCriterion("rxrq is not null");
            return (Criteria) this;
        }

        public Criteria andRxrqEqualTo(Long value) {
            addCriterion("rxrq =", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotEqualTo(Long value) {
            addCriterion("rxrq <>", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqGreaterThan(Long value) {
            addCriterion("rxrq >", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqGreaterThanOrEqualTo(Long value) {
            addCriterion("rxrq >=", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqLessThan(Long value) {
            addCriterion("rxrq <", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqLessThanOrEqualTo(Long value) {
            addCriterion("rxrq <=", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqIn(List<Long> values) {
            addCriterion("rxrq in", values, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotIn(List<Long> values) {
            addCriterion("rxrq not in", values, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqBetween(Long value1, Long value2) {
            addCriterion("rxrq between", value1, value2, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotBetween(Long value1, Long value2) {
            addCriterion("rxrq not between", value1, value2, "rxrq");
            return (Criteria) this;
        }

        public Criteria andXsxbIsNull() {
            addCriterion("xsxb is null");
            return (Criteria) this;
        }

        public Criteria andXsxbIsNotNull() {
            addCriterion("xsxb is not null");
            return (Criteria) this;
        }

        public Criteria andXsxbEqualTo(Integer value) {
            addCriterion("xsxb =", value, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbNotEqualTo(Integer value) {
            addCriterion("xsxb <>", value, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbGreaterThan(Integer value) {
            addCriterion("xsxb >", value, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbGreaterThanOrEqualTo(Integer value) {
            addCriterion("xsxb >=", value, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbLessThan(Integer value) {
            addCriterion("xsxb <", value, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbLessThanOrEqualTo(Integer value) {
            addCriterion("xsxb <=", value, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbIn(List<Integer> values) {
            addCriterion("xsxb in", values, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbNotIn(List<Integer> values) {
            addCriterion("xsxb not in", values, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbBetween(Integer value1, Integer value2) {
            addCriterion("xsxb between", value1, value2, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXsxbNotBetween(Integer value1, Integer value2) {
            addCriterion("xsxb not between", value1, value2, "xsxb");
            return (Criteria) this;
        }

        public Criteria andXssgIsNull() {
            addCriterion("xssg is null");
            return (Criteria) this;
        }

        public Criteria andXssgIsNotNull() {
            addCriterion("xssg is not null");
            return (Criteria) this;
        }

        public Criteria andXssgEqualTo(String value) {
            addCriterion("xssg =", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgNotEqualTo(String value) {
            addCriterion("xssg <>", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgGreaterThan(String value) {
            addCriterion("xssg >", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgGreaterThanOrEqualTo(String value) {
            addCriterion("xssg >=", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgLessThan(String value) {
            addCriterion("xssg <", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgLessThanOrEqualTo(String value) {
            addCriterion("xssg <=", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgLike(String value) {
            addCriterion("xssg like", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgNotLike(String value) {
            addCriterion("xssg not like", value, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgIn(List<String> values) {
            addCriterion("xssg in", values, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgNotIn(List<String> values) {
            addCriterion("xssg not in", values, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgBetween(String value1, String value2) {
            addCriterion("xssg between", value1, value2, "xssg");
            return (Criteria) this;
        }

        public Criteria andXssgNotBetween(String value1, String value2) {
            addCriterion("xssg not between", value1, value2, "xssg");
            return (Criteria) this;
        }

        public Criteria andXdIsNull() {
            addCriterion("xd is null");
            return (Criteria) this;
        }

        public Criteria andXdIsNotNull() {
            addCriterion("xd is not null");
            return (Criteria) this;
        }

        public Criteria andXdEqualTo(String value) {
            addCriterion("xd =", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotEqualTo(String value) {
            addCriterion("xd <>", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdGreaterThan(String value) {
            addCriterion("xd >", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdGreaterThanOrEqualTo(String value) {
            addCriterion("xd >=", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdLessThan(String value) {
            addCriterion("xd <", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdLessThanOrEqualTo(String value) {
            addCriterion("xd <=", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdLike(String value) {
            addCriterion("xd like", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotLike(String value) {
            addCriterion("xd not like", value, "xd");
            return (Criteria) this;
        }

        public Criteria andXdIn(List<String> values) {
            addCriterion("xd in", values, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotIn(List<String> values) {
            addCriterion("xd not in", values, "xd");
            return (Criteria) this;
        }

        public Criteria andXdBetween(String value1, String value2) {
            addCriterion("xd between", value1, value2, "xd");
            return (Criteria) this;
        }

        public Criteria andXdNotBetween(String value1, String value2) {
            addCriterion("xd not between", value1, value2, "xd");
            return (Criteria) this;
        }

        public Criteria andNjIsNull() {
            addCriterion("nj is null");
            return (Criteria) this;
        }

        public Criteria andNjIsNotNull() {
            addCriterion("nj is not null");
            return (Criteria) this;
        }

        public Criteria andNjEqualTo(Integer value) {
            addCriterion("nj =", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjNotEqualTo(Integer value) {
            addCriterion("nj <>", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjGreaterThan(Integer value) {
            addCriterion("nj >", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjGreaterThanOrEqualTo(Integer value) {
            addCriterion("nj >=", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjLessThan(Integer value) {
            addCriterion("nj <", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjLessThanOrEqualTo(Integer value) {
            addCriterion("nj <=", value, "nj");
            return (Criteria) this;
        }

        public Criteria andNjIn(List<Integer> values) {
            addCriterion("nj in", values, "nj");
            return (Criteria) this;
        }

        public Criteria andNjNotIn(List<Integer> values) {
            addCriterion("nj not in", values, "nj");
            return (Criteria) this;
        }

        public Criteria andNjBetween(Integer value1, Integer value2) {
            addCriterion("nj between", value1, value2, "nj");
            return (Criteria) this;
        }

        public Criteria andNjNotBetween(Integer value1, Integer value2) {
            addCriterion("nj not between", value1, value2, "nj");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("xh is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("xh is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(String value) {
            addCriterion("xh =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(String value) {
            addCriterion("xh <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(String value) {
            addCriterion("xh >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(String value) {
            addCriterion("xh >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(String value) {
            addCriterion("xh <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(String value) {
            addCriterion("xh <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLike(String value) {
            addCriterion("xh like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotLike(String value) {
            addCriterion("xh not like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<String> values) {
            addCriterion("xh in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<String> values) {
            addCriterion("xh not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(String value1, String value2) {
            addCriterion("xh between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(String value1, String value2) {
            addCriterion("xh not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXjhIsNull() {
            addCriterion("xjh is null");
            return (Criteria) this;
        }

        public Criteria andXjhIsNotNull() {
            addCriterion("xjh is not null");
            return (Criteria) this;
        }

        public Criteria andXjhEqualTo(String value) {
            addCriterion("xjh =", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhNotEqualTo(String value) {
            addCriterion("xjh <>", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhGreaterThan(String value) {
            addCriterion("xjh >", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhGreaterThanOrEqualTo(String value) {
            addCriterion("xjh >=", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhLessThan(String value) {
            addCriterion("xjh <", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhLessThanOrEqualTo(String value) {
            addCriterion("xjh <=", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhLike(String value) {
            addCriterion("xjh like", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhNotLike(String value) {
            addCriterion("xjh not like", value, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhIn(List<String> values) {
            addCriterion("xjh in", values, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhNotIn(List<String> values) {
            addCriterion("xjh not in", values, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhBetween(String value1, String value2) {
            addCriterion("xjh between", value1, value2, "xjh");
            return (Criteria) this;
        }

        public Criteria andXjhNotBetween(String value1, String value2) {
            addCriterion("xjh not between", value1, value2, "xjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhIsNull() {
            addCriterion("qgxjh is null");
            return (Criteria) this;
        }

        public Criteria andQgxjhIsNotNull() {
            addCriterion("qgxjh is not null");
            return (Criteria) this;
        }

        public Criteria andQgxjhEqualTo(String value) {
            addCriterion("qgxjh =", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhNotEqualTo(String value) {
            addCriterion("qgxjh <>", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhGreaterThan(String value) {
            addCriterion("qgxjh >", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhGreaterThanOrEqualTo(String value) {
            addCriterion("qgxjh >=", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhLessThan(String value) {
            addCriterion("qgxjh <", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhLessThanOrEqualTo(String value) {
            addCriterion("qgxjh <=", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhLike(String value) {
            addCriterion("qgxjh like", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhNotLike(String value) {
            addCriterion("qgxjh not like", value, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhIn(List<String> values) {
            addCriterion("qgxjh in", values, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhNotIn(List<String> values) {
            addCriterion("qgxjh not in", values, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhBetween(String value1, String value2) {
            addCriterion("qgxjh between", value1, value2, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhNotBetween(String value1, String value2) {
            addCriterion("qgxjh not between", value1, value2, "qgxjh");
            return (Criteria) this;
        }

        public Criteria andJyidIsNull() {
            addCriterion("jyid is null");
            return (Criteria) this;
        }

        public Criteria andJyidIsNotNull() {
            addCriterion("jyid is not null");
            return (Criteria) this;
        }

        public Criteria andJyidEqualTo(String value) {
            addCriterion("jyid =", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidNotEqualTo(String value) {
            addCriterion("jyid <>", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidGreaterThan(String value) {
            addCriterion("jyid >", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidGreaterThanOrEqualTo(String value) {
            addCriterion("jyid >=", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidLessThan(String value) {
            addCriterion("jyid <", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidLessThanOrEqualTo(String value) {
            addCriterion("jyid <=", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidLike(String value) {
            addCriterion("jyid like", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidNotLike(String value) {
            addCriterion("jyid not like", value, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidIn(List<String> values) {
            addCriterion("jyid in", values, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidNotIn(List<String> values) {
            addCriterion("jyid not in", values, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidBetween(String value1, String value2) {
            addCriterion("jyid between", value1, value2, "jyid");
            return (Criteria) this;
        }

        public Criteria andJyidNotBetween(String value1, String value2) {
            addCriterion("jyid not between", value1, value2, "jyid");
            return (Criteria) this;
        }

        public Criteria andSydIsNull() {
            addCriterion("syd is null");
            return (Criteria) this;
        }

        public Criteria andSydIsNotNull() {
            addCriterion("syd is not null");
            return (Criteria) this;
        }

        public Criteria andSydEqualTo(String value) {
            addCriterion("syd =", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydNotEqualTo(String value) {
            addCriterion("syd <>", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydGreaterThan(String value) {
            addCriterion("syd >", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydGreaterThanOrEqualTo(String value) {
            addCriterion("syd >=", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydLessThan(String value) {
            addCriterion("syd <", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydLessThanOrEqualTo(String value) {
            addCriterion("syd <=", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydLike(String value) {
            addCriterion("syd like", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydNotLike(String value) {
            addCriterion("syd not like", value, "syd");
            return (Criteria) this;
        }

        public Criteria andSydIn(List<String> values) {
            addCriterion("syd in", values, "syd");
            return (Criteria) this;
        }

        public Criteria andSydNotIn(List<String> values) {
            addCriterion("syd not in", values, "syd");
            return (Criteria) this;
        }

        public Criteria andSydBetween(String value1, String value2) {
            addCriterion("syd between", value1, value2, "syd");
            return (Criteria) this;
        }

        public Criteria andSydNotBetween(String value1, String value2) {
            addCriterion("syd not between", value1, value2, "syd");
            return (Criteria) this;
        }

        public Criteria andYxzjlxIsNull() {
            addCriterion("yxzjlx is null");
            return (Criteria) this;
        }

        public Criteria andYxzjlxIsNotNull() {
            addCriterion("yxzjlx is not null");
            return (Criteria) this;
        }

        public Criteria andYxzjlxEqualTo(Integer value) {
            addCriterion("yxzjlx =", value, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxNotEqualTo(Integer value) {
            addCriterion("yxzjlx <>", value, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxGreaterThan(Integer value) {
            addCriterion("yxzjlx >", value, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxGreaterThanOrEqualTo(Integer value) {
            addCriterion("yxzjlx >=", value, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxLessThan(Integer value) {
            addCriterion("yxzjlx <", value, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxLessThanOrEqualTo(Integer value) {
            addCriterion("yxzjlx <=", value, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxIn(List<Integer> values) {
            addCriterion("yxzjlx in", values, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxNotIn(List<Integer> values) {
            addCriterion("yxzjlx not in", values, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxBetween(Integer value1, Integer value2) {
            addCriterion("yxzjlx between", value1, value2, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjlxNotBetween(Integer value1, Integer value2) {
            addCriterion("yxzjlx not between", value1, value2, "yxzjlx");
            return (Criteria) this;
        }

        public Criteria andYxzjhIsNull() {
            addCriterion("yxzjh is null");
            return (Criteria) this;
        }

        public Criteria andYxzjhIsNotNull() {
            addCriterion("yxzjh is not null");
            return (Criteria) this;
        }

        public Criteria andYxzjhEqualTo(String value) {
            addCriterion("yxzjh =", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhNotEqualTo(String value) {
            addCriterion("yxzjh <>", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhGreaterThan(String value) {
            addCriterion("yxzjh >", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhGreaterThanOrEqualTo(String value) {
            addCriterion("yxzjh >=", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhLessThan(String value) {
            addCriterion("yxzjh <", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhLessThanOrEqualTo(String value) {
            addCriterion("yxzjh <=", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhLike(String value) {
            addCriterion("yxzjh like", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhNotLike(String value) {
            addCriterion("yxzjh not like", value, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhIn(List<String> values) {
            addCriterion("yxzjh in", values, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhNotIn(List<String> values) {
            addCriterion("yxzjh not in", values, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhBetween(String value1, String value2) {
            addCriterion("yxzjh between", value1, value2, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andYxzjhNotBetween(String value1, String value2) {
            addCriterion("yxzjh not between", value1, value2, "yxzjh");
            return (Criteria) this;
        }

        public Criteria andJbsIsNull() {
            addCriterion("jbs is null");
            return (Criteria) this;
        }

        public Criteria andJbsIsNotNull() {
            addCriterion("jbs is not null");
            return (Criteria) this;
        }

        public Criteria andJbsEqualTo(String value) {
            addCriterion("jbs =", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsNotEqualTo(String value) {
            addCriterion("jbs <>", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsGreaterThan(String value) {
            addCriterion("jbs >", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsGreaterThanOrEqualTo(String value) {
            addCriterion("jbs >=", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsLessThan(String value) {
            addCriterion("jbs <", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsLessThanOrEqualTo(String value) {
            addCriterion("jbs <=", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsLike(String value) {
            addCriterion("jbs like", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsNotLike(String value) {
            addCriterion("jbs not like", value, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsIn(List<String> values) {
            addCriterion("jbs in", values, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsNotIn(List<String> values) {
            addCriterion("jbs not in", values, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsBetween(String value1, String value2) {
            addCriterion("jbs between", value1, value2, "jbs");
            return (Criteria) this;
        }

        public Criteria andJbsNotBetween(String value1, String value2) {
            addCriterion("jbs not between", value1, value2, "jbs");
            return (Criteria) this;
        }

        public Criteria andSfsbtIsNull() {
            addCriterion("sfsbt is null");
            return (Criteria) this;
        }

        public Criteria andSfsbtIsNotNull() {
            addCriterion("sfsbt is not null");
            return (Criteria) this;
        }

        public Criteria andSfsbtEqualTo(Integer value) {
            addCriterion("sfsbt =", value, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtNotEqualTo(Integer value) {
            addCriterion("sfsbt <>", value, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtGreaterThan(Integer value) {
            addCriterion("sfsbt >", value, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtGreaterThanOrEqualTo(Integer value) {
            addCriterion("sfsbt >=", value, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtLessThan(Integer value) {
            addCriterion("sfsbt <", value, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtLessThanOrEqualTo(Integer value) {
            addCriterion("sfsbt <=", value, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtIn(List<Integer> values) {
            addCriterion("sfsbt in", values, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtNotIn(List<Integer> values) {
            addCriterion("sfsbt not in", values, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtBetween(Integer value1, Integer value2) {
            addCriterion("sfsbt between", value1, value2, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSfsbtNotBetween(Integer value1, Integer value2) {
            addCriterion("sfsbt not between", value1, value2, "sfsbt");
            return (Criteria) this;
        }

        public Criteria andSbtxhIsNull() {
            addCriterion("sbtxh is null");
            return (Criteria) this;
        }

        public Criteria andSbtxhIsNotNull() {
            addCriterion("sbtxh is not null");
            return (Criteria) this;
        }

        public Criteria andSbtxhEqualTo(Integer value) {
            addCriterion("sbtxh =", value, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhNotEqualTo(Integer value) {
            addCriterion("sbtxh <>", value, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhGreaterThan(Integer value) {
            addCriterion("sbtxh >", value, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhGreaterThanOrEqualTo(Integer value) {
            addCriterion("sbtxh >=", value, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhLessThan(Integer value) {
            addCriterion("sbtxh <", value, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhLessThanOrEqualTo(Integer value) {
            addCriterion("sbtxh <=", value, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhIn(List<Integer> values) {
            addCriterion("sbtxh in", values, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhNotIn(List<Integer> values) {
            addCriterion("sbtxh not in", values, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhBetween(Integer value1, Integer value2) {
            addCriterion("sbtxh between", value1, value2, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andSbtxhNotBetween(Integer value1, Integer value2) {
            addCriterion("sbtxh not between", value1, value2, "sbtxh");
            return (Criteria) this;
        }

        public Criteria andXslbIsNull() {
            addCriterion("xslb is null");
            return (Criteria) this;
        }

        public Criteria andXslbIsNotNull() {
            addCriterion("xslb is not null");
            return (Criteria) this;
        }

        public Criteria andXslbEqualTo(Integer value) {
            addCriterion("xslb =", value, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbNotEqualTo(Integer value) {
            addCriterion("xslb <>", value, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbGreaterThan(Integer value) {
            addCriterion("xslb >", value, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbGreaterThanOrEqualTo(Integer value) {
            addCriterion("xslb >=", value, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbLessThan(Integer value) {
            addCriterion("xslb <", value, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbLessThanOrEqualTo(Integer value) {
            addCriterion("xslb <=", value, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbIn(List<Integer> values) {
            addCriterion("xslb in", values, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbNotIn(List<Integer> values) {
            addCriterion("xslb not in", values, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbBetween(Integer value1, Integer value2) {
            addCriterion("xslb between", value1, value2, "xslb");
            return (Criteria) this;
        }

        public Criteria andXslbNotBetween(Integer value1, Integer value2) {
            addCriterion("xslb not between", value1, value2, "xslb");
            return (Criteria) this;
        }

        public Criteria andGbIsNull() {
            addCriterion("gb is null");
            return (Criteria) this;
        }

        public Criteria andGbIsNotNull() {
            addCriterion("gb is not null");
            return (Criteria) this;
        }

        public Criteria andGbEqualTo(String value) {
            addCriterion("gb =", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbNotEqualTo(String value) {
            addCriterion("gb <>", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbGreaterThan(String value) {
            addCriterion("gb >", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbGreaterThanOrEqualTo(String value) {
            addCriterion("gb >=", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbLessThan(String value) {
            addCriterion("gb <", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbLessThanOrEqualTo(String value) {
            addCriterion("gb <=", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbLike(String value) {
            addCriterion("gb like", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbNotLike(String value) {
            addCriterion("gb not like", value, "gb");
            return (Criteria) this;
        }

        public Criteria andGbIn(List<String> values) {
            addCriterion("gb in", values, "gb");
            return (Criteria) this;
        }

        public Criteria andGbNotIn(List<String> values) {
            addCriterion("gb not in", values, "gb");
            return (Criteria) this;
        }

        public Criteria andGbBetween(String value1, String value2) {
            addCriterion("gb between", value1, value2, "gb");
            return (Criteria) this;
        }

        public Criteria andGbNotBetween(String value1, String value2) {
            addCriterion("gb not between", value1, value2, "gb");
            return (Criteria) this;
        }

        public Criteria andMzIsNull() {
            addCriterion("mz is null");
            return (Criteria) this;
        }

        public Criteria andMzIsNotNull() {
            addCriterion("mz is not null");
            return (Criteria) this;
        }

        public Criteria andMzEqualTo(String value) {
            addCriterion("mz =", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotEqualTo(String value) {
            addCriterion("mz <>", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzGreaterThan(String value) {
            addCriterion("mz >", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzGreaterThanOrEqualTo(String value) {
            addCriterion("mz >=", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzLessThan(String value) {
            addCriterion("mz <", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzLessThanOrEqualTo(String value) {
            addCriterion("mz <=", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzLike(String value) {
            addCriterion("mz like", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotLike(String value) {
            addCriterion("mz not like", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzIn(List<String> values) {
            addCriterion("mz in", values, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotIn(List<String> values) {
            addCriterion("mz not in", values, "mz");
            return (Criteria) this;
        }

        public Criteria andMzBetween(String value1, String value2) {
            addCriterion("mz between", value1, value2, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotBetween(String value1, String value2) {
            addCriterion("mz not between", value1, value2, "mz");
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

        public Criteria andZzmmEqualTo(Integer value) {
            addCriterion("zzmm =", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotEqualTo(Integer value) {
            addCriterion("zzmm <>", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmGreaterThan(Integer value) {
            addCriterion("zzmm >", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmGreaterThanOrEqualTo(Integer value) {
            addCriterion("zzmm >=", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLessThan(Integer value) {
            addCriterion("zzmm <", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLessThanOrEqualTo(Integer value) {
            addCriterion("zzmm <=", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmIn(List<Integer> values) {
            addCriterion("zzmm in", values, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotIn(List<Integer> values) {
            addCriterion("zzmm not in", values, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmBetween(Integer value1, Integer value2) {
            addCriterion("zzmm between", value1, value2, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotBetween(Integer value1, Integer value2) {
            addCriterion("zzmm not between", value1, value2, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZslbIsNull() {
            addCriterion("zslb is null");
            return (Criteria) this;
        }

        public Criteria andZslbIsNotNull() {
            addCriterion("zslb is not null");
            return (Criteria) this;
        }

        public Criteria andZslbEqualTo(Integer value) {
            addCriterion("zslb =", value, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbNotEqualTo(Integer value) {
            addCriterion("zslb <>", value, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbGreaterThan(Integer value) {
            addCriterion("zslb >", value, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbGreaterThanOrEqualTo(Integer value) {
            addCriterion("zslb >=", value, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbLessThan(Integer value) {
            addCriterion("zslb <", value, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbLessThanOrEqualTo(Integer value) {
            addCriterion("zslb <=", value, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbIn(List<Integer> values) {
            addCriterion("zslb in", values, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbNotIn(List<Integer> values) {
            addCriterion("zslb not in", values, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbBetween(Integer value1, Integer value2) {
            addCriterion("zslb between", value1, value2, "zslb");
            return (Criteria) this;
        }

        public Criteria andZslbNotBetween(Integer value1, Integer value2) {
            addCriterion("zslb not between", value1, value2, "zslb");
            return (Criteria) this;
        }

        public Criteria andLydqIsNull() {
            addCriterion("lydq is null");
            return (Criteria) this;
        }

        public Criteria andLydqIsNotNull() {
            addCriterion("lydq is not null");
            return (Criteria) this;
        }

        public Criteria andLydqEqualTo(String value) {
            addCriterion("lydq =", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqNotEqualTo(String value) {
            addCriterion("lydq <>", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqGreaterThan(String value) {
            addCriterion("lydq >", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqGreaterThanOrEqualTo(String value) {
            addCriterion("lydq >=", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqLessThan(String value) {
            addCriterion("lydq <", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqLessThanOrEqualTo(String value) {
            addCriterion("lydq <=", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqLike(String value) {
            addCriterion("lydq like", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqNotLike(String value) {
            addCriterion("lydq not like", value, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqIn(List<String> values) {
            addCriterion("lydq in", values, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqNotIn(List<String> values) {
            addCriterion("lydq not in", values, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqBetween(String value1, String value2) {
            addCriterion("lydq between", value1, value2, "lydq");
            return (Criteria) this;
        }

        public Criteria andLydqNotBetween(String value1, String value2) {
            addCriterion("lydq not between", value1, value2, "lydq");
            return (Criteria) this;
        }

        public Criteria andHkszdIsNull() {
            addCriterion("hkszd is null");
            return (Criteria) this;
        }

        public Criteria andHkszdIsNotNull() {
            addCriterion("hkszd is not null");
            return (Criteria) this;
        }

        public Criteria andHkszdEqualTo(String value) {
            addCriterion("hkszd =", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdNotEqualTo(String value) {
            addCriterion("hkszd <>", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdGreaterThan(String value) {
            addCriterion("hkszd >", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdGreaterThanOrEqualTo(String value) {
            addCriterion("hkszd >=", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdLessThan(String value) {
            addCriterion("hkszd <", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdLessThanOrEqualTo(String value) {
            addCriterion("hkszd <=", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdLike(String value) {
            addCriterion("hkszd like", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdNotLike(String value) {
            addCriterion("hkszd not like", value, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdIn(List<String> values) {
            addCriterion("hkszd in", values, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdNotIn(List<String> values) {
            addCriterion("hkszd not in", values, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdBetween(String value1, String value2) {
            addCriterion("hkszd between", value1, value2, "hkszd");
            return (Criteria) this;
        }

        public Criteria andHkszdNotBetween(String value1, String value2) {
            addCriterion("hkszd not between", value1, value2, "hkszd");
            return (Criteria) this;
        }

        public Criteria andXjzdIsNull() {
            addCriterion("xjzd is null");
            return (Criteria) this;
        }

        public Criteria andXjzdIsNotNull() {
            addCriterion("xjzd is not null");
            return (Criteria) this;
        }

        public Criteria andXjzdEqualTo(String value) {
            addCriterion("xjzd =", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdNotEqualTo(String value) {
            addCriterion("xjzd <>", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdGreaterThan(String value) {
            addCriterion("xjzd >", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdGreaterThanOrEqualTo(String value) {
            addCriterion("xjzd >=", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdLessThan(String value) {
            addCriterion("xjzd <", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdLessThanOrEqualTo(String value) {
            addCriterion("xjzd <=", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdLike(String value) {
            addCriterion("xjzd like", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdNotLike(String value) {
            addCriterion("xjzd not like", value, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdIn(List<String> values) {
            addCriterion("xjzd in", values, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdNotIn(List<String> values) {
            addCriterion("xjzd not in", values, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdBetween(String value1, String value2) {
            addCriterion("xjzd between", value1, value2, "xjzd");
            return (Criteria) this;
        }

        public Criteria andXjzdNotBetween(String value1, String value2) {
            addCriterion("xjzd not between", value1, value2, "xjzd");
            return (Criteria) this;
        }

        public Criteria andHkxzIsNull() {
            addCriterion("hkxz is null");
            return (Criteria) this;
        }

        public Criteria andHkxzIsNotNull() {
            addCriterion("hkxz is not null");
            return (Criteria) this;
        }

        public Criteria andHkxzEqualTo(String value) {
            addCriterion("hkxz =", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzNotEqualTo(String value) {
            addCriterion("hkxz <>", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzGreaterThan(String value) {
            addCriterion("hkxz >", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzGreaterThanOrEqualTo(String value) {
            addCriterion("hkxz >=", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzLessThan(String value) {
            addCriterion("hkxz <", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzLessThanOrEqualTo(String value) {
            addCriterion("hkxz <=", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzLike(String value) {
            addCriterion("hkxz like", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzNotLike(String value) {
            addCriterion("hkxz not like", value, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzIn(List<String> values) {
            addCriterion("hkxz in", values, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzNotIn(List<String> values) {
            addCriterion("hkxz not in", values, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzBetween(String value1, String value2) {
            addCriterion("hkxz between", value1, value2, "hkxz");
            return (Criteria) this;
        }

        public Criteria andHkxzNotBetween(String value1, String value2) {
            addCriterion("hkxz not between", value1, value2, "hkxz");
            return (Criteria) this;
        }

        public Criteria andSfbshkIsNull() {
            addCriterion("sfbshk is null");
            return (Criteria) this;
        }

        public Criteria andSfbshkIsNotNull() {
            addCriterion("sfbshk is not null");
            return (Criteria) this;
        }

        public Criteria andSfbshkEqualTo(Integer value) {
            addCriterion("sfbshk =", value, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkNotEqualTo(Integer value) {
            addCriterion("sfbshk <>", value, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkGreaterThan(Integer value) {
            addCriterion("sfbshk >", value, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkGreaterThanOrEqualTo(Integer value) {
            addCriterion("sfbshk >=", value, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkLessThan(Integer value) {
            addCriterion("sfbshk <", value, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkLessThanOrEqualTo(Integer value) {
            addCriterion("sfbshk <=", value, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkIn(List<Integer> values) {
            addCriterion("sfbshk in", values, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkNotIn(List<Integer> values) {
            addCriterion("sfbshk not in", values, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkBetween(Integer value1, Integer value2) {
            addCriterion("sfbshk between", value1, value2, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andSfbshkNotBetween(Integer value1, Integer value2) {
            addCriterion("sfbshk not between", value1, value2, "sfbshk");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
            return (Criteria) this;
        }

        public Criteria andClassIdLikeInsensitive(String value) {
            addCriterion("upper(class_id) like", value.toUpperCase(), "classId");
            return (Criteria) this;
        }

        public Criteria andAccountLikeInsensitive(String value) {
            addCriterion("upper(account) like", value.toUpperCase(), "account");
            return (Criteria) this;
        }

        public Criteria andXsxmLikeInsensitive(String value) {
            addCriterion("upper(xsxm) like", value.toUpperCase(), "xsxm");
            return (Criteria) this;
        }

        public Criteria andXmpyLikeInsensitive(String value) {
            addCriterion("upper(xmpy) like", value.toUpperCase(), "xmpy");
            return (Criteria) this;
        }

        public Criteria andXszpLikeInsensitive(String value) {
            addCriterion("upper(xszp) like", value.toUpperCase(), "xszp");
            return (Criteria) this;
        }

        public Criteria andPhoneLikeInsensitive(String value) {
            addCriterion("upper(phone) like", value.toUpperCase(), "phone");
            return (Criteria) this;
        }

        public Criteria andXssgLikeInsensitive(String value) {
            addCriterion("upper(xssg) like", value.toUpperCase(), "xssg");
            return (Criteria) this;
        }

        public Criteria andXdLikeInsensitive(String value) {
            addCriterion("upper(xd) like", value.toUpperCase(), "xd");
            return (Criteria) this;
        }

        public Criteria andXhLikeInsensitive(String value) {
            addCriterion("upper(xh) like", value.toUpperCase(), "xh");
            return (Criteria) this;
        }

        public Criteria andXjhLikeInsensitive(String value) {
            addCriterion("upper(xjh) like", value.toUpperCase(), "xjh");
            return (Criteria) this;
        }

        public Criteria andQgxjhLikeInsensitive(String value) {
            addCriterion("upper(qgxjh) like", value.toUpperCase(), "qgxjh");
            return (Criteria) this;
        }

        public Criteria andJyidLikeInsensitive(String value) {
            addCriterion("upper(jyid) like", value.toUpperCase(), "jyid");
            return (Criteria) this;
        }

        public Criteria andSydLikeInsensitive(String value) {
            addCriterion("upper(syd) like", value.toUpperCase(), "syd");
            return (Criteria) this;
        }

        public Criteria andYxzjhLikeInsensitive(String value) {
            addCriterion("upper(yxzjh) like", value.toUpperCase(), "yxzjh");
            return (Criteria) this;
        }

        public Criteria andJbsLikeInsensitive(String value) {
            addCriterion("upper(jbs) like", value.toUpperCase(), "jbs");
            return (Criteria) this;
        }

        public Criteria andGbLikeInsensitive(String value) {
            addCriterion("upper(gb) like", value.toUpperCase(), "gb");
            return (Criteria) this;
        }

        public Criteria andMzLikeInsensitive(String value) {
            addCriterion("upper(mz) like", value.toUpperCase(), "mz");
            return (Criteria) this;
        }

        public Criteria andJgLikeInsensitive(String value) {
            addCriterion("upper(jg) like", value.toUpperCase(), "jg");
            return (Criteria) this;
        }

        public Criteria andLydqLikeInsensitive(String value) {
            addCriterion("upper(lydq) like", value.toUpperCase(), "lydq");
            return (Criteria) this;
        }

        public Criteria andHkszdLikeInsensitive(String value) {
            addCriterion("upper(hkszd) like", value.toUpperCase(), "hkszd");
            return (Criteria) this;
        }

        public Criteria andXjzdLikeInsensitive(String value) {
            addCriterion("upper(xjzd) like", value.toUpperCase(), "xjzd");
            return (Criteria) this;
        }

        public Criteria andHkxzLikeInsensitive(String value) {
            addCriterion("upper(hkxz) like", value.toUpperCase(), "hkxz");
            return (Criteria) this;
        }

        public Criteria andRemarksLikeInsensitive(String value) {
            addCriterion("upper(remarks) like", value.toUpperCase(), "remarks");
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