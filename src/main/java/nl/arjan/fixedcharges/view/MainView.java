package nl.arjan.fixedcharges.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nl.arjan.fixedcharges.view.category.CategoryView;

/**
 * Main view of the app.
 *
 * @author arjan
 * @since 11-11-18
 */
@Route
public class MainView extends VerticalLayout {
    public MainView(CategoryView categoryView) {
        // build layout
        add(categoryView);
    }
}
