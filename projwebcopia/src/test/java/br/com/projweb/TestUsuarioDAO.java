package br.com.projweb;

import java.util.List;

import br.com.projweb.entidade.Usuario;
import br.com.projweb.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {

		// testCadastrar();
		// testAlterar();
		// testExcluir();
		// testSalar();
		//testbuscaPorId();
		testBuscaTodos();
		//testAutenticar();
		
	}

	public static void testCadastrar() {
		// cria um usuário
		Usuario usuario = new Usuario();
		usuario.setNome("Maria Dolores Maranhão");
		usuario.setLogin("ma");
		usuario.setSenha("ma123");

		// cadastra um usuário
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.cadastrar(usuario);

		System.out.println("Cadastrado com sucesso!");
	}

	public static void testAlterar() {
		// cria um usuário
		Usuario usuario = new Usuario();
		usuario.setId(2);
		usuario.setNome("Maria das Flores Maranhão");
		usuario.setLogin("ma");
		usuario.setSenha("ma123");

		// cadastra um usuário
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.alterar(usuario);

		System.out.println("Alterado com sucesso!");
	}

	public static void testSalar() {
		// cria um usuário
		Usuario usuario = new Usuario();
		usuario.setNome("Rita de Cassia Napoleão");
		usuario.setLogin("rita");
		usuario.setSenha("rita123");

		// cadastra um usuário
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.salvar(usuario);

		System.out.println("Salvo com sucesso!");
	}

	public static void testExcluir() {
	
		Usuario usuario = new Usuario();
		usuario.setId(2);

		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.excluir(usuario);

		System.out.println("Exluido com sucesso!");
	}

	public static void testbuscaPorId() {

		UsuarioDAO usuarioDao = new UsuarioDAO();
		Usuario usuario = usuarioDao.buscaPorId(3);

		System.out.println(usuario);
		System.out.println("Nome: "+usuario.getNome());
	}

	private static void testBuscaTodos() {

		UsuarioDAO usuarioDao = new UsuarioDAO();
		List<Usuario> lista = usuarioDao.buscarTodos();
		
		for (Usuario u : lista) {
			System.out.println(u);
		}
	}
	
	private static void testAutenticar() {

		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("rita");
		usu.setSenha("rita123");
		
		Usuario usuRetorno = usuarioDao.autenticar(usu);
		
		System.out.println(usuRetorno);
	}
}
