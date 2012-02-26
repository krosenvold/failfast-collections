package org.rosenvold.concurrency.accesscontrollers;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class ThreadAccessViolationException extends RuntimeException {

    public ThreadAccessViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThreadAccessViolationException(String message) {
        super(message);
    }
}