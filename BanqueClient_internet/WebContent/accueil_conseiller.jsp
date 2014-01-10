<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="banque.entites.Client" %>
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

 List<Client> clients=c.getClients();
%>
<h2>Synthèse compte</h2>
<form action="index" method="GET">
<TABLE BORDER="1"> 
  <CAPTION> Liste des comptes </CAPTION> 
  <TR> 
 <TH> Numéro de compte </TH> 
 <TH> Solde </TH> 
 <TH>Historique </TH> 
  </TR> 
  

<% for(Client client:clients){
	%> 
	<TR> 
	<th><%=client.getNom()%></th>
	<td><%=client.getPrenom()%></td>
	<td><input name="action" type="submit" value="compte_<%=client.getId()%>" /></td>
	</TR>

<%}
%> 
</form> 
</TABLE> 
</body>
</html>