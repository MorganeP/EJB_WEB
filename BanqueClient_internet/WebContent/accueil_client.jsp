<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="banque.entites.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="banque.entites.Compte" %>
<jsp:useBean id="client" scope="session" class="banque.entites.Client" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion Compte</title>
</head>
<body>
 <%
Client c = (Client) request.getSession().getAttribute("client");
c.getNom();
client.getNom();
 List<Compte> comptes=(List) request.getSession().getAttribute("comptes");

%>
<h1> compte Client </h1nthèse >
<h2>Informations clients</h2>
<p>Nom Client : <%=c.getNom()%></p>
<p>Login Client : <%=c.getLogin()%></p>
<p>Conseiller : <%=c.getConseiller().getNom()%></p>
<p>Banque : <%=c.getBanque().getNom()%></p>

<h2>Synthèse compte</h2>
<form action="index" method="GET">
<TABLE BORDER="1"> 
  <CAPTION> Liste des comptes </CAPTION> 
  <TR> 
 <TH> Numéro de compte </TH> 
 <TH> Solde </TH> 
 <TH>Historique </TH> 
  </TR> 
  


<% for(Compte compte:comptes){
	%> 
	<TR> 
	<th><%=compte.getNumeroCompte()%></th>
	<td><%=compte.getSolde()%></td>
	<td><input name="action" type="submit" value="historique_<%=compte.getId()%>" /></td>
	</TR>

<%}
%> 
</form> 
</TABLE> 
<p></p>




</body>
</html>