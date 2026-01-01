package com.pss.pss.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.pss.pss.global.PagePolicy.*;

@Component
@RequiredArgsConstructor
public class PageNormalizer {

    public Pageable normalize(Pageable pageable){
        int page = Math.max(pageable.getPageNumber(),1) - 1; //클라이언트는 (1-based) 이므로 -1처리하여 (0-based)로 로직수행
        int size = pageable.getPageSize();
        if (size > MAX_SIZE) size = MAX_SIZE; //maxSize 이상이면 maxSize로
        if (size <= 0) size = 20; // 0이하면 20으로 고정

        Sort normalizedSort = normalizeSort(pageable.getSort()); //정규화된 sort

        if (normalizedSort.isUnsorted()){ //화이트리스트 통과x시 defaultSort로
            normalizedSort = DEFAULT_SORT;
        }

        return PageRequest.of((page),size,normalizedSort);
    }

    private Sort normalizeSort(Sort sort){
        if (sort == null||sort.isUnsorted()){ //Sort값이 비어있으면 unsorted -> normalize에서 default 집어넣음
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>(); //Sort는 내부적으로 Order들을 가지고 Iterable한 형태임, 새로운 order List 생성
        for (Sort.Order order : sort) {
            if (ALLOWED.contains(order.getProperty())) { //Set에 규정된 화이트리스트 값과 같은 값이 들어오면 order 리스트에 추가 ignoreCase, nullHandling 같은 옵션 제거
                orders.add(new Sort.Order(order.getDirection(), order.getProperty()));
            }
        }
        if (orders.isEmpty()){ //0이면 unsorted 반환 후 normalize에서 default
            return Sort.unsorted();
        }
        boolean hasId = false;
        for (Sort.Order o : orders){
            if ("id".equalsIgnoreCase(o.getProperty())) {
                hasId = true;
            }
        }
        if (!hasId){orders.add(Sort.Order.desc("id"));}
        return Sort.by(orders);

    }
}
