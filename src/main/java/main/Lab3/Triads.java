package main.Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Triads {
    class Triad {
        public String operation;
        public String first;
        public String second;

        public Triad(String operation, String first, String second) {
            this.operation = operation;
            this.first = first;
            this.second = second;
        }
    }

    // тут храним наши триады
    public List<Triad> triadList = new ArrayList<>();

    // тут для V хранятся индексы уже рассчитанных триад, константы, идентификаторы
    public List<String> stack = new ArrayList<>();

    String triad_remember_call = "";

    Stack<Integer> triad_remember_if_num = new Stack<>();
    Stack<Integer> triad_remember_goto_num = new Stack<>();

    public void add(String o, String f, String s) {
        this.triadList.add(new Triad(o, f, s));
    }

    public void printTriads() {
        IntStream.range(0, 100).forEach(value -> System.out.print("+"));
        System.out.println("\n");
        for (int i = 0; i < this.triadList.size(); i++) {
            String result = i + ")" ;
            if (this.triadList.get(i).operation != null && !this.triadList.get(i).operation.isEmpty())
                result += "    " + this.triadList.get(i).operation;

            if (this.triadList.get(i).first != null && !this.triadList.get(i).first.isEmpty())
                result += "    " + this.triadList.get(i).first;

            if (this.triadList.get(i).second != null && !this.triadList.get(i).second.isEmpty())
                result += "    " + this.triadList.get(i).second;

            System.out.println(result);
        }
    }

    public void stackAdd(String s) {
        stack.add(s);
    }

    public String stackGetId(int index) {
        if (index >= 0) {
            String result = stack.get(index);
            stack.remove(index);
            return result;
        } else {
            String result = stack.get(stack.size() + index);
            stack.remove(stack.size() + index);
            return result;
        }
    }

    public void addMathOperation(String sign) {
        String s_2 = this.stackGetId(-1);
        String s_1 = this.stackGetId(-1);
        this.add(sign, s_1, s_2);
        if (!sign.equals("="))
            this.stackAdd("(" + (this.triadList.size() - 1) + ")");
    }
}
