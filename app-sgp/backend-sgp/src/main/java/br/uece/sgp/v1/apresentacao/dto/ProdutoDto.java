package br.uece.sgp.v1.apresentacao.dto;


public class ProdutoDto {
	
    private Long id;
    private String nome;
    private String descr;
    private String qtd;
    private String preco;

    // Construtor
    public ProdutoDto(Long id, String valor1, String valor2, String valor3) {
        this.id = id;
        this.nome = valor1;
        this.descr = valor2;
        this.preco = valor3;
    }
    // Construtor
    public ProdutoDto(String valor1, String valor2) {
        this.nome = valor1;
        this.descr = valor2;
    }
    
    public ProdutoDto() {
	}
    
	// Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String valor1) {
        this.nome = valor1;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String valor2) {
        this.descr = valor2;
    }
	public String getPreco() {
		return preco;
	}
	public void setPreco(String valor3) {
		this.preco = valor3;
	}
	public String getQtd() {
		return qtd;
	}
	public void setQtd(String qtd) {
		this.qtd = qtd;
	}
    
}
