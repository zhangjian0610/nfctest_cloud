package com.bupt.cardtest.model.bean;

import java.util.ArrayList;
import java.util.List;

public class RoleResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoleResourceExample() {
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

        public Criteria andTresourceIdIsNull() {
            addCriterion("TRESOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTresourceIdIsNotNull() {
            addCriterion("TRESOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTresourceIdEqualTo(String value) {
            addCriterion("TRESOURCE_ID =", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdNotEqualTo(String value) {
            addCriterion("TRESOURCE_ID <>", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdGreaterThan(String value) {
            addCriterion("TRESOURCE_ID >", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRESOURCE_ID >=", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdLessThan(String value) {
            addCriterion("TRESOURCE_ID <", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdLessThanOrEqualTo(String value) {
            addCriterion("TRESOURCE_ID <=", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdLike(String value) {
            addCriterion("TRESOURCE_ID like", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdNotLike(String value) {
            addCriterion("TRESOURCE_ID not like", value, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdIn(List<String> values) {
            addCriterion("TRESOURCE_ID in", values, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdNotIn(List<String> values) {
            addCriterion("TRESOURCE_ID not in", values, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdBetween(String value1, String value2) {
            addCriterion("TRESOURCE_ID between", value1, value2, "tresourceId");
            return (Criteria) this;
        }

        public Criteria andTresourceIdNotBetween(String value1, String value2) {
            addCriterion("TRESOURCE_ID not between", value1, value2, "tresourceId");
            return (Criteria) this;
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