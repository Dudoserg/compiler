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
        LLK llk = new LLK();
    }

    Map<Pair<Elem, Elem>, List<RightPart>> table;
    Table tableObj;
    boolean flag_working = true;
    private Stack<Elem> stack = new Stack<>();

    public LLK() throws Exception {
        this.tableInit();
    }

    private void tableInit() throws Exception {
        CreateLLK createLLK = new CreateLLK();
        ReadLLK readLLK = new ReadLLK();
        table = readLLK.getTable();
        tableObj = readLLK.getTableOjb();

        List<Character> lexem = new ArrayList<>();
        ScanerV2 scanerV2 = new ScanerV2();

        stack.push(new Elem(ElemType.TERMINAL,"#", LexTypeTERMINAL._END));
        stack.push(new Elem(ElemType.NOT_TERMINAL,"_программа", LexTypeNot._программа));

        LexTypeTERMINAL next = scanerV2.next(lexem);
        while (flag_working){

            printStack();
            printNext(lexem, next);

            Elem topELem = this.stack.pop();
            //TODO случай когда епсилон

            if( topELem.elementType == ElemType.TERMINAL ){
                System.out.print("");
                if (topELem.lexTypeTERMINAL == LexTypeTERMINAL._END)
                    flag_working = false;
                else
                     next = scanerV2.next(lexem);
            }
            else if( topELem.elementType == ElemType.NOT_TERMINAL ){
                List<RightPart> rightParts = table.get(new Pair<>(topELem, new Elem(ElemType.TERMINAL, lexemToStr(lexem), next)));
                if(rightParts.size() == 0){
                    throw new Exception("нет такого в таблице");
                }
                if( rightParts.size() == 1){
                    pushRightPartToStack(rightParts.get(0));
                    printStack();
                    printNext(lexem, next);
                    System.out.print("");
                }else {
                    //<одно_описание> _INT
                    if( topELem.lexTypeNot == LexTypeNot._одно_описание && ( next == LexTypeTERMINAL._INT || next == LexTypeTERMINAL._DOUBLE)){
                        SavePoint savePoint = scanerV2.getSavePoint();

                        ArrayList<Character> lexem_2 = new ArrayList<>();
                        LexTypeTERMINAL next_2 = scanerV2.next(lexem_2);

                        ArrayList<Character> lexem_3 = new ArrayList<>();
                        LexTypeTERMINAL next_3 = scanerV2.next(lexem_2);

                        System.out.print("");
                    }
                }
            }
            else if( topELem.elementType == ElemType.PROGRAMM ){

            }


            System.out.println();

        }





        System.out.print("");
    }

    private void pushRightPartToStack(RightPart rightPart) {
        for(int i = rightPart.elemList.size() - 1; i>= 0 ; i--){
            this.stack.push(rightPart.elemList.get(i));
        }
    }

    private String lexemToStr(List<Character> lexem){
        String lexem_str = lexem.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        return lexem_str;
    }
    
    private void printStack(){
        System.out.println("===========================================================\n");
        List<Elem> stackNaoborot = new ArrayList<>();
        for (Elem elem : stack) {
            stackNaoborot.add(elem);
        }
        for(int i = stackNaoborot.size() - 1 ; i >=0 ; --i){
            System.out.println(stackNaoborot.get(i).getStrByType_LIGHT());
        }
    }

    private void printNext( List<Character> lexem, LexTypeTERMINAL next){
        System.out.println();
        String lexem_str = this.lexemToStr(lexem);
        System.out.println("next: " + lexem_str + "     type: " + next.getString());
        System.out.println();
    }
}
