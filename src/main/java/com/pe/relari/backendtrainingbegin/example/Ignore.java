package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ignore {

    public static void main(String[] args) {

        completable1().subscribe();
        completable2().subscribe();

    }

    private static Completable completable1() {
        return Observable.fromIterable(StudentRepository.students())
                .doOnNext(student -> log.info(student.toString()))
                .ignoreElements()
                .doOnComplete(() -> log.info("Complete Observable"));
    }

    private static Completable completable2() {
        return Single.just(StudentRepository.student01())
                .doOnSuccess(student -> log.info(student.toString()))
                .ignoreElement()
                .doOnComplete(() -> log.info("Complete Single"));
    }

}
