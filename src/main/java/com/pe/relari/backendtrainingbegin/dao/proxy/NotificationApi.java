package com.pe.relari.backendtrainingbegin.dao.proxy;

import com.pe.relari.backendtrainingbegin.model.external.ProductDetail;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationApi {

    public static Completable sendNotification(List<ProductDetail> productDetails) {
        return Single.just(productDetails)
                .doOnSuccess(details ->
                        log.info("Active Products Details -> {}", details)
                )
                .ignoreElement()
                .doOnSubscribe(disposable -> log.info("Microservice Notification Starting"))
                .doOnError(throwable -> log.error("Notification Error", throwable))
                .doOnComplete(() -> log.info("Notification Completable"))
                .doOnTerminate(() -> log.info("Microservice Notification Completable"));
    }
}
