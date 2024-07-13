package com.book_catalog.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.book_catalog.exception.SearchException;

@Component
public class GutendexApiClient {
  private static final String API_URL = "https://gutendex.com/books/?search=";
  private final HttpClient client = HttpClient.newHttpClient();

  public String search(String query) {
    String endpoint = API_URL + query.replace(" ", "+");
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        throw new IOException("Failed to get response from API");
      }
      return response.body();
    } catch (IOException | InterruptedException e) {
      throw new SearchException("Error connecting to the API", e);
    }
  }
}
