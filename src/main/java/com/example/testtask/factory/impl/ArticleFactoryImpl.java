package com.example.testtask.factory.impl;

import com.example.testtask.dto.ArticleDto;
import com.example.testtask.factory.ArticleFactory;
import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import com.example.testtask.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ArticleFactoryImpl implements ArticleFactory {

    private final UserService userService;

    @Override
    public Article getArticleFromDto(ArticleDto articleDto) {
        return new Article(articleDto.getText(),
                Color.valueOf(articleDto.getColor().toUpperCase(Locale.ROOT)),
                userService.findById(articleDto.getUserId()));
    }
}