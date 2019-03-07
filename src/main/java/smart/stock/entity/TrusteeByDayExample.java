package smart.stock.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrusteeByDayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TrusteeByDayExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdIsNull() {
            addCriterion("trustee_id is null");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdIsNotNull() {
            addCriterion("trustee_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdEqualTo(Long value) {
            addCriterion("trustee_id =", value, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdNotEqualTo(Long value) {
            addCriterion("trustee_id <>", value, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdGreaterThan(Long value) {
            addCriterion("trustee_id >", value, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("trustee_id >=", value, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdLessThan(Long value) {
            addCriterion("trustee_id <", value, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdLessThanOrEqualTo(Long value) {
            addCriterion("trustee_id <=", value, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdIn(List<Long> values) {
            addCriterion("trustee_id in", values, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdNotIn(List<Long> values) {
            addCriterion("trustee_id not in", values, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdBetween(Long value1, Long value2) {
            addCriterion("trustee_id between", value1, value2, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andTrusteeIdNotBetween(Long value1, Long value2) {
            addCriterion("trustee_id not between", value1, value2, "trusteeId");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(String value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(String value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(String value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(String value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(String value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(String value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLike(String value) {
            addCriterion("date like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotLike(String value) {
            addCriterion("date not like", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<String> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<String> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(String value1, String value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(String value1, String value2) {
            addCriterion("date not between", value1, value2, "date");
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

        public Criteria andPrincipalIsNull() {
            addCriterion("principal is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalIsNotNull() {
            addCriterion("principal is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalEqualTo(BigDecimal value) {
            addCriterion("principal =", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotEqualTo(BigDecimal value) {
            addCriterion("principal <>", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalGreaterThan(BigDecimal value) {
            addCriterion("principal >", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("principal >=", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLessThan(BigDecimal value) {
            addCriterion("principal <", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("principal <=", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalIn(List<BigDecimal> values) {
            addCriterion("principal in", values, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotIn(List<BigDecimal> values) {
            addCriterion("principal not in", values, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("principal between", value1, value2, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("principal not between", value1, value2, "principal");
            return (Criteria) this;
        }

        public Criteria andTotalUnitIsNull() {
            addCriterion("total_unit is null");
            return (Criteria) this;
        }

        public Criteria andTotalUnitIsNotNull() {
            addCriterion("total_unit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalUnitEqualTo(Integer value) {
            addCriterion("total_unit =", value, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitNotEqualTo(Integer value) {
            addCriterion("total_unit <>", value, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitGreaterThan(Integer value) {
            addCriterion("total_unit >", value, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_unit >=", value, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitLessThan(Integer value) {
            addCriterion("total_unit <", value, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitLessThanOrEqualTo(Integer value) {
            addCriterion("total_unit <=", value, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitIn(List<Integer> values) {
            addCriterion("total_unit in", values, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitNotIn(List<Integer> values) {
            addCriterion("total_unit not in", values, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitBetween(Integer value1, Integer value2) {
            addCriterion("total_unit between", value1, value2, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andTotalUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("total_unit not between", value1, value2, "totalUnit");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueIsNull() {
            addCriterion("net_unit_value is null");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueIsNotNull() {
            addCriterion("net_unit_value is not null");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueEqualTo(BigDecimal value) {
            addCriterion("net_unit_value =", value, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueNotEqualTo(BigDecimal value) {
            addCriterion("net_unit_value <>", value, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueGreaterThan(BigDecimal value) {
            addCriterion("net_unit_value >", value, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("net_unit_value >=", value, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueLessThan(BigDecimal value) {
            addCriterion("net_unit_value <", value, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("net_unit_value <=", value, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueIn(List<BigDecimal> values) {
            addCriterion("net_unit_value in", values, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueNotIn(List<BigDecimal> values) {
            addCriterion("net_unit_value not in", values, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_unit_value between", value1, value2, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andNetUnitValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("net_unit_value not between", value1, value2, "netUnitValue");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(BigDecimal value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(BigDecimal value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(BigDecimal value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(BigDecimal value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<BigDecimal> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<BigDecimal> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andIncomeIsNull() {
            addCriterion("income is null");
            return (Criteria) this;
        }

        public Criteria andIncomeIsNotNull() {
            addCriterion("income is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeEqualTo(BigDecimal value) {
            addCriterion("income =", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotEqualTo(BigDecimal value) {
            addCriterion("income <>", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeGreaterThan(BigDecimal value) {
            addCriterion("income >", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("income >=", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeLessThan(BigDecimal value) {
            addCriterion("income <", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("income <=", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeIn(List<BigDecimal> values) {
            addCriterion("income in", values, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotIn(List<BigDecimal> values) {
            addCriterion("income not in", values, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income between", value1, value2, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income not between", value1, value2, "income");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnIsNull() {
            addCriterion("rate_of_return is null");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnIsNotNull() {
            addCriterion("rate_of_return is not null");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnEqualTo(BigDecimal value) {
            addCriterion("rate_of_return =", value, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnNotEqualTo(BigDecimal value) {
            addCriterion("rate_of_return <>", value, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnGreaterThan(BigDecimal value) {
            addCriterion("rate_of_return >", value, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rate_of_return >=", value, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnLessThan(BigDecimal value) {
            addCriterion("rate_of_return <", value, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rate_of_return <=", value, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnIn(List<BigDecimal> values) {
            addCriterion("rate_of_return in", values, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnNotIn(List<BigDecimal> values) {
            addCriterion("rate_of_return not in", values, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate_of_return between", value1, value2, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andRateOfReturnNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate_of_return not between", value1, value2, "rateOfReturn");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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