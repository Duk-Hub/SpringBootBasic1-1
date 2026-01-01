package com.pss.pss.article.repository;

import com.pss.pss.article.domain.Article;
import com.pss.pss.article.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleQueryRepository {
    Page<Article> search(SearchCondition cond, Pageable pageable);
}
