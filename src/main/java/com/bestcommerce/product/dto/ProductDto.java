package com.bestcommerce.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDto {
    private Long productId;

    private Long brandId;

    private String brandName;

    private String productName;

    private int productCost;

    private String info;

    private String thumbPath;

    private int deliveryCost;

    public ProductDto(Long productId,
                      Long brandId,
                      String brandName,
                      String productName,
                      int productCost,
                      String info,
                      String thumbPath,
                      int deliveryCost){
        this.productId = productId;
        this.brandId = brandId;
        this.brandName = brandName;
        this.productName = productName;
        this.productCost = productCost;
        this.info = info;
        this.thumbPath = thumbPath;
        this.deliveryCost = deliveryCost;
    }
}