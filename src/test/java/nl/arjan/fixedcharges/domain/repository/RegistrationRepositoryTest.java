package nl.arjan.fixedcharges.domain.repository;

import nl.arjan.fixedcharges.domain.Registration;
import nl.arjan.fixedcharges.domain.RegistrationTestFactory;
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
 * Unittests of the {@link RegistrationRepository} class.
 *
 * @author arjan
 * @since 07-02-18
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrationRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Test
    public void findById() {
        Registration registration = RegistrationTestFactory.init();
        testEntityManager.persist(registration);

        Optional<Registration> result = registrationRepository.findById(registration.getId());

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(registration));
    }

    @Test
    public void findByIdNotFound() {
        Optional<Registration> result = registrationRepository.findById(1L);

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void findAllByDateBetweenFound() {
        Registration registration = RegistrationTestFactory.init();
        testEntityManager.persist(registration);

        List<Registration> result = registrationRepository.findAllByDateBetween(registration.getDate().minusDays(1L), registration.getDate().plusDays(1L));

        assertThat(result, contains(registration));
    }

    @Test
    public void findAllByDateBetweenNotFoundToEarly() {
        Registration registration = RegistrationTestFactory.init();
        testEntityManager.persist(registration);

        List<Registration> result = registrationRepository.findAllByDateBetween(registration.getDate().minusDays(3L), registration.getDate().minusDays(2L));

        assertThat(result, not(contains(registration)));
    }

    @Test
    public void findAllByDateBetweenNotFoundToLate() {
        Registration registration = RegistrationTestFactory.init();
        testEntityManager.persist(registration);

        List<Registration> result = registrationRepository.findAllByDateBetween(registration.getDate().plusDays(1L), registration.getDate().plusDays(2L));

        assertThat(result, not(contains(registration)));
    }
}