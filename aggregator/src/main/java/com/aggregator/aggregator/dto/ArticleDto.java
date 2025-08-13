package com.aggregator.aggregator.dto;

import java.time.LocalDateTime;

/**
 * ArticleDto
 */
public class ArticleDto {
  private String title;
  private String description;
  private String url;
  private String source;
  private LocalDateTime publishedAt;

  public ArticleDto() {
  }

  public ArticleDto(String title, String description, String url, String source, LocalDateTime publishedAt) {
    this.title = title;
    this.description = description;
    this.url = url;
    this.source = source;
    this.publishedAt = publishedAt;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public LocalDateTime getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(LocalDateTime publishedAt) {
    this.publishedAt = publishedAt;
  }
}
