package com.pe.relari.backendtrainingbegin.dao.proxy;

import com.pe.relari.backendtrainingbegin.model.external.Product;
import io.reactivex.Observable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductApi {

    public static Observable<Product> getProducts() {
        var product1 = Product.builder()
                .id(1)
                .description("product 1")
                .status(Boolean.TRUE)
                .build();

        var product2 = Product.builder()
                .id(2)
                .description("product 2")
                .status(Boolean.FALSE)
                .build();

        var product3 = Product.builder()
                .id(3)
                .description("product 3")
                .status(Boolean.TRUE)
                .build();

        return Observable.just(product1, product2, product3)
                .doOnSubscribe(disposable -> log.info("Microservice Products Starting"))
                .doOnNext(demo -> log.info(demo.toString()))
                .doOnError(throwable -> log.error("Product Error", throwable))
                .doOnComplete(() -> log.info("Products Complete"))
                .doOnTerminate(() -> log.info("Microservice Products Terminate"));
    }


}
