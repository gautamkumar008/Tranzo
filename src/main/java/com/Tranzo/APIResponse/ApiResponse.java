package com.Tranzo.APIResponse;

import java.util.List;

public class ApiResponse {
    private String status;        // SUCCESS / ERROR
    private String message;       // human Readable
    private Object data;          // result data (optional)
    private List<String> errors;  // validation errors (optional)

    public ApiResponse() {}

    public ApiResponse(String status, String message, Object data, List<String> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse("SUCCESS", message, data, null);
    }

    public static ApiResponse error(String message, List<String> errors) {
        return new ApiResponse("ERROR", message, null, errors);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
