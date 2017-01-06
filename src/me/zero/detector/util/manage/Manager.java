package me.zero.detector.util.manage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Brady on 1/6/2017.
 */
public class Manager<T> {

    private List<T> data = new ArrayList<>();

    protected void add(List<T> data) {
        this.data.addAll(data);
    }

    protected void add(T... data) {
        this.data.addAll(Arrays.asList(data));
    }

    protected void add(T data) {
        this.data.add(data);
    }

    protected List<T> getData() {
        return this.data;
    }
}
