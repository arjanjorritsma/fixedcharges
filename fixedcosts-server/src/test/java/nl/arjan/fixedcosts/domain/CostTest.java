package nl.arjan.fixedcosts.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unittests of the {@link Cost} class.
 *
 * @author arjan
 * @since 07-02-18
 */
public class CostTest {

    @Test
    public void update() {
        Cost cost = CostTestFactory.init();
        Cost costToUpdate = new Cost();
        cost.setCategory(CategoryTestFactory.init());
        cost.setAmount(BigDecimal.valueOf(33553L));

        Cost result = cost.update(costToUpdate);

        assertThat(result.getAmount(), is(costToUpdate.getAmount()));
        assertThat(result.getCategory(), is(costToUpdate.getCategory()));
    }
}