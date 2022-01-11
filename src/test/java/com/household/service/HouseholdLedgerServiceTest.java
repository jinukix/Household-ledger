package com.household.service;

import static org.mockito.Mockito.verify;

import com.household.mapper.HouseholdLedgerMapper;
import com.household.model.dto.HouseholdLedgerRequestDto;
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
    public void makeUser() {
        householdLedgerRequestDto = HouseholdLedgerRequestDto.builder()
            .build();

    }

    @Test
    @DisplayName("가계부 등록에 성공합니다.")
    public void signUpTestWhenSuccess() {
        householdLedgerService.createHouseholdLedger(householdLedgerRequestDto, 1L);
        verify(householdLedgerMapper).insertHouseholdLedger(householdLedgerRequestDto.toEntity(),
            1L);
    }
}