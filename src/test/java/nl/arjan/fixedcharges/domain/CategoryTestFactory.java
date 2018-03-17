package nl.arjan.fixedcharges.domain;

/**
 * Factory for test purposes.
 *
 * @author arjan
 * @since 07-02-18
 */
public class CategoryTestFactory {
    public static Category init() {
        Category category = new Category();
        category.setDayOfDebit(1);
        category.setDescription("description");
        category.getKeys().add("key");
        return category;
    }
}
