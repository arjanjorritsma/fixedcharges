package nl.arjan.fixedcharges.service.dto;

import lombok.Builder;
import lombok.Data;
import nl.arjan.fixedcharges.domain.Category;

/**
 * DTO of the Category domain object.
 *
 * @author arjan
 * @since 23-07-18
 */
@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String description;
    private Integer dayOfDebit;

    public static CategoryDTO fromDomain(Category category) {
        return CategoryDTO.builder() //
                .id(category.getId()) //
                .description(category.getDescription()) //
                .dayOfDebit(category.getDayOfDebit()) //
                .build();
    }

    public Category toDomain() {
        return new Category(id, description, dayOfDebit);
    }
}
