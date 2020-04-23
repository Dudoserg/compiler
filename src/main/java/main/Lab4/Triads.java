package main.Lab4;

import lombok.*;
import main.Lab2.LexTypeTERMINAL;
import main.Lab3.Node;
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
    public List<StackElem> stack = new ArrayList<>();

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StackElem {
        private String lexemStr;
        private LexTypeTERMINAL lexTypeTERMINAL;        //

        private boolean isTriad;
        private int triad_index;

        private boolean isConstant = false;

        private boolean isCallVariable = false;

        private boolean isDeclareVariable = false;

        private boolean isDeclareFunc = false;

        private boolean isCallFunc = false;
        private Node node;

        public static StackElem createDeclareVariable(LexTypeTERMINAL dataType, String lexem) {
            StackElem stackElem = new StackElem();
            stackElem.isDeclareVariable = true;
            stackElem.lexemStr = lexem;
            stackElem.lexTypeTERMINAL = dataType;
            return stackElem;
        }

        public static StackElem createCallVariable(LexTypeTERMINAL next, String lexemStr, Node find_last) {
            StackElem stackElem = new StackElem();
            stackElem.isCallVariable = true;
            stackElem.lexTypeTERMINAL = next;
            stackElem.lexemStr = lexemStr;
            stackElem.node = find_last;
            return stackElem;
        }

        public static StackElem createConstant(LexTypeTERMINAL next, String lexemStr) {
            StackElem stackElem = new StackElem();
            stackElem.isConstant = true;
            stackElem.lexTypeTERMINAL = next;
            stackElem.lexemStr = lexemStr;
            return stackElem;
        }

        public static StackElem createCallFunc() {
            StackElem stackElem = new StackElem();
            return stackElem;
        }

        public static StackElem createDeclareFunc(String lexemStr) {
            StackElem stackElem = new StackElem();
            stackElem.isDeclareFunc = true;
            stackElem.lexemStr = lexemStr;
            return stackElem;
        }

        public static StackElem createTriad(int i) {
            StackElem stackElem = new StackElem();
            stackElem.isTriad = true;
            stackElem.triad_index = i;
            return stackElem;
        }
    }

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

    public void stackAdd(StackElem stackElem) {
        stack.add(stackElem);
    }

    public StackElem stackGetId(int index) {
        if (index >= 0) {
            StackElem result = stack.get(index);
            stack.remove(index);
            return result;
        } else {
            StackElem result = stack.get(stack.size() + index);
            stack.remove(stack.size() + index);
            return result;
        }
    }

    public void addMathOperation(String sign) {
        StackElem s_2 = this.stackGetId(-1);
        StackElem s_1 = this.stackGetId(-1);
        String s_1Str = s_1.isTriad == true ? "(" + s_1.triad_index + ")" : s_1.lexemStr;
        String s_2Str = s_2.isTriad == true ? "(" + s_2.triad_index + ")" : s_2.lexemStr;

        Triad triad = new Triad(sign, s_1Str, s_2Str);
//        this.add(sign, s_1Str, s_2Str);

        Triad_Math_Operation triad_math_operation = new Triad_Math_Operation();

        {
            if (s_1.isTriad) {
                triad_math_operation.left_index = s_1.triad_index;
                triad_math_operation.left_triad = this.triadList.get(s_1.triad_index);
            } else if (s_1.isCallVariable) {
                triad_math_operation.left_lexemStr = s_1.lexemStr;
                triad_math_operation.left_node = s_1.node;
            } else if (s_1.isConstant) {
                triad_math_operation.left_lexTypeTERMINAL = s_1.lexTypeTERMINAL;
                triad_math_operation.left_lexemStr = s_1.lexemStr;
            }
        }
        {
            if (s_2.isTriad) {
                triad_math_operation.right_index = s_2.triad_index;
                triad_math_operation.right_triad = this.triadList.get(s_2.triad_index);
            } else if (s_2.isCallVariable) {
                triad_math_operation.right_lexemStr = s_2.lexemStr;
                triad_math_operation.right_node = s_2.node;
            } else if (s_2.isConstant) {
                triad_math_operation.right_lexTypeTERMINAL = s_2.lexTypeTERMINAL;
                triad_math_operation.right_lexemStr = s_2.lexemStr;
            }
        }

        triad.triad_base = triad_math_operation;

        this.triadList.add(triad);


        if (!sign.equals("=")) {
            StackElem stackElem = StackElem.createTriad((this.triadList.size() - 1));
//            this.stackAdd("(" + (this.triadList.size() - 1) + ")");
            this.stackAdd(stackElem);
        } else
            System.out.print("");
    }

    // =================================================================================================================
    public void triad_new_variable(LexTypeTERMINAL dataType, String lexemStr) {
        Triad triad = new Triad(dataType.toString(), lexemStr, null);

        Triad_Declare_Variable triad_declare_variable =
                new Triad_Declare_Variable(dataType, lexemStr, this.semantic.getCurrent());

        triad.triad_base = triad_declare_variable;

        this.triadList.add(triad);
//        this.add(dataType.toString(), lexemStr, null);

        StackElem stackElem = StackElem.createDeclareVariable(dataType, lexemStr);

        this.stackAdd(stackElem);
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

    public void triad_push(String lexemStr, LexTypeTERMINAL next) throws Exception {
        StackElem stackElem = null;
        if (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._DOUBLE ||
                next == LexTypeTERMINAL._TYPE_INT_10 || next == LexTypeTERMINAL._TYPE_INT_16 ||
                next == LexTypeTERMINAL._TYPE_INT_8)
            stackElem = StackElem.createConstant(next, lexemStr);
        else if (next == LexTypeTERMINAL._ID)
            stackElem = StackElem.createCallVariable(next, lexemStr, semantic.getFind_last());
        else
            throw new Exception("triad_push не константа и не переменная");
        this.stackAdd(stackElem);
    }

    public void triad_push_param() {
        StackElem stackElem = this.stackGetId(-1);
        Triad triad = null;

        Triad_PUSH triad_push = new Triad_PUSH();

        if (stackElem.isTriad) {
            triad = new Triad("push", "(" + stackElem.triad_index + ")", null);
            triad_push.index = stackElem.triad_index;
            triad_push.triad = this.triadList.get(triad_push.index);

        } else if (stackElem.isCallVariable) {
            triad = new Triad("push", stackElem.lexemStr , null);
            triad_push.lexemStr = stackElem.lexemStr;

        } else if (stackElem.isConstant) {
            triad = new Triad("push", stackElem.lexemStr , null);
            triad_push.constantStr = stackElem.lexemStr;
        }

        triad.triad_base = triad_push;

        this.triadList.add(triad);
//        this.add("push", s_1, null);
    }

    public void triad_call() {
        StackElem stackElem = this.stackGetId(-1);
        String s_1 = stackElem.lexemStr;
//        this.add("call", s_1, null);
        Triad triad = new Triad("call", s_1, null);

        Triad_CALL triad_call = new Triad_CALL(this.semantic.getNode_callFunc());

        triad.triad_base = triad_call;

        triadList.add(triad);
        StackElem stackElemResult = StackElem.createTriad((this.triadList.size() - 1));
//        this.stackAdd("(" + (this.triadList.size() - 1) + ")");
        this.stackAdd(stackElemResult);
    }

    public void triad_return() throws Exception {
        StackElem stackElem = this.stackGetId(-1);
        Triad triad ;
        Triad_Push_For_Return triad_push_for_return = new Triad_Push_For_Return();
        if (stackElem.isTriad) {
            triad = new Triad("push_for_return", "(" + stackElem.triad_index + ")", null);
            triad_push_for_return.index = stackElem.triad_index;
            triad_push_for_return.triad = this.triadList.get(stackElem.triad_index);
//            this.add("push_for_return", "(" + stackElem.triad_index + ")", null);

        } else if (stackElem.isCallVariable) {
            triad = new Triad("push_for_return", stackElem.lexemStr, null);
            triad_push_for_return.lexemStr = stackElem.lexemStr;
            triad_push_for_return.node = stackElem.node;
//            this.add("push_for_return", stackElem.lexemStr, null);

        } else if (stackElem.isConstant) {
            triad = new Triad("push_for_return", stackElem.lexemStr, null);
            triad_push_for_return.lexTypeTERMINAL = stackElem.lexTypeTERMINAL;
            triad_push_for_return.lexemStr = stackElem.lexemStr;
            //this.add("push_for_return", stackElem.lexemStr, null);
        }else {
            throw  new Exception("triad_return");
        }

        triad.triad_base = triad_push_for_return;

        triadList.add(triad);

    }

    public void triad_remember_call(String lexemStr) {
        StackElem stackElem = StackElem.createDeclareFunc(lexemStr);
//        this.stackAdd(lexemStr);
        this.stackAdd(stackElem);
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


