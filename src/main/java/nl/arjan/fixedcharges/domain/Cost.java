package nl.arjan.fixedcharges.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity for fixed charges.
 *
 * @author arjan
 * @since 31-01-18
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Cost {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal amount;

    public Cost update(Cost cost) {
        category = cost.getCategory();
        amount = cost.getAmount();
        return this;
    }
}