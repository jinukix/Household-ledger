package com.household.mapper;

import com.household.model.dto.HouseholdLedgerResponseDto;
import com.household.model.dto.PageOption;
import com.household.model.entity.HouseholdLedger;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseholdLedgerMapper {

    List<HouseholdLedgerResponseDto> selectHouseholdLedgersByUserId(@Param("userId") Long userId,
        @Param("pageOption") PageOption pageOption);

    Long selectHouseholdLedgerCountByUserId(Long userId);

    void insertHouseholdLedger(HouseholdLedger householdLedger);

    int updateHouseholdLedger(HouseholdLedger householdLedger);

    int deleteHouseholdLedger(@Param("userId") Long userId,
        @Param("householdLedgerId") Long householdLedgerId);
}
