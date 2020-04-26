package main.Lab3;

import javafx.util.Pair;
import main.Lab2.LexTypeTERMINAL;
import main.Lab2.ScanerV2;
import main.Lab3.createLLK.CreateLLK;
import main.Lab3.createLLK.ReadLLK;
import main.Lab3.createLLK.Table;
import main.Lab3.exceptions.Ex_Exception;
import main.Lab4.TreeNext.TreeNext;
import main.Lab4.Triads;
import main.SavePoint;
import main.algoritm_1and2.maga.Elem;
import main.algoritm_1and2.maga.ElemType;
import main.algoritm_1and2.maga.RightPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class LLK {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        LLK llk = new LLK(true);
        llk.start(System.getProperty("user.dir") + "/test.txt");
        System.out.println("\n\n\nFULL TIME = " + (System.currentTimeMillis() - start) / 1000.0);
    }

    Triads triads;
    Map<Pair<Elem, Elem>, List<RightPart>> table;
    Table tableObj;
    boolean flag_working = true;
    boolean devMode;
    Stack<Elem> stack = new Stack<>();
    List<Elem> historyStack = new ArrayList<>();
    List<Pair<LexTypeTERMINAL, String>> historyScaner = new ArrayList<>();
    Semantic semantic;
    TreeNext treeNext;
    public static SavePoint savePointCurrent;

    public LLK(boolean devMode) throws Exception {
        this.devMode = devMode;
        this.semantic = new Semantic(devMode);
        triads = new Triads(semantic, devMode);
        treeNext = new TreeNext(semantic, triads, devMode);
        this.tableInit();
    }

    private void tableInit() throws Exception {
        CreateLLK createLLK;
        if (devMode)
            createLLK = new CreateLLK(devMode);
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
        savePointCurrent = scanerV2.getSavePoint();
        historyScaner.add(0, new Pair<>(next, lexemToStr(lexem)));
        try {
            while (flag_working) {

                printStack();
                printNext(lexem, next);
                if (count == 84)
                    System.out.print("");

                Elem topELem = this.stack.pop();
                historyStack.add(0, topELem);


                if (topELem.elementType == ElemType.TERMINAL) {
                    if (topELem.lexTypeTERMINAL == LexTypeTERMINAL._epsilon)
                        continue;
                    if (topELem.lexTypeTERMINAL == next) {
                        if (topELem.lexTypeTERMINAL == LexTypeTERMINAL._END)
                            flag_working = false;
                        else {
                            next = scanerV2.next(lexem);
                            savePointCurrent = scanerV2.getSavePoint();
                            historyScaner.add(0, new Pair<>(next, lexemToStr(lexem)));
                        }
                    } else {
                        throw new Ex_Exception("терминалы не совпали");
                    }

                } else if (topELem.elementType == ElemType.NOT_TERMINAL) {
                    List<RightPart> rightParts = table.get(new Pair<>(topELem, new Elem(ElemType.TERMINAL, lexemToStr(lexem), next)));

                    if (rightParts == null || rightParts.size() == 0) {
                        throw new Ex_Exception("нет такого в таблице : " + topELem.getStrByType_LIGHT() + "  " + lexemToStr(lexem));
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
                                throw new Ex_Exception("Чета не то, тут и ни <функция> и <объявление_переменных> [" + next_3.getString() + "]");
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
                                RightPart neededPart = findNeededPart(
                                        rightParts, new Elem(ElemType.NOT_TERMINAL, "_вызов_функции", LexTypeNot._вызов_функции));
                                pushRightPartToStack(neededPart);
                                //stack.push(new Elem(ElemType.NOT_TERMINAL, "_вызов_функции", LexTypeNot._вызов_функции));
                            } else {
                                //идентификатор
                                RightPart neededPart = findNeededPart(
                                        rightParts, new Elem(ElemType.TERMINAL, "идентификатор", LexTypeTERMINAL._ID));
                                pushRightPartToStack(neededPart);
                                //stack.push(new Elem(ElemType.TERMINAL, "идентификатор", LexTypeTERMINAL._ID));
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
                                RightPart neededPart = findNeededPart(
                                        rightParts, new Elem(ElemType.NOT_TERMINAL, "_присваивание", LexTypeNot._присваивание));
                                pushRightPartToStack(neededPart);
                                //stack.push(new Elem(ElemType.NOT_TERMINAL, "_присваивание", LexTypeNot._присваивание));
                            } else if (next_2 == LexTypeTERMINAL._PARENTHESIS_OPEN) {
                                // <вызов_функции>
                                RightPart neededPart = findNeededPart(
                                        rightParts, new Elem(ElemType.NOT_TERMINAL, "_вызов_функции", LexTypeNot._вызов_функции));
                                pushRightPartToStack(neededPart);
                                //stack.push(new Elem(ElemType.NOT_TERMINAL, "_вызов_функции", LexTypeNot._вызов_функции));
                            }

                            System.out.print("");
                        }
                    }
                } else if (topELem.elementType == ElemType.PROGRAMM) {
                    System.out.print("");
                    Elem tmp = historyStack.get(1);
                    switch (topELem.str) {
                        case "startDecl": {
                            treeNext.startDecl(next); // тут тип int\double
                            semantic.startDecl(next); // тут тип int\double
                            break;
                        }
                        case "setIdent": {
                            treeNext.setIdent(lexemToStr(lexem));
                            semantic.setIdent(lexemToStr(lexem));
                            break;
                        }
                        case "triad_new_variable": {
                            this.triads.triad_new_variable(semantic.dataType, lexemToStr(lexem));
                            this.treeNext.new_variable(semantic.dataType, lexemToStr(lexem));
//                            this.triads.add(semantic.dataType.toString(), lexemToStr(lexem), null);
//                            this.triads.stackAdd(lexemToStr(lexem));
                            break;
                        }
                        case "triad_new_func": {
                            this.triads.triad_new_func(lexemToStr(lexem));
//                            this.triads.add("proc", lexemToStr(lexem), null);
                            break;
                        }
                        case "triad_prolog": {
                            this.triads.triad_prolog();
                            break;
                        }
                        case "triad_epilog": {
                            this.triads.triad_epilog();
                            break;
                        }
                        case "endDecl": {
                            treeNext.endDecl();
                            semantic.endDecl();
                            break;
                        }
                        case "startLevel": {
                            semantic.startLevel(); // отводка вправо в дереве
                            break;
                        }
                        case "next_startLevel": {
                            treeNext.startLevel(); // отводка влево в дереве NEXT
                            break;
                        }
                        case "endLevel": {
                            semantic.endLevel(); // возвращаем current
                            break;
                        }
                        case "next_endLevel": {
                            treeNext.endLevel(); // возвращаем current
                            break;
                        }
                        case "startFunc": {
                            treeNext.startFunc(lexemToStr(lexem)); // возвращаем current
                            semantic.startFunc(lexemToStr(lexem)); // возвращаем current
                            break;
                        }
                        case "newBlack": {
                            treeNext.newBlack(); // возвращаем current
                            semantic.newBlack(); // возвращаем current
                            break;
                        }
                        case "plusParam": {
                            treeNext.plusParam(); // возвращаем current
                            semantic.plusParam(); // возвращаем current
                            break;
                        }
                        case "find": {
                            treeNext.find(lexemToStr(lexem));
                            semantic.find(lexemToStr(lexem));
                            System.out.print("");
                            break;
                        }
                        case "checkDubl": {
                            treeNext.checkDubl(lexemToStr(lexem));
                            semantic.checkDubl(lexemToStr(lexem));
                            System.out.print("");
                            break;
                        }
                        case "saveVariable": {
//                            System.out.print("");
//                            semantic.saveVariable();
                            break;
                        }
                        case "matchLeft": {
                            treeNext.matchLeft();
                            semantic.matchLeft();
                            System.out.print("");
                            break;
                        }
                        case "match": {
                            treeNext.match();
                            semantic.match();
                            System.out.print("");
                            break;
                        }
                        case "triad_gener_if": {
                            this.triads.triads_gener_if_before();
                            this.triads.triad_gener_if();
                            this.treeNext.triad_gener_if();
                            break;
                        }
                        case "triad_setAddr": {
                            break;
                        }
                        case "triad_form_if": {
                            this.treeNext.triad_form_if();
                            break;
                        }
                        case "triad_gener_goto": {
                            this.triads.triad_gener_goto();
                            this.treeNext.triad_gener_goto();
                            break;
                        }
                        case "triad_form_GOTO": {
                            break;
                        }
                        case "triad_gener_if_NOP": {
                            this.triads.triad_gener_if_NOP();
                            this.treeNext.triad_gener_if_NOP();
                            break;
                        }

                        case "triad_push": {
                            treeNext.triad_push(next, lexemToStr(lexem));
                            this.triads.triad_push(lexemToStr(lexem), next);
                            break;
                        }
                        case "triad_push_param": {
                            this.triads.triad_push_param();
                            this.treeNext.triad_push_param();
                            break;
                        }
                        case "triad_call": {
                            this.treeNext.triad_call();
                            this.triads.triad_call();
                            break;
                        }
                        case "triad_return": {
                            this.triads.triad_return();
                            this.treeNext.triad_return();
                            break;
                        }
                        case "triad_remember_call": {
                            this.treeNext.triad_remember_call(lexemToStr(lexem));
                            this.triads.triad_remember_call(lexemToStr(lexem));
                            break;
                        }

                        case "gener_equal": {
                            this.triads.gener_equal();
                            this.treeNext.gener_equal();
                            break;
                        }
                        case "gener_not_equal": {
                            this.triads.gener_not_equal();
                            this.treeNext.gener_not_equal();
                            break;
                        }
                        case "gener_great": {
                            this.triads.gener_great();
                            this.treeNext.gener_great();
                            break;
                        }
                        case "gener_great_equal": {
                            this.triads.gener_great_equal();
                            this.treeNext.gener_great_equal();
                            break;
                        }
                        case "gener_less": {
                            this.triads.gener_less();
                            this.treeNext.gener_less();

                            break;
                        }
                        case "gener_less_equal": {
                            this.triads.gener_less_equal();
                            this.treeNext.gener_less_equal();

                            break;
                        }
                        case "gener_assign": {
                            this.treeNext.generAssign();
                            this.triads.gener_assign();
                            break;
                        }
                        case "gener_star": {
                            this.treeNext.generStar();
                            this.triads.gener_star();
                            break;
                        }
                        case "gener_div": {
                            this.treeNext.generDiv();
                            this.triads.gener_div();
                            break;
                        }
                        case "gener_percent": {
                            this.triads.gener_percent();
                            break;
                        }
                        case "gener_plus": {
                            this.treeNext.generPlus();
                            this.triads.gener_plus();
                            break;
                        }
                        case "gener_minus": {
                            this.triads.gener_minus();
                            break;
                        }

                        case "push_t": {
                            semantic.push_t(next, lexemToStr(lexem)); // возвращаем current
                            break;
                        }

                        case "callFunc": {
                            treeNext.callFunc(); // возвращаем current
                            semantic.callFunc(); // возвращаем current
                            break;
                        }
                        case "start_parameter_counting": {
                            treeNext.start_parameter_counting();
                            // TODO semantic.start_parameter_counting();
                            break;
                        }
                        case "end_parameter_counting": {
                            treeNext.end_parameter_counting();
                            // TODO semantic.end_parameter_counting();
                            break;
                        }
                        case "plus_parameter_counting": {
                            treeNext.plus_parameter_counting();
                            // TODO semantic.plus_parameter_counting();
                            break;
                        }
                        case "endFunc": {
                            this.treeNext.endFunc();
                            break;
                        }
                        default: {
                            throw new Exception("семантическое правило " + topELem.str + " из файла грамматики не найдено в списке");
                        }
                    }
                } else {
                    throw new Ex_Exception("errorina");
                }


                System.out.print("");
            }
        } catch (Exception e) {
            System.out.println("ошибка : " + lexemToStr(lexem));
            savePointCurrent.print();
            throw e;
        }


        System.out.print("Ошибок не найдено =) \n\n");
        if (devMode) {
            semantic.createGraphViz();
            semantic.drawTree();
        }
        triadsStr = this.triads.printTriads();

        if (devMode)
            treeNext.draw(null);

        if (devMode && triads.isTriads)
            if (!triadsStr.equals(lolkek))
                throw new Exception("!triadsS197tr.equals(\"lolkek\")");
        return true;
    }

    public String triadsStr;

    private RightPart findNeededPart(List<RightPart> rightParts, Elem elem) {
        List<RightPart> tmpList = rightParts.stream()
                .filter(rightPart -> rightPart.isContain(elem))
                .collect(Collectors.toList());
        RightPart rightPart = tmpList.get(0);
        return rightPart;
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

        if (count > 30000)
            throw new Ex_Exception("count > 30000");

        if (devMode == false)
            return;

        System.out.println("==========================" + count + "=================================\n");
        if (count == 152)
            System.out.println();
        List<Elem> stackNaoborot = new ArrayList<>();
        for (Elem elem : stack) {
            stackNaoborot.add(elem);
        }
        for (int i = stackNaoborot.size() - 1; i >= 0; --i) {
            System.out.println(stackNaoborot.get(i).getStrByType_LIGHT());
        }
        System.out.print("");
    }

    private void printNext(List<Character> lexem, LexTypeTERMINAL next) {
        if (!devMode)
            return;
        System.out.println();
        String lexem_str = this.lexemToStr(lexem);
        System.out.println("next: " + lexem_str + "     type: " + next.getString());
        System.out.println();
    }

    String lolkek = "0)    proc    heh\n" +
            "1)    prolog\n" +
            "2)    _INT    x\n" +
            "3)    *    a    a\n" +
            "4)    =    x    (3)\n" +
            "5)    *    x    3\n" +
            "6)    push_for_return    (5)\n" +
            "7)    epilog\n" +
            "8)    ret\n" +
            "9)    endp\n" +
            "10)    proc    main\n" +
            "11)    prolog\n" +
            "12)    -    0    2\n" +
            "13)    +    (12)    5\n" +
            "14)    >    (13)    3\n" +
            "15)    if    (16)    (33)\n" +
            "16)    _INT    a\n" +
            "17)    =    a    2\n" +
            "18)    _INT    qwer\n" +
            "19)    push    a\n" +
            "20)    call    heh\n" +
            "21)    =    qwer    (20)\n" +
            "22)    _INT    b\n" +
            "23)    =    b    2\n" +
            "24)    >    123    138\n" +
            "25)    if    (26)    (29)\n" +
            "26)    =    a    228\n" +
            "27)    goto    (29)\n" +
            "28)    =    a    282\n" +
            "29)    NOP\n" +
            "30)    goto    (33)\n" +
            "31)    _INT    x\n" +
            "32)    =    x    2\n" +
            "33)    NOP\n" +
            "34)    _DOUBLE    cc\n" +
            "35)    =    cc    228\n" +
            "36)    *    cc    3\n" +
            "37)    push_for_return    (36)\n" +
            "38)    epilog\n" +
            "39)    ret\n" +
            "40)    endp";
}
