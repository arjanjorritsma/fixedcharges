package nl.arjan.fixedcosts.domain.repository;

import nl.arjan.fixedcosts.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to persist {@link Category}.
 *
 * @author arjan
 * @since 31-01-18
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    /**
     * Find all {@link Category} by a part of the discription.
     *
     * @param description The discription of the {@link Category}.
     * @return A {@link List} of the found {@link Category}.
     */
    List<Category> findAllByDescriptionContainsIgnoreCase(String description);
}
