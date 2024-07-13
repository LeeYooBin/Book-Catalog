package com.book_catalog.view;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book_catalog.exception.SearchException;
import com.book_catalog.model.Author;
import com.book_catalog.model.Book;
import com.book_catalog.service.AuthorService;
import com.book_catalog.service.BookService;

@Component
public class App {
  private final Scanner scanner = new Scanner(System.in);

  @Autowired
  private final BookService bookService;
  
  @Autowired
  private final AuthorService authorService;

  public App(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

  public void init() throws IOException {
    while (true) {
      clearScreen();
      displayMenu();
      System.out.print("Escolha uma opção: ");
      
      int option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1:
          registerBook();
          break;
        case 2:
          listAllBooks();
          break;
        case 3:
          listAllAuthors();
          break;
        case 4:
          listAuthorsAliveInYear();
          break;
        case 5:
          listBooksByLanguage();
          break;
        case 6:
          clearScreen();
          System.out.println("\nEncerrando o programa...\n");
          scanner.close();
          return;
        default:
          clearScreen();
          System.out.println("Opção inválida.");
          waitForKeyPress();
      }
    }
  }

  private void displayMenu() {
    System.out.println("1. Cadastrar Livro");
    System.out.println("2. Listar Todos os Livros Cadastrados");
    System.out.println("3. Listar Todos os Autores Registrados");
    System.out.println("4. Listar Autores Vivos em um Determinado Ano");
    System.out.println("5. Listar Livros em um Determinado Idioma");
    System.out.println("6. Sair");
  }

  private void registerBook() throws IOException {
    clearScreen();
    System.out.print("Digite o título do livro para busca: ");
    String title = scanner.nextLine();

    try {
      bookService.registerBooks(title);
      System.out.println("Livros cadastrados com sucesso!");
    } catch (SearchException e) {
      System.out.println(e.getMessage());
    }

    waitForKeyPress();
  }

  private void listAllBooks() {
    List<Book> bookList = bookService.listAllBooks();
    if (bookList.isEmpty()) {
      System.out.println("Nenhum livro registrado.");
    } else {
      bookList.forEach(System.out::println);
    }
    waitForKeyPress();
  }
  
  private void listAllAuthors() {
    List<Author> authorList = authorService.listAllAuthors();
    if (authorList.isEmpty()) {
      System.out.println("Nenhum autor registrado.");
    } else {
      authorList.forEach(System.out::println);
    }
    waitForKeyPress();
  }

  private void listAuthorsAliveInYear() {
    System.out.println("Digite o ano: ");
    Integer yearValue = scanner.nextInt();
    scanner.nextLine();

    Year year = Year.of(yearValue);

    List<Author> authorList = authorService.listAuthorsAliveInYear(year);
    if (authorList.isEmpty()) {
      System.out.println("Nenhum autor vivo encontrado.");
    } else {
      System.out.println("Lista de autores vivos no ano de " + yearValue + ":\n");
      authorList.forEach(System.out::println);
    }
    waitForKeyPress();
  }

  private void listBooksByLanguage() {
    System.out.println("""
        Digite o idioma pretendido:
        Inglês (en)
        Português (pt)
        Espanhol (es)
        Francês (fr)
        Alemão (de)
        """);
    String language = scanner.nextLine();

    List<Book> bookList = bookService.listBooksByLanguage(language);
    if (bookList.isEmpty()) {
      System.out.println("Nenhum livro encontrado no idioma especificado.");
    } else {
      bookList.forEach(System.out::println);
    }
    waitForKeyPress();
  }

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private void waitForKeyPress() {
    System.out.println("Pressione Enter para continuar...");
    try {
      System.in.read();
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}