package com.stombie.croaker_api.models;

import java.util.Date;

public class Message<T> {

    public static <T> Message<T> of(T message) {
        return new Message<>(message);
    }

    private final T message;
    private final Date timestamp = new Date();

    public Message(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
