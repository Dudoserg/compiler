package main;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
    }

    public Test() {
        int x = func1() + func2() * func3();
        System.out.println();
    }

    private int func3() {
        return 3;
    }

    private int func2() {
        return 2;
    }

    int func1(){
        return 1;
    }
}
