package com.qingdy.rest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.web.util.WebUtils;

import com.cyeam.util.ConvertUtil;
import com.cyeam.util.FileUtil;
import com.cyeam.util.PropUtil;
import com.qingdy.common.Constant;
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
import com.qingdy.model.Timeline;
import com.qingdy.model.Transaction;
import com.qingdy.model.User;
import com.qingdy.model.UserDetail;
import com.qingdy.model.Visit;
import com.qingdy.model.domain.Forums;
import com.qingdy.model.domain.Grid;
import com.qingdy.model.domain.Specialist;
import com.qingdy.model.domain.UserTop;
import com.qingdy.model.domain.VisitDate;
import com.qingdy.service.FacadeManager;
import com.qingdy.shiro.QingDyShiro;

@Path("/metadata")
public class Resources {
	private Log log = LogFactory.getLog(getClass());

	@Resource(name = "facadeManager")
	private FacadeManager facadeManager;

	public String getIP(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
	
	@Path("/user/username")
	@GET
	public Response getUsername() {
		String username = facadeManager.getCurrentUser();
		return Response.noContent().header("username", username).build();
	}

	/*
	 * User
	 */
	@Path("/user/register")
	@POST
	public Response register(User user, @Context HttpServletRequest request) {
		facadeManager.saveUser(user);
		
		UserDetail userDetail = new UserDetail();
		userDetail.setUsername(user.getUsername());
		userDetail.setRegdate(new Date());
		userDetail.setAvatar("/avatar/" + user.getUsername() + "." + ConvertUtil.PNG);
		facadeManager.saveUserDetail(userDetail);
		
		// Default avatar & skin
		File defaultAvatar = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), "defaultAvatar"));
		File avatar = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), "uploadAvatar"));
		FileUtil.copyFile(defaultAvatar, avatar, user.getUsername() + ".png");
		
		if (user.getGroupid() == 2) {
			File defaultSkin = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), "defaultSkin"));
			File skin = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), "uploadSkin"));
			FileUtil.copyFile(defaultSkin, skin, user.getUsername() + ".png");
			
			Mall mall = new Mall();
			mall.setPoster(userDetail);
			mall.setPostDate(new Date());
			facadeManager.saveMall(mall);
		}
		
		// Create user's file for uploading
		File file_date = new File(PropUtil.getProps(request.getServletContext().getRealPath("/"), "uploadFile") + user.getUsername());
		boolean file_true = false;
		if (file_date != null) {
			file_true = file_date.mkdirs();
		}
		else {
			System.out.println("************" + PropUtil.getProps(request.getServletContext().getRealPath("/"), "uploadFile") + user.getUsername());
		}
		
		if (file_true) {
			System.out.println("文件夹建立成功");
		} else {
			System.out.println("文件建立失败");
		}
		
		QingDyShiro.setLogin(user.getUsername(), user.getPassword(), true);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/user/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean rememberMe = request.getParameter("rememberMe").equals("1");
		
		System.out.println(username + password + rememberMe);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean loginSuccess = facadeManager.validateUser(user);
		if (loginSuccess) {
			QingDyShiro.setLogin(user.getUsername(), user.getPassword(), rememberMe);
			
			String fallbackUrl = "/";
	        try {
	            // redirect to previously requested page
	            WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
	        } catch (IOException e) {
	        }
	        return Response.ok().build();
		}
		else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@Path("/user/logout")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginout(@Context HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
        if (subject != null) {           
            subject.logout();
        }
        request.getSession().invalidate();
        
		return Response.noContent().build();
	}

	@Path("/user/exists")
	@HEAD
	public Response isUserExists(@QueryParam("username") String username) {
		System.out.println(username);
		return Response.noContent().header("exist", facadeManager.isUserExists(username)).build();
	}

	@Path("/user/{username}")
	@PUT
	public Response updateUser(@PathParam("username") String username,
			HashMap<String, String> params) {
		User user = facadeManager.getUser(username);
		if (user.getPassword().equals(params.get("oldPWD"))) {
			user.setPassword(params.get("newPWD"));
			facadeManager.updateUser(user);
		}

		return Response.noContent().build();
	}

	@Path("/user/{username}")
	@DELETE
	public Response deleteUser(@PathParam("username") String username) {
		facadeManager.removeUser(username);
		return Response.noContent().build();
	}

	/*
	 * UserDetail
	 */
	// complete user detail
	@Path("/userdetail/{username}")
	@POST
	public Response addUserDetail(@PathParam("username") String username,
			UserDetail userDetail) {
		userDetail.setUsername(username);
		userDetail.setRegdate(new Date());
		facadeManager.saveUserDetail(userDetail);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/userdetail/{username}")
	@PUT
	public Response updateUserDetail(@PathParam("username") String username,
			UserDetail userDetail) {
		userDetail.setAvatar(facadeManager.getUserDetail(username).getAvatar());
		userDetail.setUsername(username);
		facadeManager.updateUserDetail(userDetail);
		return Response.noContent().build();
	}

	@Path("/userdetail/{username}")
	@PUT
	public Response updateUserAvatar(UserDetail userDetail) {
		facadeManager.updateUserDetail(userDetail);
		return Response.status(Response.Status.CREATED).build();
	}

	// get users detail
	@Path("/userdetail")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersDetail() {
		List<UserDetail> list = facadeManager.getUsersDetail();
		return Response.ok(list).build();
	}

	// get user detail
	@Path("/userdetail/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserDetail(@PathParam("username") String username) {
		System.out.println("(((((((((((((" + username);
		UserDetail userDetail = facadeManager.getUserDetail(username);
		return Response.ok(userDetail).build();
	}

	// get contact
	@Path("/userdetail/{username}/contact")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserContact(@PathParam("username") String username) {
		return Response.ok(facadeManager.getUserDetail(username)).build();
	}

	/*
	 * Blog
	 */
	@Path("/blog")
	@POST
	// @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addBlog(Blog blog) {
		blog.setPostDate(new Date());
		facadeManager.saveBlog(blog);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/blog/{id}")
	@PUT
	public Response updateBlog(@PathParam("id") Long id, Blog blog) {
		blog.setId(id);
		Blog oldBlog = facadeManager.getBlog(id);
		blog.setPostDate(oldBlog.getPostDate());
		facadeManager.saveBlog(blog);
		return null;
	}

	@Path("/blog/{id}")
	@DELETE
	public Response deleteBlog(@PathParam("id") Long id) {
		facadeManager.removeBlog(id);
		return Response.noContent().build();
	}

	@Path("/blog/{id}/activate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateBlog(@PathParam("id") Long id) {
		facadeManager.verifyBlog(id, true);
		return Response.noContent().build();
	}

	@Path("/blog/{id}/deactivate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactivateBlog(@PathParam("id") Long id) {
		facadeManager.verifyBlog(id, false);
		return Response.noContent().build();
	}

	@Path("/blog/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlog(@PathParam("id") Long id) {
		Blog blog = facadeManager.getBlog(id);
		return Response.ok(blog).build();
	}

	@Path("/blog")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedBlog(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Blog> blogs = facadeManager.getBlogs(size, page, field, value,
				operator, sidx, sord, true);
		return Response.ok(blogs).build();
	}

	@Path("/blog/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBlogs(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Blog> blogs = facadeManager.getBlogs(size, page, field, value,
				operator, sidx, sord, false);
		return Response.ok(blogs).build();
	}

	@Path("/blog/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlogsByUser(@PathParam("username") String username) {
		List<Blog> blogs = facadeManager.getBlogsByUser(username);
		return Response.ok(blogs).build();
	}

	/*
	 * Evaluate
	 */
	@Path("/evaluate")
	@POST
	public Response addEvaluate(Evaluate evaluate) {
		evaluate.setPostDate(new Date());
		facadeManager.saveEvaluate(evaluate);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/evaluate/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvaluate(@PathParam("id") Long id) {
		Evaluate evaluate = facadeManager.getEvaluate(id);
		return Response.ok(evaluate).build();
	}

	/*
	 * Mall
	 */
	@Path("/mall")
	@POST
	public Response addMall(Mall mall) {
		mall.setPostDate(new Date());
		facadeManager.saveMall(mall);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/mall/{id}")
	@PUT
	public Response updateMall(Mall mall, @PathParam("id") Long id) {
		// Update mall
		mall.setId(id);
		if (mall.getExperience() != null) {
			System.out.println("*******Update mall");
			Mall oldMall = facadeManager.getMall(id);
			mall.setClientLocation(oldMall.getClientLocation());
			mall.setUsesofloan(oldMall.getUsesofloan());
			mall.setSpeciality(oldMall.getSpeciality());
			mall.setClients(oldMall.getClients());
			mall.setLendType(oldMall.getLendType());
			facadeManager.saveMall(mall);
		}
		// Content is null, set mall business setting
		else {
			System.out.println("**********Update businesss setting");
			Mall oldMall = facadeManager.getMall(id);
			oldMall.setClientLocation(mall.getClientLocation());
			oldMall.setUsesofloan(mall.getUsesofloan());
			oldMall.setSpeciality(mall.getSpeciality());
			oldMall.setClients(mall.getClients());
			oldMall.setLendType(mall.getLendType());
			facadeManager.saveMall(oldMall);
		}

		return Response.noContent().build();
	}

	@Path("/mall/{id}")
	@DELETE
	public Response removeMall(@PathParam("id") Long id) {
		facadeManager.removeMall(id);
		return Response.noContent().build();
	}

	@Path("/mall/{id}/activate")
	@POST
	public Response activateMall(@PathParam("id") Long id) {
		facadeManager.verifyMall(id, true);
		return Response.noContent().build();
	}

	@Path("/mall/{id}/deactivate")
	@POST
	public Response deactivateMall(@PathParam("id") Long id) {
		facadeManager.verifyMall(id, false);
		return Response.noContent().build();
	}

	@Path("/mall/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMall(@PathParam("id") Long id) {
		Mall mall = facadeManager.getMall(id);
		return Response.ok(mall).build();
	}

	@Path("/mall/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMall(@PathParam("username") String username) {
		Mall mall = facadeManager.getMall(username);
		return Response.ok(mall).build();
	}

	@Path("/mall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedMalls(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Mall> malls = facadeManager.getMalls(size, page, field, value,
				operator, sidx, sord, true);
		return Response.ok(malls).build();
	}

	@Path("/mall/nkeys/count")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMallCount(@QueryParam("searchField") String[] field,
			@QueryParam("searchString") String[] value,
			@QueryParam("searchOper") String[] operator) {
		Integer count = facadeManager
				.getMallCount(field, value, operator, true);
		return Response.noContent().header("count", count).build();
	}

	@Path("/mall/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMalls(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Mall> malls = facadeManager.getMalls(size, page, field, value,
				operator, sidx, sord, false);
		Grid grid = new Grid();
		grid.setPage(page);
		grid.setRecords(malls.size());
		grid.setTotal(malls.size());
		grid.setRows(malls);

		return Response.ok(grid).build();
	}

	/*
	 * Product
	 */
	@Path("/product")
	@POST
	public Response addProduct(Product product) {
		Mall mall = facadeManager.getMall(product.getPoster().getUsername());
		product.setMall(mall);
		product.setPostDate(new Date());
		facadeManager.saveProduct(product);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/product/{id}")
	@PUT
	public Response updateProduct(Product product, @PathParam("id") Long id) {
		product.setId(id);
		facadeManager.saveProduct(product);
		return Response.noContent().build();
	}

	@Path("/product/{id}")
	@DELETE
	public Response removeProduct(@PathParam("id") Long id) {
		facadeManager.removeProduct(id);
		return Response.noContent().build();
	}

	@Path("/product/{id}/activate")
	@POST
	public Response activateProduct(@PathParam("id") Long id) {
		facadeManager.verifyProduct(id, true);
		return Response.noContent().build();
	}

	@Path("/product/{id}/deactivate")
	@POST
	public Response deactivateProduct(@PathParam("id") Long id) {
		facadeManager.verifyProduct(id, false);
		return Response.noContent().build();
	}

	@Path("/product")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedProducts(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Product> products = facadeManager.getProducts(size, page, field,
				value, operator, sidx, sord, true);
		return Response.ok(products).build();
	}

	// http://localhost:8080/rest/metadata/product/nkeys?rows=10&page=1&searchField=location&searchString=2010-01-01&searchOper=bw&searchField=startTime&searchString=2010-01-01&searchOper=bw&searchField=endTime&searchString=2014-01-01&searchOper=ew&sidx=postDate&sord=asc
	@Path("/product/nkeys")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedProducts(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String[] field,
			@QueryParam("searchString") String[] value,
			@QueryParam("searchOper") String[] operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Product> products = facadeManager.getProducts(size, page, field,
				value, operator, sidx, sord, true);
		return Response.ok(products).build();
	}

	@Path("/product/nkeys/count")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedProductsCount(
			@QueryParam("searchField") String[] field,
			@QueryParam("searchString") String[] value,
			@QueryParam("searchOper") String[] operator) {
		Integer count = facadeManager.getProductsCount(field, value, operator,
				true);
		return Response.noContent().header("count", count).build();
	}

	@Path("/product/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@PathParam("username") String username) {
		List<Product> products = facadeManager.getProducts(username);
		return Response.ok(products).build();
	}

	@Path("/product/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("id") Long id) {
		Product product = facadeManager.getProduct(id);
		return Response.ok(product).build();
	}

	@Path("/product/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Product> products = facadeManager.getProducts(size, page, field,
				value, operator, sidx, sord, false);
		Grid grid = new Grid();
		grid.setPage(page);
		grid.setRecords(products.size());
		grid.setTotal(products.size());
		grid.setRows(products);
		return Response.ok(grid).build();
	}

	/*
	 * Question
	 */
	@Path("/question")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addQuestion(Question question) {
		question.setPostDate(new Date());
		facadeManager.saveQuestion(question);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/question/{id}")
	@PUT
	public Response updateQuestion(Question question, @PathParam("id") Long id) {
		question.setId(id);
		facadeManager.saveQuestion(question);
		return Response.noContent().build();
	}

	@Path("/question/{id}")
	@DELETE
	public Response removeQuestion(@PathParam("id") Long id) {
		facadeManager.removeQuestion(id);
		return Response.noContent().build();
	}

	@Path("/question/{id}/activate")
	@POST
	public Response activateQuestion(@PathParam("id") Long id) {
		facadeManager.verifyQuestion(id, true);
		return Response.noContent().build();
	}

	@Path("/question/{id}/deactivate")
	@POST
	public Response deactivateQuestion(@PathParam("id") Long id) {
		facadeManager.verifyQuestion(id, false);
		return Response.noContent().build();
	}

	@Path("/question/{id}/best/{answerId}")
	@POST
	public Response bestAnswer(@PathParam("id") Long id,
			@PathParam("answerId") Long answerId) {
		facadeManager.bestAnswer(id, answerId);
		return Response.noContent().build();
	}

	@Path("/question")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedQuestions(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Question> questions = facadeManager.getQuestions(size, page,
				field, value, operator, sidx, sord, true);
		return Response.ok(questions).build();
	}

	@Path("/question/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("id") Long id) {
		Question question = facadeManager.getQuestion(id);
		return Response.ok(question).build();
	}

	@Path("/question/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("username") String username) {
		List<Question> questions = facadeManager.getQuestion(username);
		return Response.ok(questions).build();
	}

	@Path("/question/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllQuestions(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Question> questions = facadeManager.getQuestions(size, page,
				field, value, operator, sidx, sord, false);
		return Response.ok(questions).build();
	}
	
	@Path("/question/count")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedQuestionCount() {
		Long count = facadeManager.getQuestionCount();
		return Response.noContent().header("count", count).build();
	}

	/*
	 * Answer
	 */
	@Path("/answer")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAnswer(Answer answer) {
		answer.setPostDate(new Date());
		facadeManager.saveAnswer(answer);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/answer/{id}")
	@PUT
	public Response updateAnswer(@PathParam("id") Long id, Answer answer) {
		answer.setId(id);
		facadeManager.saveAnswer(answer);
		return Response.noContent().build();
	}

	@Path("/answer/{id}")
	@DELETE
	public Response removeAnswer(@PathParam("id") Long id) {
		facadeManager.removeAnswer(id);
		return Response.noContent().build();
	}

	@Path("/answer/{id}/activate")
	@POST
	public Response activateAnswer(@PathParam("id") Long id) {
		facadeManager.verifyAnswer(id, true);
		return Response.noContent().build();
	}

	@Path("/answer/{id}/deactivate")
	@POST
	public Response deactivateAnswer(@PathParam("id") Long id) {
		facadeManager.verifyAnswer(id, false);
		return Response.noContent().build();
	}

	@Path("/answer/{qid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswers(@PathParam("qid") Long qid) {
		List<Answer> answers = facadeManager.getAnswers(qid);
		return Response.ok(answers).build();
	}

	@Path("/answer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedAnswers(@QueryParam("username") String username) {
		System.out.println(username);
		List<Answer> answers = facadeManager.getAnswers(username);
		return Response.ok(answers).build();
	}

	@Path("/answer/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAnswers(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Answer> answers = facadeManager.getAnswers(size, page, field,
				value, operator, sidx, sord, false);
		return Response.ok(answers).build();
	}

	/*
	 * @Path("/answer/{id}")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * getAnswer(@PathParam("id") Long id) { Answer answer =
	 * facadeManager.getAnswer(id); return Response.ok(answer).build(); }
	 */

	/*
	 * Specialist
	 */
	@Path("/specialist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecialists(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Specialist> specialists = facadeManager.getSpecialists(size, page,
				field, value, operator, sidx, sord, true);
		return Response.ok(specialists).build();
	}

	@Path("/specialist/nkeys")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecialists(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field[],
			@QueryParam("searchString") String value[],
			@QueryParam("searchOper") String[] operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Specialist> specialists = facadeManager.getSpecialists(size, page,
				field, value, operator, sidx, sord, true);
		return Response.ok(specialists).build();
	}

	@Path("/specialist/count")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecialistsCount(
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator) {
		Integer count = facadeManager.getSpecialistsCount(field, value,
				operator, true);
		System.out.println(count);
		return Response.noContent().header("count", count).build();
	}

	@Path("/specialist/nkeys/count")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpecialistsCount(
			@QueryParam("searchField") String field[],
			@QueryParam("searchString") String value[],
			@QueryParam("searchOper") String[] operator) {
		Integer count = facadeManager.getSpecialistsCount(field, value,
				operator, true);
		return Response.noContent().header("count", count).build();
	}

	/*
	 * News
	 */
	@Path("/news")
	@POST
	public Response addNews(News news) {
		news.setPostDate(new Date());
		System.out.println(news.getPoster().getUsername());
		System.out.println(news.getContent());
		System.out.println(news.getId());
		facadeManager.saveNews(news);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/news/{id}")
	@PUT
	public Response updateNews(News news, @PathParam("id") Long id) {
		news.setId(id);
		facadeManager.saveNews(news);
		return Response.noContent().build();
	}

	@Path("/news/{id}")
	@DELETE
	public Response removeNews(@PathParam("id") Long id) {
		facadeManager.removeNews(id);
		return Response.noContent().build();
	}

	@Path("/news")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedNews(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<News> newses = facadeManager.getNews(size, page, field, value,
				operator, sidx, sord, false);
		return Response.ok(newses).build();
	}

	@Path("/news/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNews(@PathParam("id") Long id) {
		News news = facadeManager.getNews(id);
		return Response.ok(news).build();
	}

	/*
	 * Transaction
	 */
	@Path("/transaction")
	@POST
	public Response addTransaction(Transaction transaction) {
		transaction.setPostDate(new Date());
		facadeManager.saveTransaction(transaction);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/transaction/{id}")
	@PUT
	public Response updateTransaction(Transaction transaction,
			@PathParam("id") Long id) {
		transaction.setId(id);
		facadeManager.saveTransaction(transaction);
		return Response.noContent().build();
	}

	@Path("/transaction/{id}")
	@DELETE
	public Response removeTransaction(Transaction transaction,
			@PathParam("id") Long id) {
		facadeManager.removeTransaction(id);
		return Response.noContent().build();
	}

	@Path("/transaction/{id}/activate")
	@POST
	public Response activateTransaction(@PathParam("id") Long id) {
		facadeManager.verifyTransaction(id, true);
		return Response.noContent().build();
	}

	@Path("/transaction/{id}/deactivate")
	@POST
	public Response deactivateTransaction(@PathParam("id") Long id) {
		facadeManager.verifyTransaction(id, false);
		return Response.noContent().build();
	}

	@Path("/transaction")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedTransactions(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		System.out.println("*****************8");
		List<Transaction> transactions = facadeManager.getTransactions(size,
				page, field, value, operator, sidx, sord, true);
		return Response.ok(transactions).build();
	}

	@Path("/transaction/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTransactions(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Transaction> transactions = facadeManager.getTransactions(size,
				page, field, value, operator, sidx, sord, false);
		Grid grid = new Grid();
		grid.setPage(page);
		grid.setRecords(transactions.size());
		grid.setTotal(transactions.size());
		grid.setRows(transactions);
		return Response.ok(grid).build();
	}

	@Path("/transaction/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTransaction(@PathParam("id") Long id) {
		Transaction transaction = facadeManager.getTransaction(id);
		return Response.ok(transaction).build();
	}

	@Path("/transaction/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTransactions(@PathParam("username") String username) {
		System.out.println("fuck the whole universe");
		List<Transaction> transactions = facadeManager
				.getTransactions(username);
		return Response.ok(transactions).build();
	}

	/*
	 * Loan
	 */
	@Path("/loan")
	@POST
	public Response addLoan(Loan loan) {
		loan.setPostDate(new Date());
		facadeManager.saveLoan(loan);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/loan/{id}")
	@PUT
	public Response updateLoan(Loan loan, @PathParam("id") Long id) {
		loan.setId(id);
		facadeManager.saveLoan(loan);
		return Response.noContent().build();
	}

	@Path("/loan/{id}")
	@DELETE
	public Response removeLoan(@PathParam("id") Long id) {
		facadeManager.removeLoan(id);
		return Response.noContent().build();
	}

	@Path("/loan/{id}/activate")
	@POST
	public Response activateLoan(@PathParam("id") Long id) {
		facadeManager.verifyLoan(id, true);
		return Response.noContent().build();
	}

	@Path("/loan/{id}/deactivate")
	@POST
	public Response deactivateLoan(@PathParam("id") Long id) {
		facadeManager.verifyLoan(id, false);
		return Response.noContent().build();
	}

	@Path("/loan")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedLoans(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Loan> loans = facadeManager.getLoans(size, page, field, value,
				operator, sidx, sord, true);
		return Response.ok(loans).build();
	}

	// http://localhost:8080/rest/metadata/loan/nkeys?rows=10&page=1&searchField=location&searchString=北京海淀&searchOper=eq&searchField=hasPawn&searchString=1&searchOper=eq&searchField=location&searchString=北京海淀&searchOper=eq&searchField=usesofloan&searchString=短期周转贷款&searchOper=cn&searchField=startTime&searchString=2010-01-01&searchOper=bw&searchField=endTime&searchString=2014-01-01&searchOper=ew&sidx=postDate&sord=asc
	@Path("/loan/nkeys")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedLoans(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field[],
			@QueryParam("searchString") String value[],
			@QueryParam("searchOper") String[] operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Loan> loans = facadeManager.getLoans(size, page, field, value,
				operator, sidx, sord, true);
		return Response.ok(loans).build();
	}

	@Path("/loan/nkeys/count")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedLoansCount(
			@QueryParam("searchField") String field[],
			@QueryParam("searchString") String value[],
			@QueryParam("searchOper") String[] operator) {
		Integer count = facadeManager.getLoansCount(field, value, operator,
				true);
		return Response.noContent().header("count", count).build();
	}

	@Path("/loan/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLoans(@QueryParam("rows") int size,
			@QueryParam("page") int page,
			@QueryParam("searchField") String field,
			@QueryParam("searchString") String value,
			@QueryParam("searchOper") String operator,
			@QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Loan> loans = facadeManager.getLoans(size, page, field, value,
				operator, sidx, sord, false);
		Grid grid = new Grid();
		grid.setPage(page);
		grid.setRecords(loans.size());
		grid.setTotal(loans.size());
		grid.setRows(loans);
		return Response.ok(grid).build();
	}

	@Path("/loan/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoan(@PathParam("id") Long id) {
		Loan loan = facadeManager.getLoan(id);
		return Response.ok(loan).build();
	}

	@Path("/loan/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoans(@PathParam("username") String username) {
		System.out.println("&&&&" + username);
		List<Loan> loans = facadeManager.getLoans(username);
		return Response.ok(loans).build();
	}

	/*
	 * UserTop
	 */
	@Path("/usertop/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserTop(@PathParam("username") String username) {
		System.out.println(SecurityUtils.getSubject().isRemembered()+ "^^^^^^^^^^^^^" + facadeManager.getCurrentUser());
		UserTop userTop = facadeManager.getUserTop(facadeManager.getCurrentUser());
		return Response.ok(userTop).build();
	}

	/*
	 * Forum
	 */
	@Path("/forum")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForum(@QueryParam("rows") int size) {
		Forums forums = facadeManager.getForums();
		return Response.ok(forums).build();
	}

	/*
	 * Message
	 */
	@Path("/message")
	@POST
	public Response addMessage(Message message) {
		message.setPostDate(new Date());
		facadeManager.addMessage(message);
		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/message/{id}")
	@POST
	public Response readMessage(@PathParam("id") Long id) {
		facadeManager.readMessage(id);
		return Response.noContent().build();
	}

	@Path("/message/{id}")
	@DELETE
	public Response removeMessage(@PathParam("id") Long id) {
		facadeManager.removeMessage(id);
		return Response.noContent().build();
	}

	@Path("/message/{username}/send")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSendMessages(@PathParam("username") String username) {
		List<Message> messages = facadeManager.getSendMessages(username);
		return Response.ok(messages).build();
	}

	@Path("/message/{username}/receive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReceiveMessages(@PathParam("username") String username) {
		List<Message> messages = facadeManager.getReceiveMessages(username);
		return Response.ok(messages).build();
	}

	@Path("/message/{username}/unread")
	@HEAD
	public Response getUnreadCount(@PathParam("username") String username) {
		return Response.ok()
				.header("unread", facadeManager.getUnreadCount(username))
				.build();
	}

	/*
	 * Timeline
	 */
	@Path("/timeline")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTimeline(@QueryParam("rows") int size,
			@QueryParam("page") int page) {
		List<Timeline> timelines = facadeManager.getTimelines(size, page);
		return Response.ok(timelines).build();
	}

	@Path("/timeline/{username}/recent")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTimelineByUser(@QueryParam("rows") int size,
			@QueryParam("page") int page, @PathParam("username") String username) {
		List<Timeline> timelines = facadeManager.getTimelinesByUser(username,
				size, page);
		return Response.ok(timelines).build();
	}

	/*
	 * Search
	 */
	@Path("/s")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@QueryParam("type") int type,
			@QueryParam("keyword") String keyword,
			@QueryParam("page") int page, @QueryParam("size") int size) {
		return Response.ok().build();
	}

	/*
	 * Visit
	 */
	/*
	 * @Path("visit/ip")
	 * 
	 * @HEAD public Response getIp(@Context HttpServletRequest request) { return
	 * Response.noContent().header("ip", getIP(request)).build(); }
	 */
	@Path("/visit/{id}/{type}/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisits(@PathParam("id") Long id,
			@PathParam("type") int type, @QueryParam("size") int size,
			@QueryParam("page") int page) {
		List<Visit> visits = facadeManager.getVisits(id, type, size, page);
		return Response.ok(visits).build();
	}

	@Path("/visit/{id}/{type}/count/")
	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisits(@PathParam("id") Long id,
			@PathParam("type") int type) {
		int count = facadeManager.getVisits(id, type);
		return Response.noContent().header("count", count).build();
	}
	
	@Path("/visit/{id}/{type}/count/date/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisitsByDate(@PathParam("id") Long id, @PathParam("type") int type, @QueryParam("start") String startTime, @QueryParam("end") String endTime) {
		List <VisitDate> visitDates = facadeManager.getVisits(id, type, ConvertUtil.str2Date(startTime, Constant.DATE_FORMAT), ConvertUtil.str2Date(endTime, Constant.DATE_FORMAT));
		return Response.ok(visitDates).build();
	}

	@Path("/visit/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response visit(Visit visit, @Context HttpServletRequest request) {
		System.out.println(visit.getStartDate().getTimezoneOffset() + "*******"
				+ visit.getStartDate());
		facadeManager.visit(visit);
		return Response.noContent().build();
	}

	@Path("/visit/{username}/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisitsByUser(@PathParam("username") String username,
			@QueryParam("size") int size, @QueryParam("page") int page) {
		List<Visit> visits = facadeManager.getUserVisits(username, size, page);
		return Response.ok(visits).build();
	}

	/*
	 * Favourite
	 */
	@Path("/favourite")
	@POST
	public Response addFavourite(Favourite favourite) {
		favourite.setPostDate(new Date());
		if (facadeManager.addFavourite(favourite) == Constant.SUCCESS_ADD) {
			System.out.println("##########################");
			return Response.noContent().build();
		}
		else {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$");
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@Path("/favourite/{id}")
	@DELETE
	public Response deleteFavourite(@PathParam("id") Long id) {
		facadeManager.deleteFavourite(id);
		return Response.noContent().build();
	}
	
	@Path("/favourite/id/{id}/type/{type}/username/{username}")
	@DELETE
	public Response deleteFavourite(@PathParam("id") Long oid, @PathParam("type") Long type, @PathParam("username") String username) {
		UserDetail user = facadeManager.getUserDetail(username);
		Favourite favourite = new Favourite();
		favourite.setOid(oid);
		favourite.setPoster(user);
		facadeManager.deleteFavourite(favourite);
		return Response.status(Response.Status.MOVED_PERMANENTLY).build();
	}

	@Path("/favourite/username/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavourites(@PathParam("username") String username) {
		List<Favourite> favourites = facadeManager.getFavourites(username);
		return Response.ok(favourites).build();
	}

	@Path("/favourite/type/{type}/id/{id}")
	@HEAD
	public Response getFavouriteCount(@PathParam("type") Integer type,
			@PathParam("id") Long oid) {
		System.out.println(type + "******************" + oid);
		int id = facadeManager.getFavouriteCount(type, oid);
		return Response.noContent().header("count", id).build();
	}

	@Path("/favourite/type/{type}/id/{id}/username/{username}")
	@HEAD
	public Response isFavourite(@PathParam("type") Integer type,
			@PathParam("id") Long oid, @PathParam("username") String username) {
		boolean isFavourite = facadeManager.isFavourite(type, oid, username);
		return Response.noContent().header("is", isFavourite).build();
	}

	/*
	 * Upload
	 */
	@POST
	@Path("upload/avatar/{username}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadAvatar(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @PathParam("username") String username) throws IOException {
		String message = facadeManager.upload(request, response, PropUtil.UPLOAD_AVATAR, username);
		return Response.ok(message).build();
	}

	@POST
	@Path("upload/skin/{username}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadSkin(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @PathParam("username") String username) throws IOException {
		String message = facadeManager.upload(request, response, PropUtil.UPLOAD_SKIN, username);
		return Response.ok(message).build();
	}

	@POST
	@Path("upload/image/{username}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadImage(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @PathParam("username") String username) throws IOException {
		String message = facadeManager.upload(request, response, PropUtil.UPLOAD_IMAGES);
		return Response.ok(message).build();
	}

	@POST
	@Path("upload/news")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadNews(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @PathParam("username") String username) throws IOException {
		String message = facadeManager.upload(request, response, PropUtil.UPLOAD_NEWS);
		return Response.ok(message).build();
	}

	@POST
	@Path("upload/file/{username}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFile(@Context HttpServletRequest request,
			@Context HttpServletResponse response, @PathParam("username") String username) throws IOException {
		String message = facadeManager.upload(request, response, PropUtil.UPLOAD_USER, username);
		return Response.ok(message).build();
	}

	/*
	 * Configuration
	 */
	@POST
	@Path("config/slide")
	public Response updateSlideJson(@Context HttpServletRequest request)
			throws IOException {
		facadeManager.updateConfigFile(PropUtil.getProps(request
				.getServletContext().getRealPath("/"), PropUtil.SLIDE_PATH),
				ConvertUtil.inputStream2String(request.getInputStream()));
		return Response.noContent().build();
	}
	
	@POST
	@Path("config/slide/preview")
	public Response updateSlidePreviewJson(@Context HttpServletRequest request)
			throws IOException {
		facadeManager.updateConfigFile(PropUtil.getProps(request
				.getServletContext().getRealPath("/"), "slidePreviewPath"),
				ConvertUtil.inputStream2String(request.getInputStream()));
		return Response.noContent().build();
	}

	@POST
	@Path("config/index")
	public Response updateIndexHTML(@Context HttpServletRequest request)
			throws IOException {
		facadeManager.updateConfigFile(PropUtil.getProps(request
				.getServletContext().getRealPath("/"), PropUtil.CONFIG_INDEX),
				ConvertUtil.inputStream2String(request.getInputStream()));
		return Response.noContent().build();
	}
	
	@POST
	@Path("config/tmpl")
	public Response updateTmplHTML(@Context HttpServletRequest request)
			throws IOException {
		facadeManager.updateConfigFile(PropUtil.getProps(request
				.getServletContext().getRealPath("/"), "tmplPath"),
				ConvertUtil.inputStream2String(request.getInputStream()));
		return Response.noContent().build();
	}

	@POST
	@Path("config/news")
	public Response updateNewsHTML(@Context HttpServletRequest request)
			throws IOException {
		facadeManager.updateConfigFile(PropUtil.getProps(request
				.getServletContext().getRealPath("/"), PropUtil.CONFIG_NEWS),
				ConvertUtil.inputStream2String(request.getInputStream()));
		return Response.noContent().build();
	}

	@POST
	@Path("config/ad")
	public Response updateAdHTML(@Context HttpServletRequest request)
			throws IOException {
		facadeManager.updateConfigFile(PropUtil.getProps(request
				.getServletContext().getRealPath("/"), PropUtil.CONFIG_AD),
				ConvertUtil.inputStream2String(request.getInputStream()));
		return Response.noContent().build();
	}

}
