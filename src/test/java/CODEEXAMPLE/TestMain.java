package CODEEXAMPLE;

import java.lang.reflect.Field;

public class TestMain {
    public static void main(String[] argv) {
        try {
            TestClass A = new TestClass();
            TestAnnotation annotation = A.getClass().getDeclaredField("test").getAnnotation(TestAnnotation.class);
            System.out.println(annotation.name());
            System.out.println("BYE!");
        } catch(NoSuchFieldException e){
            e.printStackTrace();
        }
    }
}
