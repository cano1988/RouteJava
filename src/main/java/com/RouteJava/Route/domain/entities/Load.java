package com.RouteJava.Route.domain.entities;

import com.RouteJava.Route.util.enums.LoadState;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


// Esta entidad se pone con plural, dado que, es un palabara reservada de sql
@Entity (name = "loads")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal weigh;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private BigDecimal dimension;
    @Lob // -> Mapea a Text
    private String description;
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private LoadState state;



    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "loads",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    private List<Pallet> pallets;




}
