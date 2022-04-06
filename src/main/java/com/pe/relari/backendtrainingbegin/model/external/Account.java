package com.pe.relari.backendtrainingbegin.model.external;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Integer id;
    private Integer productId;
    private Integer price;
    private Integer stock;

}
