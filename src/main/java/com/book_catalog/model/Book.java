package com.book_catalog.model;

import com.book_catalog.dto.BookDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String title;

  @ManyToOne(cascade = CascadeType.ALL)
  private Author author;

  private String language;

  private Integer authorBirthYear;

  private Integer authorDeathYear;

  private Double downloadCount;

  public Book() {}

  public Book(BookDTO bookDTO) {
    this.title = bookDTO.title();
    Author author = new Author(bookDTO.authors().get(0));
    this.author = author;
    this.language = bookDTO.languages().get(0);
    this.downloadCount = bookDTO.downloadCount();
  }

  public Book(Long idApi, String title, Author author, String language, Double downloadCount) {
    this.title = title;
    this.author = author;
    this.language = language;
    this.downloadCount = downloadCount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Integer getAuthorBirthYear() {
    return authorBirthYear;
  }

  public void setAuthorBirthYear(Integer authorBirthYear) {
    this.authorBirthYear = authorBirthYear;
  }

  public Integer getAuthorDeathYear() {
    return authorDeathYear;
  }

  public void setAuthorDeathYear(Integer authorDeathYear) {
    this.authorDeathYear = authorDeathYear;
  }

  public Double getDownloadCount() {
    return downloadCount;
  }

  public void setDownloadCount(Double downloadCount) {
    this.downloadCount = downloadCount;
  }

  @Override
  public String toString() {
    return "Title: " + title + "\n" +
            "Author: " + author + "\n" +
            "Language: " + language + "\n" +
            "Downloads: " + downloadCount + "\n" +
            "----------------------------------------";
  }
}
