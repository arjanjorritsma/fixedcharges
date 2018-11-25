package nl.arjan.fixedcosts.view.dto;

import nl.arjan.fixedcosts.domain.Category;
import nl.arjan.fixedcosts.domain.CategoryTestFactory;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unittests for the {@link CategoryDTO} class.
 *
 * @author arjan
 * @since 23-11-18
 */
public class CategoryDTOTest {

    @Test
    public void fromEntity() {
        Category category = CategoryTestFactory.init();

        CategoryDTO result = CategoryDTO.fromEntity(category);

        assertThat(result.getId(), is(category.getId()));
        assertThat(result.getDescription(), is(category.getDescription()));
        assertThat(result.getDayOfDebit(), is(category.getDayOfDebit()));
    }

    @Test
    public void toEntity() {
        CategoryDTO dto = init();

        Category result = dto.toEntity();

        assertThat(result.getId(), is(dto.getId()));
        assertThat(result.getDescription(), is(dto.getDescription()));
        assertThat(result.getDayOfDebit(), is(dto.getDayOfDebit()));


    }

    public static CategoryDTO init() {
        return new CategoryDTO(1, "description", 1);
    }
}