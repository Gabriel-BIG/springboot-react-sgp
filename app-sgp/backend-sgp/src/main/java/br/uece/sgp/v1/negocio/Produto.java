package br.uece.sgp.v1.negocio;

public class Produto {
	
    private Long id;
    private String nome;//titulo
    private String descr;//descricao
    private String qtd;
    private String preco;//data

    // Construtor
    public Produto(Long id, String nome, String descr) {
        this.id = id;
        this.nome = nome;
        this.descr = descr;
    }
    public Produto(Long id, String nome, String descr, String qtd, String preco) {
        this.id = id;
        this.nome = nome;
        this.descr = descr;
        this.qtd = qtd;
        this.preco = preco;
    }
    // Construtor
    public Produto(String valor1, String valor2) {
        this.nome = valor1;
        this.descr = valor2;
    }
    public Produto(Long id) {
        this.id = id;
    }
    public Produto() {
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
