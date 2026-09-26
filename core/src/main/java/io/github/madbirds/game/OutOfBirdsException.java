package io.github.madbirds.game;

public class OutOfBirdsException extends RuntimeException {
    public OutOfBirdsException(String message) {
        super(message);
    }
}
