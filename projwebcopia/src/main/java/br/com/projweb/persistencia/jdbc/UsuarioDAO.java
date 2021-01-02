package br.com.projweb.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projweb.entidade.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usuario) {

		String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?,?,MD5(?))";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usuario) {

		String sql = "UPDATE usuario SET nome=?, login=?, senha=MD5(?) WHERE id=?";

		// não é necessário o preparador.close(), fecha automaticamente
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());

			preparador.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvar(Usuario usuario) {

		if (usuario.getId() != null && usuario.getId() != 0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}

	public void excluir(Usuario usuario) {

		String sql = "DELETE FROM usuario WHERE id=?";

		// não é necessário o preparador.close(), fecha automaticamente
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, usuario.getId());

			preparador.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario buscaPorId(Integer id) {

		Usuario usuRetorna = null;

		String sql = "SELECT * FROM usuario WHERE id = ?";

		try (PreparedStatement prepador = con.prepareStatement(sql)) {

			prepador.setInt(1, id);

			ResultSet resultado = prepador.executeQuery();

			if (resultado.next()) {
				usuRetorna = new Usuario();
				usuRetorna.setId(resultado.getInt("id"));
				usuRetorna.setNome(resultado.getString("nome"));
				usuRetorna.setLogin(resultado.getString("login"));
				usuRetorna.setSenha(resultado.getString("senha"));
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return usuRetorna;
	}

	public List<Usuario> buscarTodos() {

		Usuario usuRetorna = null;
		String sql = "SELECT * FROM usuario";
		List<Usuario> lista = new ArrayList<Usuario>();

		try (PreparedStatement prepador = con.prepareStatement(sql)) {

			ResultSet resultado = prepador.executeQuery();

			while (resultado.next()) {
				usuRetorna = new Usuario();
				usuRetorna.setId(resultado.getInt("id"));
				usuRetorna.setNome(resultado.getString("nome"));
				usuRetorna.setLogin(resultado.getString("login"));
				usuRetorna.setSenha(resultado.getString("senha"));
				
				lista.add(usuRetorna);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return lista;
	}
	
	public Usuario autenticar(Usuario usuConsulta) {
		
		String sql = "SELECT * FROM usuario WHERE login=? AND senha=MD5(?)";
		
		try(PreparedStatement prepador = con.prepareStatement(sql)) {
			
			prepador.setString(1, usuConsulta.getLogin());
			prepador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = prepador.executeQuery();
			
			if(resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
