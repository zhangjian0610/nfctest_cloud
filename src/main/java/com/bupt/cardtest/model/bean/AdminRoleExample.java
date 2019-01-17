package com.bupt.cardtest.model.bean;

import java.util.ArrayList;
import java.util.List;

public class AdminRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminRoleExample() {
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

        public Criteria andTroleIdIsNull() {
            addCriterion("TROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTroleIdIsNotNull() {
            addCriterion("TROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTroleIdEqualTo(String value) {
            addCriterion("TROLE_ID =", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdNotEqualTo(String value) {
            addCriterion("TROLE_ID <>", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdGreaterThan(String value) {
            addCriterion("TROLE_ID >", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdGreaterThanOrEqualTo(String value) {
            addCriterion("TROLE_ID >=", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdLessThan(String value) {
            addCriterion("TROLE_ID <", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdLessThanOrEqualTo(String value) {
            addCriterion("TROLE_ID <=", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdLike(String value) {
            addCriterion("TROLE_ID like", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdNotLike(String value) {
            addCriterion("TROLE_ID not like", value, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdIn(List<String> values) {
            addCriterion("TROLE_ID in", values, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdNotIn(List<String> values) {
            addCriterion("TROLE_ID not in", values, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdBetween(String value1, String value2) {
            addCriterion("TROLE_ID between", value1, value2, "troleId");
            return (Criteria) this;
        }

        public Criteria andTroleIdNotBetween(String value1, String value2) {
            addCriterion("TROLE_ID not between", value1, value2, "troleId");
            return (Criteria) this;
        }

        public Criteria andTadminIdIsNull() {
            addCriterion("TADMIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andTadminIdIsNotNull() {
            addCriterion("TADMIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTadminIdEqualTo(String value) {
            addCriterion("TADMIN_ID =", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdNotEqualTo(String value) {
            addCriterion("TADMIN_ID <>", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdGreaterThan(String value) {
            addCriterion("TADMIN_ID >", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdGreaterThanOrEqualTo(String value) {
            addCriterion("TADMIN_ID >=", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdLessThan(String value) {
            addCriterion("TADMIN_ID <", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdLessThanOrEqualTo(String value) {
            addCriterion("TADMIN_ID <=", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdLike(String value) {
            addCriterion("TADMIN_ID like", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdNotLike(String value) {
            addCriterion("TADMIN_ID not like", value, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdIn(List<String> values) {
            addCriterion("TADMIN_ID in", values, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdNotIn(List<String> values) {
            addCriterion("TADMIN_ID not in", values, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdBetween(String value1, String value2) {
            addCriterion("TADMIN_ID between", value1, value2, "tadminId");
            return (Criteria) this;
        }

        public Criteria andTadminIdNotBetween(String value1, String value2) {
            addCriterion("TADMIN_ID not between", value1, value2, "tadminId");
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