<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="banque.entites.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="banque.entites.Compte" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion Compte</title>
</head>
<body>
 <%
Client c = (Client) request.getSession().getAttribute("client");

 List<Compte> comptes=(List) request.getSession().getAttribute("comptes");

%>
<h1> compte Client </h1nthèse >
<h2>Informations clients</h2>
<p>Nom Client : <strong><%=c.getNom()%> </strong></p>
<p>Login Client : <strong><%=c.getLogin()%></strong></p>
<p>Conseiller : <strong><%=c.getConseiller().getNom()%></strong></p>
<p>Banque : <strong><%=c.getBanque().getNom()%></strong></p>

<h2>Synthèse compte</h2>
<form action="client" method="GET">
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

</TABLE> 
<h2>effectuer une opération:</h2>
<select name="operation">
  <option value="retrait">Retrait</option>
  <option value="dépôt">Dépôt</option>
</select> 

<select name="numero_compte">
<% for(Compte compte:comptes){
	%>
	<option value=<%=compte.getId()%>><%=compte.getNumeroCompte() %></option>
	
	<% 
}%>
</select>
<input name="montant" type="number" min="0" max="1000"/>
<input name="action" type="submit" value="effectuer opération"/>
</form>

</body>
</html>