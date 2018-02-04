package cn.gukeer.classcard.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassCardExample() {
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

        public Criteria andMacIdIsNull() {
            addCriterion("mac_id is null");
            return (Criteria) this;
        }

        public Criteria andMacIdIsNotNull() {
            addCriterion("mac_id is not null");
            return (Criteria) this;
        }

        public Criteria andMacIdEqualTo(String value) {
            addCriterion("mac_id =", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotEqualTo(String value) {
            addCriterion("mac_id <>", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdGreaterThan(String value) {
            addCriterion("mac_id >", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdGreaterThanOrEqualTo(String value) {
            addCriterion("mac_id >=", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLessThan(String value) {
            addCriterion("mac_id <", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLessThanOrEqualTo(String value) {
            addCriterion("mac_id <=", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLike(String value) {
            addCriterion("mac_id like", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotLike(String value) {
            addCriterion("mac_id not like", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdIn(List<String> values) {
            addCriterion("mac_id in", values, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotIn(List<String> values) {
            addCriterion("mac_id not in", values, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdBetween(String value1, String value2) {
            addCriterion("mac_id between", value1, value2, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotBetween(String value1, String value2) {
            addCriterion("mac_id not between", value1, value2, "macId");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIsNull() {
            addCriterion("equipment_name is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIsNotNull() {
            addCriterion("equipment_name is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameEqualTo(String value) {
            addCriterion("equipment_name =", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotEqualTo(String value) {
            addCriterion("equipment_name <>", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameGreaterThan(String value) {
            addCriterion("equipment_name >", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_name >=", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLessThan(String value) {
            addCriterion("equipment_name <", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLessThanOrEqualTo(String value) {
            addCriterion("equipment_name <=", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLike(String value) {
            addCriterion("equipment_name like", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotLike(String value) {
            addCriterion("equipment_name not like", value, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameIn(List<String> values) {
            addCriterion("equipment_name in", values, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotIn(List<String> values) {
            addCriterion("equipment_name not in", values, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameBetween(String value1, String value2) {
            addCriterion("equipment_name between", value1, value2, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameNotBetween(String value1, String value2) {
            addCriterion("equipment_name not between", value1, value2, "equipmentName");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdIsNull() {
            addCriterion("teach_class_room_id is null");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdIsNotNull() {
            addCriterion("teach_class_room_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdEqualTo(String value) {
            addCriterion("teach_class_room_id =", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdNotEqualTo(String value) {
            addCriterion("teach_class_room_id <>", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdGreaterThan(String value) {
            addCriterion("teach_class_room_id >", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdGreaterThanOrEqualTo(String value) {
            addCriterion("teach_class_room_id >=", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdLessThan(String value) {
            addCriterion("teach_class_room_id <", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdLessThanOrEqualTo(String value) {
            addCriterion("teach_class_room_id <=", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdLike(String value) {
            addCriterion("teach_class_room_id like", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdNotLike(String value) {
            addCriterion("teach_class_room_id not like", value, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdIn(List<String> values) {
            addCriterion("teach_class_room_id in", values, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdNotIn(List<String> values) {
            addCriterion("teach_class_room_id not in", values, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdBetween(String value1, String value2) {
            addCriterion("teach_class_room_id between", value1, value2, "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdNotBetween(String value1, String value2) {
            addCriterion("teach_class_room_id not between", value1, value2, "teachClassRoomId");
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

        public Criteria andClassroomIsNull() {
            addCriterion("classroom is null");
            return (Criteria) this;
        }

        public Criteria andClassroomIsNotNull() {
            addCriterion("classroom is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomEqualTo(String value) {
            addCriterion("classroom =", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotEqualTo(String value) {
            addCriterion("classroom <>", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomGreaterThan(String value) {
            addCriterion("classroom >", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomGreaterThanOrEqualTo(String value) {
            addCriterion("classroom >=", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomLessThan(String value) {
            addCriterion("classroom <", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomLessThanOrEqualTo(String value) {
            addCriterion("classroom <=", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomLike(String value) {
            addCriterion("classroom like", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotLike(String value) {
            addCriterion("classroom not like", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomIn(List<String> values) {
            addCriterion("classroom in", values, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotIn(List<String> values) {
            addCriterion("classroom not in", values, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomBetween(String value1, String value2) {
            addCriterion("classroom between", value1, value2, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotBetween(String value1, String value2) {
            addCriterion("classroom not between", value1, value2, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassSloganIsNull() {
            addCriterion("class_slogan is null");
            return (Criteria) this;
        }

        public Criteria andClassSloganIsNotNull() {
            addCriterion("class_slogan is not null");
            return (Criteria) this;
        }

        public Criteria andClassSloganEqualTo(String value) {
            addCriterion("class_slogan =", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganNotEqualTo(String value) {
            addCriterion("class_slogan <>", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganGreaterThan(String value) {
            addCriterion("class_slogan >", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganGreaterThanOrEqualTo(String value) {
            addCriterion("class_slogan >=", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganLessThan(String value) {
            addCriterion("class_slogan <", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganLessThanOrEqualTo(String value) {
            addCriterion("class_slogan <=", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganLike(String value) {
            addCriterion("class_slogan like", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganNotLike(String value) {
            addCriterion("class_slogan not like", value, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganIn(List<String> values) {
            addCriterion("class_slogan in", values, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganNotIn(List<String> values) {
            addCriterion("class_slogan not in", values, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganBetween(String value1, String value2) {
            addCriterion("class_slogan between", value1, value2, "classSlogan");
            return (Criteria) this;
        }

        public Criteria andClassSloganNotBetween(String value1, String value2) {
            addCriterion("class_slogan not between", value1, value2, "classSlogan");
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

        public Criteria andBootDayOfWeekIsNull() {
            addCriterion("boot_day_of_week is null");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekIsNotNull() {
            addCriterion("boot_day_of_week is not null");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekEqualTo(String value) {
            addCriterion("boot_day_of_week =", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekNotEqualTo(String value) {
            addCriterion("boot_day_of_week <>", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekGreaterThan(String value) {
            addCriterion("boot_day_of_week >", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekGreaterThanOrEqualTo(String value) {
            addCriterion("boot_day_of_week >=", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekLessThan(String value) {
            addCriterion("boot_day_of_week <", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekLessThanOrEqualTo(String value) {
            addCriterion("boot_day_of_week <=", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekLike(String value) {
            addCriterion("boot_day_of_week like", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekNotLike(String value) {
            addCriterion("boot_day_of_week not like", value, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekIn(List<String> values) {
            addCriterion("boot_day_of_week in", values, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekNotIn(List<String> values) {
            addCriterion("boot_day_of_week not in", values, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekBetween(String value1, String value2) {
            addCriterion("boot_day_of_week between", value1, value2, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andBootDayOfWeekNotBetween(String value1, String value2) {
            addCriterion("boot_day_of_week not between", value1, value2, "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayIsNull() {
            addCriterion("start_boot_time_of_day is null");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayIsNotNull() {
            addCriterion("start_boot_time_of_day is not null");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayEqualTo(String value) {
            addCriterion("start_boot_time_of_day =", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayNotEqualTo(String value) {
            addCriterion("start_boot_time_of_day <>", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayGreaterThan(String value) {
            addCriterion("start_boot_time_of_day >", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayGreaterThanOrEqualTo(String value) {
            addCriterion("start_boot_time_of_day >=", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayLessThan(String value) {
            addCriterion("start_boot_time_of_day <", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayLessThanOrEqualTo(String value) {
            addCriterion("start_boot_time_of_day <=", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayLike(String value) {
            addCriterion("start_boot_time_of_day like", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayNotLike(String value) {
            addCriterion("start_boot_time_of_day not like", value, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayIn(List<String> values) {
            addCriterion("start_boot_time_of_day in", values, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayNotIn(List<String> values) {
            addCriterion("start_boot_time_of_day not in", values, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayBetween(String value1, String value2) {
            addCriterion("start_boot_time_of_day between", value1, value2, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayNotBetween(String value1, String value2) {
            addCriterion("start_boot_time_of_day not between", value1, value2, "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayIsNull() {
            addCriterion("end_boot_time_of_day is null");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayIsNotNull() {
            addCriterion("end_boot_time_of_day is not null");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayEqualTo(String value) {
            addCriterion("end_boot_time_of_day =", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayNotEqualTo(String value) {
            addCriterion("end_boot_time_of_day <>", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayGreaterThan(String value) {
            addCriterion("end_boot_time_of_day >", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayGreaterThanOrEqualTo(String value) {
            addCriterion("end_boot_time_of_day >=", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayLessThan(String value) {
            addCriterion("end_boot_time_of_day <", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayLessThanOrEqualTo(String value) {
            addCriterion("end_boot_time_of_day <=", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayLike(String value) {
            addCriterion("end_boot_time_of_day like", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayNotLike(String value) {
            addCriterion("end_boot_time_of_day not like", value, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayIn(List<String> values) {
            addCriterion("end_boot_time_of_day in", values, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayNotIn(List<String> values) {
            addCriterion("end_boot_time_of_day not in", values, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayBetween(String value1, String value2) {
            addCriterion("end_boot_time_of_day between", value1, value2, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayNotBetween(String value1, String value2) {
            addCriterion("end_boot_time_of_day not between", value1, value2, "endBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andIsSynchroIsNull() {
            addCriterion("is_synchro is null");
            return (Criteria) this;
        }

        public Criteria andIsSynchroIsNotNull() {
            addCriterion("is_synchro is not null");
            return (Criteria) this;
        }

        public Criteria andIsSynchroEqualTo(Integer value) {
            addCriterion("is_synchro =", value, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroNotEqualTo(Integer value) {
            addCriterion("is_synchro <>", value, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroGreaterThan(Integer value) {
            addCriterion("is_synchro >", value, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_synchro >=", value, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroLessThan(Integer value) {
            addCriterion("is_synchro <", value, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroLessThanOrEqualTo(Integer value) {
            addCriterion("is_synchro <=", value, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroIn(List<Integer> values) {
            addCriterion("is_synchro in", values, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroNotIn(List<Integer> values) {
            addCriterion("is_synchro not in", values, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroBetween(Integer value1, Integer value2) {
            addCriterion("is_synchro between", value1, value2, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIsSynchroNotBetween(Integer value1, Integer value2) {
            addCriterion("is_synchro not between", value1, value2, "isSynchro");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andMacIdLikeInsensitive(String value) {
            addCriterion("upper(mac_id) like", value.toUpperCase(), "macId");
            return (Criteria) this;
        }

        public Criteria andEquipmentNameLikeInsensitive(String value) {
            addCriterion("upper(equipment_name) like", value.toUpperCase(), "equipmentName");
            return (Criteria) this;
        }

        public Criteria andTeachClassRoomIdLikeInsensitive(String value) {
            addCriterion("upper(teach_class_room_id) like", value.toUpperCase(), "teachClassRoomId");
            return (Criteria) this;
        }

        public Criteria andClassIdLikeInsensitive(String value) {
            addCriterion("upper(class_id) like", value.toUpperCase(), "classId");
            return (Criteria) this;
        }

        public Criteria andClassroomLikeInsensitive(String value) {
            addCriterion("upper(classroom) like", value.toUpperCase(), "classroom");
            return (Criteria) this;
        }

        public Criteria andClassSloganLikeInsensitive(String value) {
            addCriterion("upper(class_slogan) like", value.toUpperCase(), "classSlogan");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLikeInsensitive(String value) {
            addCriterion("upper(school_id) like", value.toUpperCase(), "schoolId");
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

        public Criteria andBootDayOfWeekLikeInsensitive(String value) {
            addCriterion("upper(boot_day_of_week) like", value.toUpperCase(), "bootDayOfWeek");
            return (Criteria) this;
        }

        public Criteria andStartBootTimeOfDayLikeInsensitive(String value) {
            addCriterion("upper(start_boot_time_of_day) like", value.toUpperCase(), "startBootTimeOfDay");
            return (Criteria) this;
        }

        public Criteria andEndBootTimeOfDayLikeInsensitive(String value) {
            addCriterion("upper(end_boot_time_of_day) like", value.toUpperCase(), "endBootTimeOfDay");
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