<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="anagrafiche.Category" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet" 
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />



<meta charset="UTF-8">
<title>BookShop Potente ed Elegante</title>
</head>
<body>



<div class="container">
	<jsp:include page="template/header.jsp"></jsp:include>
	
	
	<div class="row">
		<h4>Gestione Categorie:</h4>
		<div class="col-6">
		<h5>Categorie presenti</h5>
		<% ArrayList<Category> categorie = Category.getAll();%>
		<table class="table">
  		<thead>
    		<tr>
		      <th scope="col">#</th>
		      <th scope="col">Genere</th>
		    </tr>
		</thead>
		<tbody>
		<% for(Category a :categorie){ %>
			<tr>
		      <td> <%= a.getId() %></td>
		      <td><%= a.getName() %></td>
		    </tr>
		    <% } %>
		 </tbody>
		 </table>
		 <h6>Visualizzati <%= categorie.size() %> categorie</h6>
		</div>
	 </div>
	 <div class="row">
		<div class="col-6">
		<h5>Aggiungi Categorie</h5>
		<form method="post" action="./gestioneCategorie">
		<div class="input-group mb-3 mt-3">
			<label for="name" class="input-group-text">Genere</label>
			<input type="text" name="name" class="form-control">
		</div>	
			<button type="submit" class="btn btn-primary" > Inserisci </button>
		</form>
		</div>
	 </div>
	<jsp:include page="template/footer.jsp"></jsp:include>
</div>



</body>
</html>