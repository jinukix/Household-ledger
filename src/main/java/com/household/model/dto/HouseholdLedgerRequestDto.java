package com.household.model.dto;

import com.household.model.entity.HouseholdLedger;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private Long id;
    private Long userId;

    @Min(0)
    @NotNull
    private BigDecimal price;

    @NotBlank
    private String description;

    public HouseholdLedger toEntity() {
        return HouseholdLedger.builder()
            .id(id)
            .userId(userId)
            .price(price)
            .description(description)
            .build();
    }
}
