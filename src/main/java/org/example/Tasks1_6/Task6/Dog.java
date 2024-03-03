package org.example.Tasks1_6.Task6;

public class Dog {
    String name;
    int age;
    int weight;

    public Dog(String name, int age, int weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    @Override
    public String toString(){
        return "name: " + name + ", age: " + age + " weight: " + weight;
    }
}
