package com.pss.pss.article.controller;

import com.pss.pss.article.dto.ArticleCreateRequest;
import com.pss.pss.article.dto.ArticleSearchRequest;
import com.pss.pss.article.dto.ArticleSummary;
import com.pss.pss.article.service.ArticleService;
import com.pss.pss.global.dto.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<PageResponse<ArticleSummary>> getArticles(
            @ModelAttribute ArticleSearchRequest request,
            @PageableDefault(size = 20, page = 1, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticleSummary> articles = articleService.getArticles(request, pageable);
        //PageResponse 메서드 통해 1-based로 래핑하여 변환
        return ResponseEntity.ok(PageResponse.of(articles));
    }

    @PostMapping
    public ResponseEntity<ArticleSummary> createArticle(@RequestBody @Valid ArticleCreateRequest request){
        ArticleSummary article = articleService.createArticle(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(article.id()).toUri();

        return ResponseEntity.created(location).body(article);
    }

}
