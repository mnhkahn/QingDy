package com.qingdy.common;

import javassist.expr.Instanceof;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.cyeam.util.ConvertUtil;
import com.cyeam.util.StringUtil;

public class cRestrictions {
	
	public static DetachedCriteria getRestrictions(Class clazz, String field, String value, String operator, String sidx, String sord, boolean verify) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		criteria.add(cRestrictions.getRestrictions(field, value, operator));
		
		// false for get all items and true for get verified items
		if (verify) {
			criteria.add(cRestrictions.getRestrictions(verify));
		}
		criteria.addOrder(cRestrictions.getRestrictions(sidx, sord));
		
		return criteria;
	}
	
	public static DetachedCriteria getRestrictions(Class clazz, String field[], String value[], String operator[], String sidx, String sord, boolean verify) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		System.out.println("*********************" + field.length + value.length + operator.length);
		for (int i = 0; i < field.length; i++) {
			if (value[i].equals("")) {
				continue;
			}
			if (StringUtil.isNumeric(value[i])) {
				criteria.add(cRestrictions.getRestrictions(field[i], Integer.parseInt(value[i]), operator[i]));
			}
			else if (StringUtil.isDouble(value[i])) {
				System.out.println(value[i]);
				criteria.add(cRestrictions.getRestrictions(field[i], Float.parseFloat(value[i]), operator[i]));
			}
			else if (StringUtil.isDate(value[i], Constant.DATE_FORMAT)) {
				System.out.println(ConvertUtil.str2Date(value[i], Constant.DATE_FORMAT));
				criteria.add(cRestrictions.getRestrictions(field[i], ConvertUtil.str2Date(value[i], Constant.DATE_FORMAT), operator[i]));
			}
			else {
				criteria.add(cRestrictions.getRestrictions(field[i], value[i], operator[i]));
			}
		}
		
		// false for get all items and true for get verified items
		if (verify) {
			criteria.add(cRestrictions.getRestrictions(verify));
		}
		criteria.addOrder(cRestrictions.getRestrictions(sidx, sord));
		
		return criteria;
	}
	
	public static DetachedCriteria getRestrictions(Class clazz, String field[], String value[], String operator[], boolean verify) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		System.out.println("*********************" + field.length + value.length + operator.length);
		for (int i = 0; i < field.length; i++) {
			if (value[i].equals("")) {
				continue;
			}
			if (StringUtil.isNumeric(value[i])) {
				criteria.add(cRestrictions.getRestrictions(field[i], Integer.parseInt(value[i]), operator[i]));
			}
			else if (StringUtil.isDouble(value[i])) {
				System.out.println(value[i]);
				criteria.add(cRestrictions.getRestrictions(field[i], Float.parseFloat(value[i]), operator[i]));
			}
			else if (StringUtil.isDate(value[i], Constant.DATE_FORMAT)) {
				System.out.println(ConvertUtil.str2Date(value[i], Constant.DATE_FORMAT));
				criteria.add(cRestrictions.getRestrictions(field[i], ConvertUtil.str2Date(value[i], Constant.DATE_FORMAT), operator[i]));
			}
			else {
				criteria.add(cRestrictions.getRestrictions(field[i], value[i], operator[i]));
			}
		}
		
		// false for get all items and true for get verified items
		if (verify) {
			criteria.add(cRestrictions.getRestrictions(verify));
		}
		
		return criteria;
	}
	
	public static SimpleExpression getRestrictions(String field, Object value, String operator) {
		SimpleExpression simpleExpression = null;
		switch (operator) {
		case "eq":
			simpleExpression = Restrictions.eq(field, value);
			break;
		case "bw":
			simpleExpression = Restrictions.ge(field, value);
			break;
		case "ew":
			simpleExpression = Restrictions.le(field, value);
			break;
		case "cn":
			simpleExpression = Restrictions.like(field, "%" + value + "%");
		default:
			break;
		}
		
		return simpleExpression;
	}
	
	public static SimpleExpression getRestrictions(boolean verify) {
		SimpleExpression simpleExpression = null;
		simpleExpression = Restrictions.eq("verify", 1);
		
		return simpleExpression;
	}
	
	public static Order getRestrictions(String sidx, String sord) {
		Order order = null;
		switch (sord) {
		case "asc":
			order = Order.asc(sidx);
			break;
		case "desc":
			order = Order.desc(sidx);
			break;
		default:
			break;
		}
		return order;
	}
}
