
package pckgB;

import pckgA.*;

class JavaSampleB {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        ChildPersonB c = new ChildPersonB("Peter", 5);
        c.GetInfoChild();
        //c.GenerateTwin();
        //System.out.println("My name is "+name);
        System.out.println("SUCCESS?");
    }
}
