package br.com.natanferraz.distribution_center_app.model;

import org.springframework.http.HttpStatus;

public class CustomError {

    private final String errorTitle;
    private final Integer status;
    private String message;

    public CustomError(HttpStatus status, String message){
        this.errorTitle = status.getReasonPhrase().toUpperCase();
        this.status = status.value();
        this.message = message;
    }

    public String getErrorTitle() {
        return errorTitle;
    }


    public Integer getStatus() {
        return this.status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
