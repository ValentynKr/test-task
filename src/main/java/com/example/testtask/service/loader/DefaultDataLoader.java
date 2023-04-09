package com.example.testtask.service.loader;

import com.example.testtask.constant.Constants;
import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import com.example.testtask.model.User;
import com.example.testtask.service.article.ArticleService;
import com.example.testtask.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class DefaultDataLoader implements ApplicationRunner {

    private final UserService userService;
    private final ArticleService articleService;

    @Override
    public void run(ApplicationArguments args) {
        int usersQuantity = getRandomInteger(5, 10 + 1);
        for (int i = 0; i < usersQuantity; i++) {
            User user = userService.save(new User(Constants.DEFAULT_USER_NAME + i, getRandomInteger(18, 100)));
            int articlesQuantity = getRandomInteger(1, 6);
            for (int j = 0; j < articlesQuantity; j++) {
                int colorIndex = getRandomInteger(0, 4);
                articleService.save(new Article(Constants.DEFAULT_ARTICLE_TEXT + j, Color.values()[colorIndex], user));
            }
        }
    }

    private int getRandomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}