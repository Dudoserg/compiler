package main.Lab3;

import javafx.util.Pair;
import main.Lab2.LexTypeTERMINAL;
import main.Lab2.ScanerV2;
import main.Lab3.createLLK.CreateLLK;
import main.SavePoint;
import main.Scaner;
import main.algoritm_1and2.maga.Elem;
import main.algoritm_1and2.maga.ElemType;
import main.algoritm_1and2.maga.RightPart;
import main.Lab3.createLLK.ReadLLK;
import main.Lab3.createLLK.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class LLK {
    public static void main(String[] args) throws Exception {
        LLK llk = new LLK(true);
        llk.start(System.getProperty("user.dir") + "/test.txt");

    }

    Map<Pair<Elem, Elem>, List<RightPart>> table;
    Table tableObj;
    boolean flag_working = true;
    boolean devMode;
    Stack<Elem> stack = new Stack<>();


    public LLK(boolean devMode) throws Exception {
        this.devMode = devMode;
        this.tableInit();
    }

    private void tableInit() throws Exception {
        CreateLLK createLLK = new CreateLLK();
        ReadLLK readLLK = new ReadLLK();
        table = readLLK.getTable();
        tableObj = readLLK.getTableOjb();
    }

    public boolean start(String path) throws Exception {
        List<Character> lexem = new ArrayList<>();
        ScanerV2 scanerV2 = new ScanerV2(path);

        stack.push(new Elem(ElemType.TERMINAL, "#", LexTypeTERMINAL._END));
        stack.push(new Elem(ElemType.NOT_TERMINAL, "_программа", LexTypeNot._программа));

        LexTypeTERMINAL next = scanerV2.next(lexem);
        while (flag_working) {

            printStack();
            printNext(lexem, next);
            if (count == 84)
                System.out.println();

            Elem topELem = this.stack.pop();
            //TODO случай когда епсилон

            if (topELem.elementType == ElemType.TERMINAL) {
                if (topELem.lexTypeTERMINAL == LexTypeTERMINAL._epsilon)
                    continue;
                if (topELem.lexTypeTERMINAL == next) {
                    if (topELem.lexTypeTERMINAL == LexTypeTERMINAL._END)
                        flag_working = false;
                    else
                        next = scanerV2.next(lexem);
                } else {
                    throw new Exception("терминалы не совпали");
                }

            } else if (topELem.elementType == ElemType.NOT_TERMINAL) {
                List<RightPart> rightParts = table.get(new Pair<>(topELem, new Elem(ElemType.TERMINAL, lexemToStr(lexem), next)));

                if (rightParts == null || rightParts.size() == 0) {
                    throw new Exception("нет такого в таблице : " + topELem.getStrByType_LIGHT() + "  " + lexemToStr(lexem));
                }
                if (rightParts.size() == 1) {
                    pushRightPartToStack(rightParts.get(0));
                    //printStack();
                    //printNext(lexem, next);
                    //System.out.print("");
                } else {
                    //<одно_описание> _INT
                    if (topELem.lexTypeNot == LexTypeNot._одно_описание && (next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._DOUBLE)) {
                        // тут либо <функция> либо <объявление_переменных>
                        SavePoint savePoint = scanerV2.getSavePoint();

                        ArrayList<Character> lexem_2 = new ArrayList<>();
                        LexTypeTERMINAL next_2 = scanerV2.next(lexem_2);
                        String lexem_str_2 = lexemToStr(lexem_2);

                        ArrayList<Character> lexem_3 = new ArrayList<>();
                        LexTypeTERMINAL next_3 = scanerV2.next(lexem_3);
                        String lexem_str_3 = lexemToStr(lexem_3);

                        scanerV2.setSavePoint(savePoint);
                        // ЕСЛИ
                        if (next_3 == LexTypeTERMINAL._PARENTHESIS_OPEN) {
                            //<функция>
                            stack.push(new Elem(ElemType.NOT_TERMINAL, "_функция", LexTypeNot._функция));

                        } else if (next_3 == LexTypeTERMINAL._COMMA || next_3 == LexTypeTERMINAL._ASSIGN) {
                            //<объявление_переменных>
                            stack.push(new Elem(ElemType.NOT_TERMINAL, "_объявление_переменных", LexTypeNot._объявление_переменных));

                        } else {
                            throw new Exception("Чета не то, тут и ни <функция> и <объявление_переменных> [" + next_3.getString() + "]");
                        }

                        System.out.print("");
                    }
                    // <A6> _ID
                    if (topELem.lexTypeNot == LexTypeNot._A6 && (next == LexTypeTERMINAL._ID)) {
                        // тут либо <функция> либо <объявление_переменных>
                        SavePoint savePoint = scanerV2.getSavePoint();

                        ArrayList<Character> lexem_2 = new ArrayList<>();
                        LexTypeTERMINAL next_2 = scanerV2.next(lexem_2);
                        String lexem_str_2 = lexemToStr(lexem_2);

                        scanerV2.setSavePoint(savePoint);
                        // ЕСЛИ
                        if (next_2 == LexTypeTERMINAL._PARENTHESIS_OPEN) {
                            //<вызов_функции>
                            stack.push(new Elem(ElemType.NOT_TERMINAL, "_вызов_функции", LexTypeNot._вызов_функции));
                        } else {
                            //идентификатор
                            stack.push(new Elem(ElemType.TERMINAL, "идентификатор", LexTypeTERMINAL._ID));
                        }

                        System.out.print("");
                    }

                    // <оператор> ID
                    if (topELem.lexTypeNot == LexTypeNot._оператор && (next == LexTypeTERMINAL._ID)) {
                        // тут либо <присваивание> либо <вызов_функции>
                        SavePoint savePoint = scanerV2.getSavePoint();

                        ArrayList<Character> lexem_2 = new ArrayList<>();
                        LexTypeTERMINAL next_2 = scanerV2.next(lexem_2);
                        String lexem_str_2 = lexemToStr(lexem_2);

                        scanerV2.setSavePoint(savePoint);
                        // ЕСЛИ
                        if (next_2 == LexTypeTERMINAL._ASSIGN) {
                            //<присваивание>
                            stack.push(new Elem(ElemType.NOT_TERMINAL, "_присваивание", LexTypeNot._присваивание));
                        } else if (next_2 == LexTypeTERMINAL._PARENTHESIS_OPEN) {
                            // <вызов_функции>
                            stack.push(new Elem(ElemType.NOT_TERMINAL, "_вызов_функции", LexTypeNot._вызов_функции));
                        }

                        System.out.print("");
                    }
                }
            } else if (topELem.elementType == ElemType.PROGRAMM) {

            } else {
                throw new Exception("errorina");
            }


            System.out.println();
        }


        System.out.print("Все чики-пуки");
        return true;
    }

    private void pushRightPartToStack(RightPart rightPart) {
        for (int i = rightPart.elemList.size() - 1; i >= 0; i--) {
            this.stack.push(rightPart.elemList.get(i));
        }
    }

    private String lexemToStr(List<Character> lexem) {
        String lexem_str = lexem.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        return lexem_str;
    }

    int count = 0;

    private void printStack() throws Exception {
        ++count;

        if (count > 1000)
            throw new Exception("count > 10000");

        if(devMode == false)
            return;

        System.out.println("==========================" + count + "=================================\n");

        List<Elem> stackNaoborot = new ArrayList<>();
        for (Elem elem : stack) {
            stackNaoborot.add(elem);
        }
        for (int i = stackNaoborot.size() - 1; i >= 0; --i) {
            System.out.println(stackNaoborot.get(i).getStrByType_LIGHT());
        }
    }

    private void printNext(List<Character> lexem, LexTypeTERMINAL next) {
        System.out.println();
        String lexem_str = this.lexemToStr(lexem);
        System.out.println("next: " + lexem_str + "     type: " + next.getString());
        System.out.println();
    }
}
