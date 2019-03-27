package ua.com.codelions.controller;

public class UserNotFoundException extends Throwable {
    public String error = "user not found";

    public UserNotFoundException(String message, Throwable cause, String error) {
        super(message, cause);
        this.error = error;
    }

    @Override
    public String toString() {
        return "UserNotFoundException{" +
                "error='" + error + '\'' +
                '}';
    }
}
