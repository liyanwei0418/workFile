package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class MonitorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonitorExample() {
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

        public Criteria andCpuIsNull() {
            addCriterion("cpu is null");
            return (Criteria) this;
        }

        public Criteria andCpuIsNotNull() {
            addCriterion("cpu is not null");
            return (Criteria) this;
        }

        public Criteria andCpuEqualTo(String value) {
            addCriterion("cpu =", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotEqualTo(String value) {
            addCriterion("cpu <>", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuGreaterThan(String value) {
            addCriterion("cpu >", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuGreaterThanOrEqualTo(String value) {
            addCriterion("cpu >=", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuLessThan(String value) {
            addCriterion("cpu <", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuLessThanOrEqualTo(String value) {
            addCriterion("cpu <=", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuLike(String value) {
            addCriterion("cpu like", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotLike(String value) {
            addCriterion("cpu not like", value, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuIn(List<String> values) {
            addCriterion("cpu in", values, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotIn(List<String> values) {
            addCriterion("cpu not in", values, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuBetween(String value1, String value2) {
            addCriterion("cpu between", value1, value2, "cpu");
            return (Criteria) this;
        }

        public Criteria andCpuNotBetween(String value1, String value2) {
            addCriterion("cpu not between", value1, value2, "cpu");
            return (Criteria) this;
        }

        public Criteria andJvmIsNull() {
            addCriterion("jvm is null");
            return (Criteria) this;
        }

        public Criteria andJvmIsNotNull() {
            addCriterion("jvm is not null");
            return (Criteria) this;
        }

        public Criteria andJvmEqualTo(String value) {
            addCriterion("jvm =", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmNotEqualTo(String value) {
            addCriterion("jvm <>", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmGreaterThan(String value) {
            addCriterion("jvm >", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmGreaterThanOrEqualTo(String value) {
            addCriterion("jvm >=", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmLessThan(String value) {
            addCriterion("jvm <", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmLessThanOrEqualTo(String value) {
            addCriterion("jvm <=", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmLike(String value) {
            addCriterion("jvm like", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmNotLike(String value) {
            addCriterion("jvm not like", value, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmIn(List<String> values) {
            addCriterion("jvm in", values, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmNotIn(List<String> values) {
            addCriterion("jvm not in", values, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmBetween(String value1, String value2) {
            addCriterion("jvm between", value1, value2, "jvm");
            return (Criteria) this;
        }

        public Criteria andJvmNotBetween(String value1, String value2) {
            addCriterion("jvm not between", value1, value2, "jvm");
            return (Criteria) this;
        }

        public Criteria andRamIsNull() {
            addCriterion("ram is null");
            return (Criteria) this;
        }

        public Criteria andRamIsNotNull() {
            addCriterion("ram is not null");
            return (Criteria) this;
        }

        public Criteria andRamEqualTo(String value) {
            addCriterion("ram =", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotEqualTo(String value) {
            addCriterion("ram <>", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamGreaterThan(String value) {
            addCriterion("ram >", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamGreaterThanOrEqualTo(String value) {
            addCriterion("ram >=", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamLessThan(String value) {
            addCriterion("ram <", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamLessThanOrEqualTo(String value) {
            addCriterion("ram <=", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamLike(String value) {
            addCriterion("ram like", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotLike(String value) {
            addCriterion("ram not like", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamIn(List<String> values) {
            addCriterion("ram in", values, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotIn(List<String> values) {
            addCriterion("ram not in", values, "ram");
            return (Criteria) this;
        }

        public Criteria andRamBetween(String value1, String value2) {
            addCriterion("ram between", value1, value2, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotBetween(String value1, String value2) {
            addCriterion("ram not between", value1, value2, "ram");
            return (Criteria) this;
        }

        public Criteria andToemailIsNull() {
            addCriterion("toemail is null");
            return (Criteria) this;
        }

        public Criteria andToemailIsNotNull() {
            addCriterion("toemail is not null");
            return (Criteria) this;
        }

        public Criteria andToemailEqualTo(String value) {
            addCriterion("toemail =", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailNotEqualTo(String value) {
            addCriterion("toemail <>", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailGreaterThan(String value) {
            addCriterion("toemail >", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailGreaterThanOrEqualTo(String value) {
            addCriterion("toemail >=", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailLessThan(String value) {
            addCriterion("toemail <", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailLessThanOrEqualTo(String value) {
            addCriterion("toemail <=", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailLike(String value) {
            addCriterion("toemail like", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailNotLike(String value) {
            addCriterion("toemail not like", value, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailIn(List<String> values) {
            addCriterion("toemail in", values, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailNotIn(List<String> values) {
            addCriterion("toemail not in", values, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailBetween(String value1, String value2) {
            addCriterion("toemail between", value1, value2, "toemail");
            return (Criteria) this;
        }

        public Criteria andToemailNotBetween(String value1, String value2) {
            addCriterion("toemail not between", value1, value2, "toemail");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(String value) {
            addCriterion("client_id =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("client_id <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("client_id >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("client_id >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(String value) {
            addCriterion("client_id <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("client_id <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLike(String value) {
            addCriterion("client_id like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotLike(String value) {
            addCriterion("client_id not like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<String> values) {
            addCriterion("client_id in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("client_id not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("client_id between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("client_id not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andOpenUrlIsNull() {
            addCriterion("open_url is null");
            return (Criteria) this;
        }

        public Criteria andOpenUrlIsNotNull() {
            addCriterion("open_url is not null");
            return (Criteria) this;
        }

        public Criteria andOpenUrlEqualTo(String value) {
            addCriterion("open_url =", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotEqualTo(String value) {
            addCriterion("open_url <>", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlGreaterThan(String value) {
            addCriterion("open_url >", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlGreaterThanOrEqualTo(String value) {
            addCriterion("open_url >=", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLessThan(String value) {
            addCriterion("open_url <", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLessThanOrEqualTo(String value) {
            addCriterion("open_url <=", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLike(String value) {
            addCriterion("open_url like", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotLike(String value) {
            addCriterion("open_url not like", value, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlIn(List<String> values) {
            addCriterion("open_url in", values, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotIn(List<String> values) {
            addCriterion("open_url not in", values, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlBetween(String value1, String value2) {
            addCriterion("open_url between", value1, value2, "openUrl");
            return (Criteria) this;
        }

        public Criteria andOpenUrlNotBetween(String value1, String value2) {
            addCriterion("open_url not between", value1, value2, "openUrl");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andCpuLikeInsensitive(String value) {
            addCriterion("upper(cpu) like", value.toUpperCase(), "cpu");
            return (Criteria) this;
        }

        public Criteria andJvmLikeInsensitive(String value) {
            addCriterion("upper(jvm) like", value.toUpperCase(), "jvm");
            return (Criteria) this;
        }

        public Criteria andRamLikeInsensitive(String value) {
            addCriterion("upper(ram) like", value.toUpperCase(), "ram");
            return (Criteria) this;
        }

        public Criteria andToemailLikeInsensitive(String value) {
            addCriterion("upper(toemail) like", value.toUpperCase(), "toemail");
            return (Criteria) this;
        }

        public Criteria andClientIdLikeInsensitive(String value) {
            addCriterion("upper(client_id) like", value.toUpperCase(), "clientId");
            return (Criteria) this;
        }

        public Criteria andOpenUrlLikeInsensitive(String value) {
            addCriterion("upper(open_url) like", value.toUpperCase(), "openUrl");
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