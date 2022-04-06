package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.model.domain.Student;
import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TypeReactive {

    public static Observable<Student> studentObservable() {
        return Observable.fromCallable(StudentRepository::students)
                .flatMapIterable(students -> students)
                .doOnSubscribe(disposable -> log.info("Subscribe Observable"))
                .doOnNext(demo -> log.info("Next Observable -> {}", demo.toString()))
                .doOnError(throwable -> log.error("Error Observable", throwable))
                .doOnComplete(() -> log.info("Complete Observable"))
                .doOnTerminate(() -> log.info("Terminate Observable"));
    }

    public static void main(String[] args) {

//        studentSingle().subscribe();
//        studentMaybe().subscribe();
//        studentObservable().subscribe();
//        listSingle().subscribe();
//        completable().subscribe();
//        studentCompletableToSingle().subscribe();
//        studentObservable3().subscribe();

    }

    public static Single<Student> studentSingle() {
        return studentObservable()
                .filter(student -> student.getId() == 5)
                .singleOrError()
                .doOnSubscribe(disposable -> log.info("Subscribe Single"))
                .doOnError(throwable -> log.error("Error Single", throwable))
                .doOnSuccess(demo -> log.info("Success Single -> {}", demo.toString()))
                .doOnTerminate(() -> log.info("Terminate Single"));
    }

    public static Maybe<Student> studentMaybe() {
        return studentObservable()
                .filter(student -> student.getId() == 5)
                .firstElement()
                .doOnSubscribe(disposable -> log.info("Subscribe Maybe"))
                .doOnError(throwable -> log.error("Error Maybe", throwable))
                .doOnSuccess(demo -> log.info("Success Maybe -> {}", demo.toString()))
                .doOnTerminate(() -> log.info("Terminate Maybe"));
    }

    public static Single<List<Student>> listSingle() {
        return Single.just(StudentRepository.students())
                .doOnSubscribe(disposable -> log.info("Subscribe Single List"))
                .doOnError(throwable -> log.error("Error Single List", throwable))
                .doOnSuccess(demo -> log.info("Success Single List -> {}", demo.toString()))
                .doOnTerminate(() -> log.info("Terminate Single List"));
    }

    public static Completable completable() {
        return Completable.complete()
                .doOnSubscribe(disposable -> log.info("Subscribe Completable"))
                .doOnError(throwable -> log.error("Error Completable", throwable))
                .doOnComplete(() -> log.info("Complete Completable"))
                .doOnTerminate(() -> log.info("Terminate Completable"));
    }

    public static Single<Student> studentCompletableToSingle() {
        return completable()
                .andThen(studentSingle())
                .doOnSubscribe(disposable -> log.info("Subscribe Single"))
                .doOnError(throwable -> log.error("Error Single", throwable))
                .doOnSuccess(demo -> log.info("Success Single -> {}", demo.toString()))
                .doOnTerminate(() -> log.info("Terminate Single"));
    }

    public static Observable<String > getFirstName() {
        return studentObservable()
                .map(Student::getFirstName)
                .doOnSubscribe(disposable -> log.info("Subscribe Observable"))
                .doOnNext(firstName -> log.info("Next Observable -> {}", firstName))
                .doOnError(throwable -> log.error("Error Observable", throwable))
                .doOnComplete(() -> log.info("Complete Observable"))
                .doOnTerminate(() -> log.info("Terminate Observable"));
    }

    public static Observable<Student> studentObservable3() {
        return studentObservable()
                .flatMapSingle(demo -> completable()
                                .andThen(Single.just(demo))
                );
    }
}
