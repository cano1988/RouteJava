package com.RouteJava.Route.api.dto;


import com.RouteJava.Route.util.enums.LoadState;
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
public class LoadResp {

    private Long id;
    private BigDecimal weigh;
    private LocalDateTime date;
    private BigDecimal dimension;
    private String description;
    private LoadState state;


}
