package com.RouteJava.Route.api.dto.request;

import com.RouteJava.Route.util.enums.LoadState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadRequest {

    @NotBlank(message = "El peso es requerido")
    @Size(
            min = 1,
            max = 300,
            message = "El peso debe ser entre 1 a 300 kg"
    )
    private BigDecimal weigh;
    private LocalDateTime date;
    @NotBlank(message = "Las dimenskiones son requeridas")
    @Size(
            min = 1,
            max = 100,
            message = "Las dimensiones deben ser de 100 cm * 100 cm"
    )
    private BigDecimal dimension;

    private String description;
    @NotNull( message = "El estado de la carga es requerido")
    private LoadState state;
}


