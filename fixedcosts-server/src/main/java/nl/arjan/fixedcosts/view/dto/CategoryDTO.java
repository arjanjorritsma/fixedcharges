package nl.arjan.fixedcosts.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.arjan.fixedcosts.domain.Category;

/**
 * DTO for the {@link nl.arjan.fixedcosts.domain.Category} entity.
 *
 * @author arjan
 * @since 23-11-18
 */
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private String description;
    private Integer dayOfDebit;

    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(category.getId(), category.getDescription(), category.getDayOfDebit());
    }

    public Category toEntity() {
         return new Category(id, description, dayOfDebit);
    }
}
