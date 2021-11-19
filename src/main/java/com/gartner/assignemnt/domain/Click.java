package com.gartner.assignemnt.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class Click implements Serializable {

    private String ip;
    private LocalDateTime timestamp;
    private Double amount;
    private int period;
}
