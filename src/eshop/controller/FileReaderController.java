package eshop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileReaderController")
public class FileReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			Connection conn = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			
			Blob file = null;
			String mime = null;
			
			ServletOutputStream out = response.getOutputStream();
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "ruslan");
				statement = conn.prepareStatement("SELECT mime, file FROM products where id = ?");
				statement.setInt(1, Integer.parseInt(id));
				result = statement.executeQuery();
				
				if (result.next()) {
					mime = result.getString(1);
					file = result.getBlob(2);
				} else {
					RequestDispatcher view = request.getRequestDispatcher("/fail.jsp");
					view.forward(request, response);
				}
				
				response.setContentType(mime);
				InputStream inputStream = file.getBinaryStream();
				
				int length = (int) file.length();
				int bufferSize = 1024;
				byte[] buffer = new byte[bufferSize];
				while ((length = inputStream.read(buffer)) != -1) {
					out.write(buffer, 0, length);
				}
				
				inputStream.close();
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					result.close();
					statement.close();
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
