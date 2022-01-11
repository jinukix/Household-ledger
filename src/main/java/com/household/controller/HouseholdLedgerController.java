package com.household.controller;

import com.household.annotation.CurrentUserId;
import com.household.annotation.LoginCheck;
import com.household.model.dto.HouseholdLedgerRequestDto;
import com.household.service.HouseholdLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/household_ledgers")
@RequiredArgsConstructor
@RestController
public class HouseholdLedgerController {

    private final HouseholdLedgerService householdLedgerService;

    @LoginCheck
    @PostMapping
    public ResponseEntity<Void> createHouseholdLedger(
        @RequestBody HouseholdLedgerRequestDto householdLedgerRequestDto,
        @CurrentUserId Long currentUserId) {
        householdLedgerService.createHouseholdLedger(householdLedgerRequestDto, currentUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
