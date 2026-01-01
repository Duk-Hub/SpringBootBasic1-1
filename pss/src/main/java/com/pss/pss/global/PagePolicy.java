package com.pss.pss.global;

import com.pss.pss.article.dto.SortBy;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class PagePolicy {

    private PagePolicy() {}

    public static final int MAX_SIZE = 50;
    public static final Sort DEFAULT_SORT = Sort.by(Sort.Order.desc("createdAt"), Sort.Order.desc("id"));
    public static final Set<String> ALLOWED = Arrays.stream(SortBy.values()).map(SortBy::property).collect(Collectors.toSet());
}
