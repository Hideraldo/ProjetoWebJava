<!DOCTYPE html>
<%@page import="br.com.projweb.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
	<%@include file="menu.jsp" %>
<%
	Usuario u = (Usuario)request.getAttribute("usu");
%>
	<form action="usucontroller.do" method="post">
		<br><a>Id:</a>
		<input type="text" name="id" value="<%=u.getId()%>">
		<br><br><a>Nome:</a>
		<input type="text" name="nome" value="<%=u.getNome()%>">
		<br><br><a>Login:</a>
		<input type="text" name="login" value="<%=u.getLogin()%>">
		<br><br><a>Senha:</a>
		<input type="password" name="senha" value="<%=u.getSenha()%>">
		<br><br>
		<input type="submit" value="Salvar">
	</form>
</body>
</html>