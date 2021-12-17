package CODEEXAMPLE;


public class TestClass {
    @TestAnnotation
    public int test;

    public TestClass(){
        test = 0;
    }
    public TestClass(int test) {
        this.test = test;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
