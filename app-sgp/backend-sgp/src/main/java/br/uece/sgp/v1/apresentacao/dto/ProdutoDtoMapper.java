package br.uece.sgp.v1.apresentacao.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.uece.sgp.v1.negocio.Produto;

public class ProdutoDtoMapper {

    public static ProdutoDto toDto(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescr(produto.getDescr());
        dto.setQtd(produto.getQtd());
        dto.setPreco(produto.getPreco());
        return dto;
    }
    
    public static Produto fromDto(ProdutoDto dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescr(dto.getDescr());
        produto.setQtd(dto.getQtd());
        produto.setPreco(dto.getPreco());
        return produto;
    }

    public static List<ProdutoDto> toDtoList(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Produto> fromDtoList(List<ProdutoDto> dtos) {
        return dtos.stream()
                .map(ProdutoDtoMapper::fromDto)
                .collect(Collectors.toList());
    }
}
