package com.pe.relari.backendtrainingbegin.service;

import com.pe.relari.backendtrainingbegin.dao.proxy.AccountApi;
import com.pe.relari.backendtrainingbegin.model.external.Product;
import com.pe.relari.backendtrainingbegin.model.external.ProductDetail;
import com.pe.relari.backendtrainingbegin.dao.proxy.NotificationApi;
import com.pe.relari.backendtrainingbegin.dao.proxy.ProductApi;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductDetailService {

    public static void main(String[] args) {

        processProductDetail().subscribe();

    }

    private static Observable<ProductDetail> processProductDetail() {
        return ProductApi.getProducts()
                .flatMapSingle(ProductDetailService::getAccountAndBuildProductDetail)
                .filter(ProductDetail::getStatus)
                .toList()
                .flatMapObservable(productDetails ->
                        NotificationApi.sendNotification(productDetails)
                                .andThen(Observable.fromIterable(productDetails))
                )
                .doOnSubscribe(disposable -> log.info("Microservice ProductDetail Starting"))
                .doOnNext(productDetail -> log.info(productDetail.toString()))
                .doOnComplete(() -> log.info("Microservice ProductDetail Complete"))
                .doOnTerminate(() -> log.info("Microservice ProductDetail Terminate"));
    }

    private static Single<ProductDetail> getAccountAndBuildProductDetail(Product product) {
        return AccountApi.searchAccountByProductId(product.getId())
                .map(account -> ProductDetail.builder()
                        .productId(product.getId())
                        .description(product.getDescription())
                        .status(product.getStatus())
                        .price(account.getPrice())
                        .stock(account.getStock())
                        .build()
                );
    }

}
