package insert;

import java.io.IOException;
import java.sql.PreparedStatement;

import db.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gestioneAutori")
public class InsertAuthor extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	 
	public InsertAuthor() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name= request.getParameter("name");
		String lastname= request.getParameter("lastname");
		if(name == null || lastname == null) {
			response.sendRedirect(request.getContextPath() + "/gestioneAutori.jsp?e=1");
			return;
		}
		try {
			PreparedStatement stmt= DB.getPreparedStmt(
					"INSERT INTO author (name,lastname) VALUES (?,?)");
			stmt.setString(1,name);
			stmt.setString(2,lastname);
			if(stmt.executeUpdate() > 0) response.sendRedirect(request.getContextPath() + "/gestioneAutori.jsp");
			
		}
		catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/gestioneAutori.jsp?e=2");
			return;
		}
	}
}
