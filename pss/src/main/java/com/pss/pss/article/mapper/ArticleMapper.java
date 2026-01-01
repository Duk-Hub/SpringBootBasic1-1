package com.pss.pss.article.mapper;

import com.pss.pss.article.domain.Article;
import com.pss.pss.article.dto.ArticleCreateRequest;
import com.pss.pss.article.dto.ArticleSummary;
import com.pss.pss.global.util.MapStructConfig;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(config = MapStructConfig.class)
public interface ArticleMapper {
    Article toEntity(ArticleCreateRequest request);
    ArticleSummary toSummary(Article article);
    Page<ArticleSummary> toSummaryList(Page<Article> articles);
}
