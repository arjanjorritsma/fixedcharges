package nl.arjan.fixedcharges.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import nl.arjan.fixedcharges.domain.Category;
import nl.arjan.fixedcharges.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * View to edit a {@link Category}.
 *
 * @author arjan
 * @since 11-11-18
 */
@SpringComponent
@UIScope
public class CategoryEditor extends VerticalLayout implements KeyNotifier {

    private final transient CategoryRepository repository;
    /* Fields to edit properties in Customer entity */
    TextField description = new TextField("Description");
    TextField dayOfDebit = new TextField("Day of debit");
    /* Action buttons */
    Button saveButton = new Button("Save", VaadinIcon.CHECK.create());
    Button cancelButton = new Button("Cancel");
    Button deleteButton = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(saveButton, cancelButton, deleteButton);
    Binder<Category> binder = new Binder<>(Category.class);
    /**
     * The currently edited category
     */
    private transient Category category;
    private transient ChangeHandler changeHandler;

    @Autowired
    public CategoryEditor(CategoryRepository repository) {
        this.repository = repository;

        add(description, dayOfDebit, actions);
        binder.forField(dayOfDebit)
                .withConverter(
                        new StringToIntegerConverter("Please enter a number"))
                .bind(Category::getDayOfDebit, Category::setDayOfDebit);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        saveButton.getElement().getThemeList().add("primary");
        deleteButton.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        saveButton.addClickListener(e -> save());
        deleteButton.addClickListener(e -> delete());
        cancelButton.addClickListener(e -> editCategory(category));
        setVisible(false);
    }

    public void delete() {
        repository.delete(category);
        changeHandler.onChange();
    }

    public void save() {
        repository.save(category);
        changeHandler.onChange();
    }

    public final void editCategory(Category c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            category = repository.findById(c.getId()).orElse(null);
        } else {
            category = c;
        }
        cancelButton.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(category);

        setVisible(true);

        // Focus first name initially
        description.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    public interface ChangeHandler {
        void onChange();
    }

}
