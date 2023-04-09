package com.example.testtask.controller;

import com.example.testtask.dto.ArticleDto;
import com.example.testtask.factory.ArticleFactory;
import com.example.testtask.model.Article;
import com.example.testtask.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleFactory articleFactory;
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> findAllArticles() {
        return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDto articleDto) {
        return new ResponseEntity<>(articleService.save(articleFactory.getArticleFromDto(articleDto)), HttpStatus.OK);
    }
}