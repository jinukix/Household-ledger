package com.household.model.dto;

import lombok.Getter;

@Getter
public class PageOption {

    private static final int DEFAULT_LIMIT = 10;

    private final Integer offset;
    private final Integer limit;

    public PageOption(Integer page) {
        if (page == null) {
            page = 1;
        }

        if (page <= 0) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }

        this.offset = (page - 1) * DEFAULT_LIMIT;
        this.limit = DEFAULT_LIMIT;
    }
}
