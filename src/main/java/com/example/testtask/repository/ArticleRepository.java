package com.example.testtask.repository;

import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    List<Article> findAllByColor(Color color);

    @Query("SELECT user.id FROM Article GROUP BY user.id HAVING COUNT(user.id) >= :quantity")
    List<Long> findUserIdWithThreeAndMoreArticles(@Param("quantity") Long quantity);

}
