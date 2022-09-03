package com.lars.store.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer state;
    private String message;
    private T data;

    public Result(Throwable e) {
        super();
        this.message = e.getMessage();
    }

    public Result(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }
}
