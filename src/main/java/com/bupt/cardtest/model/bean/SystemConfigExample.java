package com.bupt.cardtest.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemConfigExample() {
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

        public Criteria andConfIdIsNull() {
            addCriterion("CONF_ID is null");
            return (Criteria) this;
        }

        public Criteria andConfIdIsNotNull() {
            addCriterion("CONF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConfIdEqualTo(String value) {
            addCriterion("CONF_ID =", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotEqualTo(String value) {
            addCriterion("CONF_ID <>", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdGreaterThan(String value) {
            addCriterion("CONF_ID >", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_ID >=", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdLessThan(String value) {
            addCriterion("CONF_ID <", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdLessThanOrEqualTo(String value) {
            addCriterion("CONF_ID <=", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdLike(String value) {
            addCriterion("CONF_ID like", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotLike(String value) {
            addCriterion("CONF_ID not like", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdIn(List<String> values) {
            addCriterion("CONF_ID in", values, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotIn(List<String> values) {
            addCriterion("CONF_ID not in", values, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdBetween(String value1, String value2) {
            addCriterion("CONF_ID between", value1, value2, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotBetween(String value1, String value2) {
            addCriterion("CONF_ID not between", value1, value2, "confId");
            return (Criteria) this;
        }

        public Criteria andConfNameIsNull() {
            addCriterion("CONF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConfNameIsNotNull() {
            addCriterion("CONF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConfNameEqualTo(String value) {
            addCriterion("CONF_NAME =", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotEqualTo(String value) {
            addCriterion("CONF_NAME <>", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameGreaterThan(String value) {
            addCriterion("CONF_NAME >", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_NAME >=", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLessThan(String value) {
            addCriterion("CONF_NAME <", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLessThanOrEqualTo(String value) {
            addCriterion("CONF_NAME <=", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLike(String value) {
            addCriterion("CONF_NAME like", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotLike(String value) {
            addCriterion("CONF_NAME not like", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameIn(List<String> values) {
            addCriterion("CONF_NAME in", values, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotIn(List<String> values) {
            addCriterion("CONF_NAME not in", values, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameBetween(String value1, String value2) {
            addCriterion("CONF_NAME between", value1, value2, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotBetween(String value1, String value2) {
            addCriterion("CONF_NAME not between", value1, value2, "confName");
            return (Criteria) this;
        }

        public Criteria andConfContextIsNull() {
            addCriterion("CONF_CONTEXT is null");
            return (Criteria) this;
        }

        public Criteria andConfContextIsNotNull() {
            addCriterion("CONF_CONTEXT is not null");
            return (Criteria) this;
        }

        public Criteria andConfContextEqualTo(String value) {
            addCriterion("CONF_CONTEXT =", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextNotEqualTo(String value) {
            addCriterion("CONF_CONTEXT <>", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextGreaterThan(String value) {
            addCriterion("CONF_CONTEXT >", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_CONTEXT >=", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextLessThan(String value) {
            addCriterion("CONF_CONTEXT <", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextLessThanOrEqualTo(String value) {
            addCriterion("CONF_CONTEXT <=", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextLike(String value) {
            addCriterion("CONF_CONTEXT like", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextNotLike(String value) {
            addCriterion("CONF_CONTEXT not like", value, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextIn(List<String> values) {
            addCriterion("CONF_CONTEXT in", values, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextNotIn(List<String> values) {
            addCriterion("CONF_CONTEXT not in", values, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextBetween(String value1, String value2) {
            addCriterion("CONF_CONTEXT between", value1, value2, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfContextNotBetween(String value1, String value2) {
            addCriterion("CONF_CONTEXT not between", value1, value2, "confContext");
            return (Criteria) this;
        }

        public Criteria andConfDescIsNull() {
            addCriterion("CONF_DESC is null");
            return (Criteria) this;
        }

        public Criteria andConfDescIsNotNull() {
            addCriterion("CONF_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andConfDescEqualTo(String value) {
            addCriterion("CONF_DESC =", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescNotEqualTo(String value) {
            addCriterion("CONF_DESC <>", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescGreaterThan(String value) {
            addCriterion("CONF_DESC >", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_DESC >=", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescLessThan(String value) {
            addCriterion("CONF_DESC <", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescLessThanOrEqualTo(String value) {
            addCriterion("CONF_DESC <=", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescLike(String value) {
            addCriterion("CONF_DESC like", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescNotLike(String value) {
            addCriterion("CONF_DESC not like", value, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescIn(List<String> values) {
            addCriterion("CONF_DESC in", values, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescNotIn(List<String> values) {
            addCriterion("CONF_DESC not in", values, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescBetween(String value1, String value2) {
            addCriterion("CONF_DESC between", value1, value2, "confDesc");
            return (Criteria) this;
        }

        public Criteria andConfDescNotBetween(String value1, String value2) {
            addCriterion("CONF_DESC not between", value1, value2, "confDesc");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andAdminNameIsNull() {
            addCriterion("ADMIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdminNameIsNotNull() {
            addCriterion("ADMIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminNameEqualTo(String value) {
            addCriterion("ADMIN_NAME =", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotEqualTo(String value) {
            addCriterion("ADMIN_NAME <>", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameGreaterThan(String value) {
            addCriterion("ADMIN_NAME >", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_NAME >=", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLessThan(String value) {
            addCriterion("ADMIN_NAME <", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_NAME <=", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLike(String value) {
            addCriterion("ADMIN_NAME like", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotLike(String value) {
            addCriterion("ADMIN_NAME not like", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameIn(List<String> values) {
            addCriterion("ADMIN_NAME in", values, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotIn(List<String> values) {
            addCriterion("ADMIN_NAME not in", values, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameBetween(String value1, String value2) {
            addCriterion("ADMIN_NAME between", value1, value2, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotBetween(String value1, String value2) {
            addCriterion("ADMIN_NAME not between", value1, value2, "adminName");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagIsNull() {
            addCriterion("DATA_VER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagIsNotNull() {
            addCriterion("DATA_VER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagEqualTo(Short value) {
            addCriterion("DATA_VER_FLAG =", value, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagNotEqualTo(Short value) {
            addCriterion("DATA_VER_FLAG <>", value, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagGreaterThan(Short value) {
            addCriterion("DATA_VER_FLAG >", value, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagGreaterThanOrEqualTo(Short value) {
            addCriterion("DATA_VER_FLAG >=", value, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagLessThan(Short value) {
            addCriterion("DATA_VER_FLAG <", value, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagLessThanOrEqualTo(Short value) {
            addCriterion("DATA_VER_FLAG <=", value, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagIn(List<Short> values) {
            addCriterion("DATA_VER_FLAG in", values, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagNotIn(List<Short> values) {
            addCriterion("DATA_VER_FLAG not in", values, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagBetween(Short value1, Short value2) {
            addCriterion("DATA_VER_FLAG between", value1, value2, "dataVerFlag");
            return (Criteria) this;
        }

        public Criteria andDataVerFlagNotBetween(Short value1, Short value2) {
            addCriterion("DATA_VER_FLAG not between", value1, value2, "dataVerFlag");
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