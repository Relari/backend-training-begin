package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import com.pe.relari.backendtrainingbegin.model.domain.Student;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Element {

    public static void main(String[] args) {

        Observable<Student> students = Observable.fromIterable(
                StudentRepository.students()
        );

        Single<Student> studentFemale = students
                .filter(student -> student.getId().equals(1))
                .singleOrError()
                .doOnSubscribe(disposable -> log.info("Single Test"))
                .doOnSuccess(student -> log.info(student.toString()))
                .doOnTerminate(() -> log.info("Single Terminate"));

        studentFemale.subscribe();

        log.info("-----------------------------------------------------");

        Maybe<Student> studentMale = students
                .filter(student -> student.getId().equals(3))
                .firstElement()
                .doOnSubscribe(disposable -> log.info("Maybe Test"))
                .doOnSuccess(student -> log.info(student.toString()))
                .doOnTerminate(() -> log.info("Maybe Terminate"));

        studentMale.subscribe();

    }




}
