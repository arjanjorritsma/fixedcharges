package nl.arjan.fixedcharges.view.category;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import nl.arjan.fixedcharges.domain.Category;
import nl.arjan.fixedcharges.domain.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;

/**
 * Category view to list, add en edit categories
 *
 * @author arjan
 * @since 11-11-18
 */
@SpringComponent
@UIScope
public class CategoryView extends VerticalLayout {
    private final Grid<Category> grid;
    private final TextField filter;
    private final transient CategoryRepository repository;
    @SuppressWarnings("squid:S1068")
    private final CategoryEditor editor;
    private final Button addNewButton;

    public CategoryView(CategoryRepository repository, CategoryEditor editor) {
        this.repository = repository;
        this.editor = editor;
        this.grid = new Grid<>(Category.class);
        this.filter = new TextField();
        this.addNewButton = new Button("New category", VaadinIcon.PLUS.create());
        init();
    }

    private void init() {
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewButton, editor);
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
        addNewButton.addClickListener(e -> editor.editCategory(new Category("", 1)));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCategories(filter.getValue());
        });

        // Initialize listing
        listCategories(null);
    }

    // tag::listCustomers[]
    private void listCategories(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repository.findAll());
        } else {
            grid.setItems(repository.findAllByDescriptionContainsIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]
}
