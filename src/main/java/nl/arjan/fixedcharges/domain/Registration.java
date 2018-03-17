package nl.arjan.fixedcharges.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity to register fixed charges on a certain date.
 *
 * @author arjan
 * @since 31-01-18
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Registration {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDate date;
    @OneToMany
    @JoinColumn(name="REGISTRATION_ID", nullable = false)
    private List<Cost> costs = new ArrayList<>();

    public Registration update(Registration registration) {
        date = registration.getDate();
        registration.getCosts() //
                .forEach(cost -> {
                    if (costs.contains(cost)) {
                        costs.get(costs.indexOf(cost)).update(cost);
                    } else {
                        costs.add(cost);
                    }
                });
        return this;
    }
}
