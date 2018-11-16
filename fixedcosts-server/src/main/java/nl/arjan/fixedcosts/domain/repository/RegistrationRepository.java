package nl.arjan.fixedcosts.domain.repository;

import nl.arjan.fixedcosts.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Registration to persist {@link Registration}.
 *
 * @author arjan
 * @since 31-01-18
 */
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    /**
     * Find the {@link Registration} by id.
     *
     * @param id The id of the {@link Registration}.
     * @return An {@link Optional} with the found {@link Registration}.
     */
    Optional<Registration> findById(Long id);

    /**
     * Find all registrations between two dates.
     *
     * @param begin The begin date of the search.
     * @param end   The end date of the search.
     * @return A {@link List} with the found {@link Registration}
     */
    List<Registration> findAllByDateBetween(LocalDate begin, LocalDate end);
}
