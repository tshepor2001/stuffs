package com.perago.techtest;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Diff<T extends Serializable> {

    private String parent;
    private List<String> fields;
    private T obj;

    private Diff<T> inner;

    public List<String> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return fields;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "Diff{" +
                "parent='" + parent + '\'' +
                ", fields=" + fields +
                ", obj=" + obj +
                ", inner=" + inner +
                '}';
    }

    public Diff<T> getInner() {
        return inner;
    }

    public void setInner(Diff<T> inner) {
        this.inner = inner;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
