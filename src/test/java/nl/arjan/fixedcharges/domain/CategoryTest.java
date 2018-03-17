package nl.arjan.fixedcharges.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

/**
 * Unittests of the {@link Category} class.
 *
 * @author arjan
 * @since 07-02-18
 */
public class CategoryTest {

    @Test
    public void update() {
        Category category = CategoryTestFactory.init();
        Category categoryToUpdate = CategoryTestFactory.init();
        categoryToUpdate.setDescription("updated");
        categoryToUpdate.setDayOfDebit(2);
        categoryToUpdate.getKeys().add("uk1");
        categoryToUpdate.getKeys().add( "uk2");

        categoryToUpdate.update(category);

        assertThat(category.getDescription(), is(categoryToUpdate.getDescription()));
        assertThat(category.getDayOfDebit(), is(categoryToUpdate.getDayOfDebit()));
        assertThat(category.getKeys(), contains(categoryToUpdate.getKeys().get(0)));
    }
}