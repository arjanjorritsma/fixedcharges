package nl.arjan.fixedcosts.view.resource;

import lombok.AllArgsConstructor;
import nl.arjan.fixedcosts.domain.repository.CategoryRepository;
import nl.arjan.fixedcosts.view.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Resource to register categories.
 *
 * @author arjan
 * @since 23-11-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryResource {
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream() //
                .map(CategoryDTO::fromEntity) //
                .collect(Collectors.toList());
    }

    @GetMapping("/query")
    public List<CategoryDTO> query(@RequestParam("description") String description) {
        return categoryRepository.findAllByDescriptionContainsIgnoreCase(description).stream() //
                .map(CategoryDTO::fromEntity) //
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Integer id) {
        return categoryRepository.findById(id) //
                .map(CategoryDTO::fromEntity) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return CategoryDTO.fromEntity(categoryRepository.save(dto.toEntity()));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto, @PathVariable("id") Integer id) {
        return categoryRepository.findById(id) //
                .map(existing -> categoryRepository.save(existing.update(dto.toEntity()))) //
                .map(CategoryDTO::fromEntity) //
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
