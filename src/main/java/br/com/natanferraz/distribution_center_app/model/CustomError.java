package br.com.natanferraz.distribution_center_app.model;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CustomError {

    private String errorTitle;
    private HttpStatus status;
    private String message;

    public String getErrorTitle() {
        return this.status.getReasonPhrase().toUpperCase();
    }


    public Integer getStatus() {
        return status.value();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
