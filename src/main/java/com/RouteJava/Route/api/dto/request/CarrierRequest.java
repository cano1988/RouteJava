package com.RouteJava.Route.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrierRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(
            min = 1,
            max = 100,
            message = "El nombre debe tener entre 1 y 100 caracteres"
    )
    private  String name;
    @NotBlank(message = "La descripción es requerida")
    private  String description;
}
