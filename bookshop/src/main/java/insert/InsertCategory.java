package insert;

import java.io.IOException;
import java.sql.PreparedStatement;

import db.DB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gestioneCategorie")
public class InsertCategory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 
	public InsertCategory() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name == null)  {
			response.sendRedirect(request.getContextPath() + "/gestioneCategorie.jsp?e=1");
			return;
		}
		
		try {
			PreparedStatement stmt = DB.getPreparedStmt(
					"INSERT INTO category (name) VALUES (?) ");
			stmt.setString(1, name);
			if(stmt.executeUpdate() > 0) response.sendRedirect(request.getContextPath() + "/gestioneCategorie.jsp");
		}
		 catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/gestioneCategorie.jsp?e=2");
			return;
		}
		
	}
	

}
