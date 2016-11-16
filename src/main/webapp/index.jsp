<%@page import="java.util.List"%>
<%@page import="co.simplon.prairie.Joueur"%>
<%@ taglib prefix="j" uri="/jodd" %>
<html>
<body>
<h1>Gestion d'équipe de football</h1>

L'équipe située à San Francisco est : <b>${nomEquipe}</b>
<br/><br/>
Liste filtrée et triée des membres de l'équipe :

<table border="1">
	<thead>
		<tr>
			<th>Prénom</th>
			<th>Nom</th>
			<th>Date de naissance</th>
			<th>Poids</th>
			<th>Numéro</th>
		</tr>
	</thead>
	<j:iter items="${joueurs}" var="j">
		<tr>
			<td>${j.prenom}</td>
			<td>${j.nom}</td>
			<td>${j.dateNaissance}</td>
			<td>${j.poids}</td>
			<td>${j.numeroMaillot}</td>
		</tr>
	</j:iter>
</table>
 
</body>
</html>
