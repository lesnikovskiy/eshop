package eshop.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import eshop.model.CartLine;
import eshop.model.FormAttributes;
import eshop.model.Product;
import eshop.model.ProductDao;
import eshop.model.ProductRepository;

@WebServlet("/")
@MultipartConfig
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
				FormAttributes attrs = new FormAttributes();
				attrs.setAction("edit");
				
				request.setAttribute("product", p);
				request.setAttribute("attributes", attrs);
				
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
	
	private String getUploadFileName(Part p) {
		String file = "";
		String header = "Content-Disposition";
		
		String[] strArray = p.getHeader(header).split(";");
		for (String split : strArray) {
			if (split.trim().startsWith("filename")) {
				file = split.substring(split.indexOf("=") + 1);
				file = file.trim().replace("\"", "");				
			}
		}
		
		return file;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = false;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			for (Part part : request.getParts()) {
				String name = part.getName();
				InputStream stream = request.getPart(name).getInputStream();
				String fileName = getUploadFileName(part);
				String location = getServletContext().getRealPath("") + fileName;
				FileOutputStream fileStream = new FileOutputStream(location);
				int data = 0;
				while ((data = stream.read()) != -1) {
					fileStream.write(data);
				}
				
				fileStream.close();
				stream.close();
				
				result = true;
			}
		}
		
		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
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
			
			result = true;
		}
		
		if (result)
			response.sendRedirect("/eshop/products");
		else
			response.sendRedirect("/eshop/edit.jsp");
	}
}
