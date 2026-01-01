package com.pss.pss.article.repository;

import com.pss.pss.article.domain.Article;
import com.pss.pss.article.domain.QArticle;
import com.pss.pss.article.dto.SearchCondition;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Article> search(SearchCondition cond, Pageable pageable) {
        QArticle article = QArticle.article;
        BooleanExpression[] predicates = containsCond(article, cond);

        JPAQuery<Article> contentQuery = jpaQueryFactory
                .selectFrom(article)
                .where(predicates)
                .orderBy(toOrderSpecifiers(pageable, article))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<Article> content = contentQuery.fetch();
        //PageableExecutionUtils.getPage는 content를 확인해서 total이 없어도 마지막 페이지를 확인할 수 있다면
        //Supplier를 호출하지 않음(total 쿼리 미실행)
        //total은 검색조건에 맞는 전체 데이터셋의 크기를 알기 위한 값
       return PageableExecutionUtils.getPage(
               content,
               pageable,
               () ->{
                   Long total = jpaQueryFactory
                           .select(article.count())
                           .from(article)
                           .where(predicates)
                           .fetchOne();
                   return total == null ? 0L : total;
               }
       );
    }

    private OrderSpecifier<?>[] toOrderSpecifiers(Pageable pageable, QArticle article) {
        PathBuilder<?> path = new PathBuilder<>(article.getType(), article.getMetadata().getName());
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            Order direction = o.isAscending() ? Order.ASC : Order.DESC;
            String property = o.getProperty();

            orderSpecifiers.add(new OrderSpecifier<>(direction, path.getComparable(property, Comparable.class)));
        }

        return orderSpecifiers.toArray(OrderSpecifier[]::new);
    }

    private BooleanExpression[] containsCond(QArticle article, SearchCondition cond) {
        //BooleanExpression 메서드들을 모아서 배열반환 메서드로 합침
        List<BooleanExpression> conditions = new ArrayList<>();
        if (cond.q() != null && !cond.q().isBlank()) {
            conditions.add(article.title.containsIgnoreCase(cond.q()));
        }
        if (cond.author() != null && !cond.author().isBlank()) {
            conditions.add(article.author.containsIgnoreCase(cond.author()));
        }
        if (cond.category() != null) {
            conditions.add(article.category.eq(cond.category()));
        }
        BooleanExpression fromTo = fromTo(article, cond.from(), cond.to());
        if (fromTo != null) {
            conditions.add(fromTo);
        }
        return conditions.toArray(BooleanExpression[]::new);
    }

    //로직이 길어지므로 fromTo는 유지
    private BooleanExpression fromTo(QArticle article, Instant from, Instant to) {
        if (from == null && to == null) return null;
        if (from == null) return article.createdAt.loe(to);
        if (to == null) return article.createdAt.goe(from);
        if (from.isAfter(to)){
            throw new IllegalArgumentException("from and to cannot be after to");
        }
        return article.createdAt.between(from, to);
    }

}