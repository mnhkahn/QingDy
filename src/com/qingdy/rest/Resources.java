package com.qingdy.rest;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qingdy.model.Answer;
import com.qingdy.model.Blog;
import com.qingdy.model.Evaluate;
import com.qingdy.model.Loan;
import com.qingdy.model.Mall;
import com.qingdy.model.News;
import com.qingdy.model.Product;
import com.qingdy.model.Question;
import com.qingdy.model.User;
import com.qingdy.model.UserDetail;
import com.qingdy.service.FacadeManager;

@Path("/metadata")
public class Resources {
	private Log log = LogFactory.getLog(getClass());
	
	@Resource(name = "facadeManager")
	private FacadeManager facadeManager;
	
	/*
	 * User
	 */
	@Path("/user/register")
	@POST
	public Response register(User user) {
		facadeManager.saveUser(user);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@Path("/user/login")
	@POST
	public Response login(User user) {
		return null;
	}
	
	@Path("/user/{username}/exists")
	@HEAD
	public Response isUserExists(@PathParam("username") String username) {
		return Response.noContent().header("exist", facadeManager.isUserExists(username)).build();
	}
	
	@Path("/user/{username}")
	@PUT
	public Response updateUser(@PathParam("username") String username, User user) {
		facadeManager.updateUser(user);
		return Response.ok().build();
	}
	
	@Path("/user/{username}")
	@DELETE
	public Response deleteUser(@PathParam("username") String username) {
		return null;
	}
	
	/*
	 * UserDetail
	 */
	// complete user detail
	@Path("/userdetail/{username}")
	@POST
	public Response addUserDetail(@PathParam("username") String username, UserDetail userDetail) {
		userDetail.setUsername(username);
		userDetail.setRegdate(new Date());
		facadeManager.saveUserDetail(userDetail);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@Path("/userdetail/{username}")
	@PUT
	public Response updateUserDetail(@PathParam("username") String username, UserDetail userDetail) {
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
	public Response getUsersDetail(@PathParam("username") String username) {
		List<UserDetail> list = facadeManager.getUsersDetail();
		return Response.ok(list).build();
	}
		
	// get user detail
	@Path("/userdetail/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserDetail(@PathParam("username") String username) {
		UserDetail userDetail = facadeManager.getUserDetail(username);
		return Response.ok(userDetail).build();
	}
	
	// get contact
	@Path("/userdetail/{username}/contact")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserContact(@PathParam("username") String username) {
		return Response.ok().build();
	}
	
	/*
	 * Blog
	 */
	@Path("/blog")
	@POST
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addBlog(Blog blog) {
		blog.setPostDate(new Date());
		facadeManager.saveBlog(blog);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@Path("/blog/manage")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVerifiedBlog(@QueryParam("rows") int size, @QueryParam("page") int page, @QueryParam("searchField") String field, @QueryParam("searchString") String value, @QueryParam("searchOper") String operator, @QueryParam("sidx") String sidx, @QueryParam("sord") String sord) {
		List<Blog> blogs = facadeManager.getBlogs();
		return Response.ok(blogs).build();
	}
	
	@Path("/blog/{id}")
	@DELETE
	public Response deleteBlog(@PathParam("id") Long id) {
		facadeManager.removeBlog(id);
		return Response.noContent().build();
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBlog(@PathParam("id") Long id) {
		Blog blog = facadeManager.getBlog(id);
		return Response.ok(blog).build();
	}
	
	@Path("/blog")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBlogs() {
		List<Blog> blogs = facadeManager.getBlogs();
		return Response.ok(blogs).build();
	}
	
	@Path("/blog/{id}/activate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateBlog(@PathParam("id") Long id) {
		
		return Response.noContent().build();
	}
	
	@Path("/blog/{id}/deactivate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deactivateBlog(@PathParam("id") Long id) {
		
		return Response.noContent().build();
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMalls() {
		return null;
	}
	
	@Path("/mall/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMall(@PathParam("id") Long id) {
		return null;
	}
	
	@Path("/mall")
	@POST
	public Response addMall(Mall mall) {
		return Response.status(Response.Status.CREATED).build();
	}
	
	@Path("/mall/{id}")
	@PUT
	public Response updateMall(Mall mall, @PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/mall/{id}")
	@DELETE
	public Response removeMall(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/mall/{id}/activate")
	@POST
	public Response activateMall(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/mall/{id}/deactivate")
	@POST
	public Response deactivateMall(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	/*
	 * Product
	 */
	@Path("/product")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		return Response.ok().build();
	}
	
	@Path("/product/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("id") Long id) {
		return Response.ok().build();
	}
	
	@Path("/product")
	@POST
	public Response addProduct(Product product) {
		return Response.noContent().build();
	}
	
	@Path("/product/{id}")
	@PUT
	public Response updateProduct(Product product, @PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/product/{id}")
	@DELETE
	public Response removeProduct(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/product/activate")
	@POST
	public Response activateProduct(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/product/deactivate")
	@POST
	public Response deactivateProduct(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	/*
	 * Question
	 */
	@Path("/quesion")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestions() {
		return Response.ok().build();
	}
	
	@Path("/quesion/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("id") Long id) {
		return Response.ok().build();
	}
	
	@Path("/quesion/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("username") String username) {
		return Response.ok().build();
	}
	
	@Path("/quesion")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addQuestion(Question question) {
		return Response.noContent().build();
	}
	
	@Path("/question/{id}")
	@PUT
	public Response updateQuestion(Question question, @PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/question/{id}")
	@DELETE
	public Response removeQuestion(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/question/{id}/activate")
	@POST
	public Response activateQuestion(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/question/{id}/deactivate")
	@POST
	public Response deactivateQuestion(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/question/{id}/best")
	@POST
	public Response bestAnswer(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	/*
	 * Answer
	 */
	@Path("/answer/{qid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswers(@PathParam("qid") Long qid) {
		return Response.ok().build();
	}
	
	@Path("/answer/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswers(@PathParam("username") String username) {
		return Response.ok().build();
	}
	
	@Path("/answer/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswer(@PathParam("id") Long id) {
		return Response.ok().build();
	}
	
	@Path("/answer")
	@POST
	public Response addAnswer(Answer answer) {
		return Response.noContent().build();
	}
	
	@Path("/answer/{id}")
	@DELETE
	public Response removeAnswer(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/answer/{id}/activate")
	@POST
	public Response activateAnswer(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/answer/{id}/deactivate")
	@POST
	public Response deactivateAnswer(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	/*
	 * News
	 */
	@Path("/news")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNews() {
		return Response.ok().build();
	}
	
	@Path("/news/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNews(@PathParam("id") Long id) {
		return Response.ok().build();
	}
	
	@Path("/news")
	@POST
	public Response addNews(News news) {
		return Response.noContent().build();
	}
	
	@Path("/news/{id}")
	@PUT
	public Response updateNews(News news, @PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/news/{id}")
	@DELETE
	public Response removeNews(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	/*
	 * Loan
	 */
	@Path("/loan")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoans() {
		return Response.ok().build();
	}
	
	@Path("/loan/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoan(@PathParam("id") Long id) {
		return Response.ok().build();
	}
	
	@Path("/loan/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoans(@PathParam("username") String username) {
		return Response.ok().build();
	}
	
	@Path("/loan")
	@POST
	public Response addLoan(Loan loan) {
		return Response.noContent().build();
	}
	
	@Path("/loan/{id}")
	@POST
	public Response updateLoan(Loan loan, @PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/loan/{id}")
	@DELETE
	public Response removeLoan(Loan loan, @PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/loan/{id}/activate")
	@POST
	public Response activateLoan(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	@Path("/loan/{id}/deactivate")
	@POST
	public Response deactivateLoan(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
	
	/*
	 * UserTop
	 */
	@Path("/usertop/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserTop(@PathParam("username") String username) {
		return Response.ok().build();
	}
}
