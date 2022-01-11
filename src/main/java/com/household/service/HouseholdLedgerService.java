package com.household.service;

import com.household.exception.NotFoundException;
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

        householdLedgerRequestDto.setUserId(userId);
        householdLedgerMapper.insertHouseholdLedger(householdLedgerRequestDto.toEntity());
    }

    public void updateHouseholdLedger(HouseholdLedgerRequestDto householdLedgerRequestDto,
        Long currentUserId, Long householdLedgerId) {

        householdLedgerRequestDto.setId(householdLedgerId);
        householdLedgerRequestDto.setUserId(currentUserId);
        int updateCount = householdLedgerMapper.updateHouseholdLedger(
            householdLedgerRequestDto.toEntity());
        if (updateCount == 0) {
            throw new NotFoundException("This household ledger not found exception");
        }
    }

    public void deleteHouseholdLedger(Long currentUserId, Long householdLedgerId) {
        int deleteCount = householdLedgerMapper.deleteHouseholdLedger(currentUserId,
            householdLedgerId);

        if (deleteCount == 0) {
            throw new NotFoundException("This household ledger not found exception");
        }
    }
}
