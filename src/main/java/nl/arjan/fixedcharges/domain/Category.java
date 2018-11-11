package nl.arjan.fixedcharges.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for fixed charge categories.
 *
 * @author arjan
 * @since 31-01-18
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    @Column(nullable = false, length = 50)
    private String description;
    @Column(name = "DAY_OF_DEBIT", nullable = false)
    private Integer dayOfDebit;
    @Transient
    private List<String> keys = new ArrayList<>();

    public Category() {

    }

    public Category(Long id, String description, Integer dayOfDebit) {
        this.id = id;
        this.description = description;
        this.dayOfDebit = dayOfDebit;
    }

    public Category update(Category category) {
        this.dayOfDebit = category.getDayOfDebit();
        this.description = category.getDescription();
        this.keys = category.getKeys();
        return this;
    }
}
