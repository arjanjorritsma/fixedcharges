package nl.arjan.fixedcharges.domain;

import java.math.BigDecimal;

/**
 * Factory for test purposes.
 *
 * @author arjan
 * @since 07-02-18
 */
public class CostTestFactory {
    public static Cost init() {
        Cost cost = new Cost();
        cost.setAmount(BigDecimal.valueOf(1L));
        cost.setCategory(CategoryTestFactory.init());
        return cost;
    }
}
