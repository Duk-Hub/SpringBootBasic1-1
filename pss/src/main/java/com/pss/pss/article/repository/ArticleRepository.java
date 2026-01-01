package com.pss.pss.article.repository;

import com.pss.pss.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleQueryRepository {
}
