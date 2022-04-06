package com.pe.relari.backendtrainingbegin.dao.proxy;

import com.pe.relari.backendtrainingbegin.model.external.Account;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountApi {

    public static Single<Account> searchAccountByProductId(Integer productId) {

        var account1 = Account.builder()
                .id(1)
                .productId(1)
                .price(100)
                .stock(10)
                .build();

        var account2 = Account.builder()
                .id(2)
                .productId(2)
                .price(200)
                .stock(20)
                .build();

        var account3 = Account.builder()
                .id(3)
                .productId(3)
                .price(300)
                .stock(30)
                .build();

        return Observable.just(account1, account2, account3)
//                .doOnNext(demo -> log.info("Next Observable Account -> {}", demo.toString()))
                .filter(account -> account.getProductId().equals(productId))
                .singleOrError()
                .doOnSubscribe(disposable -> log.info("Microservice Account Starting"))
                .doOnError(throwable -> log.error("Account Error", throwable))
                .doOnSuccess(account -> log.info(account.toString()))
                .doOnTerminate(() -> log.info("Microservice Account Terminate"));
    }

}
