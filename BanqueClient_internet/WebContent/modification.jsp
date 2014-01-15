<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="banque.entites.Banque" %>
<%@ page import="banque.entites.Conseiller" %>
<%@ page import="banque.entites.Client" %>
<%@ page import="banque.entites.Compte" %>
<%@ page import="banque.entites.CompteEpargne" %>
<%@ page import="banque.entites.ComptePlatine" %>
<%@ page import="banque.entites.CompteStandard" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style_client.css" type="text/css"  /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification</title>
</head>
<body>
<div class="body">
<%
if(((String)request.getAttribute("page_origine")).equals("admin")){%>
<form action="admincontroler">
<%	
}
else{
%>
<form action="client">

<%
}
String type=(String)request.getAttribute("type");


%>
<%
if(type.equals("client")){
		Client a_modif=(Client)request.getAttribute("a_modif");%>
		<input name="id" type="hidden" value="<%=a_modif.getId() %>"/>
		nom : <input name="nom" type="text" value="<%=a_modif.getNom()%>" /><br>
		Prenom : <input name="prenom" type="text" value="<%=a_modif.getPrenom()%>" />  <br>
		 Conseiller:<select name="conseiller">
  			<%   
 			 List<Conseiller> conseillers= (List)request.getSession().getAttribute("conseillers");
  			
 			 for(Conseiller c:conseillers){
 				 if(c.getId()==a_modif.getConseiller().getId()){%>
 					 <option selected value="<%=c.getId() %>"><%=c.getNom() %></option>
 				 <%}
 				 else{
 			 %>  
  			 <option value="<%=c.getId() %>"><%=c.getNom() %></option>  
				 
			 <%} 
 				 }
 			 %>
		 </select><br>
		Login : <input name="login" type="text" value="<%=a_modif.getLogin()%>" /> <br>
		Mdp : <input name="mdp" type="text" value="<%=a_modif.getPassword()%>" /><br>
		<input name="action" type="submit" value="valider modification" /><br>
		<input name="type" type="hidden" value="client" /><br>
		
		
	<%
	}
	 else if(type.equals("conseiller")){ 
		 Conseiller a_modif=(Conseiller)request.getAttribute("a_modif");
	 %>
			<input name="id" type="hidden" value="<%=a_modif.getId() %>"/>
			nom : <input name="nom" type="text" value="<%=a_modif.getNom() %>" /><br>
			Mdp : <input name="mdp" type="text" value="<%=a_modif.getMdp() %>" /><br>
			 Banque : <select name="banque">
  			<%   
 			 List<Banque> banquess= (List)request.getSession().getAttribute("banque");
 			 for(Banque b:banquess){
 				 if(a_modif.getBanque().getNom().equals(b.getNom())){%>
 					 <option selected value="<%=b.getId() %>"><%=b.getNom() %></option>  
 				 <%}
 				 else {
 			 %>  
  			 <option value="<%=b.getId() %>"><%=b.getNom() %></option>  
				 
			 <%} 
 				 }
 			 %>
 			 </select><br>
		<input name="action" type="submit" value="valider modification" /><br>
			<input name="type" type="hidden" value="conseiller" /><br>
			 
	<%
	}
	 else if (type.equals("banque")){
		 Banque a_modif=(Banque)request.getAttribute("a_modif");
	 %>
 			<input name="id" type="hidden" value="<%=a_modif.getId() %>"/>
			nom : <input name="nom" type="text" value="<%=a_modif.getNom()%>" /><br>
		<input name="action" type="submit" value="valider modification" /><br>
			<input name="type" type="hidden" value="banque" /><br>
			
	<%
	}
	 else if(type.equals("compte")){
		 Compte a_modif=(Compte)request.getAttribute("a_modif");
		 
	 %>
	 	<input name="id" type="hidden" value="<%=a_modif.getId() %>"/>
			numéro de compte : <input name="numero_compte" type="text" value="<%=a_modif.getNumeroCompte()%>" /><br>
<%-- 			type de compte : <select name="type_compte"><%%> --%>
<!--  			if (a_modif instanceof CompteEpargne){%> 
<!-- 				<option selected value="epargne">compte epargne</option> -->
<!-- 				<option  value="platinium">compte platinium</option> -->
<!-- 				<option  value="standard">compte standard</option> -->
<%-- 			<%} --%>
<%-- 			else if(a_modif instanceof ComptePlatine){%> --%>
<!-- 				<option selected value="platinium">compte platinium</option> -->
<!-- 				<option  value="standard">compte standard</option> -->
<!-- 				<option  value="epargne">compte epargne</option> -->
<%-- 			<%} --%>
<%-- 			else if(a_modif instanceof CompteStandard){%> --%>
<!-- 			<option selected value="standard">compte standard</option> -->
<!-- 			<option  value="epargne">compte epargne</option> -->
<!-- 			<option  value="platinium">compte platinium</option> -->
<%-- 			<%} --%>
<%-- 			else{%> --%>
<!-- 				<option selected value=""></option> -->
<%-- 			<%} --%>
<%-- 			%> --%>
<!-- 			</select><br> -->
			Clients :
			 <select name="client">
  			 <%   
 			 List<Client> clients= (List)request.getSession().getAttribute("liste_clients");
 			 for(Client c:clients){
 				 if(a_modif.getTitulaire().getId()==c.getId()){
 					%>
 					 <option selected value="<%=c.getId() %>"><%=c.getNom() +" "+ c.getPrenom() %></option> 
 				 <%}
 				 else{
 			 %>  
  			 <option value="<%=c.getId() %>"><%=c.getNom() +" "+ c.getPrenom() %></option>  
				 
			 <%} 
 				 } 
 			 %>  
			</select><br>
		<input name="action" type="submit" value="valider modification" /><br>
			<input name="type" type="hidden" value="compte" /><br>
			
	 <%}%>
	 </form>
<div><a href="admincontroler?"> Retour</a></div>
</div>
</body>
</html>