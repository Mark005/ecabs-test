package org.ecabs.booking.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseError {
    private Integer status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
}
