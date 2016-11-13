package com.perago.techtest;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

public class DiffEngineService implements DiffEngine {
    public <T extends Serializable> T apply(T original, Diff<?> diff) throws DiffException {
        if ( diff != null && diff.getObj() == null) return null;

        T clone = original == null ? (T) diff.getObj() : SerializationUtils.clone(original);

        for (String field : diff.getFields()) {
            try {
                Field field1 = original.getClass().getDeclaredField(field);
                field1.setAccessible(true);
                field1.set(clone, field1.get(diff.getObj()));
            } catch (IllegalAccessException e) {
                throw new DiffException(e);
            } catch (NoSuchFieldException e) {
                throw new DiffException(e);
            }
        }
        return clone;
    }

    public <T extends Serializable> Diff<T> calculate(T original, T modified) throws DiffException {
        Diff<T> tDiff = null;
        if (original != null) {
            tDiff = new Diff<>();
            tDiff.setObj(modified);
            diffFields(original, modified, tDiff);
        } else if (modified != null) {
            try {
                tDiff = new Diff<>();
                tDiff.setObj(modified);
                original = (T) modified.getClass().newInstance();
                diffFields(original, modified, tDiff);
            } catch (InstantiationException e) {
                throw new DiffException(e);
            } catch (IllegalAccessException e) {
                throw new DiffException(e);
            }
        }
        return tDiff;
    }

    private <T extends Serializable> void diffFields(T original, T modified, Diff<T> tDiff) throws DiffException {
        if (modified != null) {
            for (Field field : Arrays.asList(original.getClass().getDeclaredFields())) {
                field.setAccessible(true);
                try {
                    if (field.get(modified) != null && !field.get(modified).equals(field.get(original))) {
                        tDiff.getFields().add(field.getName());
                    }
                } catch (Exception e) {
                    throw new DiffException(e);
                }
            }
        }
    }
}
