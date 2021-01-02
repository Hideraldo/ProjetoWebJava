<%@page import="java.util.List"%>
<%@page import="br.com.projweb.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuários</title>

<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm('Tem certeza que deseja excluir?')) {
			location.href = "usucontroller.do?acao=excluir&id=" + id;
		}
	}

	function novo() {
		location.href = 'usucontroller.do?acao=cad';
	}
</script>

</head>
<body>
	<%@include file="menu.jsp"%>
	<%
	List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
	%>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Login</th>
			<th>Ação</th>
		</tr>
		<%
		for (Usuario u : lista) {
			//out.print(u.getId() + " - " + u.getNome() + "<br>");
		%>

		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getNome()%></td>
			<td><%=u.getLogin()%></td>
			<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)">excluir</a>
				| <a href="usucontroller.do?acao=alt&id=<%=u.getId()%>">alterar</a>
			</td>
		</tr>
		<%
		}
		%>
	</table>
		<br><br>
		<input type="button" onclick="javascript:novo()" value="Novo">
</body>
</html>