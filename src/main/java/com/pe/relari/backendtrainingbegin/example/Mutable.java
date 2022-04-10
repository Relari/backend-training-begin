package com.pe.relari.backendtrainingbegin.example;

import com.pe.relari.backendtrainingbegin.dao.repository.StudentRepository;
import com.pe.relari.backendtrainingbegin.util.StudentUtil;
import lombok.extern.java.Log;

@Log
public class Mutable {

    public static void main(String[] args) {

        var studentId = 1;
        var student = StudentRepository.students()
                .stream()
                .filter(studentDomain -> studentDomain.getId().equals(studentId))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        log.info(student.toString());

        var student2 = student.mutate()
                .studentCode(StudentUtil.buildStudentCode(student.getId()))
                .status(false)
                .build();

        log.info(student2.toString());

    }
}
