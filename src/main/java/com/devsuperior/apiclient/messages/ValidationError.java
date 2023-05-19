package com.devsuperior.apiclient.messages;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ApiErrorMessage {
    private List<FieldErrorMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldErrorMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName,String messge){
        errors.add(new FieldErrorMessage(fieldName,messge));
    }
}
