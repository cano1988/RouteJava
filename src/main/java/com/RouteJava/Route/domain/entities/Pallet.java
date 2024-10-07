package com.RouteJava.Route.domain.entities;


import com.RouteJava.Route.util.enums.PalletMaterial;
import com.RouteJava.Route.util.enums.PalletState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "pallet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal capacity;
    @Lob // -> Mapea a Text, aproximadamente 65400 carácteres
    private String location;
    @Enumerated(EnumType.STRING)
    private PalletMaterial material;
    @Enumerated(EnumType.STRING)
    private PalletState state;

}
