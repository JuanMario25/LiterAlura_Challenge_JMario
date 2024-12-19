package com.literalura.jmario.booksapp;

import com.literalura.jmario.booksapp.main.Menu;
import com.literalura.jmario.booksapp.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BooksAppApplication implements CommandLineRunner {

	@Autowired
	BooksRepository repository ;

	public static void main(String[] args) {
		SpringApplication.run(BooksAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu(repository);
		menu.displayMenu();
	}
}
