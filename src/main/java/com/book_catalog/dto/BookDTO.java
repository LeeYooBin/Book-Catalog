package com.book_catalog.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
  @JsonAlias("title") String title,
  @JsonAlias("download_count") Double downloadCount,
  @JsonAlias("languages") List<String> languages,
  @JsonAlias("authors") List<AuthorDTO> authors
) {
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Title: ").append(title).append("\n");
    builder.append("Author(s): \n");
    for (AuthorDTO author : authors) {
      builder.append("  - ").append(author.name()).append("\n");
    }
    builder.append("Language(s): ").append(String.join(", ", languages)).append("\n");
    builder.append("Downloads: ").append(downloadCount).append("\n");
    builder.append("----------------------------------------");
    return builder.toString();
  }
}
