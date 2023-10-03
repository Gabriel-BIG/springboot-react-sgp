package br.uece.sgp.v1.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import br.uece.sgp.v1.negocio.Produto;

@Component
public class RepositorioProdutoJdbc implements RepositorioProduto {

	private final Connection conexao;

	public RepositorioProdutoJdbc(DataSource dataSource) {
		try {

			this.conexao = dataSource.getConnection();

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		}
	}
	
	
	private Produto mapearResultadoParaProduto(ResultSet resultado) throws SQLException {
		Long id = resultado.getLong("id");
		String nome = resultado.getString("nome");
		String descr = resultado.getString("descr");
		String qtd = resultado.getString("qtd");
		String preco = resultado.getString("preco");
		return new Produto(id, nome, descr, qtd, preco);
	}

	@Override
	public Produto salvarProduto(Produto produto) {
		String sql = "INSERT INTO tbl_produto (nome, descr, qtd, preco) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescr());
			stmt.setString(3, produto.getQtd());
			stmt.setString(4, produto.getPreco());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A inserção falhou, nenhum registro foi adicionado.");
			}

			// Recupere o ID gerado para o registro inserido
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					produto.setId(generatedKeys.getLong(1));
					System.out.printf("[LOG] ID do registro 'inserido': %d!\n", produto.getId());
				} else {
					throw new PersistenciaException("Nenhum ID gerado.");
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao salvar produto.", e);
		}
		return produto;
	}

	@Override
	public Produto buscarProdutoPorId(Long id) {
		String sql = "SELECT * FROM tbl_produto WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet resultado = stmt.executeQuery()) {
				if (resultado.next()) {
					return mapearResultadoParaProduto(resultado);
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao buscar produto por ID.", e);
		}
		return null;
	}

	@Override
	public List<Produto> listarProdutos() {
		
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM tbl_produto ORDER BY id DESC";
		
		try (
				PreparedStatement stmt = conexao.prepareStatement(sql); 
				ResultSet resultado = stmt.executeQuery()
		) {
			
			while (resultado.next()) {
				produtos.add(mapearResultadoParaProduto(resultado));
			}
			
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar produtos.", e);
		}
		return produtos;
	}

	@Override
	public void atualizarProduto(Produto produto) {
		String sql = "UPDATE tbl_produto SET nome = ?, descr = ?, qtd =?, preco = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescr());
			stmt.setString(3, produto.getQtd());
			stmt.setString(4, produto.getPreco());
			stmt.setLong(5, produto.getId());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A atualização falhou, nenhum registro foi atualizado.");
			}

			System.out.printf("[LOG] ID do registro 'atualizado': %d!\n", produto.getId());
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao atualizar produto.", e);
		}
	}

	@Override
	public void excluirProduto(Produto produto) {
		String sql = "DELETE FROM tbl_produto WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setLong(1, produto.getId());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new PersistenciaException("A exclusão falhou, nenhum registro foi removido.");
			}

			System.out.printf("[LOG] ID do registro 'removido': %d!\n", produto.getId());
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao excluir produto.", e);
		}
	}
	
}
