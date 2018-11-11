package nl.arjan.fixedcharges.service.dto;

import lombok.Builder;
import lombok.Data;
import nl.arjan.fixedcharges.domain.Cost;

import java.math.BigDecimal;

/**
 * DTO of the Cost domain object.
 *
 * @author arjan
 * @since 23-07-18
 */
@Data
@Builder
public class CostDTO {
    private Long id;
    private CategoryDTO category;
    private BigDecimal amount;

    public static CostDTO fromDomain(Cost cost) {
        return CostDTO.builder() //
                .id(cost.getId()) //
                .amount(cost.getAmount()) //
                .category(CategoryDTO.fromDomain(cost.getCategory())) //
                .build();
    }

    public Cost toDomain() {
        return new Cost(id, category.toDomain(), amount);
    }
}
