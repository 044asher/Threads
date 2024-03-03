package org.example.Tasks1_6.Task6;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Samuel", 2, 5);
        Converter<Dog, Cat> converter = x -> new Cat(x.name, x.age, x.age);
        converter.write(dog);

        Cat cat = converter.convert(dog);
        System.out.println("Cat has parameters: name - " + cat.name + ", age - " + cat.age + ", weight - " + cat.weight);


        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4));
        System.out.println(isEven.test(35));


        Consumer<String> greetings = x -> System.out.println("Hello " + x + "!");
        greetings.accept("Nizar");

    }
}
