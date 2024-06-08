package anagrafiche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.DB;

public class Category {
	
	private String name;
	private int id;
	
	public Category(String c) {
		this.name = c;
	}
	
	public Category(String c, int id) {
		this(c);
		this.id = id;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	
	public static ArrayList<Category> getAll() throws Exception{
		ArrayList<Category> cat = new ArrayList<>();
		PreparedStatement catStmt = DB.getPreparedStmt("Select * from category ORDER BY name");
		ResultSet rs = catStmt.executeQuery();
		while (rs.next()) {
			cat.add(new Category(rs.getString("name"),rs.getInt("id")));
		}
		return cat;
	}

	@Override
	public String toString() {
		return name;
		
		
	}
}
