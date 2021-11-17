package com.nyh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourierExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourierExample() {
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

        public Criteria andExnameIsNull() {
            addCriterion("exname is null");
            return (Criteria) this;
        }

        public Criteria andExnameIsNotNull() {
            addCriterion("exname is not null");
            return (Criteria) this;
        }

        public Criteria andExnameEqualTo(String value) {
            addCriterion("exname =", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameNotEqualTo(String value) {
            addCriterion("exname <>", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameGreaterThan(String value) {
            addCriterion("exname >", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameGreaterThanOrEqualTo(String value) {
            addCriterion("exname >=", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameLessThan(String value) {
            addCriterion("exname <", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameLessThanOrEqualTo(String value) {
            addCriterion("exname <=", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameLike(String value) {
            addCriterion("exname like", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameNotLike(String value) {
            addCriterion("exname not like", value, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameIn(List<String> values) {
            addCriterion("exname in", values, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameNotIn(List<String> values) {
            addCriterion("exname not in", values, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameBetween(String value1, String value2) {
            addCriterion("exname between", value1, value2, "exname");
            return (Criteria) this;
        }

        public Criteria andExnameNotBetween(String value1, String value2) {
            addCriterion("exname not between", value1, value2, "exname");
            return (Criteria) this;
        }

        public Criteria andExphoneIsNull() {
            addCriterion("exphone is null");
            return (Criteria) this;
        }

        public Criteria andExphoneIsNotNull() {
            addCriterion("exphone is not null");
            return (Criteria) this;
        }

        public Criteria andExphoneEqualTo(String value) {
            addCriterion("exphone =", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneNotEqualTo(String value) {
            addCriterion("exphone <>", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneGreaterThan(String value) {
            addCriterion("exphone >", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneGreaterThanOrEqualTo(String value) {
            addCriterion("exphone >=", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneLessThan(String value) {
            addCriterion("exphone <", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneLessThanOrEqualTo(String value) {
            addCriterion("exphone <=", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneLike(String value) {
            addCriterion("exphone like", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneNotLike(String value) {
            addCriterion("exphone not like", value, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneIn(List<String> values) {
            addCriterion("exphone in", values, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneNotIn(List<String> values) {
            addCriterion("exphone not in", values, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneBetween(String value1, String value2) {
            addCriterion("exphone between", value1, value2, "exphone");
            return (Criteria) this;
        }

        public Criteria andExphoneNotBetween(String value1, String value2) {
            addCriterion("exphone not between", value1, value2, "exphone");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idcard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idcard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idcard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idcard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idcard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idcard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idcard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idcard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idcard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idcard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idcard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idcard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idcard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andExpasswordIsNull() {
            addCriterion("expassword is null");
            return (Criteria) this;
        }

        public Criteria andExpasswordIsNotNull() {
            addCriterion("expassword is not null");
            return (Criteria) this;
        }

        public Criteria andExpasswordEqualTo(String value) {
            addCriterion("expassword =", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordNotEqualTo(String value) {
            addCriterion("expassword <>", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordGreaterThan(String value) {
            addCriterion("expassword >", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("expassword >=", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordLessThan(String value) {
            addCriterion("expassword <", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordLessThanOrEqualTo(String value) {
            addCriterion("expassword <=", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordLike(String value) {
            addCriterion("expassword like", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordNotLike(String value) {
            addCriterion("expassword not like", value, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordIn(List<String> values) {
            addCriterion("expassword in", values, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordNotIn(List<String> values) {
            addCriterion("expassword not in", values, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordBetween(String value1, String value2) {
            addCriterion("expassword between", value1, value2, "expassword");
            return (Criteria) this;
        }

        public Criteria andExpasswordNotBetween(String value1, String value2) {
            addCriterion("expassword not between", value1, value2, "expassword");
            return (Criteria) this;
        }

        public Criteria andTrannumberIsNull() {
            addCriterion("trannumber is null");
            return (Criteria) this;
        }

        public Criteria andTrannumberIsNotNull() {
            addCriterion("trannumber is not null");
            return (Criteria) this;
        }

        public Criteria andTrannumberEqualTo(String value) {
            addCriterion("trannumber =", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberNotEqualTo(String value) {
            addCriterion("trannumber <>", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberGreaterThan(String value) {
            addCriterion("trannumber >", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberGreaterThanOrEqualTo(String value) {
            addCriterion("trannumber >=", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberLessThan(String value) {
            addCriterion("trannumber <", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberLessThanOrEqualTo(String value) {
            addCriterion("trannumber <=", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberLike(String value) {
            addCriterion("trannumber like", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberNotLike(String value) {
            addCriterion("trannumber not like", value, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberIn(List<String> values) {
            addCriterion("trannumber in", values, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberNotIn(List<String> values) {
            addCriterion("trannumber not in", values, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberBetween(String value1, String value2) {
            addCriterion("trannumber between", value1, value2, "trannumber");
            return (Criteria) this;
        }

        public Criteria andTrannumberNotBetween(String value1, String value2) {
            addCriterion("trannumber not between", value1, value2, "trannumber");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNull() {
            addCriterion("regtime is null");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNotNull() {
            addCriterion("regtime is not null");
            return (Criteria) this;
        }

        public Criteria andRegtimeEqualTo(Date value) {
            addCriterion("regtime =", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotEqualTo(Date value) {
            addCriterion("regtime <>", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThan(Date value) {
            addCriterion("regtime >", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("regtime >=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThan(Date value) {
            addCriterion("regtime <", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThanOrEqualTo(Date value) {
            addCriterion("regtime <=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeIn(List<Date> values) {
            addCriterion("regtime in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotIn(List<Date> values) {
            addCriterion("regtime not in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeBetween(Date value1, Date value2) {
            addCriterion("regtime between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotBetween(Date value1, Date value2) {
            addCriterion("regtime not between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeIsNull() {
            addCriterion("prelogtime is null");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeIsNotNull() {
            addCriterion("prelogtime is not null");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeEqualTo(Date value) {
            addCriterion("prelogtime =", value, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeNotEqualTo(Date value) {
            addCriterion("prelogtime <>", value, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeGreaterThan(Date value) {
            addCriterion("prelogtime >", value, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("prelogtime >=", value, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeLessThan(Date value) {
            addCriterion("prelogtime <", value, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeLessThanOrEqualTo(Date value) {
            addCriterion("prelogtime <=", value, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeIn(List<Date> values) {
            addCriterion("prelogtime in", values, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeNotIn(List<Date> values) {
            addCriterion("prelogtime not in", values, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeBetween(Date value1, Date value2) {
            addCriterion("prelogtime between", value1, value2, "prelogtime");
            return (Criteria) this;
        }

        public Criteria andPrelogtimeNotBetween(Date value1, Date value2) {
            addCriterion("prelogtime not between", value1, value2, "prelogtime");
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