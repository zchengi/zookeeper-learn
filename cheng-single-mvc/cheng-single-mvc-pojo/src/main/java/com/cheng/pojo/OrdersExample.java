package com.cheng.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/10/3 12:41
 */
public class OrdersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<CriteriaAbstract> oredCriteria;

    public OrdersExample() {
        oredCriteria = new ArrayList<>();
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

    public List<CriteriaAbstract> getOredCriteria() {
        return oredCriteria;
    }

    public void or(CriteriaAbstract criteria) {
        oredCriteria.add(criteria);
    }

    public CriteriaAbstract or() {
        CriteriaAbstract criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public CriteriaAbstract createCriteria() {
        CriteriaAbstract criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected CriteriaAbstract createCriteriaInternal() {
        return new CriteriaAbstract();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class AbstractGeneratedCriteria {
        protected List<Criterion> criteria;

        protected AbstractGeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public CriteriaAbstract andIdIsNull() {
            addCriterion("id is null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdIsNotNull() {
            addCriterion("id is not null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumEqualTo(String value) {
            addCriterion("order_num =", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumNotEqualTo(String value) {
            addCriterion("order_num <>", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumGreaterThan(String value) {
            addCriterion("order_num >", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("order_num >=", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumLessThan(String value) {
            addCriterion("order_num <", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumLessThanOrEqualTo(String value) {
            addCriterion("order_num <=", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumLike(String value) {
            addCriterion("order_num like", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumNotLike(String value) {
            addCriterion("order_num not like", value, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumIn(List<String> values) {
            addCriterion("order_num in", values, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumNotIn(List<String> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumBetween(String value1, String value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andOrderNumNotBetween(String value1, String value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdIsNull() {
            addCriterion("item_id is null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (CriteriaAbstract) this;
        }
    }

    public static class CriteriaAbstract extends AbstractGeneratedCriteria {

        protected CriteriaAbstract() {
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