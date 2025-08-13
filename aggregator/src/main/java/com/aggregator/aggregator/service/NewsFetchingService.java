package com.aggregator.aggregator.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aggregator.aggregator.domain.User;
import com.aggregator.aggregator.dto.ArticleDto;
import com.aggregator.aggregator.dto.NewsApiResponse;
import com.aggregator.aggregator.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class NewsFetchingService {

  private final WebClient webClient;

  @Value("${newsapi.apiKey}")
  private String apiKey;

  public NewsFetchingService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://newsapi.org/v2").build();
  }

  public List<ArticleDto> fetchNewsByCategory(String category) {
    Mono<NewsApiResponse> responseMono = webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/top-headlines")
        .queryParam("category", category)
        .queryParam("country", "us")
        .queryParam("apiKey", apiKey)
        .build())
      .retrieve()
      .bodyToMono(NewsApiResponse.class);

    NewsApiResponse response = responseMono.block(); // Blocking since we’re in MVC
    return response != null ? response.getArticles() : List.of();
  }
}
