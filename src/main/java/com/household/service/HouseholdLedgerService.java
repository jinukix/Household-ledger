package com.household.service;

import com.household.mapper.HouseholdLedgerMapper;
import com.household.model.dto.HouseholdLedgerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdLedgerService {

    private final HouseholdLedgerMapper householdLedgerMapper;

    public void createHouseholdLedger(HouseholdLedgerRequestDto householdLedgerRequestDto,
        Long userId) {
        householdLedgerMapper.insertHouseholdLedger(householdLedgerRequestDto.toEntity(),
            userId);
    }
}
