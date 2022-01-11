package com.household.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.household.exception.NotFoundException;
import com.household.mapper.HouseholdLedgerMapper;
import com.household.model.dto.HouseholdLedgerRequestDto;
import com.household.model.entity.HouseholdLedger;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HouseholdLedgerServiceTest {

    @InjectMocks
    HouseholdLedgerService householdLedgerService;

    @Mock
    HouseholdLedgerMapper householdLedgerMapper;

    HouseholdLedgerRequestDto householdLedgerRequestDto;


    @BeforeEach
    public void beforeEach() {
        householdLedgerRequestDto = HouseholdLedgerRequestDto.builder()
            .id(2L)
            .userId(1L)
            .price(BigDecimal.valueOf(10.22))
            .description("메모장")
            .build();

    }

    @Test
    @DisplayName("가계부 등록에 성공합니다.")
    public void createHouseholdLedgerTestWhenSuccess() {
        householdLedgerService.createHouseholdLedger(householdLedgerRequestDto, 1L);
        verify(householdLedgerMapper).insertHouseholdLedger(any(HouseholdLedger.class));
    }

    @Test
    @DisplayName("가계부 수정에 성공합니다.")
    public void updateHouseholdLedgerTestWhenSuccess() {
        when(householdLedgerMapper.updateHouseholdLedger(any(HouseholdLedger.class))).thenReturn(1);
        householdLedgerService.updateHouseholdLedger(householdLedgerRequestDto, 1L, 2L);
    }

    @Test
    @DisplayName("가계부 수정에 실패합니다. :입력과 일치하는 가계부가 존재하지 않습니다.")
    public void updateHouseholdLedgerTestWhenFail() {
        when(householdLedgerMapper.updateHouseholdLedger(any(HouseholdLedger.class))).thenReturn(0);
        assertThrows(NotFoundException.class,
            () -> householdLedgerService.updateHouseholdLedger(householdLedgerRequestDto, 1L, 2L));
    }
}