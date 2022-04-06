package com.pe.relari.backendtrainingbegin.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * <b>Class:</b> StudentUtil.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentUtil {

    public static String buildStudentCode(Integer id) {
        return LocalDate.now().getYear() + String.format("%04d", id);
    }
}
