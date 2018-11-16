package nl.arjan.fixedcosts.domain.repository;

import nl.arjan.fixedcosts.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to persist {@link Category}.
 *
 * @author arjan
 * @since 31-01-18
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Find the {@link Category} by id.
     *
     * @param id The id of the {@link Category}.
     * @return An {@link Optional} with the found {@link Category}.
     */
    Optional<Category> findById(Long id);

    /**
     * Find all {@link Category} by a part of the discription.
     *
     * @param description The discription of the {@link Category}.
     * @return A {@link List} of the found {@link Category}.
     */
    List<Category> findAllByDescriptionContainsIgnoreCase(String description);
}
