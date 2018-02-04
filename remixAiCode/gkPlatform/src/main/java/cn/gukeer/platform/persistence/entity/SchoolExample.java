package cn.gukeer.platform.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class SchoolExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchoolExample() {
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

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
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

        public Criteria andEnameIsNull() {
            addCriterion("ename is null");
            return (Criteria) this;
        }

        public Criteria andEnameIsNotNull() {
            addCriterion("ename is not null");
            return (Criteria) this;
        }

        public Criteria andEnameEqualTo(String value) {
            addCriterion("ename =", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotEqualTo(String value) {
            addCriterion("ename <>", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThan(String value) {
            addCriterion("ename >", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThanOrEqualTo(String value) {
            addCriterion("ename >=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThan(String value) {
            addCriterion("ename <", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThanOrEqualTo(String value) {
            addCriterion("ename <=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLike(String value) {
            addCriterion("ename like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotLike(String value) {
            addCriterion("ename not like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameIn(List<String> values) {
            addCriterion("ename in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotIn(List<String> values) {
            addCriterion("ename not in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameBetween(String value1, String value2) {
            addCriterion("ename between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotBetween(String value1, String value2) {
            addCriterion("ename not between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andXzIsNull() {
            addCriterion("xz is null");
            return (Criteria) this;
        }

        public Criteria andXzIsNotNull() {
            addCriterion("xz is not null");
            return (Criteria) this;
        }

        public Criteria andXzEqualTo(String value) {
            addCriterion("xz =", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzNotEqualTo(String value) {
            addCriterion("xz <>", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzGreaterThan(String value) {
            addCriterion("xz >", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzGreaterThanOrEqualTo(String value) {
            addCriterion("xz >=", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzLessThan(String value) {
            addCriterion("xz <", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzLessThanOrEqualTo(String value) {
            addCriterion("xz <=", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzLike(String value) {
            addCriterion("xz like", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzNotLike(String value) {
            addCriterion("xz not like", value, "xz");
            return (Criteria) this;
        }

        public Criteria andXzIn(List<String> values) {
            addCriterion("xz in", values, "xz");
            return (Criteria) this;
        }

        public Criteria andXzNotIn(List<String> values) {
            addCriterion("xz not in", values, "xz");
            return (Criteria) this;
        }

        public Criteria andXzBetween(String value1, String value2) {
            addCriterion("xz between", value1, value2, "xz");
            return (Criteria) this;
        }

        public Criteria andXzNotBetween(String value1, String value2) {
            addCriterion("xz not between", value1, value2, "xz");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andBgPictureIsNull() {
            addCriterion("bg_picture is null");
            return (Criteria) this;
        }

        public Criteria andBgPictureIsNotNull() {
            addCriterion("bg_picture is not null");
            return (Criteria) this;
        }

        public Criteria andBgPictureEqualTo(String value) {
            addCriterion("bg_picture =", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureNotEqualTo(String value) {
            addCriterion("bg_picture <>", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureGreaterThan(String value) {
            addCriterion("bg_picture >", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureGreaterThanOrEqualTo(String value) {
            addCriterion("bg_picture >=", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureLessThan(String value) {
            addCriterion("bg_picture <", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureLessThanOrEqualTo(String value) {
            addCriterion("bg_picture <=", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureLike(String value) {
            addCriterion("bg_picture like", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureNotLike(String value) {
            addCriterion("bg_picture not like", value, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureIn(List<String> values) {
            addCriterion("bg_picture in", values, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureNotIn(List<String> values) {
            addCriterion("bg_picture not in", values, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureBetween(String value1, String value2) {
            addCriterion("bg_picture between", value1, value2, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andBgPictureNotBetween(String value1, String value2) {
            addCriterion("bg_picture not between", value1, value2, "bgPicture");
            return (Criteria) this;
        }

        public Criteria andHomeTextIsNull() {
            addCriterion("home_text is null");
            return (Criteria) this;
        }

        public Criteria andHomeTextIsNotNull() {
            addCriterion("home_text is not null");
            return (Criteria) this;
        }

        public Criteria andHomeTextEqualTo(String value) {
            addCriterion("home_text =", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextNotEqualTo(String value) {
            addCriterion("home_text <>", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextGreaterThan(String value) {
            addCriterion("home_text >", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextGreaterThanOrEqualTo(String value) {
            addCriterion("home_text >=", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextLessThan(String value) {
            addCriterion("home_text <", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextLessThanOrEqualTo(String value) {
            addCriterion("home_text <=", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextLike(String value) {
            addCriterion("home_text like", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextNotLike(String value) {
            addCriterion("home_text not like", value, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextIn(List<String> values) {
            addCriterion("home_text in", values, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextNotIn(List<String> values) {
            addCriterion("home_text not in", values, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextBetween(String value1, String value2) {
            addCriterion("home_text between", value1, value2, "homeText");
            return (Criteria) this;
        }

        public Criteria andHomeTextNotBetween(String value1, String value2) {
            addCriterion("home_text not between", value1, value2, "homeText");
            return (Criteria) this;
        }

        public Criteria andBottomTextIsNull() {
            addCriterion("bottom_text is null");
            return (Criteria) this;
        }

        public Criteria andBottomTextIsNotNull() {
            addCriterion("bottom_text is not null");
            return (Criteria) this;
        }

        public Criteria andBottomTextEqualTo(String value) {
            addCriterion("bottom_text =", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextNotEqualTo(String value) {
            addCriterion("bottom_text <>", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextGreaterThan(String value) {
            addCriterion("bottom_text >", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextGreaterThanOrEqualTo(String value) {
            addCriterion("bottom_text >=", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextLessThan(String value) {
            addCriterion("bottom_text <", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextLessThanOrEqualTo(String value) {
            addCriterion("bottom_text <=", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextLike(String value) {
            addCriterion("bottom_text like", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextNotLike(String value) {
            addCriterion("bottom_text not like", value, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextIn(List<String> values) {
            addCriterion("bottom_text in", values, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextNotIn(List<String> values) {
            addCriterion("bottom_text not in", values, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextBetween(String value1, String value2) {
            addCriterion("bottom_text between", value1, value2, "bottomText");
            return (Criteria) this;
        }

        public Criteria andBottomTextNotBetween(String value1, String value2) {
            addCriterion("bottom_text not between", value1, value2, "bottomText");
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

        public Criteria andMIdIsNull() {
            addCriterion("m_id is null");
            return (Criteria) this;
        }

        public Criteria andMIdIsNotNull() {
            addCriterion("m_id is not null");
            return (Criteria) this;
        }

        public Criteria andMIdEqualTo(String value) {
            addCriterion("m_id =", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotEqualTo(String value) {
            addCriterion("m_id <>", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThan(String value) {
            addCriterion("m_id >", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThanOrEqualTo(String value) {
            addCriterion("m_id >=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThan(String value) {
            addCriterion("m_id <", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThanOrEqualTo(String value) {
            addCriterion("m_id <=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLike(String value) {
            addCriterion("m_id like", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotLike(String value) {
            addCriterion("m_id not like", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdIn(List<String> values) {
            addCriterion("m_id in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotIn(List<String> values) {
            addCriterion("m_id not in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdBetween(String value1, String value2) {
            addCriterion("m_id between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotBetween(String value1, String value2) {
            addCriterion("m_id not between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMasterIsNull() {
            addCriterion("master is null");
            return (Criteria) this;
        }

        public Criteria andMasterIsNotNull() {
            addCriterion("master is not null");
            return (Criteria) this;
        }

        public Criteria andMasterEqualTo(String value) {
            addCriterion("master =", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotEqualTo(String value) {
            addCriterion("master <>", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterGreaterThan(String value) {
            addCriterion("master >", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterGreaterThanOrEqualTo(String value) {
            addCriterion("master >=", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLessThan(String value) {
            addCriterion("master <", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLessThanOrEqualTo(String value) {
            addCriterion("master <=", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLike(String value) {
            addCriterion("master like", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotLike(String value) {
            addCriterion("master not like", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterIn(List<String> values) {
            addCriterion("master in", values, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotIn(List<String> values) {
            addCriterion("master not in", values, "master");
            return (Criteria) this;
        }

        public Criteria andMasterBetween(String value1, String value2) {
            addCriterion("master between", value1, value2, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotBetween(String value1, String value2) {
            addCriterion("master not between", value1, value2, "master");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("zip_code is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("zip_code is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("zip_code =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("zip_code <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("zip_code >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("zip_code >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("zip_code <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("zip_code <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("zip_code like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("zip_code not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("zip_code in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("zip_code not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("zip_code between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("zip_code not between", value1, value2, "zipCode");
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

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
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

        public Criteria andPatriarchRuleIsNull() {
            addCriterion("patriarch_rule is null");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleIsNotNull() {
            addCriterion("patriarch_rule is not null");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleEqualTo(Integer value) {
            addCriterion("patriarch_rule =", value, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleNotEqualTo(Integer value) {
            addCriterion("patriarch_rule <>", value, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleGreaterThan(Integer value) {
            addCriterion("patriarch_rule >", value, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("patriarch_rule >=", value, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleLessThan(Integer value) {
            addCriterion("patriarch_rule <", value, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleLessThanOrEqualTo(Integer value) {
            addCriterion("patriarch_rule <=", value, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleIn(List<Integer> values) {
            addCriterion("patriarch_rule in", values, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleNotIn(List<Integer> values) {
            addCriterion("patriarch_rule not in", values, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleBetween(Integer value1, Integer value2) {
            addCriterion("patriarch_rule between", value1, value2, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andPatriarchRuleNotBetween(Integer value1, Integer value2) {
            addCriterion("patriarch_rule not between", value1, value2, "patriarchRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleIsNull() {
            addCriterion("student_rule is null");
            return (Criteria) this;
        }

        public Criteria andStudentRuleIsNotNull() {
            addCriterion("student_rule is not null");
            return (Criteria) this;
        }

        public Criteria andStudentRuleEqualTo(Integer value) {
            addCriterion("student_rule =", value, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleNotEqualTo(Integer value) {
            addCriterion("student_rule <>", value, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleGreaterThan(Integer value) {
            addCriterion("student_rule >", value, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_rule >=", value, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleLessThan(Integer value) {
            addCriterion("student_rule <", value, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleLessThanOrEqualTo(Integer value) {
            addCriterion("student_rule <=", value, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleIn(List<Integer> values) {
            addCriterion("student_rule in", values, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleNotIn(List<Integer> values) {
            addCriterion("student_rule not in", values, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleBetween(Integer value1, Integer value2) {
            addCriterion("student_rule between", value1, value2, "studentRule");
            return (Criteria) this;
        }

        public Criteria andStudentRuleNotBetween(Integer value1, Integer value2) {
            addCriterion("student_rule not between", value1, value2, "studentRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleIsNull() {
            addCriterion("teacher_rule is null");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleIsNotNull() {
            addCriterion("teacher_rule is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleEqualTo(Integer value) {
            addCriterion("teacher_rule =", value, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleNotEqualTo(Integer value) {
            addCriterion("teacher_rule <>", value, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleGreaterThan(Integer value) {
            addCriterion("teacher_rule >", value, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("teacher_rule >=", value, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleLessThan(Integer value) {
            addCriterion("teacher_rule <", value, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleLessThanOrEqualTo(Integer value) {
            addCriterion("teacher_rule <=", value, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleIn(List<Integer> values) {
            addCriterion("teacher_rule in", values, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleNotIn(List<Integer> values) {
            addCriterion("teacher_rule not in", values, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleBetween(Integer value1, Integer value2) {
            addCriterion("teacher_rule between", value1, value2, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andTeacherRuleNotBetween(Integer value1, Integer value2) {
            addCriterion("teacher_rule not between", value1, value2, "teacherRule");
            return (Criteria) this;
        }

        public Criteria andShortFlagIsNull() {
            addCriterion("short_flag is null");
            return (Criteria) this;
        }

        public Criteria andShortFlagIsNotNull() {
            addCriterion("short_flag is not null");
            return (Criteria) this;
        }

        public Criteria andShortFlagEqualTo(String value) {
            addCriterion("short_flag =", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagNotEqualTo(String value) {
            addCriterion("short_flag <>", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagGreaterThan(String value) {
            addCriterion("short_flag >", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagGreaterThanOrEqualTo(String value) {
            addCriterion("short_flag >=", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagLessThan(String value) {
            addCriterion("short_flag <", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagLessThanOrEqualTo(String value) {
            addCriterion("short_flag <=", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagLike(String value) {
            addCriterion("short_flag like", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagNotLike(String value) {
            addCriterion("short_flag not like", value, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagIn(List<String> values) {
            addCriterion("short_flag in", values, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagNotIn(List<String> values) {
            addCriterion("short_flag not in", values, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagBetween(String value1, String value2) {
            addCriterion("short_flag between", value1, value2, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andShortFlagNotBetween(String value1, String value2) {
            addCriterion("short_flag not between", value1, value2, "shortFlag");
            return (Criteria) this;
        }

        public Criteria andDeployUrlIsNull() {
            addCriterion("deploy_url is null");
            return (Criteria) this;
        }

        public Criteria andDeployUrlIsNotNull() {
            addCriterion("deploy_url is not null");
            return (Criteria) this;
        }

        public Criteria andDeployUrlEqualTo(String value) {
            addCriterion("deploy_url =", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlNotEqualTo(String value) {
            addCriterion("deploy_url <>", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlGreaterThan(String value) {
            addCriterion("deploy_url >", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlGreaterThanOrEqualTo(String value) {
            addCriterion("deploy_url >=", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlLessThan(String value) {
            addCriterion("deploy_url <", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlLessThanOrEqualTo(String value) {
            addCriterion("deploy_url <=", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlLike(String value) {
            addCriterion("deploy_url like", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlNotLike(String value) {
            addCriterion("deploy_url not like", value, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlIn(List<String> values) {
            addCriterion("deploy_url in", values, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlNotIn(List<String> values) {
            addCriterion("deploy_url not in", values, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlBetween(String value1, String value2) {
            addCriterion("deploy_url between", value1, value2, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andDeployUrlNotBetween(String value1, String value2) {
            addCriterion("deploy_url not between", value1, value2, "deployUrl");
            return (Criteria) this;
        }

        public Criteria andUserableIsNull() {
            addCriterion("userable is null");
            return (Criteria) this;
        }

        public Criteria andUserableIsNotNull() {
            addCriterion("userable is not null");
            return (Criteria) this;
        }

        public Criteria andUserableEqualTo(String value) {
            addCriterion("userable =", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableNotEqualTo(String value) {
            addCriterion("userable <>", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableGreaterThan(String value) {
            addCriterion("userable >", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableGreaterThanOrEqualTo(String value) {
            addCriterion("userable >=", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableLessThan(String value) {
            addCriterion("userable <", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableLessThanOrEqualTo(String value) {
            addCriterion("userable <=", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableLike(String value) {
            addCriterion("userable like", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableNotLike(String value) {
            addCriterion("userable not like", value, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableIn(List<String> values) {
            addCriterion("userable in", values, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableNotIn(List<String> values) {
            addCriterion("userable not in", values, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableBetween(String value1, String value2) {
            addCriterion("userable between", value1, value2, "userable");
            return (Criteria) this;
        }

        public Criteria andUserableNotBetween(String value1, String value2) {
            addCriterion("userable not between", value1, value2, "userable");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionIsNull() {
            addCriterion("primary_persion is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionIsNotNull() {
            addCriterion("primary_persion is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionEqualTo(String value) {
            addCriterion("primary_persion =", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionNotEqualTo(String value) {
            addCriterion("primary_persion <>", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionGreaterThan(String value) {
            addCriterion("primary_persion >", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionGreaterThanOrEqualTo(String value) {
            addCriterion("primary_persion >=", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionLessThan(String value) {
            addCriterion("primary_persion <", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionLessThanOrEqualTo(String value) {
            addCriterion("primary_persion <=", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionLike(String value) {
            addCriterion("primary_persion like", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionNotLike(String value) {
            addCriterion("primary_persion not like", value, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionIn(List<String> values) {
            addCriterion("primary_persion in", values, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionNotIn(List<String> values) {
            addCriterion("primary_persion not in", values, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionBetween(String value1, String value2) {
            addCriterion("primary_persion between", value1, value2, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionNotBetween(String value1, String value2) {
            addCriterion("primary_persion not between", value1, value2, "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionIsNull() {
            addCriterion("deputy_persion is null");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionIsNotNull() {
            addCriterion("deputy_persion is not null");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionEqualTo(String value) {
            addCriterion("deputy_persion =", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionNotEqualTo(String value) {
            addCriterion("deputy_persion <>", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionGreaterThan(String value) {
            addCriterion("deputy_persion >", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionGreaterThanOrEqualTo(String value) {
            addCriterion("deputy_persion >=", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionLessThan(String value) {
            addCriterion("deputy_persion <", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionLessThanOrEqualTo(String value) {
            addCriterion("deputy_persion <=", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionLike(String value) {
            addCriterion("deputy_persion like", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionNotLike(String value) {
            addCriterion("deputy_persion not like", value, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionIn(List<String> values) {
            addCriterion("deputy_persion in", values, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionNotIn(List<String> values) {
            addCriterion("deputy_persion not in", values, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionBetween(String value1, String value2) {
            addCriterion("deputy_persion between", value1, value2, "deputyPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionNotBetween(String value1, String value2) {
            addCriterion("deputy_persion not between", value1, value2, "deputyPersion");
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

        public Criteria andParentIdLikeInsensitive(String value) {
            addCriterion("upper(parent_id) like", value.toUpperCase(), "parentId");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andEnameLikeInsensitive(String value) {
            addCriterion("upper(ename) like", value.toUpperCase(), "ename");
            return (Criteria) this;
        }

        public Criteria andXzLikeInsensitive(String value) {
            addCriterion("upper(xz) like", value.toUpperCase(), "xz");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(code) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andLogoLikeInsensitive(String value) {
            addCriterion("upper(logo) like", value.toUpperCase(), "logo");
            return (Criteria) this;
        }

        public Criteria andBgPictureLikeInsensitive(String value) {
            addCriterion("upper(bg_picture) like", value.toUpperCase(), "bgPicture");
            return (Criteria) this;
        }

        public Criteria andHomeTextLikeInsensitive(String value) {
            addCriterion("upper(home_text) like", value.toUpperCase(), "homeText");
            return (Criteria) this;
        }

        public Criteria andBottomTextLikeInsensitive(String value) {
            addCriterion("upper(bottom_text) like", value.toUpperCase(), "bottomText");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andMIdLikeInsensitive(String value) {
            addCriterion("upper(m_id) like", value.toUpperCase(), "mId");
            return (Criteria) this;
        }

        public Criteria andMasterLikeInsensitive(String value) {
            addCriterion("upper(master) like", value.toUpperCase(), "master");
            return (Criteria) this;
        }

        public Criteria andZipCodeLikeInsensitive(String value) {
            addCriterion("upper(zip_code) like", value.toUpperCase(), "zipCode");
            return (Criteria) this;
        }

        public Criteria andPhoneLikeInsensitive(String value) {
            addCriterion("upper(phone) like", value.toUpperCase(), "phone");
            return (Criteria) this;
        }

        public Criteria andFaxLikeInsensitive(String value) {
            addCriterion("upper(fax) like", value.toUpperCase(), "fax");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andShortFlagLikeInsensitive(String value) {
            addCriterion("upper(short_flag) like", value.toUpperCase(), "shortFlag");
            return (Criteria) this;
        }

        public Criteria andDeployUrlLikeInsensitive(String value) {
            addCriterion("upper(deploy_url) like", value.toUpperCase(), "deployUrl");
            return (Criteria) this;
        }

        public Criteria andUserableLikeInsensitive(String value) {
            addCriterion("upper(userable) like", value.toUpperCase(), "userable");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersionLikeInsensitive(String value) {
            addCriterion("upper(primary_persion) like", value.toUpperCase(), "primaryPersion");
            return (Criteria) this;
        }

        public Criteria andDeputyPersionLikeInsensitive(String value) {
            addCriterion("upper(deputy_persion) like", value.toUpperCase(), "deputyPersion");
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