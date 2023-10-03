-- Produto definition

CREATE TABLE tbl_produto (

    id INTEGER PRIMARY KEY AUTOINCREMENT,

    nome TEXT NOT NULL,

    descr TEXT NOT NULL,

    qtd TEXT,    

    preco TEXT NOT NULL       
);
