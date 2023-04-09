package com.example.testtask.service.article.impl;

import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import com.example.testtask.repository.ArticleRepository;
import com.example.testtask.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> findAll() {
        Iterable<Article> iterable = articleRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Article> findAllByColor(Color color) {
        return articleRepository.findAllByColor(color);
    }

    @Override
    public List<Long> findUserIdsWithQuantityOfArticlesAndMore(Long articleQuantity) {
        return articleRepository.findUserIdWithThreeAndMoreArticles(articleQuantity);
    }
}