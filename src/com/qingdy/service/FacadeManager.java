package com.qingdy.service;

import java.util.List;

import com.qingdy.model.Answer;
import com.qingdy.model.Blog;
import com.qingdy.model.Evaluate;
import com.qingdy.model.Favourite;
import com.qingdy.model.Loan;
import com.qingdy.model.Mall;
import com.qingdy.model.Message;
import com.qingdy.model.News;
import com.qingdy.model.Product;
import com.qingdy.model.Question;
import com.qingdy.model.Score;
import com.qingdy.model.Timeline;
import com.qingdy.model.Transaction;
import com.qingdy.model.User;
import com.qingdy.model.UserDetail;
import com.qingdy.model.Visit;
import com.qingdy.model.domain.Forums;
import com.qingdy.model.domain.Item;
import com.qingdy.model.domain.Specialist;
import com.qingdy.model.domain.UserTop;

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
	
	public List<Blog> getBlogsByUser(String username);
	
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
	
	public Mall getMall(String username);
	
	public void saveMall(Mall mall);
	
	public void removeMall(Long id);
	
	public void verifyMall(Long id, boolean verify);
	
	/*
	 * Product
	 */
	public List<Product> getProducts(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
	
	public Product getProduct(Long id);
	
	public List<Product> getProducts(String username);
	
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
	 * Specialist
	 */
	public List<Score> getSpecialists(int size, int page, String field, String value, String operator, String sidx, String sord, boolean verify);
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
	
	public List<Transaction> getTransactions(String username);
	
	public void saveTransaction(Transaction transaction);
	
	public void removeTransaction(Long id);
	
	public void verifyTransaction(Long id, boolean verify);
	
	/*
	 * UserTop
	 */
	public UserTop getUserTop(String username);
	
	/*
	 * Forum
	 */
	public Forums getForums();
	
	/*
	 * Message
	 */
	public void addMessage(Message message);
	
	public void removeMessage(Long id);
	
	public void readMessage(Long id);
	
	public List<Message> getSendMessages(String username);
	
	public List<Message> getReceiveMessages(String username);
	
	public Integer getUnreadCount(String username);
	
	/*
	 * Timeline
	 */
	public List<Timeline> getTimelines(int size, int page);
	
	public List<Timeline> getTimelinesByUser(String username, int size, int page);
	
	/*
	 * Search
	 */
	public List<Item> search(int type, String keyword, int size, int page);
	
	/*
	 * Visit
	 */
	public List<Visit> getMallVisits(Long id, int size, int page);
	
	public void visitMall(Visit visit);
	
	
	/*
	 * Favourite
	 */
	public void addFavourite(Favourite favourite);
	
	public void deleteFavourite(Long id);
	
	public List<Favourite> getFavourites(String username);
	
	public Integer getFavouriteCount(Integer type, Long oid);
	
	public boolean isFavourite(Integer type, Long oid, String username);
}
