package com.pss.pss.article.service;

import com.pss.pss.article.domain.Article;
import com.pss.pss.article.dto.*;
import com.pss.pss.article.mapper.ArticleMapper;
import com.pss.pss.article.repository.ArticleRepository;
import com.pss.pss.article.support.SearchCondNormalizer;
import com.pss.pss.global.util.PageNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final SearchCondNormalizer searchCondNormalizer;
    private final PageNormalizer pageNormalizer;


    @Override
    public Page<ArticleSummary> getArticles(ArticleSearchRequest req, Pageable pageable) {
        Pageable normalizedPage = pageNormalizer.normalize(pageable);
        SearchCondition normalizedCond = searchCondNormalizer.normalize(req);
        Page<Article> searched = articleRepository.search(normalizedCond, normalizedPage);
        return articleMapper.toSummaryList(searched);
    }

    @Override
    public ArticleSummary createArticle(ArticleCreateRequest req) {
        Article saved = articleRepository.save(articleMapper.toEntity(req));
        return articleMapper.toSummary(saved);
    }


}
