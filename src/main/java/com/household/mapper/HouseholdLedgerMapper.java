package com.household.mapper;

import com.household.model.entity.HouseholdLedger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseholdLedgerMapper {

    void insertHouseholdLedger(@Param("householdLedger") HouseholdLedger householdLedger,
        @Param("userId") Long userId);
}
