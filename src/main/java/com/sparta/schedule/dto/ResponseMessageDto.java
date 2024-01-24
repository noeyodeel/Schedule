package com.sparta.schedule.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseMessageDto {

    private HttpStatus status;
    private String message;
    private ScheduleResponseDto response;

    public ResponseMessageDto(HttpStatus status, String message, ScheduleResponseDto response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }
}
