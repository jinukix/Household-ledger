package com.household.model.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdLedger {

    private Long id;
    private Long userId;
    private Long price;
    private String description;
    private Boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
