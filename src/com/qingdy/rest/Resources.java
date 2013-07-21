package com.qingdy.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qingdy.common.SQLParameters;
import com.qingdy.dao.AreaDao;
import com.qingdy.dao.ClientsDao;
import com.qingdy.dao.LendTypeDao;
import com.qingdy.dao.LoanDao;
import com.qingdy.dao.LoanPawnDao;
import com.qingdy.dao.MallDao;
import com.qingdy.dao.MessageDao;
import com.qingdy.dao.NewsDao;
import com.qingdy.dao.NewsclassesDao;
import com.qingdy.dao.PawnDao;
import com.qingdy.dao.ProductDao;
import com.qingdy.dao.QuestionclassesDao;
import com.qingdy.dao.RatetypeDao;
import com.qingdy.dao.RepaymethodDao;
import com.qingdy.dao.SpecialityDao;
import com.qingdy.dao.TransactionDao;
import com.qingdy.dao.UserDao;
import com.qingdy.dao.UsesofloanDao;
import com.qingdy.dao.impl.AreaDaoImpl;
import com.qingdy.dao.impl.ClientsDaoImpl;
import com.qingdy.dao.impl.LaonPawnDaoImpl;
import com.qingdy.dao.impl.LendTypeDaoImpl;
import com.qingdy.dao.impl.LoanDaoImpl;
import com.qingdy.dao.impl.MallDaoImpl;
import com.qingdy.dao.impl.MessageDaoImpl;
import com.qingdy.dao.impl.NewsDaoImpl;
import com.qingdy.dao.impl.NewsclassesDaoImpl;
import com.qingdy.dao.impl.PawnDaoImpl;
import com.qingdy.dao.impl.ProductDaoImpl;
import com.qingdy.dao.impl.QuestionclassesDaoImpl;
import com.qingdy.dao.impl.RatetypeDaoImpl;
import com.qingdy.dao.impl.RepaymethodDaoImpl;
import com.qingdy.dao.impl.SpecialityDaoImpl;
import com.qingdy.dao.impl.TransactionDaoImpl;
import com.qingdy.dao.impl.UserDaoImpl;
import com.qingdy.dao.impl.UsesofloanDaoImpl;

import com.qingdy.domain.*;

@Path("/metadata")
public class Resources {
	private MallDao mallDao = new MallDaoImpl();
	private ProductDao productDao = new ProductDaoImpl();
	private LoanDao loanDao = new LoanDaoImpl();
	private TransactionDao transactionDao = new TransactionDaoImpl();
	private LoanPawnDao loanPawnDao = new LaonPawnDaoImpl();
	
	private NewsDao newsDao = new NewsDaoImpl();
	
	private UserDao userDao = new UserDaoImpl();
	private MessageDao messageDao = new MessageDaoImpl();
	
	
	private AreaDao areaDao = new AreaDaoImpl();
	private ClientsDao clientsDao = new ClientsDaoImpl();
	private LendTypeDao lendTypeDao = new LendTypeDaoImpl();
	private NewsclassesDao newsclassesDao = new NewsclassesDaoImpl();
	private PawnDao pawnDao = new PawnDaoImpl();
	private QuestionclassesDao questionclassesDao = new QuestionclassesDaoImpl();
	private RatetypeDao ratetypeDao = new RatetypeDaoImpl();
	private RepaymethodDao repaymethodDao = new RepaymethodDaoImpl();
	private SpecialityDao specialityDao = new SpecialityDaoImpl();
	private UsesofloanDao usesofloanDao = new UsesofloanDaoImpl();
	
	@Path("/members/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response login(QdMember user) {
		QdMember loginUser = userDao.getUser(user.getUsername(), user.getPassword());
		
		if (loginUser != null) {
			return Response.ok(loginUser).build();
		}
		else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@Path("/members/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response register(QdMember user) {
		int result = userDao.addUser(user);
		
		if (result == 1)
			return Response.status(Response.Status.CREATED).build();
		else
			return Response.status(Response.Status.FORBIDDEN).build();
	}
	
	@Path("/members/{username}/exists")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response isUserExists(@PathParam("username") String username) {
		int result = userDao.getUid(username);
		if (result != 1) {
			return Response.noContent().build();
		}
		// exists
		else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@Path("/malls/verify")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedMalls(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		SQLParameters parameters = new SQLParameters(size, page, field, value, operator, sidx, sord);
		return Response.ok(mallDao.getAllMallList(parameters)).build();
	}
	
	@Path("/malls/{id}/positive")
	@POST
	public Response activateMalls(@PathParam("id") int id) {
		mallDao.verifyMall(id, 1);
		return Response.ok().build();
	}
	
	@Path("/malls/{id}/negative")
	@POST
	public Response deactivateMalls(@PathParam("id") int id) {
		mallDao.verifyMall(id, 0);
		return Response.ok().build();
	}
	
	@Path("/products/verify")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedProducts(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		SQLParameters parameters = new SQLParameters(size, page, field, value, operator, sidx, sord);
		return Response.ok(productDao.getProductList(parameters)).build();
	}
	
	@Path("/products/{id}/positive")
	@POST
	public Response activateProducts(@PathParam("id") int id) {
		productDao.verifyProduct(id, 1);
		return Response.ok().build();
	}
	
	@Path("/products/{id}/negative")
	@POST
	public Response deactivateProducts(@PathParam("id") int id) {
		productDao.verifyProduct(id, 0);
		return Response.ok().build();
	}
	
	@Path("/loans/verify")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedLoans(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		SQLParameters parameters = new SQLParameters(size, page, field, value, operator, sidx, sord);
		return Response.ok(loanDao.getLoanList(parameters)).build();
	}
	
	@Path("/loans/{id}/positive")
	@POST
	public Response activateLoans(@PathParam("id") int id) {
		loanDao.verifyLoan(id, 1);
		return Response.ok().build();
	}
	
	@Path("/loans/{id}/negative")
	@POST
	public Response deactivateLoans(@PathParam("id") int id) {
		loanDao.verifyLoan(id, 0);
		return Response.ok().build();
	}
	
	@Path("/loans/")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addLoan(QdLoan loan) {
		System.out.println(loan.getHaspawn());
		return Response.ok().build();
	}
	
	@Path("/usesofloan/{id}")
	@POST
	public Response addLoanUsesofloan(@PathParam("id") int id) {
		return Response.ok().build();
	}
	
	@Path("/pawn/{id}")
	@POST
	public Response addLoanPawn(@PathParam("id") int id) {
		return Response.ok().build();
	}
	
	@Path("/transactions/verify")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedTransactions(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		SQLParameters parameters = new SQLParameters(size, page, field, value, operator, sidx, sord);
		return Response.ok(transactionDao.geTransactionList(parameters)).build();
	}
	
	@Path("/transactions/{id}/positive")
	@POST
	public Response activateTransactions(@PathParam("id") int id) {
		transactionDao.verifyTransaction(id, 1);
		return Response.ok().build();
	}
	
	@Path("/transactions/{id}/negative")
	@POST
	public Response deactivateTransactions(@PathParam("id") int id) {
		transactionDao.verifyTransaction(id, 0);
		return Response.ok().build();
	}
	
	@Path("/news")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addNews(QdNews news) {
		int result = newsDao.addNews(news);
		if (result == 1) {
			return Response.ok(Response.Status.CREATED).build();
		}
		else
			return Response.ok(Response.Status.FORBIDDEN).build();
	}
	
	@Path("/news/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedNews(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		SQLParameters parameters = new SQLParameters(size, page, field, value, operator, sidx, sord);
		return Response.ok(newsDao.getNewsList(parameters)).build();
	}
	
	@Path("/news/{id}")
	@DELETE
	public Response deleteNews(@PathParam("id") int id) {
		int result = newsDao.removeNews(id);
		if (result == 1) {
			return Response.ok(Response.Status.CREATED).build();
		}
		else
			return Response.ok(Response.Status.FORBIDDEN).build();
	}
	
	@Path("/news/{id}")
	@PUT
	public Response updateNews(QdNews news) {
		int result = newsDao.updateNews(news);
		if (result == 1) {
			return Response.ok(Response.Status.CREATED).build();
		}
		else
			return Response.ok(Response.Status.FORBIDDEN).build();
	}
	
	@Path("/news/{id}")
	@GET
	public Response getNews(@PathParam("id") int id) {
		return Response.ok(newsDao.getNews(id)).build();
	}
	
	@Path("/messages/{id}/unread")
	@GET
	public Response getUnreadMessages(@PathParam("id") int id) {
		return Response.ok(messageDao.getUnreadCount(id)).build();
	}
	
	@Path("/productpawn")
	@GET
	public Response getProductPawns() {
		System.out.println("fuck");
		return Response.ok().build();
	}
	
	@Path("/areas/province")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProvince() {
		return Response.ok(areaDao.getProvince()).build();
	}

	@Path("/areas/city")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCity(@QueryParam("province") int province) {
		return Response.ok(areaDao.getCity(province)).build();
	}

	@Path("/areas/county")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCounty(@QueryParam("city") int city) {
		return Response.ok(areaDao.getCounty(city)).build();
	}
	
	@Path("/clients")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClients() {
		return Response.ok(clientsDao.getClients()).build();
	}
	
	@Path("/lendtype")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLendType() {
		return Response.ok(lendTypeDao.getLendtype()).build();
	}
	
	@Path("/newsclasses")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNewsClasses() {
		return Response.ok(newsclassesDao.getNewsclasses()).build();
	}
	
	@Path("/pawns")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPawns() {
		return Response.ok(pawnDao.getPawn()).build();
	}
	
	@Path("/questionclasses")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionClasses() {
		return Response.ok(questionclassesDao.getQuestionclasses()).build();
	}
	
	@Path("/ratetype")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRateType() {
		return Response.ok(ratetypeDao.getRatetypes()).build();
	}
	
	@Path("/repaymethod")
	@GET
	public Response getRepayMethod() {
		return Response.ok(repaymethodDao.getRepaymethods()).build();
	}
	
	@Path("/speciality")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpeciality() {
		return Response.ok(specialityDao.getSpecialities()).build();
	}
	
	@Path("/usesofloan")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsesOfLoan() {
		return Response.ok(usesofloanDao.getUsesofloans()).build();
	}
	

/*	@Path("/history")
	@GET
	public List<String> getHistory() {
		List<String> list = new LinkedList<String>();
		list.add("hello");
		return list;
	}*/
	
	@Path("/history")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public QdHistory addHistory(QdHistory history) {
		System.out.println(history.getUid());
		return history;
	}
	
/*	@Path("/history/{id}/")
	@GET
	public String readPersonByName(@PathParam("id") int id) {
		return Integer.toString(id);
	}*/
	
	@Path("/history/{id}/activate")
	@POST
	public String activate(@PathParam("id") int id) {
		return Integer.toString(id) + "activate";
	}
	
	@Path("/history/{id}/")
	@GET
	public String search(@PathParam("id") int id, @QueryParam("keyword") String keyword) {
		return Integer.toString(id) + keyword;
	}
}
