package nl.arjan.fixedcharges.service;

import lombok.AllArgsConstructor;
import nl.arjan.fixedcharges.domain.Registration;
import nl.arjan.fixedcharges.domain.repository.RegistrationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Endpoint to maintain fixed charge registrations.
 *
 * @author arjan
 * @since 31-01-18
 */
@Controller
@RequestMapping("/registrations")
@AllArgsConstructor
public class RegistrationEndpoint {
    private RegistrationRepository registrationRepository;

    @GetMapping
    public List<Registration> query(@RequestParam("begin") LocalDate begin, @RequestParam("end") LocalDate end) {
        return registrationRepository.findAllByDateBetween(begin, end);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> get(@PathVariable("id") Long id) {
        return registrationRepository.findById(id) //
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> save(@RequestBody Registration registration, @PathVariable("id") Long id) {
        return ResponseEntity.ok( //
                registrationRepository.findById(id) //
                        .map(existing -> registrationRepository.save(existing.update(registration))) //
                        .orElse(registrationRepository.save(registration)));
    }
}
