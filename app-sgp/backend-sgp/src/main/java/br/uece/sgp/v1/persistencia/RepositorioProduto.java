package br.uece.sgp.v1.persistencia;

import java.util.List;

import br.uece.sgp.v1.negocio.Produto;

public interface RepositorioProduto{
    Produto salvarProduto(Produto produto);
    Produto buscarProdutoPorId(Long id);
    List<Produto> listarProdutos();
    void atualizarProduto(Produto produto);
    void excluirProduto(Produto produto);
}
