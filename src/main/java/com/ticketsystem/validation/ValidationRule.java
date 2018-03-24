package com.ticketsystem.validation;

/**
 * Created by rohitkumar on 11/02/17.
 */
public interface ValidationRule <T> {

    void validate(T data);
}
