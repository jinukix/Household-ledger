package com.household.mapper;

import com.household.model.entity.HouseholdLedger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseholdLedgerMapper {

    void insertHouseholdLedger(HouseholdLedger householdLedger);

    int updateHouseholdLedger(HouseholdLedger householdLedger);
}
