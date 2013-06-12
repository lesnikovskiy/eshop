package eshop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eshop.model.CartLine;
import eshop.model.Product;
import eshop.model.ProductDao;
import eshop.model.ProductRepository;

@WebServlet("/")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PRODUCTS_SESSION_KEY = "products_session_key";
	
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
		
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("new")) {
			String name = request.getParameter("name");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			
			result = repository.saveProduct(new Product(name, price));			
		}
		
		if (action.equalsIgnoreCase("edit")) {
			String name = request.getParameter("name");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			int id = Integer.parseInt(request.getParameter("id"));
			
			result = repository.updateProduct(new Product(id, name, price));
		}
		
		if (action.equalsIgnoreCase("addToCart")) {
			String id = request.getParameter("id4cart");
			if (id != null) {
				Product p = repository.getProduct(Integer.parseInt(id));
				if (p != null) {
					HttpSession session = request.getSession();
					
					@SuppressWarnings("unchecked")
					List<CartLine> cartLines = (ArrayList<CartLine>) session.getAttribute(PRODUCTS_SESSION_KEY);
					if (cartLines == null) {
						cartLines = new ArrayList<CartLine>();
						cartLines.add(new CartLine(p, 1));
					}
					
					boolean found = false;
					
					for (CartLine c : cartLines) {
						Product cp = c.getProduct();
						if (cp.getId() == p.getId()) {
							int q = c.getQuantity();
							q++;
							c.setQuantity(q);
							found = true;
						}
					}
					
					if (!found)
						cartLines.add(new CartLine(p, 1));
										
					session.setAttribute(PRODUCTS_SESSION_KEY, cartLines);
				}
			}
			
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
			
			return;
		}
		
		if (result)
			response.sendRedirect("/eshop/index.jsp");
		else
			response.sendRedirect("/eshop/edit.jsp");
	}
}
