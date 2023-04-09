package com.example.testtask.factory;

import com.example.testtask.dto.ArticleDto;
import com.example.testtask.model.Article;

public interface ArticleFactory {

    Article getArticleFromDto(ArticleDto articleDto);
}
