package com.amauri.algafood.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

public class PageableTranslator {

    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
        var orders = pageable.getSort()
                .filter(order -> fieldsMapping.containsKey(order.getProperty()))
                .stream()
                .map(order -> new Sort.Order(order.getDirection(),
                        fieldsMapping.get(order.getProperty())))
                .collect(Collectors.toList());

        System.out.println(orders);
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(orders));
    }
}
