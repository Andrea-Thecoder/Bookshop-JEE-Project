package anagrafiche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DB;


public class Book {

	private String title;
	private Author author;
	private Category category;
	private float price;
	private int id;
	
	public Book(String t, Author a,Category c,float p) {
		this.title = t;
		this.author = a;
		this.category = c;
		this.price = p;
	}
	
	public Book(String t, Author a,Category c,float p, int id) {
		this(t,a,c,p);
		this.id = id;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitolo(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public static ArrayList<Book> getAll() throws Exception{
		ArrayList<Book> books = new ArrayList<>();
		PreparedStatement stmt = DB.getPreparedStmt("SELECT b.id, b.title, a.name, a.lastname, c.name, b.price FROM book b JOIN author a ON b.author_id = a.id JOIN category c ON b.category_id = c.id ORDER BY b.title");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			books.add(new Book(
						rs.getString("b.title"), 
						new Author(rs.getString("a.name"), rs.getString("a.lastname")),
						new Category(rs.getString("c.name")), 
						rs.getFloat("b.price"),
						rs.getInt("b.id") 
					));
		}
		return books;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb
		  .append("Libro: ").append(title)
		  .append("\n Scritto da: ").append(author)
		  .append("\n Genere: ").append(category)
		  .append("\n Prezzo: ").append(price)
		  .append("\n ID: ").append(id).append("\n")
		  .toString();
	}
	
		
}
