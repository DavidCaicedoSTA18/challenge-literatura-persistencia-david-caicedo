package com.alura.catalogolibros;

import com.alura.catalogolibros.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogolibrosApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;


	public static void main(String[] args) {
		SpringApplication.run(CatalogolibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.muestraElMenu();
	}

}
