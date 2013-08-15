package com.qingdy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qingdy.dao.AnswerDao;
import com.qingdy.dao.BlogDao;
import com.qingdy.dao.EvaluateDao;
import com.qingdy.dao.LoanDao;
import com.qingdy.dao.MallDao;
import com.qingdy.dao.NewsDao;
import com.qingdy.dao.ProductDao;
import com.qingdy.dao.QuestionDao;
import com.qingdy.dao.TransactionDao;
import com.qingdy.dao.UserDao;
import com.qingdy.dao.UserDetailDao;
import com.qingdy.model.Answer;
import com.qingdy.model.Evaluate;
import com.qingdy.model.Loan;
import com.qingdy.model.Mall;
import com.qingdy.model.News;
import com.qingdy.model.Product;
import com.qingdy.model.Question;
import com.qingdy.model.Transaction;
import com.qingdy.model.User;
import com.qingdy.model.Blog;
import com.qingdy.model.UserDetail;
import com.qingdy.service.BaseManager;
import com.qingdy.service.FacadeManager;

@Service("facadeManager")
public class FacadeManagerImpl extends BaseManager implements FacadeManager {
	
	@Resource(name = "blogDao")
	private BlogDao blogDao;
	@Resource(name = "evaluateDao")
	private EvaluateDao evaluateDao;
	@Resource(name = "userDao")
	private UserDao userDao;
	@Resource(name = "userDetailDao")
	private UserDetailDao userDetailDao;
	@Resource(name = "answerDao")
	private AnswerDao answerDao;
	@Resource(name = "loanDao")
	private LoanDao loanDao;
	@Resource(name = "mallDao")
	private MallDao mallDao;
	@Resource(name = "newsDao")
	private NewsDao newsDao;
	@Resource(name = "productDao")
	private ProductDao productDao;
	@Resource(name = "questionDao")
	private QuestionDao questionDao;
	@Resource(name = "transactionDao")
	private TransactionDao transactionDao;
	
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}

	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

	public void setMallDao(MallDao mallDao) {
		this.mallDao = mallDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public void setEvaluateDao(EvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}
	/*
	 * User(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void removeUser(String username) {
		userDao.removeUser(username);
	}
	
	@Override
	public boolean validateUser(User aUser) {
		User user = getUser(aUser.getUsername());
		if (user != null && user.getPassword().equals(aUser.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public boolean isUserExists(String username) {
		return userDao.isUserExists(username);
	}	

	@Override
	public void verifyUser(Long id, boolean verify) {
		
	}

	/*
	 * UserDetail(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#saveUserDetail(com.qingdy.model.UserDetail)
	 */
	@Override
	public void saveUserDetail(UserDetail userDetail) {
		this.userDetailDao.saveUserDetail(userDetail);
	}
	
	@Override
	public UserDetail getUserDetail(String username) {
		return this.userDetailDao.getUserDetail(username);
	}
	
	@Override
	public void updateUserDetail(UserDetail userDetail) {
		userDetailDao.updateUserDetail(userDetail);
	}
	

	@Override
	public List<UserDetail> getUsersDetail() {
		List<UserDetail> list = userDetailDao.getUsersDetail();
		return list;
	}
	
	/*
	 * Blog(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getBlog(java.lang.Long)
	 */
	@Override
	public List<Blog> getBlogs() {
		return blogDao.getBlogs();
	}
	
	@Override
	public Blog getBlog(Long id) {
		return blogDao.getBlog(id);
	}

	@Override
	public void saveBlog(Blog blog) {
		blogDao.saveBlog(blog);
	}

	@Override
	public void removeBlog(Long id) {
		blogDao.removeBlog(id);
	}

	/*
	 * Evaluate(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getEvaluate(java.lang.Long)
	 */
	@Override
	public Evaluate getEvaluate(Long id) {
		return evaluateDao.getEvaluate(id);
	}

	@Override
	public void saveEvaluate(Evaluate evaluate) {
		evaluateDao.saveBlogEvaluate(evaluate);
	}

	@Override
	public void removeEvaluate(Long id) {
		evaluateDao.removeEvaluate(id);
	}

	@Override
	public void verifyBlog(Long id, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Mall(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getMalls()
	 */
	@Override
	public List<Mall> getMalls() {
		return mallDao.getMalls();
	}

	@Override
	public Mall getMall(Long id) {
		return mallDao.getMall(id);
	}

	@Override
	public void saveMall(Mall mall) {
		mallDao.saveMall(mall);
	}

	@Override
	public void removeMall(Long id) {
		mallDao.removeMall(id);
	}

	@Override
	public void verifyMall(Long id, boolean verify) {
		mallDao.verifyMall(id, verify);
	}

	/*
	 * Product(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getProducts()
	 */
	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public Product getProduct(Long id) {
		return productDao.getProduct(id);
	}

	@Override
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	@Override
	public void removeProduct(Long id) {
		productDao.removeProduct(id);
	}

	@Override
	public void verifyProduct(Long id, boolean verify) {
		productDao.verifyProduct(id, verify);
	}

	/*
	 * Question(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getqQuestions()
	 */
	@Override
	public List<Question> getQuestions() {
		return questionDao.getQuestions();
	}

	@Override
	public Question getQuestion(Long id) {
		return questionDao.getQuestion(id);
	}

	@Override
	public List<Question> getQuestion(String username) {
		return questionDao.getQuestion(username);
	}

	@Override
	public void saveQuestion(Question question) {
		questionDao.saveQuestion(question);
	}

	@Override
	public void verifyQuestion(Long id, boolean verify) {
		questionDao.verifyQuestion(id, verify);
	}
	
	@Override
	public void bestAnswer(Long id) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Answer(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getAnswers()
	 */
	@Override
	public List<Answer> getAnswers() {
		return answerDao.getAnswers();
	}

	@Override
	public List<Answer> getAnswers(Long qid) {
		return answerDao.getAnswers(qid);
	}

	@Override
	public Answer getAnswer(Long id) {
		return answerDao.getAnswer(id);
	}

	@Override
	public void saveAnswer(Answer answer) {
		answerDao.saveAnswer(answer);
	}

	@Override
	public void removeAnswer(Long id) {
		answerDao.removeAnswer(id);
	}

	@Override
	public void verifyAnswer(Long id, boolean verify) {
		answerDao.verifyAnswer(id, verify);
	}

	/*
	 * News(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getNews()
	 */
	@Override
	public List<News> getNews() {
		return newsDao.getNews();
	}

	@Override
	public News getNews(Long id) {
		return newsDao.getNews(id);
	}

	@Override
	public void saveNews(News news) {
		newsDao.saveNews(news);
	}

	@Override
	public void removeNews(Long id) {
		newsDao.removeNews(id);
	}

	/*
	 * Loan(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#getLoans()
	 */
	@Override
	public List<Loan> getLoans() {
		return loanDao.getLoans();
	}

	@Override
	public Loan getLoan(Long id) {
		return loanDao.getLoan(id);
	}

	@Override
	public void saveLoan(Loan loan) {
		loanDao.saveLoan(loan);
	}

	@Override
	public void removeLoan(Long id) {
		loanDao.removeLoan(id);
	}

	@Override
	public void verifyLoan(Long id, boolean verify) {
		loanDao.verifyLoan(id, verify);
	}

	/*
	 * Transaction(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#geTransactions()
	 */
	@Override
	public List<Transaction> geTransactions() {
		return transactionDao.geTransactions();
	}

	@Override
	public Transaction getTransaction(Long id) {
		return transactionDao.getTransaction(id);
	}

	@Override
	public Transaction getTransaction(String username) {
		return getTransaction(username);
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionDao.saveTransaction(transaction);
	}

	@Override
	public void removeTransaction(Long id) {
		transactionDao.removeTransaction(id);
	}

	@Override
	public void verifyTransaction(Long id, boolean verify) {
		transactionDao.verifyTransaction(id, verify);
	}


}
