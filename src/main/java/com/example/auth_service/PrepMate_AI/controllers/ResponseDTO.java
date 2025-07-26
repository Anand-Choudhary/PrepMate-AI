package com.example.auth_service.PrepMate_AI.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T>
{
    public boolean success;
    public String statusCode;
    public String message = null;
    public T data;

    public ResponseDTO(String responseCode, String message, boolean status) {
        this.statusCode = responseCode;
        this.message = message;
        this.success = status;
    }

    public ResponseDTO(String responseCode, String message, boolean status, T data) {
        this.statusCode = responseCode;
        this.message = message;
        this.success = status;
        this.data = data;
    }

    public boolean getSuccess() {
    return success;
}

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ResponseDTO [success=" + success + ", statusCode=" + statusCode + ", message=" + message + ", data="
                + data + "]";
    }

    public static ResponseDTO errorResponse(String message, String statusCode){
        return ResponseDTO.builder().message(message).statusCode(statusCode).success(false).data(null).build();
    }
}
