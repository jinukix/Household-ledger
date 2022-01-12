package com.household.service;

import com.household.exception.NotFoundException;
import com.household.mapper.HouseholdLedgerMapper;
import com.household.model.dto.HouseholdLedgerRequestDto;
import com.household.model.dto.HouseholdLedgerResponseDto;
import com.household.model.dto.PageInfo;
import com.household.model.dto.PageOption;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdLedgerService {

    private final HouseholdLedgerMapper householdLedgerMapper;

    public PageInfo<HouseholdLedgerResponseDto> getHouseholdLedgers(Long currentUserId,
        PageOption pageOption) {
        List<HouseholdLedgerResponseDto> householdLedgers = householdLedgerMapper.selectHouseholdLedgersByUserId(
            currentUserId, pageOption);
        Long totalCount = householdLedgerMapper.selectHouseholdLedgerCountByUserId(currentUserId);
        return new PageInfo<>(totalCount, householdLedgers);
    }

    public HouseholdLedgerResponseDto getHouseholdLedger(Long currentUserId,
        Long householdLedgerId) {
        return householdLedgerMapper.selectHouseholdLedger(currentUserId, householdLedgerId)
            .orElseThrow(() -> new NotFoundException("Select not found household ledger"));
    }

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
