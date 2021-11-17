package com.nyh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpressExample() {
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

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUserphoneIsNull() {
            addCriterion("userphone is null");
            return (Criteria) this;
        }

        public Criteria andUserphoneIsNotNull() {
            addCriterion("userphone is not null");
            return (Criteria) this;
        }

        public Criteria andUserphoneEqualTo(String value) {
            addCriterion("userphone =", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotEqualTo(String value) {
            addCriterion("userphone <>", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneGreaterThan(String value) {
            addCriterion("userphone >", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneGreaterThanOrEqualTo(String value) {
            addCriterion("userphone >=", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLessThan(String value) {
            addCriterion("userphone <", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLessThanOrEqualTo(String value) {
            addCriterion("userphone <=", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLike(String value) {
            addCriterion("userphone like", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotLike(String value) {
            addCriterion("userphone not like", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneIn(List<String> values) {
            addCriterion("userphone in", values, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotIn(List<String> values) {
            addCriterion("userphone not in", values, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneBetween(String value1, String value2) {
            addCriterion("userphone between", value1, value2, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotBetween(String value1, String value2) {
            addCriterion("userphone not between", value1, value2, "userphone");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
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

        public Criteria andIntimeIsNull() {
            addCriterion("intime is null");
            return (Criteria) this;
        }

        public Criteria andIntimeIsNotNull() {
            addCriterion("intime is not null");
            return (Criteria) this;
        }

        public Criteria andIntimeEqualTo(Date value) {
            addCriterion("intime =", value, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeNotEqualTo(Date value) {
            addCriterion("intime <>", value, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeGreaterThan(Date value) {
            addCriterion("intime >", value, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeGreaterThanOrEqualTo(Date value) {
            addCriterion("intime >=", value, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeLessThan(Date value) {
            addCriterion("intime <", value, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeLessThanOrEqualTo(Date value) {
            addCriterion("intime <=", value, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeIn(List<Date> values) {
            addCriterion("intime in", values, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeNotIn(List<Date> values) {
            addCriterion("intime not in", values, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeBetween(Date value1, Date value2) {
            addCriterion("intime between", value1, value2, "intime");
            return (Criteria) this;
        }

        public Criteria andIntimeNotBetween(Date value1, Date value2) {
            addCriterion("intime not between", value1, value2, "intime");
            return (Criteria) this;
        }

        public Criteria andOuttimeIsNull() {
            addCriterion("outtime is null");
            return (Criteria) this;
        }

        public Criteria andOuttimeIsNotNull() {
            addCriterion("outtime is not null");
            return (Criteria) this;
        }

        public Criteria andOuttimeEqualTo(Date value) {
            addCriterion("outtime =", value, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeNotEqualTo(Date value) {
            addCriterion("outtime <>", value, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeGreaterThan(Date value) {
            addCriterion("outtime >", value, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("outtime >=", value, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeLessThan(Date value) {
            addCriterion("outtime <", value, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeLessThanOrEqualTo(Date value) {
            addCriterion("outtime <=", value, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeIn(List<Date> values) {
            addCriterion("outtime in", values, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeNotIn(List<Date> values) {
            addCriterion("outtime not in", values, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeBetween(Date value1, Date value2) {
            addCriterion("outtime between", value1, value2, "outtime");
            return (Criteria) this;
        }

        public Criteria andOuttimeNotBetween(Date value1, Date value2) {
            addCriterion("outtime not between", value1, value2, "outtime");
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

        public Criteria andSysPhoneIsNull() {
            addCriterion("sysPhone is null");
            return (Criteria) this;
        }

        public Criteria andSysPhoneIsNotNull() {
            addCriterion("sysPhone is not null");
            return (Criteria) this;
        }

        public Criteria andSysPhoneEqualTo(String value) {
            addCriterion("sysPhone =", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneNotEqualTo(String value) {
            addCriterion("sysPhone <>", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneGreaterThan(String value) {
            addCriterion("sysPhone >", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("sysPhone >=", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneLessThan(String value) {
            addCriterion("sysPhone <", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneLessThanOrEqualTo(String value) {
            addCriterion("sysPhone <=", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneLike(String value) {
            addCriterion("sysPhone like", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneNotLike(String value) {
            addCriterion("sysPhone not like", value, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneIn(List<String> values) {
            addCriterion("sysPhone in", values, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneNotIn(List<String> values) {
            addCriterion("sysPhone not in", values, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneBetween(String value1, String value2) {
            addCriterion("sysPhone between", value1, value2, "sysPhone");
            return (Criteria) this;
        }

        public Criteria andSysPhoneNotBetween(String value1, String value2) {
            addCriterion("sysPhone not between", value1, value2, "sysPhone");
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