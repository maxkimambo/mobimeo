package com.kimambo.mobimeo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScheduleNotFoundException extends Exception {

    public ScheduleNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
