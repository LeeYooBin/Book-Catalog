package com.book_catalog.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book_catalog.model.Author;
import com.book_catalog.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title)")
  List<Book> findByTitle(@Param("title") String title);

  @Query("SELECT a FROM Author a WHERE a.birthYear = :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
  List<Author> findAuthorsAliveInYear(@Param("year") Year year);

  @Query("SELECT b FROM Book b WHERE b.language LIKE %:language%")
  List<Book> findByLanguage(@Param("language") String language);
}
