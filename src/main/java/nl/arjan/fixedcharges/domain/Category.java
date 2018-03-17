package nl.arjan.fixedcharges.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 50)
    private String description;
    @Column(name = "DAY_OF_DEBIT", nullable = false)
    private Integer dayOfDebit;
    @Transient
    private List<String> keys = new ArrayList<>();

    public Category update(Category category) {
        this.dayOfDebit = category.getDayOfDebit();
        this.description = category.getDescription();
        this.keys = category.getKeys();
        return this;
    }
}
