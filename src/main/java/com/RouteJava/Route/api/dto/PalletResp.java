package com.RouteJava.Route.api.dto;


import com.RouteJava.Route.util.enums.PalletMaterial;
import com.RouteJava.Route.util.enums.PalletState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PalletResp {

    private Long id;
    private BigDecimal capacity;
    private String location;
    private PalletMaterial material;
    private PalletState state;
}
