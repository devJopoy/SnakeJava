package pckgB;

import pckgA.*;

public class ChildPersonB extends Person{
    private int age;
    private String name;
    
    public ChildPersonB(String name, int age){
        //super(name);
        this.name=name;
        this.age = age;
    }

    public void GetInfoChild(){
        GetInfo();
        System.out.println("My name is " + super.name);
        System.out.println("My age is " + age);
    }
}
