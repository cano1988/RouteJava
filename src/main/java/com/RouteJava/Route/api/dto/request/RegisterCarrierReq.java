package com.RouteJava.Route.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper= false)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCarrierReq {
    @NotBlank(message = "El nombre es requerido")
    @Size(
            min = 1,
            max = 100,
            message = "El nombre debe tener entre 1 y 100 caracteres"
    )
    private String name;
    @NotBlank(message = "La descripción es requerida")
    private String description;

}
