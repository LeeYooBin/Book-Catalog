package com.book_catalog.service;

import com.book_catalog.dto.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataConverterService {
  private final ObjectMapper objectMapper;

  public DataConverterService() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public <T> T parseJsonToObject(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error parsing JSON", e);
    }
  }

  public List<BookDTO> parseJsonToBookList(String json) {
    try {
      CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, BookDTO.class);
      return objectMapper.readValue(json, listType);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error parsing JSON", e);
    }
  }
}
