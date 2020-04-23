package main.Lab4;

import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Semantic;
import main.Lab4.TriadsByType.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Triads {

    private Semantic semantic;
    private boolean devMode;

    public Triads(Semantic semantic, boolean devMode) {
        this.semantic = semantic;
        this.devMode = devMode;
    }

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
        Triad triad = new Triad(dataType.toString(), lexemStr, null);

        Triad_Declare_Variable triad_declare_variable =
                new Triad_Declare_Variable(dataType, lexemStr, this.semantic.getCurrent());

        triad.triad_base = triad_declare_variable;

        this.triadList.add(triad);
//        this.add(dataType.toString(), lexemStr, null);

        this.stackAdd(lexemStr);
    }

    public void triad_new_func(String lexemStr) {
        Triad triad = new Triad("proc", lexemStr, null);

        Triad_Proc triad_proc = new Triad_Proc(lexemStr, this.semantic.getCurrent());

        triad.triad_base = triad_proc;

        this.triadList.add(triad);

//        this.add("proc", lexemStr, null);
    }

    public void triad_prolog() {
        Triad triad = new Triad("prolog", null, null);

        Triad_Prolog triad_prolog = new Triad_Prolog();

        triad.triad_base = triad_prolog;

        this.triadList.add(triad);
//        this.add("prolog", null, null);
    }

    public void triad_epilog() {
        {
            Triad triad = new Triad("epilog", null, null);

            Triad_Epilog triad_epilog = new Triad_Epilog();

            triad.triad_base = triad_epilog;

            this.triadList.add(triad);
//            this.add("epilog", null, null);
        }
        {
            Triad triad = new Triad("ret", null, null);

            Triad_Ret triad_ret = new Triad_Ret();

            triad.triad_base = triad_ret;

            this.triadList.add(triad);
//          this.add("ret", null, null);
        }
        {
            Triad triad = new Triad("endp", null, null);

            Triad_Endp triad_endp = new Triad_Endp();

            triad.triad_base = triad_endp;

            this.triadList.add(triad);
//        this.add("endp", null, null);
        }
    }

    public void triad_gener_if() {
        Triad triad = new Triad("if", null, "numFalse");

        Triad_IF triad_if = new Triad_IF();


        triad.triad_base = triad_if;

        this.triadList.add(triad);
//        this.add("if", "(" + String.valueOf(this.triadList.size() + 1) + ")", "numFalse");
        this.triad_remember_if_num.push(this.triadList.size() - 1);
    }

    public void triad_gener_goto() {
        Triad triad = new Triad("goto", "xz", null);

        Triad_GOTO triad_goto = new Triad_GOTO();

        triad.triad_base = triad_goto;

        this.triadList.add(triad);
//        this.add("goto", "xz", null);
        this.triad_remember_goto_num.push(this.triadList.size() - 1);
    }

    public void triad_gener_if_NOP() throws Exception {
        Triad triad = new Triad("NOP", null, null);

        Triad_NOP triad_nop = new Triad_NOP();

        triad.triad_base = triad_nop;

        this.triadList.add(triad);
//        this.add("NOP", null, null);

        int indexTriadNOP = this.triadList.size() - 1;
        // установим правильные адреса в if
        int indexTriadIfInList = this.triad_remember_if_num.pop();

        Triad triad_if = this.triadList.get(indexTriadIfInList);
        triad_if.second = "(" + indexTriadNOP + ")";
        if (triad_if.triad_base instanceof Triad_IF) {
            ((Triad_IF) triad_if.triad_base).isFalse = this.triadList.get(indexTriadNOP);
            ((Triad_IF) triad_if.triad_base).isFalse_index = indexTriadNOP;

            ((Triad_IF) triad_if.triad_base).isTrue = this.triadList.get(indexTriadIfInList + 1);
            ((Triad_IF) triad_if.triad_base).isTrue_index = indexTriadIfInList + 1;
            triad_if.first = "(" + (indexTriadIfInList + 1) + ")";
        } else
            throw new Exception(" triad_if.triad_base instanceof Triad_IF ");

        // установим правильные адреса в goto
        Triad triad_goto = this.triadList.get(this.triad_remember_goto_num.pop());

        triad_goto.first = "(" + indexTriadNOP + ")";
        if (triad_goto.triad_base instanceof Triad_GOTO) {
            ((Triad_GOTO) triad_goto.triad_base).jumpTo = this.triadList.get(this.triadList.size() - 1);
            ((Triad_GOTO) triad_goto.triad_base).jumpTo_index = this.triadList.size() - 1;
        } else
            throw new Exception(" triad_goto.triad_base instanceof Triad_GOTO ");
        System.out.print("");
    }

    public void triad_push(String lexemStr) {
        this.stackAdd(lexemStr);
    }

    public void triad_push_param() {
        String s_1 = this.stackGetId(-1);
        Triad triad = new Triad("push", s_1, null);

        Triad_PUSH triad_push = new Triad_PUSH();
        if (s_1.charAt(0) == '(' && s_1.charAt(s_1.length() - 1) == ')') {
            triad_push.index = Integer.valueOf(s_1.substring(1, s_1.length() - 1));
            triad_push.triad = this.triadList.get(triad_push.index);
        } else {
            triad_push.lexemStr = s_1;
        }

        triad.triad_base = triad_push;

        this.triadList.add(triad);
//        this.add("push", s_1, null);
    }

    public void triad_call() {
        String s_1 = this.stackGetId(-1);
//        this.add("call", s_1, null);
        Triad triad = new Triad("call", s_1, null);

        Triad_CALL triad_call = new Triad_CALL(this.semantic.getNode_callFunc());

        triad.triad_base = triad_call;

        triadList.add(triad);
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


