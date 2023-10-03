package br.uece.sgp;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Main {
	
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Bean
    public DataSource dataSource() {
    	//configuração do banco de dados
        return DataSourceBuilder
                .create()
                .driverClassName("org.sqlite.JDBC")
                .url("jdbc:sqlite:banco.db")
                .build();
    }
}
