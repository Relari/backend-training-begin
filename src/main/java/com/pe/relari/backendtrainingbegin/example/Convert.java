package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.model.domain.Student;
import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class:</b> Convert.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Slf4j
public class Convert {

    public static void main(String[] args) {

        Mono<Student> studentMono = Mono.just(StudentRepository.student01());
        studentMono
                .doOnNext(student -> log.info(student.toString()))
                .doOnTerminate(() -> log.info("Mono -----------------------------------"))
                .subscribe();

        Flux<Student> studentFlux = Flux.just(
                StudentRepository.student01(), StudentRepository.student02(),
                StudentRepository.student03(), StudentRepository.student04()
        );

        studentFlux
                .doOnNext(student -> log.info(student.toString()))
                .doOnComplete(() -> log.info("Flux -----------------------------------"))
                .subscribe();

        Single<Student> studentSingle = studentMono
                .as(RxJava2Adapter::monoToSingle);

        studentSingle
                .doOnSuccess(student -> log.info(student.toString()))
                .doOnTerminate(() -> log.info("Single -----------------------------------"))
                .subscribe();

        Observable<Student> studentObservable = studentFlux
                .as(RxJava2Adapter::fluxToObservable);

        studentObservable
                .doOnNext(student -> log.info(student.toString()))
                .doOnComplete(() -> log.info("Observable -----------------------------------"))
                .subscribe();
    }
}
