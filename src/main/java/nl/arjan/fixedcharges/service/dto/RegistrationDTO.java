package nl.arjan.fixedcharges.service.dto;

import lombok.Builder;
import lombok.Data;
import nl.arjan.fixedcharges.domain.Registration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO of the Registration domain object.
 *
 * @author arjan
 * @since 23-07-18
 */
@Data
@Builder
public class RegistrationDTO {
    private Long id;
    private LocalDate date;
    private List<CostDTO> costs = new ArrayList<>();

    public static RegistrationDTO fromDomain(Registration registration) {
        return RegistrationDTO.builder() //
                .id(registration.getId()) //
                .date(registration.getDate()) //
                .costs(registration.getCosts().stream() //
                        .map(CostDTO::fromDomain) //
                        .collect(Collectors.toList()))
                .build();
    }

    public Registration toDomain() {
        return new Registration(id, date, costs.stream().map(CostDTO::toDomain).collect(Collectors.toList()));
    }
}
