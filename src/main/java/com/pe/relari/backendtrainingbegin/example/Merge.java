package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.model.domain.Student;
import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

/**
 * <b>Class:</b> Merge.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Slf4j
public class Merge {

    public static void main(String[] args) {

        Observable<Student> studentFemales = Observable.just(StudentRepository.student01(), StudentRepository.student02());
        studentFemales.doOnNext(student -> log.info(student.toString()))
                .doOnComplete(() -> log.info("Observable Females -----------------------------------"))
                .subscribe();

        Observable<Student> studentMales = Observable.just(StudentRepository.student03(), StudentRepository.student04());
        studentMales.doOnNext(student -> log.info(student.toString()))
                .doOnComplete(() -> log.info("Observable Males -----------------------------------"))
                .subscribe();

        Observable<Student> students = Observable.merge(studentFemales, studentMales);
        students.doOnNext(student -> log.info(student.toString()))
                .doOnComplete(() -> log.info("Observable Merge -----------------------------------"))
                .subscribe();

    }

}
