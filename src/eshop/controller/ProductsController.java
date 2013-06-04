package eshop.controller;

import java.io.IOException;
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

@WebServlet("/")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String p2 = request.getServletPath();
		String p3 = request.getRequestURI();
		List<Product> products = new ArrayList<Product>();
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "ruslan");
			statement = conn.prepareStatement("select id, name, price from products");
			result = statement.executeQuery();
			while (result.next()) {
				Product p = new Product(result.getInt(1), result.getString(2), result.getBigDecimal(3));
				products.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				 result.close();
				 statement.close();
				 conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("products", products);
		
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
