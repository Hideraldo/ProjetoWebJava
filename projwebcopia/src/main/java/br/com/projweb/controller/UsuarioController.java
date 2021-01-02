package br.com.projweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projweb.entidade.Usuario;
import br.com.projweb.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp);
		// System.out.println("chamou");

		resp.setContentType("text/html");

		String acao = req.getParameter("acao");
		if (acao.equals("excluir")) {
			// para excluir pelo doget
			String id = req.getParameter("id");

			Usuario usu = new Usuario();
			if (id != null) {
				usu.setId(Integer.parseInt(id));
			}

			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.excluir(usu);

			// resp.getWriter().print("<br>Excluído com sucesso!</br>");
			resp.sendRedirect("usucontroller.do?acao=list");

		} else if (acao.equals("list")) {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			List<Usuario> lista = usuarioDao.buscarTodos();

			// resp.getWriter().print(lista);

			req.setAttribute("lista", lista);

			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("alt")) {
			String id = req.getParameter("id");

			UsuarioDAO usuarioDao = new UsuarioDAO();
			Usuario usuario = usuarioDao.buscaPorId(Integer.parseInt(id));

			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("cad")) {
			String id = req.getParameter("id");

			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");

			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doPost(req, resp);

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usu = new Usuario();
		if (id != null) {
			usu.setId(Integer.parseInt(id));
		}
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);

		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.salvar(usu);

		//resp.getWriter().print("<br>Sucesso!</br>");
		resp.sendRedirect("usucontroller.do?acao=list");
	}
}
