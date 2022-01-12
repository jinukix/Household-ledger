package com.household.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PageOptionTest {

    private static final int DEFAULT_LIMIT = 10;

    @Test
    @DisplayName("페이지 옵션 생성에 성공합니다.")
    public void createPageOptionTestWhenSuccess() {
        PageOption pageOption = new PageOption(null);
        assertEquals(pageOption.getOffset(), 0);
        assertEquals(pageOption.getLimit(), DEFAULT_LIMIT);

        pageOption = new PageOption(1);
        assertEquals(pageOption.getOffset(), 0);

        pageOption = new PageOption(5);
        assertEquals(pageOption.getOffset(), 4 * DEFAULT_LIMIT);

        pageOption = new PageOption(10);
        assertEquals(pageOption.getOffset(), 9 * DEFAULT_LIMIT);
    }

    @Test
    @DisplayName("페이지 옵션 생성에 실패합니다.")
    public void createPageOptionTestWhenFail() {
        assertThrows(IllegalArgumentException.class, () -> new PageOption(0));
        assertThrows(IllegalArgumentException.class, () -> new PageOption(-1));
    }

}