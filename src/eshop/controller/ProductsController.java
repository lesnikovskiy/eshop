package eshop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eshop.model.Product;
import eshop.model.ProductDao;
import eshop.model.ProductRepository;

@WebServlet("/")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String driver;
	private String url;
	private String login;
	private String pass;
	
	private ProductDao repository;
       
    @Override
    public void init() throws ServletException {
    	super.init();
    	   	
    	String driver = getServletContext().getInitParameter("driver");
    	String url = getServletContext().getInitParameter("url");
    	String login = getServletContext().getInitParameter("login");
    	String pass = getServletContext().getInitParameter("pass");
    	
    	repository = new ProductRepository(driver, url, login, pass);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String id = request.getParameter("id");
		if (id != null) {		
			Product p = repository.getProduct(Integer.parseInt(id));
			if (p != null) {
				request.setAttribute("product", p);
				
				ServletContext ctx = getServletContext();
				RequestDispatcher view = ctx.getRequestDispatcher("/edit.jsp");
				view.forward(request, response);
				
				return;
			}
		}
		
		List<Product> products = repository.getProducts();
		
		request.setAttribute("products", products);
		
		ServletContext ctx = getServletContext();
		RequestDispatcher view = ctx.getRequestDispatcher("/index.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = false;
		
		String method = request.getParameter("method");
		if (method.equalsIgnoreCase("post")) {
			String name = request.getParameter("name");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			
			result = repository.saveProduct(new Product(name, price));			
		}
		
		if (method.equalsIgnoreCase("put")) {
			String name = request.getParameter("name");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			int id = Integer.parseInt(request.getParameter("id"));
			
			result = repository.updateProduct(new Product(id, name, price));
		}
		
		if (result)
			response.sendRedirect("/eshop/index.jsp");
		else
			response.sendRedirect("/eshop/edit.jsp");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
