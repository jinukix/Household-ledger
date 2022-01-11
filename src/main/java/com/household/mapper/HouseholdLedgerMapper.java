package com.household.mapper;

import com.household.model.entity.HouseholdLedger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseholdLedgerMapper {

    void insertHouseholdLedger(HouseholdLedger householdLedger);

    int updateHouseholdLedger(HouseholdLedger householdLedger);

    int deleteHouseholdLedger(@Param("userId") Long userId,
        @Param("householdLedgerId") Long householdLedgerId);
}
