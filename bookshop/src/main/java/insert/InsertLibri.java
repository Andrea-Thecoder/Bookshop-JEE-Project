package insert;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/gestioneLibri")
public class InsertLibri extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 
	public InsertLibri() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String aName = request.getParameter("aName");
		String aLastname = request.getParameter("aLastname");
		String cName = request.getParameter("cName");
		float price = Float.parseFloat(request.getParameter("price"));
		
		if(title == null || aName == null || aLastname == null || cName == null || price == 0.00) {
			response.sendRedirect(request.getContextPath() + "/gestioneLibri.jsp");
			return;
		}
		
		//Zona di sscrittura delle query e inizializzazione delle variabili per gli id.
		int authorID = 0;
		int categoryID = 0;
		
		String sqlGetAuthor = "SELECT id FROM author WHERE name = ? AND lastname = ? ;";
		String sqlGetCategory = "SELECT id FROM category WHERE name = ? ;";
		String sqlSetAuthor = "INSERT INTO author (name,lastname) VALUES (?,?);";
		String sqlSetCategory = "INSERT INTO category (name) VALUES (?);";
		String sqlSetBook = "INSERT INTO book (title,author_id,category_id,price) VALUES (?,?,?,?) ;";
		  
		try {
				//controllo se esiste l'autore altrimenti lo inserisco, in entrambi i casi recupero ID di esso.
				try (PreparedStatement stmtAuthor = DB.getPreparedStmt(sqlGetAuthor);) {
					stmtAuthor.setString(1, aName);
					stmtAuthor.setString(2, aLastname);
					ResultSet rs = stmtAuthor.executeQuery();
					if(rs.next())
						if (rs.getInt("id") > 0) authorID = rs.getInt("id");
					if (authorID == 0) {
						try (PreparedStatement stmtSetAuthor = DB.getPreparedStmt(sqlSetAuthor,Statement.RETURN_GENERATED_KEYS);) {
							stmtSetAuthor.setString(1, aName);
							stmtSetAuthor.setString(2, aLastname);
							stmtSetAuthor.executeUpdate();
							rs = stmtSetAuthor.getGeneratedKeys();
							if(rs.next()) authorID = rs.getInt(1);
						}
					}
					
				}
				catch(Exception e) {
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/gestioneLibri.jsp?e=13");
					return;
				}
				//controllo se esiste una categoria altrimenti la creo. in entrambi i casi ricevo ID di essa.
				try (PreparedStatement stmtCategory = DB.getPreparedStmt(sqlGetCategory)){
						stmtCategory.setString(1,cName);
						ResultSet rs = stmtCategory.executeQuery();
						if(rs.next())
							if(rs.getInt("id") > 0 ) categoryID = rs.getInt("id");
						if(categoryID == 0) {
							try (PreparedStatement stmtSetCategory = DB.getPreparedStmt(sqlSetCategory,Statement.RETURN_GENERATED_KEYS)){
								stmtSetCategory.setString(1, cName);
								stmtSetCategory.executeUpdate();
								rs = stmtSetCategory.getGeneratedKeys();
								if(rs.next()) categoryID = rs.getInt(1);
							}
						}
				}
				catch(Exception e) {
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/gestioneLibri.jsp?e=4");
					return;
				}
				
				PreparedStatement stmtSetBook = DB.getPreparedStmt(sqlSetBook);
				stmtSetBook.setString(1,title);
				stmtSetBook.setInt(2,authorID);
				stmtSetBook.setInt(3,categoryID);
				stmtSetBook.setFloat(4,price);
				if(stmtSetBook.executeUpdate() > 0) response.sendRedirect(request.getContextPath() + "/gestioneLibri.jsp");
			}
		 catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/gestioneLibri.jsp?e=2");
			return;
		}
		
	}
	
}
