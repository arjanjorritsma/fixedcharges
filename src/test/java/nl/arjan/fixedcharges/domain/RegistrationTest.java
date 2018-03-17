package nl.arjan.fixedcharges.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

/**
 * Unittests of the {@link Registration} class.
 *
 * @author arjan
 * @since 07-02-18
 */
public class RegistrationTest {

    @Test
    public void update() {
        Registration registration = RegistrationTestFactory.init();
        Registration registrationToUpdate = new Registration();
        registrationToUpdate.setDate(LocalDate.now());
        registrationToUpdate.getCosts().add(CostTestFactory.init());

        Registration result = registration.update(registrationToUpdate);

        assertThat(result.getDate(), is(registrationToUpdate.getDate()));
        assertThat(result.getCosts(), contains(registrationToUpdate.getCosts().get(0)));
    }
}