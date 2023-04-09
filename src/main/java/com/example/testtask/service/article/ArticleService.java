package com.example.testtask.service.article;

import com.example.testtask.model.Article;
import com.example.testtask.model.Color;

import java.util.List;

public interface ArticleService {

    Article save(Article article);

    List<Article> findAll();

    List<Article> findAllByColor(Color color);

    List<Long> findUserIdsWithQuantityOfArticlesAndMore(Long articleQuantity);

}