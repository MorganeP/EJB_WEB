<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banque DRAGON</title>
</head>
<body>
<h1>Page de connexion</h1>


<form action="index" method="GET">
	  Login: <input type="text" name="login"><br>
	  password: <input type="password" name="password"><br><br>
	  <input type="radio" name="type" value="conseiller">Conseiller
	  <input type="radio" name="type" value="client" checked>Client
	  <input type="radio" name="type" value="admin" >Admin
	  <br><br><br>
	  <input type="submit" name="action" value="identification">
</form>
<div style="display:inline-block;border:1px black solid;padding:10px;">
<h3>Client</h3>
<p>login:Morgane</p>
<p>mdp:morgane</p>
<p>action:</p>
<ul>
	<li>consultation comptes</li>
	<li>effectuer retrait/dépôts</li>
	<li>consultation de l'historique des opérations</li>
</ul>
</div>
<div style="display:inline-block;border:1px black solid;padding:10px;">
<h3>Conseiller</h3>
<p>login:Jacque</p>
<p>mdp:Jacque</p>
 <ul>
	<li>consultation des différents Clients</li>
	<li>consultation des comptes</li>
	<li>consultation de l'historique</li>
</ul>
</div>
<div style="display:inline-block;border:1px black solid;padding:10px;">
<h3>Admin</h3>
<p>login:admin</p>
<p>mdp:admin</p>
 <ul>
 	<li>consultation des différentes Banques</li>
	<li>consultation des différents Conseillers</li>
	<li>possibilité d'ajouter Conseiller</li>
	<li>consultation des différents Clients</li>
	<li>consultation des comptes</li>
	<li>consultation de l'historique</li>
</ul>
</div>
</body>
</html>