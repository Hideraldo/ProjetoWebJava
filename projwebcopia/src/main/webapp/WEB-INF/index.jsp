<!DOCTYPE html>
<%@page import="br.com.projweb.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela de index</title>
</head>
<body>
		
	<%@include file="menu.jsp" %>
	<br><br>
	<h2>Bem vindo
	<%Usuario usuario = (Usuario)request.getSession().getAttribute("usuAutenticado");
	out.print(usuario.getNome());%>
	</h2>
	</body>
</html>