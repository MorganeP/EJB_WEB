<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banque DRAGON</title>
<link rel="stylesheet" href="css/style.css" type="text/css"  /> 
</head>
<body>
<div id="init" style="text-align: center;font-size: 20px;padding:20px;width:80%;margin:auto;">
<a href="init_db?action=init" style="border-radius:15px;border:white 20px solid;background-color:white;">Initialisation BDD</a>
</div>
<div style="padding:20px;width:50%;margin:auto;text-align:center;margin-top:20px;margin-bottom:20px;">
<h1>Page de connexion</h1>


<form action="index" method="GET">
	  Login: <input type="text" name="login" style="margin-left: 21px;"><br>
	  password: <input type="password" name="password"><br><br>
	  <input type="radio" name="type" value="conseiller">Conseiller
	  <input type="radio" name="type" value="client" checked>Client
	  <input type="radio" name="type" value="admin" >Admin
	  <br><br><br>
	  <input type="submit" name="action" value="identification">
</form>
</div>
<div style="display:inline-block;border:1px black solid;padding:10px;vertical-align:bottom;height:350px;text-align:center;margin-left:30px;margin-right:30px;">
<h3>Client</h3>
<p>login:Morgane</p>
<p>mdp:morgane</p>
<p style="font-size:20px;"><strong>actions possibles:</strong></p>
<ul>
	<li>consultation comptes</li>
	<li>effectuer retrait/dépôts</li>
	<li>consultation de l'historique des opérations</li>
</ul>
</div>
<div style="display:inline-block;border:1px black solid;padding:10px;vertical-align:bottom;height:350px;text-align:center;margin-left:30px;margin-right:30px;">
<h3>Conseiller</h3>
<p>login:Jacque</p>
<p>mdp:Jacque</p>
<p style="font-size:20px;"><strong>actions possibles:</strong></p>
 <ul>
	<li>consultation des différents Clients</li>
	<li>consultation des comptes</li>
	<li>consultation de l'historique</li>
</ul>
</div>
<div style="display:inline-block;border:1px black solid;padding:10px;vertical-align:bottom;height:350px;text-align:center;margin-left:30px;">
<h3>Admin</h3>
<p>login:admin</p>
<p>mdp:admin</p>
<p style="font-size:20px;"><strong>actions possibles:</strong></p>
 <ul>
 	<li>consultation Banques/Clients/Conseillers/Comptes</li>
	<li>consultation de l'historique</li>
	<li>possibilité création Client/Conseiller/Banque/Compte</li>

	
</ul>
</div>
</body>
</html>