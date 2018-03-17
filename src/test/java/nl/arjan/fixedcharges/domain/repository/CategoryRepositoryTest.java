package nl.arjan.fixedcharges.domain.repository;

import nl.arjan.fixedcharges.domain.Category;
import nl.arjan.fixedcharges.domain.CategoryTestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Unittests of the {@link CategoryRepository} class.
 *
 * @author arjan
 * @since 07-02-18
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findById() {
        Category category = CategoryTestFactory.init();
        testEntityManager.persist(category);

        Optional<Category> result = categoryRepository.findById(category.getId());

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(category));
    }

    @Test
    public void findByIdNotFound() {
        Optional<Category> result = categoryRepository.findById(1L);

        assertThat(result.isPresent(), is(false));

    }

    @Test
    public void findAllByDescriptionContains() {
        Category category = CategoryTestFactory.init();
        testEntityManager.persist(category);

        List<Category> result = categoryRepository.findAllByDescriptionContainsIgnoreCase(category.getDescription());

        assertThat(result, contains(category));
    }

    @Test
    public void findAllByDescriptionContainsPart() {
        Category category = CategoryTestFactory.init();
        testEntityManager.persist(category);

        List<Category> result = categoryRepository.findAllByDescriptionContainsIgnoreCase(category.getDescription().substring(1,3));

        assertThat(result, contains(category));
    }

    @Test
    public void findAllByDescriptionContainsIgnoreCase() {
        Category category = CategoryTestFactory.init();
        testEntityManager.persist(category);

        List<Category> result = categoryRepository.findAllByDescriptionContainsIgnoreCase(category.getDescription().toUpperCase());

        assertThat(result, contains(category));
    }

    @Test
    public void findAllByDescriptionContainsNotFound() {
        Category category = CategoryTestFactory.init();
        testEntityManager.persist(category);

        List<Category> result = categoryRepository.findAllByDescriptionContainsIgnoreCase("test");

        assertThat(result, not(contains(category)));
    }
}