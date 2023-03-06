package main.utils;


import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

public class ListUtils {

    public static <T, R> T findOne(Collection<T> collection, R object, String declaredField) {
        return collection
                .stream()
                .filter(t -> {
                    Object o = null;
                    try {
                        Field field = t.getClass().getDeclaredField(declaredField);
                        field.setAccessible(true);
                        o = field.get(t);
                    } catch (Exception ignored) {}
                    return nonNull(o) && o.equals(object);
                })
                .findFirst()
                .orElse(null);
    }
}
