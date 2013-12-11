package com.qingdy.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import com.cyeam.util.ConvertUtil;
import com.cyeam.util.FileUtil;
import com.cyeam.util.PropUtil;
import com.qingdy.common.Constant;
import com.qingdy.dao.AnswerDao;
import com.qingdy.dao.BlogDao;
import com.qingdy.dao.EvaluateDao;
import com.qingdy.dao.FavouriteDao;
import com.qingdy.dao.LoanDao;
import com.qingdy.dao.MallDao;
import com.qingdy.dao.MessageDao;
import com.qingdy.dao.NewsDao;
import com.qingdy.dao.ProductDao;
import com.qingdy.dao.QuestionDao;
import com.qingdy.dao.ScoreDao;
import com.qingdy.dao.SpecialistDao;
import com.qingdy.dao.TimelineDao;
import com.qingdy.dao.TransactionDao;
import com.qingdy.dao.UserDao;
import com.qingdy.dao.UserDetailDao;
import com.qingdy.dao.VisitDao;
import com.qingdy.model.Answer;
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
import com.qingdy.model.Blog;
import com.qingdy.model.UserDetail;
import com.qingdy.model.Visit;
import com.qingdy.model.domain.Forums;
import com.qingdy.model.domain.Item;
import com.qingdy.model.domain.Specialist;
import com.qingdy.model.domain.UserTop;
import com.qingdy.model.domain.VisitDate;
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
	@Resource(name = "specialistDao")
	private SpecialistDao specialistDao;
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
	@Resource(name = "scoreDao")
	private ScoreDao scoreDao;
	@Resource(name = "messageDao")
	private MessageDao messageDao;
	@Resource(name = "timelineDao")
	private TimelineDao timelineDao;
	@Resource(name = "visitDao")
	private VisitDao visitDao;
	@Resource(name = "favouriteDao")
	private FavouriteDao favouriteDao;

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

	public void setSpecialistDao(SpecialistDao specialistDao) {
		this.specialistDao = specialistDao;
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

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void setFavouriteDao(FavouriteDao favouriteDao) {
		this.favouriteDao = favouriteDao;
	}

	/*
	 * User(non-Javadoc)
	 * 
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
	 * 
	 * @see
	 * com.qingdy.service.FacadeManager#saveUserDetail(com.qingdy.model.UserDetail
	 * )
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
	 * 
	 * @see com.qingdy.service.FacadeManager#getBlog(java.lang.Long)
	 */
	@Override
	public List<Blog> getBlogsByUser(String username) {
		return blogDao.getBlogs(username);
	}

	@Override
	public List<Blog> getBlogs(int size, int page, String field, String value,
			String operator, String sidx, String sord, boolean verify) {
		return blogDao.getBlogs(size, page, field, value, operator, sidx, sord,
				verify);
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

	@Override
	public void verifyBlog(Long id, boolean verify) {
		blogDao.verifyBlog(id, verify);
	}

	/*
	 * Evaluate(non-Javadoc)
	 * 
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

	/*
	 * Mall(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#getMalls()
	 */
	@Override
	public List<Mall> getMalls(int size, int page, String field, String value,
			String operator, String sidx, String sord, boolean verify) {
		return mallDao.getMalls(size, page, field, value, operator, sidx, sord,
				verify);
	}
	
	@Override
	public Integer getMallCount(String[] field, String[] value,
			String[] operator, boolean verify) {
		// TODO Auto-generated method stub
		return mallDao.getMallsCount(field, value, operator, verify);
	}

	@Override
	public Mall getMall(Long id) {
		return mallDao.getMall(id);
	}

	@Override
	public Mall getMall(String username) {
		return mallDao.getMall(username);
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
	 * 
	 * @see com.qingdy.service.FacadeManager#getProducts()
	 */
	@Override
	public List<Product> getProducts(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		return productDao.getProducts(size, page, field, value, operator, sidx,
				sord, verify);
	}
	
	@Override
	public List<Product> getProducts(int size, int page, String[] field,
			String[] value, String[] operator, String sidx, String sord, boolean verify) {
		// TODO Auto-generated method stub
		return productDao.getProducts(size, page, field, value, operator, sidx, sord, verify);
	}
	

	@Override
	public Integer getProductsCount(String[] field, String[] value,
			String[] operator, boolean verify) {
		// TODO Auto-generated method stub
		return productDao.getProductsCount(field, value, operator, verify);
	}

	@Override
	public List<Product> getProducts(String username) {
		return productDao.getProduct(username);
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
	 * 
	 * @see com.qingdy.service.FacadeManager#getqQuestions()
	 */
	@Override
	public List<Question> getQuestions(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		return questionDao.getQuestions(size, page, field, value, operator,
				sidx, sord, verify);
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
	public void removeQuestion(Long id) {
		questionDao.removeQuestion(id);
	}

	@Override
	public void bestAnswer(Long qid, Long aid) {
		questionDao.bestAnswer(qid, aid);
	}

	/*
	 * Answer(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#getAnswers()
	 */
	@Override
	public List<Answer> getAnswers(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		return answerDao.getAnswers(size, page, field, value, operator, sidx,
				sord, verify);
	}

	@Override
	public List<Answer> getAnswers(String username) {
		return answerDao.getAnswer(username);
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
		
		if (!verify) {
			Score score = new Score();
			score.setPoster(answerDao.getAnswer(id).getPoster());
			score.setQuestion(answerDao.getAnswer(id).getQuestion());
			score.setScore(-1);
			scoreDao.addScore(score);
		}
	}

	/*
	 * Specialist
	 */
	@Override
	public List<Specialist> getSpecialists(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		List<Specialist> specialists = specialistDao.getSpecialists(size, page,
				field, value, operator, sidx, sord, verify);
		for (int i = 0; i < specialists.size(); i++) {
			System.out.println(specialists.get(i).getUser().getUsername());
			System.out.println(mallDao.getMall(specialists.get(i).getUser()
					.getUsername()));
			specialists.get(i).setId(
					mallDao.getMall(
							specialists.get(i).getUser().getUsername())
							.getId());
		}
		return specialists;
	}
	
	@Override
	public List<Specialist> getSpecialists(int size, int page, String[] field,
			String[] value, String[] operator, String sidx, String sord, boolean verify) {
		// TODO Auto-generated method stub
		return specialistDao.getSpecialists(size, page, field, value, operator, sidx, sord, verify);
	}

	@Override
	public Integer getSpecialistsCount(String[] field, String[] value,
			String[] operator, boolean verify) {
		// TODO Auto-generated method stub
		return specialistDao.getSpecialistsCount(field, value, operator, verify);
	}
	
	@Override
	public Integer getSpecialistsCount(String field, String value, String operator, boolean verify) {
		return specialistDao.getSpecialistsCount(field, value, operator, verify);
	}


	/*
	 * News(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#getNews()
	 */
	@Override
	public List<News> getNews(int size, int page, String field, String value,
			String operator, String sidx, String sord, boolean verify) {
		return newsDao.getNews(size, page, field, value, operator, sidx, sord,
				verify);
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
	 * 
	 * @see com.qingdy.service.FacadeManager#getLoans()
	 */
	@Override
	public List<Loan> getLoans(int size, int page, String field, String value,
			String operator, String sidx, String sord, boolean verify) {
		return loanDao.getLoans(size, page, field, value, operator, sidx, sord,
				verify);
	}
	
	@Override
	public List<Loan> getLoans(int size, int page, String[] field,
			String[] value, String[] operator, String sidx, String sord, boolean verify) {
		// TODO Auto-generated method stub
		return loanDao.getLoans(size, page, field, value, operator, sidx, sord, verify);
	}

	@Override
	public Integer getLoansCount(String[] field, String[] value,
			String[] operator, boolean verify) {
		// TODO Auto-generated method stub
		return loanDao.getLoansCount(field, value, operator, verify);
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
	public List<Loan> getLoans(String username) {
		return loanDao.getLoans(username);
	}

	@Override
	public void verifyLoan(Long id, boolean verify) {
		loanDao.verifyLoan(id, verify);
	}

	/*
	 * Transaction(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#geTransactions()
	 */
	@Override
	public List<Transaction> getTransactions(int size, int page, String field,
			String value, String operator, String sidx, String sord,
			boolean verify) {
		return transactionDao.geTransactions(size, page, field, value,
				operator, sidx, sord, verify);
	}

	@Override
	public Transaction getTransaction(Long id) {
		return transactionDao.getTransaction(id);
	}

	@Override
	public List<Transaction> getTransactions(String username) {
		return transactionDao.getTransactions(username);
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

	/*
	 * UserTop(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#getUserTop(java.lang.String)
	 */

	@Override
	public UserTop getUserTop(String username) {
		User user = getUser(username);
		UserDetail userDetail = getUserDetail(username);
		Message message = new Message();

		UserTop userTop = new UserTop();
		userTop.setName(userDetail.getLastname() + userDetail.getFirstname());
		userTop.setAvatar(userDetail.getAvatar());
		userTop.setMessage(getUnreadCount(username));
		userTop.setGroupId(user.getGroupid());

		return userTop;
	}

	/*
	 * Forum
	 */
	public Forums getForums() {
		Forums forums = new Forums();

		forums.setMallCount(mallDao.getMallCount());
		forums.setSpecialistCount(scoreDao.getSpecialistCount());
		forums.setTransactionCount(transactionDao.getTransactionCount());
		forums.setTimelines(getTimelines(Constant.DEFAULT_SIZE,
				Constant.DEFAULT_PAGE));

		return forums;
	}

	/*
	 * Message(non-Javadoc)
	 * 
	 * @see
	 * com.qingdy.service.FacadeManager#addMessage(com.qingdy.model.Message)
	 */
	@Override
	public void addMessage(Message message) {
		messageDao.saveMessage(message);
	}

	@Override
	public void readMessage(Long id) {
		messageDao.readMessage(id);
	}

	@Override
	public void removeMessage(Long id) {
		messageDao.removeMessage(id);
	}

	@Override
	public List<Message> getSendMessages(String username) {
		return messageDao.getSendMessages(username);
	}

	@Override
	public List<Message> getReceiveMessages(String username) {
		return messageDao.getReceiveMessages(username);
	}

	@Override
	public Integer getUnreadCount(String username) {
		return messageDao.getUnreadCount(username);
	}

	/*
	 * Timeline(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#getTimelines(int, int)
	 */
	@Override
	public List<Timeline> getTimelines(int size, int page) {
		return timelineDao.getTimelines(size, page);
	}

	@Override
	public List<Timeline> getTimelinesByUser(String username, int size, int page) {
		return timelineDao.geTimelinesByUser(username, size, page);
	}

	/*
	 * Search(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#search(int, java.lang.String, int,
	 * int)
	 */
	@Override
	public List<Item> search(int type, String keyword, int size, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Visit(non-Javadoc)
	 * 
	 * @see com.qingdy.service.FacadeManager#getMallVisits(java.lang.Long, int,
	 * int)
	 */
	@Override
	public List<Visit> getVisits(Long id, int type, int size, int page) {
		return visitDao.getVisits(type, id, size, page);
	}

	@Override
	public void visit(Visit visit) {
		visitDao.addVisit(visit);
	}

	@Override
	public int getVisits(Long id, int type) {
		// TODO Auto-generated method stub
		return visitDao.getVisits(type, id);
	}

	@Override
	public List<Visit> getUserVisits(String username, int size, int page) {
		// TODO Auto-generated method stub
		return visitDao.getUserVisits(username, size, page);
	}

	@Override
	public List<VisitDate> getVisits(Long id, int type, Date startTime,
			Date endTime) {
		return visitDao.getVisits(type, id, startTime, endTime);
	}
	
	/*
	 * Favourite(non-Javadoc)
	 * 
	 * @see
	 * com.qingdy.service.FacadeManager#addFavourite(com.qingdy.model.Favourite)
	 */

	@Override
	public void addFavourite(Favourite favourite) {
		favouriteDao.addFavourite(favourite);
	}

	@Override
	public void deleteFavourite(Long id) {
		favouriteDao.deleteFavourite(id);
	}

	@Override
	public List<Favourite> getFavourites(String username) {
		return favouriteDao.getFavourites(username);
	}

	@Override
	public Integer getFavouriteCount(Integer type, Long oid) {
		return favouriteDao.getFavouriteCount(type, oid);
	}

	@Override
	public boolean isFavourite(Integer type, Long oid, String username) {
		return favouriteDao.isFavourite(type, oid, username);
	}

	/*
	 * Upload(non-Javadoc)
	 * 
	 * @see
	 * com.qingdy.service.FacadeManager#upload(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String upload(HttpServletRequest request, HttpServletResponse response, int type) {
		String message = "";
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			
			try {
				items = upload.parseRequest(request);

				for (int i = 0; i < items.size(); i++) {
					FileItem item = ((FileItem) (items.get(i)));
					if (item.getName() == null) {
						continue;
					}
					String fileName = item.getName();
					Date date = new Date();
					
					String path = "";
					
					// Check format
					String format = item.getName().substring(item.getName().indexOf(".") + 1);
					if (!format.equals(ConvertUtil.PNG) && !format.equals(ConvertUtil.JPEG) && !format.equals(ConvertUtil.BMP) && !format.equals(ConvertUtil.GIF) && !format.equals(ConvertUtil.JPG)) {
						message = "{\"err\":\"Wrong File Format" + "" + "\",\"msg\":\"" + date.getTime() + item.getName() + "\"}";
						System.out.println("Wrong File Format" + format);
						continue;
					}
					// Check file size
					// The size of the file item, in bytes.
					if (item.getSize() > Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), PropUtil.UPLOAD_MAX_SIZE))) {
						message = "{\"err\":\"Wrong File Size" + "" + "\",\"msg\":\"" + date.getTime() + item.getName() + "\"}";
						System.out.println("Wrong File Size");
						continue;
					}
					
					
					if (type == PropUtil.UPLOAD_NEWS) {
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), "newsUrlPath") + fileName;
					}
					else if (type == PropUtil.UPLOAD_IMAGES) {
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), "imagesUrlPath") + fileName;
					}
					
					File uploadedFile = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + "temp");
					item.write(uploadedFile);
					File file = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + fileName);

					ConvertUtil.converter(uploadedFile, ConvertUtil.PNG, file);

					System.out.println("Upload Success");
					message = "{\"err\":\"" + "" + "\",\"msg\":\"" + path + "\"}";
					break;
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return message;
	}
	
	@Override
	public String upload(HttpServletRequest request, HttpServletResponse response, int type, String username) {
		String message = "";
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			
			try {
				items = upload.parseRequest(request);
				
				for (int i = 0; i < items.size(); i++) {
//					FileItem item = (FileItem) iterator.next();
					FileItem item = ((FileItem) (items.get(i)));
					System.out.println(item.getName() + "**********************");
					if (item.getName() == null) {
						continue;
					}
					Date date = new Date();
					
					// Check format
					String path = "";
					String format = item.getName().substring(item.getName().indexOf(".") + 1);
					if (!format.equals(ConvertUtil.PNG) && !format.equals(ConvertUtil.JPEG) && !format.equals(ConvertUtil.BMP) && !format.equals(ConvertUtil.GIF) && !format.equals(ConvertUtil.JPG)) {
						message = "{\"err\":\"Wrong File Format" + "" + "\",\"msg\":\"" + date.getTime() + item.getName() + "\"}";
						System.out.println("Wrong File Format" + format);
						continue;
					}
					// Check file size
					// The size of the file item, in bytes.
					if (item.getSize() > Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), PropUtil.UPLOAD_MAX_SIZE))) {
						message = "{\"err\":\"Wrong File Size" + "" + "\",\"msg\":\"" + date.getTime() + item.getName() + "\"}";
						System.out.println("Wrong File Size");
						continue;
					}
					
					String fileName = PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + username + "." + ConvertUtil.PNG;;
					int width = 0, height = 0;
					if (type == PropUtil.UPLOAD_AVATAR) {
						width = Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), PropUtil.AVATAR_WIDTH));
						height = Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), PropUtil.AVATAR_HEIGHT));
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), "avatarUrlPath");
					}
					else if (type == PropUtil.UPLOAD_SKIN) {
						width = Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), PropUtil.SKIN_WIDTH));
						height = Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), PropUtil.SKIN_HEIGHT));
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), "skinUrlPath");
					}
					else if (type == PropUtil.UPLOAD_NEWS) {
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), "newsUrlPath");
					}
					else if (type == PropUtil.UPLOAD_IMAGES) {
						width = Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), "slideWidth"));
						height = Integer.parseInt(PropUtil.getProps(request.getServletContext().getRealPath("/"), "slideHeight"));
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), "imagesUrlPath");
					}
					else if (type == PropUtil.UPLOAD_USER) {
						path = PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + username + "/" + item.getName();
						fileName = PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + username + "/" + item.getName();
						width = -1;
					}
					
					File uploadedFile = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + "temp");
					item.write(uploadedFile);
					File file = new File(fileName);

					if (width > 0) {
						ConvertUtil.scale(uploadedFile, file, ConvertUtil.PNG, width, height, false);
						path += (username + "." + ConvertUtil.PNG);
						
					}
					else if (width == 0) {
						path += (username + "." + ConvertUtil.PNG);
						ConvertUtil.converter(uploadedFile, ConvertUtil.PNG, file);
					}
					else {
						// Upload file
						// Create Directory
						new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), type) + username + "/").mkdirs();
						item.write(file);
						
					}
					message = "{\"err\":\"" + "" + "\",\"msg\":\"" + path + "\"}";
					System.out.println("Upload Success");
					
					break;
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return message;
	}

	/*
	 * Configuration(non-Javadoc)
	 * @see com.qingdy.service.FacadeManager#updateJson(java.lang.String, java.lang.String)
	 */
	
	@Override
	public void updateConfigFile(String path, String content) {
		FileUtil.Str2File(path, content);
	}

}
