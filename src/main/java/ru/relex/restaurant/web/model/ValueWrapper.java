package ru.relex.restaurant.web.model;

public class ValueWrapper<T> {

    private final T value;

    public ValueWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
