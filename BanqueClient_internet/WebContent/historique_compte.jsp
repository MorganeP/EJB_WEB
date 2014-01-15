<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="banque.entites.Operation" %>
        <%@ page import="banque.entites.Compte" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style_client.css" type="text/css"  /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<div class="body">
<title>Historique Compte</title>
</head>
<body>
<h1>Historique du compte</h1>
<div align="center">
<TABLE BORDER="1"> 
  <CAPTION> Liste des comptes </CAPTION> 
    <TR> 
 <TH> Id </TH> 
 <TH> Type </TH> 
 <TH> Date </TH> 
 <TH>Montant </TH> 
  </TR> 
 <%
 
 Compte c=(Compte) request.getSession().getAttribute("compte_historique");
 List<Operation> operations_comptes=c.getOperations();

 

%>
<%


for(Operation operation:operations_comptes){
	%>
	<TR> 
	<td><%=operation.getId()%></td>
	<td><%=operation.getType()%></td>
	<td><%=operation.getDate()%></td>
	<td><%=operation.getMontant()%></td>
	</TR>
	<%
}
%> 
</TABLE> 


  




	<%
	
		 String page_origine=(String)request.getAttribute("page_origine");
	if(page_origine.equals("accueil_admin")) {
		%><a href="admincontroler?"> Retour</a>
		<%}
	if(page_origine.equals("conseiller")){%>
		<a href="index?action=retour"> Retour</a>
	<%}
	else{%>
		
		<a href="client?action=retour"> Retour</a>
		<%}
	%>
	</div>
</div>
</body>
</html>