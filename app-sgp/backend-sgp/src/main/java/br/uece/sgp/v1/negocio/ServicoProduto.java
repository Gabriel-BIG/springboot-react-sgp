package br.uece.sgp.v1.negocio;

import java.util.List;

import org.springframework.stereotype.Service;

import br.uece.sgp.v1.persistencia.RepositorioProduto;

@Service
public class ServicoProduto {

	private RepositorioProduto repositorioProduto;

	public ServicoProduto(RepositorioProduto repositorioProduto) {
		this.repositorioProduto = repositorioProduto;
	}

	public List<Produto> listarProdutos() {
		List<Produto> produtos = repositorioProduto.listarProdutos();
		if (produtos.isEmpty()) {
			throw new NegocioException("Nenhuma produto encontrada.");
		}
		return produtos;
	}

	public Produto criarProduto(Produto novaProduto) {
		return repositorioProduto.salvarProduto(novaProduto);
	}

	public Produto atualizarProduto(Produto produto) {
		repositorioProduto.atualizarProduto(produto);
		return buscarProdutoPorId(produto.getId());
	}
		
	public void removerProduto(Produto produto) {
		Produto produtoExistente = this.buscarProdutoPorId(produto.getId());
		repositorioProduto.excluirProduto(produtoExistente);
	}
	
	public Produto buscarProdutoPorId(Long id) {
		Produto produtoExistente = repositorioProduto.buscarProdutoPorId(id);
		
		if (produtoExistente == null) {
			throw new NegocioException("Produto não encontrado.");
		}
		
		return produtoExistente;
	}

}
