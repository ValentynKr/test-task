package com.example.testtask.controller;

import com.example.testtask.dto.ArticleDto;
import com.example.testtask.factory.ArticleFactory;
import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import com.example.testtask.model.User;
import com.example.testtask.service.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {

    private static final String EXAMPLE_TEXT = "Text";
    private static final String WHITE = "WHITE";
    private static final String EXAMPLE_USER_NAME = "PaulMcCartney";
    private static final int EXAMPLE_AGE = 80;
    private static final ArgumentCaptor<ArticleDto> articleDtoArgumentCaptor = ArgumentCaptor.forClass(ArticleDto.class);
    private static final ArgumentCaptor<Article> articleArgumentCaptor = ArgumentCaptor.forClass(Article.class);

    @Mock
    private ArticleFactory articleFactory;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    @Test
    void shouldCreateArticle() {
        ArticleDto articleDto = new ArticleDto(EXAMPLE_TEXT, WHITE, 1L);
        Article expectedArticle = new Article(EXAMPLE_TEXT, Color.WHITE, new User(EXAMPLE_USER_NAME, EXAMPLE_AGE));
        when(articleFactory.getArticleFromDto(articleDto)).thenReturn(expectedArticle);
        when(articleService.save(expectedArticle)).thenReturn(expectedArticle);

        ResponseEntity<Article> articleResponseEntity = articleController.createArticle(articleDto);

        verify(articleFactory, times(1)).getArticleFromDto(articleDtoArgumentCaptor.capture());
        assertEquals(articleDtoArgumentCaptor.getValue(), articleDto);
        verify(articleService, times(1)).save(articleArgumentCaptor.capture());
        assertEquals(articleArgumentCaptor.getValue(), expectedArticle);
        assertEquals(articleResponseEntity, new ResponseEntity<>(expectedArticle, HttpStatus.OK));
    }
}