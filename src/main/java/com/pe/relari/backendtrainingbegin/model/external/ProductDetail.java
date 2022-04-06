package com.pe.relari.backendtrainingbegin.model.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    private Integer productId;
    private String description;
    private Integer price;
    private Integer stock;
    private Boolean status;

}
