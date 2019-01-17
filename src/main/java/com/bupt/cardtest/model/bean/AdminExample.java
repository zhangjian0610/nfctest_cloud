package com.bupt.cardtest.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdminExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("PWD is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("PWD is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("PWD =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("PWD <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("PWD >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("PWD >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("PWD <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("PWD <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("PWD like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("PWD not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("PWD in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("PWD not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("PWD between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("PWD not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeIsNull() {
            addCriterion("CREATEDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeIsNotNull() {
            addCriterion("CREATEDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeEqualTo(Date value) {
            addCriterion("CREATEDATETIME =", value, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeNotEqualTo(Date value) {
            addCriterion("CREATEDATETIME <>", value, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeGreaterThan(Date value) {
            addCriterion("CREATEDATETIME >", value, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATEDATETIME >=", value, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeLessThan(Date value) {
            addCriterion("CREATEDATETIME <", value, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATEDATETIME <=", value, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeIn(List<Date> values) {
            addCriterion("CREATEDATETIME in", values, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeNotIn(List<Date> values) {
            addCriterion("CREATEDATETIME not in", values, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeBetween(Date value1, Date value2) {
            addCriterion("CREATEDATETIME between", value1, value2, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATEDATETIME not between", value1, value2, "createdatetime");
            return (Criteria) this;
        }

        public Criteria andLockSymbolIsNull() {
            addCriterion("LOCK_SYMBOL is null");
            return (Criteria) this;
        }

        public Criteria andLockSymbolIsNotNull() {
            addCriterion("LOCK_SYMBOL is not null");
            return (Criteria) this;
        }

        public Criteria andLockSymbolEqualTo(Integer value) {
            addCriterion("LOCK_SYMBOL =", value, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolNotEqualTo(Integer value) {
            addCriterion("LOCK_SYMBOL <>", value, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolGreaterThan(Integer value) {
            addCriterion("LOCK_SYMBOL >", value, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOCK_SYMBOL >=", value, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolLessThan(Integer value) {
            addCriterion("LOCK_SYMBOL <", value, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolLessThanOrEqualTo(Integer value) {
            addCriterion("LOCK_SYMBOL <=", value, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolIn(List<Integer> values) {
            addCriterion("LOCK_SYMBOL in", values, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolNotIn(List<Integer> values) {
            addCriterion("LOCK_SYMBOL not in", values, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolBetween(Integer value1, Integer value2) {
            addCriterion("LOCK_SYMBOL between", value1, value2, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andLockSymbolNotBetween(Integer value1, Integer value2) {
            addCriterion("LOCK_SYMBOL not between", value1, value2, "lockSymbol");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsIsNull() {
            addCriterion("REMAINING_LOGINS is null");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsIsNotNull() {
            addCriterion("REMAINING_LOGINS is not null");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsEqualTo(Integer value) {
            addCriterion("REMAINING_LOGINS =", value, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsNotEqualTo(Integer value) {
            addCriterion("REMAINING_LOGINS <>", value, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsGreaterThan(Integer value) {
            addCriterion("REMAINING_LOGINS >", value, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsGreaterThanOrEqualTo(Integer value) {
            addCriterion("REMAINING_LOGINS >=", value, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsLessThan(Integer value) {
            addCriterion("REMAINING_LOGINS <", value, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsLessThanOrEqualTo(Integer value) {
            addCriterion("REMAINING_LOGINS <=", value, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsIn(List<Integer> values) {
            addCriterion("REMAINING_LOGINS in", values, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsNotIn(List<Integer> values) {
            addCriterion("REMAINING_LOGINS not in", values, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsBetween(Integer value1, Integer value2) {
            addCriterion("REMAINING_LOGINS between", value1, value2, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andRemainingLoginsNotBetween(Integer value1, Integer value2) {
            addCriterion("REMAINING_LOGINS not between", value1, value2, "remainingLogins");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeIsNull() {
            addCriterion("MODIFYDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeIsNotNull() {
            addCriterion("MODIFYDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeEqualTo(Date value) {
            addCriterion("MODIFYDATETIME =", value, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeNotEqualTo(Date value) {
            addCriterion("MODIFYDATETIME <>", value, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeGreaterThan(Date value) {
            addCriterion("MODIFYDATETIME >", value, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFYDATETIME >=", value, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeLessThan(Date value) {
            addCriterion("MODIFYDATETIME <", value, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFYDATETIME <=", value, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeIn(List<Date> values) {
            addCriterion("MODIFYDATETIME in", values, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeNotIn(List<Date> values) {
            addCriterion("MODIFYDATETIME not in", values, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeBetween(Date value1, Date value2) {
            addCriterion("MODIFYDATETIME between", value1, value2, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andModifydatetimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFYDATETIME not between", value1, value2, "modifydatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeIsNull() {
            addCriterion("VALIDDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeIsNotNull() {
            addCriterion("VALIDDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("VALIDDATETIME =", value, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("VALIDDATETIME <>", value, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("VALIDDATETIME >", value, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("VALIDDATETIME >=", value, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeLessThan(Date value) {
            addCriterionForJDBCDate("VALIDDATETIME <", value, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("VALIDDATETIME <=", value, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("VALIDDATETIME in", values, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("VALIDDATETIME not in", values, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("VALIDDATETIME between", value1, value2, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andValiddatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("VALIDDATETIME not between", value1, value2, "validdatetime");
            return (Criteria) this;
        }

        public Criteria andLevelTypeIsNull() {
            addCriterion("LEVEL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLevelTypeIsNotNull() {
            addCriterion("LEVEL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLevelTypeEqualTo(Integer value) {
            addCriterion("LEVEL_TYPE =", value, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeNotEqualTo(Integer value) {
            addCriterion("LEVEL_TYPE <>", value, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeGreaterThan(Integer value) {
            addCriterion("LEVEL_TYPE >", value, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("LEVEL_TYPE >=", value, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeLessThan(Integer value) {
            addCriterion("LEVEL_TYPE <", value, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("LEVEL_TYPE <=", value, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeIn(List<Integer> values) {
            addCriterion("LEVEL_TYPE in", values, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeNotIn(List<Integer> values) {
            addCriterion("LEVEL_TYPE not in", values, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeBetween(Integer value1, Integer value2) {
            addCriterion("LEVEL_TYPE between", value1, value2, "levelType");
            return (Criteria) this;
        }

        public Criteria andLevelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("LEVEL_TYPE not between", value1, value2, "levelType");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
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