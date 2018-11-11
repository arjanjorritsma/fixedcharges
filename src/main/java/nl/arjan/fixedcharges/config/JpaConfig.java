package nl.arjan.fixedcharges.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Jpa configration class
 *
 * @author arjan
 * @since 04-02-18
 */
@EntityScan({"nl.arjan.fixedcharges.domain", //
        "org.springframework.data.jpa.convert.threeten" //
})
public class JpaConfig {
}
