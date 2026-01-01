package com.pss.pss.article.service;

import com.pss.pss.article.dto.ArticleCreateRequest;
import com.pss.pss.article.dto.ArticleSearchRequest;
import com.pss.pss.article.dto.ArticleSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    Page<ArticleSummary> getArticles(ArticleSearchRequest request, Pageable pageable);
    ArticleSummary createArticle(ArticleCreateRequest req);
}
