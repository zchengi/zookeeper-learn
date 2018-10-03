package com.cheng.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cheng
 *         2018/10/3 12:41
 */
public class ItemsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<CriteriaAbstract> oredCriteria;

    public ItemsExample() {
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

        public CriteriaAbstract andNameIsNull() {
            addCriterion("name is null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameIsNotNull() {
            addCriterion("name is not null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsIsNull() {
            addCriterion("counts is null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsIsNotNull() {
            addCriterion("counts is not null");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsEqualTo(Integer value) {
            addCriterion("counts =", value, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsNotEqualTo(Integer value) {
            addCriterion("counts <>", value, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsGreaterThan(Integer value) {
            addCriterion("counts >", value, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsGreaterThanOrEqualTo(Integer value) {
            addCriterion("counts >=", value, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsLessThan(Integer value) {
            addCriterion("counts <", value, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsLessThanOrEqualTo(Integer value) {
            addCriterion("counts <=", value, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsIn(List<Integer> values) {
            addCriterion("counts in", values, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsNotIn(List<Integer> values) {
            addCriterion("counts not in", values, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsBetween(Integer value1, Integer value2) {
            addCriterion("counts between", value1, value2, "counts");
            return (CriteriaAbstract) this;
        }

        public CriteriaAbstract andCountsNotBetween(Integer value1, Integer value2) {
            addCriterion("counts not between", value1, value2, "counts");
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