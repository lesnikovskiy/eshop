package eshop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductDao {
	private final String driver;
	private final String url;
	private final String login;
	private final String pass;
	
	public ProductRepository(String driver, String url, String login, String pass) {
		this.driver = driver;
		this.url = url;
		this.login = login;
		this.pass = pass;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			Class.forName(this.driver).newInstance();
			conn = DriverManager.getConnection(this.url, this.login, this.pass);
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
		
		return products;
	}

	@Override
	public Product getProduct(int id) {
		Product p = null;
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			Class.forName(this.driver).newInstance();
			conn = DriverManager.getConnection(this.url, this.login, this.pass);
			statement = conn.prepareStatement("select id, name, price from products where id = ?");
			statement.setInt(1, id);
			
			result = statement.executeQuery();
			
			if (result.next())
				p = new Product(result.getInt(1), result.getString(2), result.getBigDecimal(3));
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
		
		return p;
	}

	@Override
	public boolean saveProduct(Product product) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			Class.forName(this.driver).newInstance();
			conn = DriverManager.getConnection(this.url, this.login, this.pass);
			statement = conn.prepareStatement("INSERT INTO products (name, price) values (?, ?)");
			statement.setString(1, product.getName());
			statement.setBigDecimal(2, product.getPrice());
			
			statement.execute();			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@Override
	public boolean updateProduct(Product product) {
		boolean result = false;	
		
		Connection conn = null;
		PreparedStatement statement = null;
		
		try {
			Class.forName(this.driver).newInstance();
			conn = DriverManager.getConnection(this.url, this.login, this.pass);
			statement = conn.prepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?");
			statement.setString(1, product.getName());
			statement.setBigDecimal(2, product.getPrice());
			statement.setInt(3, product.getId());
			
			statement.execute();			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}	
}
