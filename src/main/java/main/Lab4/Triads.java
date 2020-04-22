package main.Lab4;

import main.Lab2.LexTypeTERMINAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Triads {


    // тут храним наши триады
    public List<Triad> triadList = new ArrayList<>();

    // тут для V хранятся индексы уже рассчитанных триад, константы, идентификаторы
    public List<String> stack = new ArrayList<>();

    public String triad_remember_call = "";

    public Stack<Integer> triad_remember_if_num = new Stack<>();
    public Stack<Integer> triad_remember_goto_num = new Stack<>();

    public void add(String o, String f, String s) {
        this.triadList.add(new Triad(o, f, s));
    }

    public String printTriads() {
        IntStream.range(0, 100).forEach(value -> System.out.print("+"));
        System.out.println("\n");
        String all = "";
        for (int i = 0; i < this.triadList.size(); i++) {
            String result = i + ")";
            if (this.triadList.get(i).operation != null && !this.triadList.get(i).operation.isEmpty())
                result += "    " + this.triadList.get(i).operation;

            if (this.triadList.get(i).first != null && !this.triadList.get(i).first.isEmpty())
                result += "    " + this.triadList.get(i).first;

            if (this.triadList.get(i).second != null && !this.triadList.get(i).second.isEmpty())
                result += "    " + this.triadList.get(i).second;

            System.out.println(result);
            all += result;
            if (i != this.triadList.size() - 1)
                all += "\n";
        }
        return all;
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

    // =================================================================================================================
    public void triad_new_variable(LexTypeTERMINAL dataType, String lexemStr) {
        this.add(dataType.toString(), lexemStr, null);
        this.stackAdd(lexemStr);
    }

    public void triad_new_func(String lexemStr) {
        this.add("proc", lexemStr, null);
    }

    public void triad_prolog() {
        this.add("prolog", null, null);
    }

    public void triad_epilog() {
        this.add("epilog", null, null);
        this.add("ret", null, null);
        this.add("endp", null, null);
    }

    public void triad_gener_if() {
        this.add("if", "(" + String.valueOf(this.triadList.size() + 1) + ")", "numFalse");
        this.triad_remember_if_num.push(this.triadList.size() - 1);
    }

    public void triad_gener_goto() {
        this.add("goto", "xz", null);
        this.triad_remember_goto_num.push(this.triadList.size() - 1);
    }

    public void triad_gener_if_NOP() {
        this.add("NOP", null, null);
        int num_NOP = this.triadList.size() - 1;
        // установим правильные адреса в if и goto
        this.triadList.get(this.triad_remember_goto_num.pop()).first = "(" + num_NOP + ")";
        this.triadList.get(this.triad_remember_if_num.pop()).second = "(" + num_NOP + ")";
    }

    public void triad_push(String lexemStr) {
        this.stackAdd(lexemStr);
    }

    public void triad_push_param() {
        String s_1 = this.stackGetId(-1);
        this.add("push", s_1, null);
    }

    public void triad_call() {
        String s_1 = this.stackGetId(-1);
        this.add("call", s_1, null);
        this.stackAdd("(" + (this.triadList.size() - 1) + ")");
    }

    public void triad_return() {
        String s_1 = this.stackGetId(-1);
        this.add("push_for_return", s_1, null);
    }

    public void triad_remember_call(String lexemStr) {
        this.stackAdd(lexemStr);
    }

    public void gener_equal() {
        this.addMathOperation("==");
    }

    public void gener_not_equal() {
        this.addMathOperation("!=");
    }

    public void gener_great() {
        this.addMathOperation(">");
    }

    public void gener_great_equal() {
        this.addMathOperation(">=");
    }

    public void gener_less() {
        this.addMathOperation("<");
    }

    public void gener_less_equal() {
        this.addMathOperation("<=");
    }

    public void gener_assign() {
        this.addMathOperation("=");
    }

    public void gener_star() {
        this.addMathOperation("*");
    }

    public void gener_div() {
        this.addMathOperation("/");
    }

    public void gener_percent() {
        this.addMathOperation("%");
    }

    public void gener_plus() {
        this.addMathOperation("+");
    }

    public void gener_minus() {
        this.addMathOperation("-");
    }
}

