package nl.arjan.fixedcharges.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import nl.arjan.fixedcharges.domain.Category;
import nl.arjan.fixedcharges.domain.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;

/**
 * Main view of the app.
 *
 * @author arjan
 * @since 11-11-18
 */
@Route
public class MainView extends VerticalLayout {
    final Grid<Category> grid;
    final TextField filter;
    private final transient CategoryRepository repo;
    @SuppressWarnings("squid:S1068")
    private final CategoryEditor editor;
    private final Button addNewBtn;

    public MainView(CategoryRepository repo, CategoryEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Category.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New category", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, editor);
        add(actions, editor, grid);

        grid.setColumnReorderingAllowed(true);
        grid.setWidth("800px");
        grid.setHeightByRows(true);
        grid.setHeight("16");
        grid.setColumns("id", "description", "dayOfDebit");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by description");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCategories(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> editor.editCategory(e.getValue()));

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCategory(new Category("", 1)));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCategories(filter.getValue());
        });

        // Initialize listing
        listCategories(null);
    }

    // tag::listCustomers[]
    void listCategories(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findAllByDescriptionContainsIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]
}
