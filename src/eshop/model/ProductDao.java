package eshop.model;

import java.util.List;

public interface ProductDao {
	List<Product> getProducts();
	Product getProduct(int id);
	boolean saveProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(Product product);
}
