package eshop.model;

public class Category {
	private int id;
	private String name;
	private String shortName;
	private boolean selected;
	
	public Category() {}
	
	public Category(int id) {
		this.setId(id);
	}
	
	public Category(int id, String name, String shortName) {
		this.setId(id);
		this.setName(name);
		this.setShortName(shortName);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
