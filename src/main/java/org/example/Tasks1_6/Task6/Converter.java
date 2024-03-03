package org.example.Tasks1_6.Task6;
@FunctionalInterface
public interface Converter<T,N> {
    N convert(T t);
    static <T> boolean isNotNull(T t){
        return t != null;
    }
    default void write(T t){
        System.out.println("Current object: " + t.toString());
    }
    boolean equals(Object object);
}
