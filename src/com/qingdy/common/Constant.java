package com.qingdy.common;

import com.qingdy.model.Blog;
import com.qingdy.model.Loan;
import com.qingdy.model.Mall;
import com.qingdy.model.Product;
import com.qingdy.model.Question;
import com.sun.istack.FinalArrayList;

public class Constant {
	
	public static final Integer DEFAULT_SIZE = 20;
	public static final Integer DEFAULT_PAGE = 1;

	public static final Integer UNREAD = 0;	
	public static final Integer READEN = 1;
	
	public static final Integer FEMALE = 0;
	public static final Integer MALE = 1;
	
	public static final Integer ADMIN = 0;
	public static final Integer LOANER = 1;
	public static final Integer LENDER = 2;
	
	public static final Integer ANSWER = 1;
	public static final Integer BLOG = 2;
	public static final Integer EVALUATE = 3;
	public static final Integer LOAN = 4;
	public static final Integer MALL = 5;
	public static final Integer PRODUCT = 6;
	public static final Integer QUESTION = 7;
	public static final Integer TRANSACTION = 8;
	public static final Integer NEWS = 9;
	public static final Integer USER = 10;
	
	public static Class getClass(Integer type) {
		Class cla = null;

		if (type.equals(MALL)) {
			cla = Mall.class;
		}
		else if (type.equals(LOAN)) {
			cla = Loan.class;
		}
		else if (type.equals(PRODUCT)) {
			cla = Product.class;
		}
		else if (type.equals(QUESTION)) {
			cla = Question.class;
		}
		else if (type.equals(BLOG)) {
			cla = Blog.class;
		}
		return cla;
	}
}
