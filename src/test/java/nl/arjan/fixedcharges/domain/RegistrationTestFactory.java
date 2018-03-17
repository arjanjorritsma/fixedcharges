package nl.arjan.fixedcharges.domain;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Factory for test purposes.
 *
 * @author arjan
 * @since 07-02-18
 */
public class RegistrationTestFactory {
    public static Registration init() {
        Registration registration = new Registration();
        registration.setCosts(Arrays.asList(CostTestFactory.init()));
        registration.setDate(LocalDate.now());
        return registration;
    }
}
