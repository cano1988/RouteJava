package com.RouteJava.Route.domain.entities;

import com.RouteJava.Route.util.enums.LoadState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.math.BigDecimal;


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
    private BigDecimal dimension;
    @Lob // -> Mapea a Text
    private String description;
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private LoadState state;






}
