package com.qingdy.service;

import java.util.List;

import com.qingdy.model.Answer;
import com.qingdy.model.Blog;
import com.qingdy.model.Evaluate;
import com.qingdy.model.Loan;
import com.qingdy.model.Mall;
import com.qingdy.model.News;
import com.qingdy.model.Product;
import com.qingdy.model.Question;
import com.qingdy.model.Transaction;
import com.qingdy.model.User;
import com.qingdy.model.UserDetail;
import com.qingdy.model.UserTop;

public interface FacadeManager {
	/*
	 * User
	 */
	public User getUser(String username);
	
	public List<User> getUsers();
	
	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public boolean isUserExists(String username);
	
	public void removeUser(String username);
	
	public boolean validateUser(User user);
	
	public void verifyUser(Long id, boolean verify);
	
	/*
	 * UserDetail
	 */
	public void saveUserDetail(UserDetail userDetail);
	
	public void updateUserDetail(UserDetail userDetail);
	
	public UserDetail getUserDetail(String username);
	
	public List<UserDetail> getUsersDetail();
	
	/*
	 * Blog
	 */
	public List<Blog> getBlogs(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Blog getBlog(Long id);
	
	public void saveBlog(Blog blog);
	
	public void removeBlog(Long id);
	
	public void verifyBlog(Long id, boolean verify);
	
	/*
	 * Evaluate
	 */	
	public Evaluate getEvaluate(Long id);
	
	public void saveEvaluate(Evaluate evaluate);
	
	public void removeEvaluate(Long id);
	
	/*
	 * Mall
	 */
	public List<Mall> getMalls(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Mall getMall(Long id);
	
	public void saveMall(Mall mall);
	
	public void removeMall(Long id);
	
	public void verifyMall(Long id, boolean verify);
	
	/*
	 * Product
	 */
	public List<Product> getProducts(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Product getProduct(Long id);
	
	public void saveProduct(Product product);
	
	public void removeProduct(Long id);
	
	public void verifyProduct(Long id, boolean verify);
	
	/*
	 * Question
	 */
	public List<Question> getQuestions(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Question getQuestion(Long id);
	
	public List<Question> getQuestion(String username);
	
	public void saveQuestion(Question question);
	
	public void removeQuestion(Long id);
	
	public void verifyQuestion(Long id, boolean verify);
	
	/*
	 * Answer
	 */
	public List<Answer> getAnswers(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public List<Answer> getAnswers(Long qid);
	
	public List<Answer> getAnswers(String username);
	
	public Answer getAnswer(Long id);
	
	public void saveAnswer(Answer answer);
	
	public void bestAnswer(Long qid, Long aid);
	
	public void removeAnswer(Long id);
	
	public void verifyAnswer(Long id, boolean verify);
	
	/*
	 * News
	 */
	public List<News> getNews(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public News getNews(Long id);
	
	public void saveNews(News news);
	
	public void removeNews(Long id);
	
	/*
	 * Loan
	 */
	public List<Loan> getLoans(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public List<Loan> getLoans(String username);
	
	public Loan getLoan(Long id);
	
	public void saveLoan(Loan loan);
	
	public void removeLoan(Long id);
	
	public void verifyLoan(Long id, boolean verify);
	
	/*
	 * Transaction
	 */
	public List<Transaction> getTransactions(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Transaction getTransaction(Long id);
	
	public Transaction getTransaction(String username);
	
	public void saveTransaction(Transaction transaction);
	
	public void removeTransaction(Long id);
	
	public void verifyTransaction(Long id, boolean verify);
	
	/*
	 * UserTop
	 */
	public UserTop getUserTop(String username);
}
