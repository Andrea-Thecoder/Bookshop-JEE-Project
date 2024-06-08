package anagrafiche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.DB;

public class Author {
	
	private String name;
	private String lastname;
	private int id;
	
	
	public Author(String n , String ln) {
		this.name=n;
		this.lastname=ln;
	}
	
	public Author(String n , String ln, int id) {
		this(n,ln);
		this.id =id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public int getId() {
		return id;
	}
	
	public static ArrayList<Author> getAll() throws Exception{
		//deve fare connessione al db, fare lo statement, impacchettare nelal lsita i valori recueprati, return lista
		//con questo richiamo nel DB avviamo automaticmaente una connessione ed uno statement, tutto in 1 grazie al method creato.
		PreparedStatement stmtAuthor = DB.getPreparedStmt(
				"SELECT * FROM author ORDER BY name");
		ResultSet rs = stmtAuthor.executeQuery();
		ArrayList<Author> author = new ArrayList<>(); 
		while (rs.next()) {
			author.add(new Author(rs.getString("name"),rs.getString("lastname"),rs.getInt("id")));
		}
		return author;
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.append(name)
				 .append(" ").append(lastname)
				 .toString();
	}

	
}
