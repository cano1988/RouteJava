package com.RouteJava.Route.domain.entities;


import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "carrier")
@Data
@Builder //Facilita la creación de objetos con un patrón flexible y legible.
@AllArgsConstructor // Crea un constructor que recibe todos los campos de la clase.
@NoArgsConstructor //Crea un constructor vacío que no recibe ningún parámetro.
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private  String name;
    @Column(nullable = false)
    private  String description;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "carrier",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    private List<Pallet> pallets;




}
