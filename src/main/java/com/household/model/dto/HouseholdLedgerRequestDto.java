package com.household.model.dto;

import com.household.model.entity.HouseholdLedger;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdLedgerRequestDto {

    @Min(0)
    @NotBlank
    private BigDecimal price;

    @NotBlank
    private String description;

    public HouseholdLedger toEntity() {
        return HouseholdLedger.builder()
            .price(price)
            .description(description)
            .build();
    }
}