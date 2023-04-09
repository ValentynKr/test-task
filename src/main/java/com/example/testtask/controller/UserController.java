package com.example.testtask.controller;

import com.example.testtask.dto.UserDto;
import com.example.testtask.factory.UserFactory;
import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import com.example.testtask.model.User;
import com.example.testtask.service.article.ArticleService;
import com.example.testtask.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserFactory userFactory;
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userFactory.getUserFromDto(userDto)), HttpStatus.OK);
    }

    @GetMapping("/ageMoreThan/{age}")
    public ResponseEntity<List<User>> findAllUsersWithAgeMoreThan(@PathVariable String age) {
        return new ResponseEntity<>(userService.findAllByAgeGreaterThan(Integer.parseInt(age)), HttpStatus.OK);
    }

    @GetMapping("/withColorOfArticle/{color}")
    public ResponseEntity<List<User>> findAllUsersWithColorOfArticle(@PathVariable String color) {
        List<Article> allByColor = articleService.findAllByColor(Color.valueOf(color.toUpperCase(Locale.ROOT)));
        Set<Long> set = allByColor.stream()
                .map(Article::getUser)
                .map(User::getId)
                .collect(Collectors.toSet());
        return new ResponseEntity<>(set.stream().map(userService::findById).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/withArticleQuantity/{quantity}")
    public ResponseEntity<List<String>> findAllUsersWithArticleQuantity(@PathVariable String quantity) {
        List<Long> userIds = articleService.findUserIdsWithQuantityOfArticlesAndMore(Long.parseLong(quantity));
        Map<String, List<User>> mapOfNamesAndUsers = userIds.stream()
                .map(userService::findById)
                .collect(Collectors.groupingBy(User::getName));
        List<String> names = mapOfNamesAndUsers.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}