package com.stombie.croaker_api.models;

import java.util.Collection;
import java.util.List;

public class Paginator<T> {

    public static <T> Paginator<T> of(Collection<T> elements, int page, int limit) {
        return new Paginator<>(elements, page, limit);
    }

    private final Collection<T> elements;
    private final int page;
    private final int limit;
    private final int pagesCount;

    public Paginator(Collection<T> elements, int page, int limit) {
        if (page <= 0) {
            throw new IllegalArgumentException("'page' must be greater than 0");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("'limit' must be be greater than 0");
        }
        this.elements = elements;
        this.page = page;
        this.limit = limit;
        pagesCount = (int) Math.ceil((double) elements.size() / limit);
    }

    public int getPage() {
        return page;
    }

    public boolean isPageExists() {
        return page <= pagesCount;
    }

    public boolean isHasNextPage() {
        return page < pagesCount;
    }

    public boolean isHasPreviousPage() {
        return 1 < page && page <= pagesCount + 1;
    }

    public List<T> getElements() {
        return elements.stream()
                .skip((page - 1) * limit)
                .limit(limit)
                .toList();
    }
}
