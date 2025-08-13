package com.aggregator.aggregator.dto;

import java.util.List;

public class NewsApiResponse {
  private String status;
  private int totalResults;
  private List<ArticleDto> articles;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }

  public List<ArticleDto> getArticles() {
    return articles;
  }

  public void setArticles(List<ArticleDto> articles) {
    this.articles = articles;
  }
}
