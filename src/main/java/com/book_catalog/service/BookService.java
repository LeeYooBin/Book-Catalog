package com.book_catalog.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book_catalog.api.GutendexApiClient;
import com.book_catalog.dto.BookDTO;
import com.book_catalog.exception.SearchException;
import com.book_catalog.model.Book;
import com.book_catalog.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;
  
  @Autowired
  private DataConverterService dataConverter;
  
  @Autowired
  private GutendexApiClient apiClient;

  public void registerBooks(String title) throws IOException {
    String response = apiClient.search(title);
    if (response.isEmpty()) {
      throw new SearchException("Livro não encontrado.");
    }
    JsonNode node = dataConverter.getObjectMapper().readTree(response).path("results");
    if (node.isEmpty()) {
      throw new SearchException("Não foi possível encontrar o livro buscado.");
    }
    List<BookDTO> bookDTOs = dataConverter.parseJsonToBookList(node.toString());
    List<Book> existingBooks = bookRepository.findByTitle(title);
    for (Book existingBook : existingBooks) {
      bookDTOs.removeIf(dto -> existingBook.getTitle().equals(dto.title()));
    }
    if (!bookDTOs.isEmpty()) {
      List<Book> newBooks = bookDTOs.stream().map(Book::new).collect(Collectors.toList());
      bookRepository.saveAll(newBooks);
    }
  }

  public List<Book> listAllBooks() {
    return bookRepository.findAll();
  }

  public List<Book> listBooksByLanguage(String language) {
    return bookRepository.findByLanguage(language);
  }
}
