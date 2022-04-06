package com.pe.relari.backendtrainingbegin.dao.repository;

import com.pe.relari.backendtrainingbegin.model.domain.Address;
import com.pe.relari.backendtrainingbegin.model.domain.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * <b>Class:</b> Data.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentRepository {

    public static Student student01() {
        return Student.builder()
                .id(1)
                .documentIdentity("00000001")
                .firstName("Ana")
                .lastName("Mendoza")
                .gender("F")
                .dateOfBirth("00/00/0001")
                .address(new Address("ana@email.com", "90000001"))
                .status(Boolean.TRUE)
                .build();
    }

    public static Student student02() {
        return Student.builder()
                .id(2)
                .documentIdentity("00000002")
                .firstName("Maria")
                .lastName("Salazar")
                .gender("F")
                .dateOfBirth("00/00/0002")
                .address(new Address("maria@email.com", "90000002"))
                .status(Boolean.TRUE)
                .build();
    }
    public static Student student03() {
        return Student.builder()
                .id(3)
                .documentIdentity("00000003")
                .firstName("Alonso")
                .lastName("Mendoza")
                .gender("M")
                .dateOfBirth("00/00/0003")
                .address(new Address("alonso@email.com", "90000003"))
                .status(Boolean.TRUE)
                .build();
    }
    public static Student student04() {
        return Student.builder()
                .id(4)
                .documentIdentity("00000004")
                .firstName("Pedro")
                .lastName("Salazar")
                .gender("M")
                .dateOfBirth("00/00/0004")
                .address(new Address("pedro@email.com", "90000004"))
                .status(Boolean.TRUE)
                .build();
    }

    public static List<Student> students() {
        return Arrays.asList(student01(), student02(), student03(), student04());
    }
}
