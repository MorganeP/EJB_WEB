<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="banque.entites.Client" %>
<%@ page import="banque.entites.Compte" %>
<%@ page import="java.util.List" %>
<%@ page import="banque.entites.Conseiller" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil Conseiller</title>
</head>
<body>
 <%
Conseiller c = (Conseiller) request.getSession().getAttribute("conseiller");

 Set<Client> clients=c.getClients();
 
%>
<h2>Synthèse compte</h2>
<form action="index" method="GET">

  

<% for(Client client:clients){
	Set<Compte> comptes_client=(Set)client.getComptes();
	%> 
	<div style="border:1px black solid;">
	<h3>Client</h3>
	<p>nom : <strong><%=client.getNom()%></strong></p>
	<p>Prenom : <strong><%=client.getPrenom()%></strong></p>

<TABLE BORDER="1"> 
  <CAPTION> Liste des Comptes </CAPTION> 
  <TR> 
 <TH> id compte </TH> 
 <TH> numéro de compte </TH> 
  <TH> Montant </TH> 
 <TH>Historique </TH> 
  </TR> 
<%

	for(Compte compte:comptes_client){%>
	<TR>
	<td><%=compte.getId() %></td>
	<td><%=compte.getNumeroCompte() %></td>
	<td><%=compte.getSolde() %></td>
	<form action="index" method="GET">
	<td><input name="action" type="submit" value="historique_<%=compte.getId()%>" /></td>
	</form>
	</TR>
	
	
		
	<%}%></TABLE> </div>
	<%
}
%> 
</form> 

</body>
</html>