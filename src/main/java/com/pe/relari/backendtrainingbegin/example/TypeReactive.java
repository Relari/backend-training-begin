package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.model.domain.Student;
import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import com.pe.relari.backendtrainingbegin.util.StudentUtil;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class TypeReactive {

    public static Observable<Student> studentObservable() {
        return Observable.fromCallable(StudentRepository::students)
                .flatMapIterable(students -> students)
                .doOnSubscribe(disposable -> log.info("Subscribe Observable"))
                .doOnNext(student -> log.info("Next Observable -> {}", student.toString()))
                .doOnError(throwable -> log.error("Error Observable", throwable))
                .doOnComplete(() -> log.info("Complete Observable"))
                .doOnTerminate(() -> log.info("Terminate Observable"));
    }

    public static Observable<Object> studentObservableEmpty() {
        return Observable.fromCallable(Collections::emptyList)
                .flatMapIterable(students -> students)
                .doOnSubscribe(disposable -> log.info("Subscribe Observable Empty"))
                .doOnNext(student -> log.info("Next Observable -> {}", student.toString()))
                .doOnError(throwable -> log.error("Error Observable Empty", throwable))
                .doOnComplete(() -> log.info("Complete Observable Empty"))
                .doOnTerminate(() -> log.info("Terminate Observable Empty"));
    }

    public static Observable<Object> studentObservableError() {
        return Observable.error(new RuntimeException("Service Error."))
                .doOnSubscribe(disposable -> log.info("Subscribe Observable Error"))
                .doOnNext(student -> log.info("Next Observable -> {}", student.toString()))
                .doOnError(throwable -> log.error("Error Observable", throwable))
                .doOnComplete(() -> log.info("Complete Observable Error"))
                .doOnTerminate(() -> log.info("Terminate Observable Error"));
    }

    public static void main(String[] args) {

//        studentSingle().subscribe();
//        studentMaybe().subscribe();
//        studentMaybeError().subscribe();
//        studentObservable().subscribe();
//        studentObservableEmpty().subscribe();
//        studentObservableError().subscribe();
//        listSingle().subscribe();
//        studentObservableSingleList().subscribe();
//        completable().subscribe();
//        completableError().subscribe();
//        studentCompletableToSingle(StudentRepository.student01()).subscribe();
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

    public static Maybe<Object> studentMaybeError() {
        return Maybe.error(new RuntimeException("Service Error"))
                .doOnSubscribe(disposable -> log.info("Subscribe Maybe"))
                .doOnError(throwable -> log.error("Error Maybe", throwable))
                .doOnSuccess(demo -> log.info("Success Maybe -> {}", demo.toString()))
                .doOnTerminate(() -> log.info("Terminate Maybe"));
    }

    public static Single<List<Student>> listSingle() {
        return Single.just(StudentRepository.students())
                .doOnSubscribe(disposable -> log.info("Subscribe Single List"))
                .doOnError(throwable -> log.error("Error Single List", throwable))
                .doOnSuccess(students -> log.info("Success Single List -> {}", students.toString()))
                .doOnTerminate(() -> log.info("Terminate Single List"));
    }

    public static Completable completable() {
        return Completable.complete()
                .doOnSubscribe(disposable -> log.info("Subscribe Completable"))
                .doOnError(throwable -> log.error("Error Completable", throwable))
                .doOnComplete(() -> log.info("Complete Completable"))
                .doOnTerminate(() -> log.info("Terminate Completable"));
    }

    public static Completable completableError() {
        return Completable.error(new RuntimeException("Service Error."))
                .doOnSubscribe(disposable -> log.info("Subscribe Completable"))
                .doOnError(throwable -> log.error("Error Completable", throwable))
                .doOnComplete(() -> log.info("Complete Completable"))
                .doOnTerminate(() -> log.info("Terminate Completable"));
    }

    public static Single<Student> studentCompletableToSingle(Student student) {
        return completable()
                .andThen(Single.just(student))
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
                .flatMapSingle(student -> completable()
                        .andThen(Single.just(student))
                        .map(studentModel -> studentModel.mutate()
                                .studentCode(StudentUtil.buildStudentCode(studentModel.getId()))
                                .build()
                        )
                )
                .doOnNext(student -> log.info(student.toString()));
    }

    public static Single<List<Student>> studentObservableSingleList() {
        return studentObservable()
                .toList()
                .doOnSubscribe(disposable -> log.info("Subscribe Single List"))
                .doOnError(throwable -> log.error("Error Single List", throwable))
                .doOnSuccess(students -> log.info("Success Single List -> {}", students.toString()))
                .doOnTerminate(() -> log.info("Terminate Single List"));
    }

}
