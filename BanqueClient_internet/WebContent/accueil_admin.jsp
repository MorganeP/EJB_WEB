<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
        <%@ page import="java.util.Set" %>
    <%@ page import="banque.entites.Banque" %>
        <%@ page import="banque.entites.Conseiller" %>
        <%@ page import="banque.entites.Client" %>
        <%@ page import="banque.entites.Compte" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil Admin</title>
<link rel="stylesheet" href="css/style_client.css" type="text/css"  /> 
</head>
<body>
<div style="display:inline-block;width:45%;border:1px black solid;height:100%;padding:20px;">
	<form action="admincontroler" method="GET">
		<%
		
		 List<Banque> banques=(List) request.getSession().getAttribute("banque");%>
		
		

	
	<TABLE BORDER="1"> 
	  <CAPTION> Liste des Banques </CAPTION> 
	  <TR> 
	 <TH>id Banque </TH> 
	 <TH> Nom Banque </TH> 
	 <TH>Conseillers </TH> 
	  <TH>Clients </TH> 
	  <TH>Comptes </TH> 
	  </TR> 
	  
	
	
	<% for(Banque banque:banques){
		%> 
		<TR> 
		<th><%=banque.getId()%></th>
		<td><%=banque.getNom()%></td>
		<td><a href="admincontroler?action=lco&id=<%=banque.getId()%>" >Liste conseillers</a></td>
		<td><a href="admincontroler?action=lcl&id=<%=banque.getId()%>" >Liste clients</a></td>
		<td><a href="admincontroler?action=lcom&id=<%=banque.getId()%>" >Liste comptes</a></td>
		<td><a href="admincontroler?action=supprimer&type=banque&id=<%=banque.getId()%>" >Supprimer</a></td>
		<td><a href="admincontroler?action=editer&type=banque&id=<%=banque.getId()%>" >Editer</a></td>
		</TR>
	
	<%}
	%>
	</TABLE>
	<br>
	<br>
	<%
	 if(request.getAttribute("action")!=null){%>
	 
	 <%
	 	if(request.getAttribute("action").equals("lcl")){%>
			<TABLE BORDER="1"> 
			<CAPTION> Liste des Clients </CAPTION> 
			<TR> 
			<TH>id Client </TH> 
			<TH> Nom Client </TH> 
			<TH>Prenom Client </TH> 
			<TH>Liste compte </TH> 
			<TH>Supprimer </TH>
	 		<TH>Editer</TH>
			
			 </TR>
			 <%
	 		Set<Client> clients=(Set)request.getSession().getAttribute("clients_banque");
	  		 for(Client client:clients){%>
	
	 			<TR>
	 			<th><%=client.getId()%></th> 
				<td><%=client.getNom()%></td> 
				<td><%=client.getPrenom()%></td> 
				<td><a href="admincontroler?action=lcl&action2=lcom&origine_action2=client&id_client=<%=client.getId()%>&id=<%=client.getBanque().getId() %>" >Liste comptes</a></td>
	 			<td><a href="admincontroler?action=supprimer&type=client&id=<%=client.getId()%>" >Supprimer</a></td>
	 			<td><a href="admincontroler?action=editer&type=client&id=<%=client.getId()%>" >Editer</a></td>
	  			</TR>  
		
	  		<%} 
	 	}
	 	else if(request.getAttribute("action").equals("lco")){%>
			<TABLE BORDER="1"> 
			<CAPTION> Liste des Conseillers </CAPTION> 
			<TR> 
			<TH>id Conseillers </TH> 
			<TH> Nom Conseillers </TH> 
			<TH>Prenom Conseillers </TH> 
			<TH>Supprimer</TH>
	 		<TH>Editer</TH>
			
			 </TR>
			 <%
			Set<Conseiller> conseillers=(Set)request.getSession().getAttribute("conseillers_banque");
			 for(Conseiller conseiller:conseillers){%>
	
				<TR>
	 			<th><%=conseiller.getId()%></th> 
				<td><%=conseiller.getNom()%></td> 
				<td><%=conseiller.getMdp()%></td> 
	 			<td><a href="admincontroler?action=supprimer&type=conseiller&id=<%=conseiller.getId()%>" >Supprimer</a></td>
	 			<td><a href="admincontroler?action=editer&type=conseiller&id=<%=conseiller.getId()%>" >Editer</a></td>
				
				</TR>  
		
			<%} 
		}
	 	else if(request.getAttribute("action").equals("lcom")){%>
			<TABLE BORDER="1"> 
			<CAPTION> Liste des Comptes </CAPTION> 
			<TR> 
			<TH>id comptes </TH> 
			<TH> Numéro comptes </TH> 
			<TH>Solde comptes </TH> 
			<TH>Historique</TH>
			<TH>Supprimer</TH>
	 		<TH>Editer</TH>
			
			 </TR>
			 <%
			Set<Compte> comptes=(Set)request.getSession().getAttribute("comptes_banque");
			 for(Compte compte:comptes){%>
	
				<TR>
				<th><%=compte.getId()%></th> 
				<td><%=compte.getNumeroCompte()%></td> 
				<td><%=compte.getSolde()%></td> 
				<td><a href="admincontroler?action=historique&id=<%=compte.getId()%>" >Historique</a></td>
	 			<td><a href="admincontroler?action=supprimer&type=compte&id=<%=compte.getId()%>" >Supprimer</a></td> 
				<td><a href="admincontroler?action=editer&type=compte&id=<%=compte.getId()%>" >Editer</a></td> 
				</TR>  
		
			<%} 
		}
	 	}%>
	 	</TABLE>
	 	<br>
	 	<br>
	 	 <%if(request.getAttribute("action2")!=null){%>
	  	<% 
	  	if(request.getAttribute("action2").equals("lcom")){%> 
			<TABLE BORDER="1">  
	 		<CAPTION> Liste des Comptes du client </CAPTION>  
	 		<TR>  
	 		<TH>id comptes </TH>  
	 		<TH> Numéro comptes </TH>  
	 		<TH>Solde comptes </TH>
	 		<TH> Historique</TH>
	 		<TH>Supprimer</TH>
	 		<TH>Editer</TH>
			
	 		 </TR> 
	  	<% 
	 	Set<Compte> comptes=(Set)request.getSession().getAttribute("comptes_client");
	 		for(Compte compte:comptes){%> 
				<TR> 
	 			<th><%=compte.getId()%></th>  
	 			<td><%=compte.getNumeroCompte()%></td>  
	 			<td><%=compte.getSolde()%></td>
	 			<td><a href="admincontroler?action=historique&id=<%=compte.getId()%>" >Historique</a></td> 
	 			<td><a href="admincontroler?action=supprimer&type=compte&id=<%=compte.getId()%>" >Supprimer</a></td>
	 			<td><a href="admincontroler?action=editer&type=compte&id=<%=compte.getId()%>" >Editer</a></td>
	 			</TR>  
	 	<%} 
	  	}
	  	
	  	
	   	}
	 	 %> 
	 </TABLE> 
	
	
	
	</form>
</div>
<div style="display:inline-block;width:45%;border:1px black solid;height:100%;vertical-align:top;">
	<form action="admincontroler">
	
		<input name="action" type="submit" value="Ajouter client" /> 
		<input name="action" type="submit" value="Ajouter conseiller" /> 
		<input name="action" type="submit"	value="Ajouter compte" /> 
		<input name="action" type="submit" value="Ajouter banque" />
	 
	</form>
	<form action="admincontroler" method="GET">
<%
if(request.getAttribute("type_ajout")!=null){
	String type_ajout=(String) request.getAttribute("type_ajout");
	
	if(type_ajout.equals("client")){%>
		
		nom : <input name="nom" type="text" value="" /><br>
		Prenom : <input name="prenom" type="text" value="" />  <br>
		 Conseiller:<select name="conseiller">
  			<%   
 			 List<Conseiller> conseillers= (List)request.getSession().getAttribute("conseillers");
 			 for(Conseiller c:conseillers){
 			 %>  
  			 <option value="<%=c.getId() %>"><%=c.getNom() %></option>  
				 
			 <%}  
 			 %>
		 </select><br>
		Login : <input name="login" type="text" value="" /> <br>
		Mdp : <input name="mdp" type="text" value="" /><br>
		<input name="action" type="submit" value="Ajouter" /><br>
		<input name="type_ajout" type="hidden" value="client" /><br>
		
		
	<%
	}
	 else if(type_ajout.equals("conseiller")){ %>
			
			nom : <input name="nom" type="text" value="" /><br>
			Mdp : <input name="mdp" type="text" value="" /><br>
			 Banque : <select name="banque">
  			<%   
 			 List<Banque> banquess= (List)request.getSession().getAttribute("banque");
 			 for(Banque b:banquess){
 			 %>  
  			 <option value="<%=b.getId() %>"><%=b.getNom() %></option>  
				 
			 <%}  
 			 %>
 			 </select><br>
			<input name="action" type="submit" value="Ajouter" /><br>
			<input name="type_ajout" type="hidden" value="conseiller" /><br>
			 
	<%
	}
	 else if (type_ajout.equals("banque")){%>
 			
			nom : <input name="nom" type="text" value="" /><br>
			<input name="action" type="submit" value="Ajouter" /><br>
			<input name="type_ajout" type="hidden" value="banque" /><br>
			
	<%
	}
	 else if(type_ajout.equals("compte")){%>
			numéro de compte : <input name="numero_compte" type="text" value="" /><br>
			type de compte : <select name="type_compte">
			<option value="epargne">compte epargne</option>
			<option value="standard">compte standard</option>
			<option value="platinium">compte platinium</option>
			</select><br>
			Clients :
			 <select name="client">
  			 <%   
 			 List<Client> clients= (List)request.getSession().getAttribute("liste_clients");
 			 for(Client c:clients){
 			 %>  
  			 <option value="<%=c.getId() %>"><%=c.getNom() +" "+ c.getPrenom() %></option>  
				 
			 <%}  
 			 %>  
			</select><br>
			<input name="action" type="submit" value="Ajouter" /><br>
			<input name="type_ajout" type="hidden" value="compte" /><br>
			
	 <%}
	
} %>
</form>	
</div>
</body>
</html>