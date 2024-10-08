package com.RouteJava.Route.api.dto.request;

import com.RouteJava.Route.util.enums.PalletMaterial;
import com.RouteJava.Route.util.enums.PalletState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PalletRequest {

    @NotBlank(message = "La capacidad es requerida")
    @Size(
            min = 1,
            max = 200,
            message = "La capacidad de la estiba es de 1 a 200 kg"
    )
    private BigDecimal capacity;
    @NotBlank(message = "La descripcion es requerida")
    private String location;
    @NotBlank(message = "Debe ingresar el tipo de marial de la estiba")
    private PalletMaterial material;
    @NotBlank(message = "Debe ingresar el estado de la estiba")
    private PalletState state;
}
