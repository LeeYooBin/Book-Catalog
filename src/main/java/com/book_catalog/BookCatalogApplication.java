package com.book_catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.book_catalog.view.App;

@SpringBootApplication
public class BookCatalogApplication implements CommandLineRunner {
  @Autowired
  private App app;

  public BookCatalogApplication (App app) {
    this.app = app;
  }

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {

    app.init();
  }

}
