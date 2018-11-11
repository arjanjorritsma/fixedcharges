package nl.arjan.fixedcharges.service;

import lombok.AllArgsConstructor;
import nl.arjan.fixedcharges.domain.Category;
import nl.arjan.fixedcharges.domain.repository.CategoryRepository;
import nl.arjan.fixedcharges.service.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint to maintain fixed charge categories.
 *
 * @author arjan
 * @since 31-01-18
 */
@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryEndpoint {
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> query(@RequestParam("description") String description) {
        return categoryRepository.findAllByDescriptionContainsIgnoreCase(description);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable("id") Long id) {
        return categoryRepository.findById(id) //
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO category, @PathVariable("id") Long id) {
        return ResponseEntity.ok( //
                categoryRepository.findById(id) //
                        .map(existing -> categoryRepository.save(existing.update(category.toDomain()))) //
                        .map(CategoryDTO::fromDomain)
                        .orElse(CategoryDTO.fromDomain(categoryRepository.save(category.toDomain()))));
    }
}
