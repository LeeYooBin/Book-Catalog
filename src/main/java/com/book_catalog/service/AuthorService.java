package com.book_catalog.service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book_catalog.model.Author;
import com.book_catalog.model.Book;
import com.book_catalog.repository.BookRepository;

@Service
public class AuthorService {
  @Autowired
  private BookRepository bookRepository;

  public List<Author> listAllAuthors() {
    return bookRepository.findAll().stream()
            .map(Book::getAuthor)
            .distinct()
            .collect(Collectors.toList());
  }

  public List<Author> listAuthorsAliveInYear(Year year) {
    return bookRepository.findAuthorsAliveInYear(year);
  }
}
