package main;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
    }

    public Test() {
        int x = func1() + func2() * func3();
        System.out.println();
    }

    public int kek() {
        int i = (int) Math.random() * 10;
        if (i > 5) {
            if (i == 2)
                return 1;
            else
                return 3;
        } else {
            return 23;
        }

    }

    private int func3() {
        return 3;
    }

    private int func2() {
        return 2;
    }

    int func1() {
        return 1;
    }
}
