package org.harvanir.demo.testcontainer.entrypoint.controller;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author Harvan Irsyadi
 */
@Slf4j
public class ControllerUtils {

    private ControllerUtils() {
    }

    public static String getExpectedDate(LocalDateTime localDateTime) {
        String dateTime = localDateTime.toString();
        boolean endsWithZero = dateTime.endsWith("0");

        if (endsWithZero) {
            log.info("dateTime ends with zero: {}" + dateTime);
        }

        return endsWithZero ? dateTime.substring(0, dateTime.length() - 1) : dateTime;
    }
}