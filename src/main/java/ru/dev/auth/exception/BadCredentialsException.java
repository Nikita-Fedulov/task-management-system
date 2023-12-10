package ru.dev.auth.exception;

import java.util.function.Supplier;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(){
        super();
    }
}
