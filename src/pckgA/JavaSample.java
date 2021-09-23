package pckgA;

public class JavaSample {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        ChildPerson c = new ChildPerson("Peter", 5);
        c.GetInfoChild();
        String name = c.name;
        System.out.println("My name is "+name);
        System.out.println("SUCCESS?");
    }
}
