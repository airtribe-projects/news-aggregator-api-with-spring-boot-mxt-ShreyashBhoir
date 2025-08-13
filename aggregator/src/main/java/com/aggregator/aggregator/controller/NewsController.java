package com.aggregator.aggregator.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aggregator.aggregator.domain.User;
import com.aggregator.aggregator.dto.ArticleDto;
import com.aggregator.aggregator.service.NewsFetchingService;
import com.aggregator.aggregator.service.UserService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

  private final NewsFetchingService newsFetchingService;
  private final UserService userService;

  public NewsController(NewsFetchingService newsFetchingService, UserService userService) {
    this.newsFetchingService = newsFetchingService;
    this.userService = userService;
  }

  @GetMapping
  public List<ArticleDto> getNews(Authentication authentication) {
    String username = authentication.getName();
    User user = userService.findByUsername(username);

    String category = user.getNewsPreference(); // Example: "technology", "sports"
    return newsFetchingService.fetchNewsByCategory(category);
  }
}
