package pckgA;

public class Person {
    protected String name;

    public Person(){
        this.name = "empty";
    }

    public Person(String name){
        this.name = name;
    }

    public void GetInfo(){
        System.out.println("My Parent name is " + name);
    }
}
