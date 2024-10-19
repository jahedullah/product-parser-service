package com.jahedul.productparserservice.exceptions;

public class NoRecordToUpdateOrInsertException extends RuntimeException {
    public NoRecordToUpdateOrInsertException(String msg) {
        super(msg);
    }
}
