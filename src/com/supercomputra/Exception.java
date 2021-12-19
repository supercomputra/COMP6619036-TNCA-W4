package com.supercomputra;

abstract class Exception extends Throwable {
    Exception(String message) {
        super(message);
    }
}

class NotFoundException extends Exception {
    NotFoundException() {
        super("Not Found");
    }
}

class DuplicateInstanceException extends Exception {
    DuplicateInstanceException() {
        super("Duplicate Instance");
    }
}