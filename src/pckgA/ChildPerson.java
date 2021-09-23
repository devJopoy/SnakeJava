package pckgA;

public class ChildPerson extends Person{
    private int age;
    
    public ChildPerson(String name, int age){
        super(name);
        this.age = age;
    }

    public void GetInfoChild(){
        GetInfo();
        System.out.println("My name is " + name);
        System.out.println("My age is " + age);
    }

    public void GenerateTwin(){
        ChildPerson p = new ChildPerson("Pete", 15);
        System.out.println("His name is "+p.name);
        System.out.println("His age is "+p.age);
    }
    
}

// create a same class within same class, declare private
// protected
// Object o = new ChildObject() best practices ?
