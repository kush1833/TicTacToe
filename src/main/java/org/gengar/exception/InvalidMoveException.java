package org.gengar.exception;

import java.io.IOException;

public class InvalidMoveException extends IOException {
    private String message = "Move to Row: %d, Column: %d is invalid. Please provide valid cell location.";

    public InvalidMoveException(int row, int col) {
        this.message = String.format(message, row, col);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
