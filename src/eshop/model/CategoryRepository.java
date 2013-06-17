package eshop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements CategoryDao {
	private final String driver;
	private final String url;
	private final String login;
	private final String pass;
	
	public CategoryRepository(String driver, String url, String login, String pass) {
		this.driver = driver;
		this.url = url;
		this.login = login;
		this.pass = pass;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			Class.forName(this.driver).newInstance();
			conn = DriverManager.getConnection(this.url, this.login, this.pass);
			statement = conn.prepareStatement("select id, name, shortname from categories order by id asc");
			result = statement.executeQuery();
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String shortName = result.getString(3);
				
				categories.add(new Category(id, name, shortName));
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
		
		return categories;
	}
}
