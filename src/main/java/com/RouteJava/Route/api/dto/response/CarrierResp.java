package com.RouteJava.Route.api.dto.response;


import com.RouteJava.Route.domain.entities.Pallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder //Facilita la creación de objetos con un patrón flexible y legible.
@AllArgsConstructor // Crea un constructor que recibe todos los campos de la clase.
@NoArgsConstructor //
public class CarrierResp {

    private Long id;
    private  String name;
    private  String description;



}
