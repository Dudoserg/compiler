package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
    }

    public Test() {
        int t = 13;
        int x = 59;


        System.out.println(t * x);
        System.out.println((t << 6) - (t << 2) - t);
//        int x = func1() + func2() * func3();
//        System.out.println();
//        int x = 6;
//        int original = x;
//        List<Integer> degrees = new ArrayList<>();
//        List<Integer> result = new ArrayList<>();
//        degrees.add(1);
//        for (int i = 0; i < 25; i++) {
//            degrees.add(degrees.get(degrees.size() - 1) * 2);
//        }
//        System.out.println();
//        int index_near = findNear(degrees, x);
//
//        result.add(degrees.get(index_near));
//
//        int sum = result.get(result.size() - 1);
//        do {
//            x = Math.abs(x - degrees.get(index_near));
//            if (x <= 1)
//                break;
//            index_near = findNear(degrees, x);
//            // если ближе с вычетанием
//            if (Math.abs((sum + degrees.get(index_near)) - original) >= Math.abs((sum - degrees.get(index_near)) - original)) {
//                result.add((-1) * degrees.get(index_near));
//                sum += result.get(result.size() - 1);
//            } else {
//                result.add(degrees.get(index_near));
//                sum += result.get(result.size() - 1);
//            }
//        } while (true);
//
//        if (original - sum != 0)
//            result.add(original - sum);
//
//        result.forEach(integer -> System.out.println(integer));

    }

    public int findNear(List<Integer> degrees, int x) {
        int i;
        for (i = 0; i < degrees.size() - 1; i++) {
            int left = degrees.get(i);
            int right = degrees.get(i + 1);
            if (left <= x && x < right)
                break;
        }
        if (Math.abs(x - degrees.get(i)) < Math.abs(x - degrees.get(i + 1)))
            return i;
        else
            return i + 1;
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
