package com.pss.pss.article.support;

import com.pss.pss.article.domain.Category;
import com.pss.pss.article.dto.ArticleSearchRequest;
import com.pss.pss.article.dto.SearchCondition;
import com.pss.pss.global.util.StringNormalizer;
import org.springframework.stereotype.Component;

@Component
public class SearchCondNormalizer {

    public SearchCondition normalize(ArticleSearchRequest request) {
        String q = StringNormalizer.normalize(request.q());
        String author = StringNormalizer.normalize(request.author());
        String requestCategory = StringNormalizer.normalize(request.category());
        Category category = switch (requestCategory) {
            case "TECH" -> Category.TECH;
            case "LIFE" -> Category.LIFE;
            case "NEWS" -> Category.NEWS;
            case null, default -> null;
        };

        return new SearchCondition(q,author,category,request.from(),request.to());
    }
}
