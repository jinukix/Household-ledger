package com.household.controller;

import com.household.annotation.CurrentUserId;
import com.household.annotation.LoginCheck;
import com.household.model.dto.HouseholdLedgerRequestDto;
import com.household.model.dto.HouseholdLedgerResponseDto;
import com.household.model.dto.PageInfo;
import com.household.model.dto.PageOption;
import com.household.service.HouseholdLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/household_ledgers")
@RequiredArgsConstructor
@RestController
public class HouseholdLedgerController {

    private final HouseholdLedgerService householdLedgerService;

    @LoginCheck
    @GetMapping
    public ResponseEntity<PageInfo<HouseholdLedgerResponseDto>> getHouseholdLedgers(
        @CurrentUserId Long currentUserId, @RequestParam(required = false) Integer page) {
        PageOption pageOption = new PageOption(page);
        PageInfo<HouseholdLedgerResponseDto> householdLedgers = householdLedgerService.getHouseholdLedgers(
            currentUserId, pageOption);
        return new ResponseEntity<>(householdLedgers, HttpStatus.OK);
    }

    @LoginCheck
    @PostMapping
    public ResponseEntity<Void> createHouseholdLedger(
        @RequestBody HouseholdLedgerRequestDto householdLedgerRequestDto,
        @CurrentUserId Long currentUserId
    ) {
        householdLedgerService.createHouseholdLedger(householdLedgerRequestDto, currentUserId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @LoginCheck
    @GetMapping("/{householdLedgerId}")
    public ResponseEntity<HouseholdLedgerResponseDto> getHouseholdLedger(
        @PathVariable("householdLedgerId") Long householdLedgerId,
        @CurrentUserId Long currentUserId
    ) {
        HouseholdLedgerResponseDto householdLedgerResponseDto = householdLedgerService.getHouseholdLedger(
            currentUserId, householdLedgerId);
        return new ResponseEntity<>(householdLedgerResponseDto, HttpStatus.OK);
    }

    @LoginCheck
    @PutMapping("/{householdLedgerId}")
    public ResponseEntity<Void> updateHouseholdLedger(
        @RequestBody HouseholdLedgerRequestDto householdLedgerRequestDto,
        @PathVariable("householdLedgerId") Long householdLedgerId,
        @CurrentUserId Long currentUserId
    ) {
        householdLedgerService.updateHouseholdLedger(householdLedgerRequestDto, currentUserId,
            householdLedgerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @LoginCheck
    @DeleteMapping("/{householdLedgerId}")
    public ResponseEntity<Void> deleteHouseholdLedger(
        @PathVariable("householdLedgerId") Long householdLedgerId,
        @CurrentUserId Long currentUserId
    ) {
        householdLedgerService.deleteHouseholdLedger(currentUserId, householdLedgerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
