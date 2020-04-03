package main.Lab2;


import main.SavePoint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScanerV2 {

    class Tuple<X, Y> {
        public final X x;
        public final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    private final int _MAX_LEX = 20;

    //public final static int _TYPE_INT_10 = 101;
//    public final static int _TYPE_INT_16 = 102;
//    public final static int _TYPE_INT_8 = 103;
//    public final static int _TYPE_CHAR = 104;
//    public final static int _ID = 105;
//    public final static int _STAR = 106;
//    public final static int _SLASH = 107;
//    public final static int _PERCENT = 108;
//    public final static int _PLUS = 109;
//    public final static int _MINUS = 110;
//    public final static int _SHIFT_LEFT = 111;              // <<
//    public final static int _SHIFT_RIGHT = 112;             // >>
//    public final static int _GREAT = 113;
//    public final static int _LESS = 114;
//    public final static int _GREAT_EQUALLY = 115;
//    public final static int _LESS_EQUALLY = 116;
//    public final static int _EQUALLY = 117;
//    public final static int _NOT_EQUALLY = 118;
//    public final static int _ASSIGN = 119;                  // =
//    public final static int _PARENTHESIS_OPEN = 120;        // (
//    public final static int _PARENTHESIS_CLOSE = 121;       // )
//    public final static int _BRACE_OPEN = 122;              // {
//    public final static int _BRACE_CLOSE = 123;             // }
//    public final static int _POINT = 124;
//    public final static int _COMMA = 125;                   // ,
//    public final static int _SEMICOLON = 126;               // ;
//    public final static int _INT = 127;
//    public final static int _DOUBLE = 128;
//    public final static int _CONST = 129;
//    public final static int _IF = 130;
//    public final static int _ELSE = 131;
//    public final static int _MAIN = 132;
//    public final static int _ERROR = 133;
//    public final static int _END = 134;


    ArrayList<Character> t;                 // Исходный текст
    ArrayList<Integer> t2;                 // Исходный текст

    ArrayList<ScanerV2.Tuple<String, LexType>> KEYWORD;
    int uk;                                 // указатель текущей позиции в исходном тексте
    int lines;                             // счетчик строк, для вывода ошибки
    int position_in_lines;                  // Счетчик позиции в строке, для вывода ошибки
    int position_in_lines_old;                  // Счетчик позиции в строке, для вывода ошибки
    int i = 0;                             // Текущая длина лексемы


    public ScanerV2(String path) throws IOException {
        this.lines = 0;
        this.position_in_lines = 0;
        this.position_in_lines_old = 0;
        this.t = new ArrayList<>();

        this.KEYWORD = new ArrayList<>();

        this.KEYWORD.add(new ScanerV2.Tuple<>("int", LexType._INT));
        this.KEYWORD.add(new ScanerV2.Tuple<>("double", LexType._DOUBLE));
        this.KEYWORD.add(new ScanerV2.Tuple<>("if", LexType._IF));
        this.KEYWORD.add(new ScanerV2.Tuple<>("else", LexType._ELSE));
        //this.KEYWORD.add(new Tuple<>("main",_MAIN));
        this.KEYWORD.add(new ScanerV2.Tuple<>("const", LexType._CONST));


        try {
            FileReader fileReader = new FileReader(path);
        } catch (FileNotFoundException ex) {
            printError("Файла не существует");
            System.exit(404);
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        int symbol = bufferedReader.read();
        while (symbol != -1) {  // Когда дойдём до конца файла, получим '-1'
            // Что-то делаем с прочитанным символом
            // Преобразовать в char:
            // char c = (char) symbol;
            char c = (char) symbol;
            if (c != '\r')
                this.t.add(c);
            else {
                //System.out.print("\\r");
            }
            //System.out.println();
            symbol = bufferedReader.read(); // Читаем символ
        }
        this.t.add('\0');
    }
    // old
    public ScanerV2() throws IOException {
        this.lines = 0;
        this.position_in_lines = 0;
        this.position_in_lines_old = 0;
        this.t = new ArrayList<>();

        this.KEYWORD = new ArrayList<>();

        this.KEYWORD.add(new ScanerV2.Tuple<>("int", LexType._INT));
        this.KEYWORD.add(new ScanerV2.Tuple<>("double", LexType._DOUBLE));
        this.KEYWORD.add(new ScanerV2.Tuple<>("if", LexType._IF));
        this.KEYWORD.add(new ScanerV2.Tuple<>("else", LexType._ELSE));
        //this.KEYWORD.add(new Tuple<>("main",_MAIN));
        this.KEYWORD.add(new ScanerV2.Tuple<>("const",LexType._CONST));

        String path = System.getProperty("user.dir") + "\\test.txt";
        try {
            FileReader fileReader = new FileReader(path);
        } catch (FileNotFoundException ex) {
            printError("Файла не существует");
            System.exit(404);
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        int symbol = bufferedReader.read();
        while (symbol != -1) {  // Когда дойдём до конца файла, получим '-1'
            // Что-то делаем с прочитанным символом
            // Преобразовать в char:
            // char c = (char) symbol;
            char c = (char) symbol;
            if (c != '\r')
                this.t.add(c);
            else {
                //System.out.print("\\r");
            }
            //System.out.println();
            symbol = bufferedReader.read(); // Читаем символ
        }
        this.t.add('\0');
    }

    private int inc_uk() {
        int oldUk = this.uk;
        this.uk++;
        this.position_in_lines++;
        return oldUk;
    }

    private void inc_lines() {
        this.lines++;
        this.position_in_lines = 0;
    }


    public int getUk() {
        return this.uk;
    }

    public void setUk(int uk) {
        this.uk = uk;
    }


    public SavePoint getSavePoint() {
        return new SavePoint(this.uk, this.lines, this.position_in_lines, this.position_in_lines_old, startLexemPositionInLine);
    }


    public void setSavePoint(SavePoint savePoint) {
        this.uk = savePoint.uk1;
        this.lines = savePoint.lines;
        this.position_in_lines = savePoint.position;
        this.position_in_lines_old = savePoint.position_old;
    }


    public LexType scaning(ArrayList<Character> l) {

        LexType resultScanerV2 = null;
        System.out.print("\n\n==========================================================================================\n\n");
        do {
            resultScanerV2 = next(l);
            System.out.print(resultScanerV2 + " - тип \t\t");
            for (char c : l) {
                System.out.print(c);
            }
            System.out.print("\n");
        } while (resultScanerV2 != LexType._END);
        return null;
    }

    public LexType next(List<Character> l) {
        this.position_in_lines_old = this.position_in_lines;
        i = 0;
        l.clear();
        char current = ' ';
        try {
            current = t.get(uk);
        } catch (IndexOutOfBoundsException ex) {
            return LexType._END;
        }
        return startMetka(l);
    }
    public int startLexemPositionInLine = 0;

    public LexType startMetka(List<Character> l) {
//        i = 0;
//        l.clear();
//        char current = ' ';
//        try {
//             current = type.get(uk);
//        }catch (IndexOutOfBoundsException  ex){
//            return _END;
//        }

        // все игнорируемые элементы:

        Character character = t.get(uk);
        while ((t.get(uk) == ' ') || (t.get(uk) == '\n') || (t.get(uk) == '\t')) {

            if (t.get(uk) == '\n')
                inc_lines();
            inc_uk();
            character = t.get(uk);
        }


        char current = t.get(uk);
        // пропуск незначащих элементов
        if ((t.get(uk) == '/') && (t.get(uk + 1) == '/')) {
            // начался комментарий, надо пропустить текст до '\n'
            uk = uk + 2;
            while (t.get(uk) != '\n' && t.get(uk) != '\0' && (t.get(uk) != '#')) {
                if (t.get(uk) == '\n')
                    inc_lines();
                inc_uk();
            }
            return this.startMetka(l); //goto startMetka;
        }
        // current = type.get(uk);
        if (t.get(uk) == '\0') {
            l.add('#');         //l.set(0,'#');   // l.get(0) = '#';
            return LexType._END;
        }



        startLexemPositionInLine = this.position_in_lines;

        /////////////////////////////////  /////////////////////////////////  /////////////////////////////////
        //current = type.get(uk);
        if (t.get(uk) >= '0' && t.get(uk) <= '9') {
            // Проверка на восьмеричную и шестнадцатеричную константу
            if (t.get(uk) == '0') {
                l.add(t.get(inc_uk()));
                i++;
                // Шестнадцатиричная
                if (t.get(uk) == 'x') {
                    while ((t.get(uk) >= '0' && t.get(uk) <= '9') || (t.get(uk) >= 'A' && t.get(uk) <= 'F')) {
                        if (i < _MAX_LEX - 1) {
                            l.add(t.get(inc_uk()));
                            i++;
                        } else {
                            inc_uk();
                        }
                    }
                    return LexType._TYPE_INT_16;
                } else if ((t.get(uk) >= '0') && (t.get(uk) <= '7')) { // восьмеричная
                    while ((t.get(uk) >= '0') && (t.get(uk) <= '7')) {
                        if (i < _MAX_LEX - 1) {
                            l.add(t.get(inc_uk()));
                            i++;
                        } else {
                            inc_uk();
                        }
                    }
                    return LexType._TYPE_INT_8;
                } else {          // десятичный, причем нуль
                    return LexType._TYPE_INT_10;
                }

            } else if (t.get(uk) != '0') {     // Десятичная
                l.add(t.get(inc_uk()));
                i++;
                while (t.get(uk) >= '0' && t.get(uk) <= '9') {
                    if (i < _MAX_LEX - 1) {
                        l.add(t.get(inc_uk()));
                        i++;
                    } else {
                        inc_uk();
                    }
                }
                return LexType._TYPE_INT_10;
            }
        } else if ((t.get(uk) >= 'a' && t.get(uk) <= 'z') || (t.get(uk) >= 'A' && t.get(uk) <= 'Z')) {
            // начинается идентификатор
            l.add(t.get(inc_uk()));
            i++; //l[i++]=type[inc_uk()];
            while ((t.get(uk) >= '0' && t.get(uk) <= '9') || (t.get(uk) >= 'a' && t.get(uk) <= 'z') ||
                    (t.get(uk) >= 'A' && t.get(uk) <= 'Z'))
                if (i < _MAX_LEX - 1) {
                    l.add(t.get(inc_uk()));
                    i++; //l[i++]=type[inc_uk()];
                } else
                    inc_uk();
            // длинный идентификатор обрезали

            // проверка на ключевое слово:
            LexType resultCheckKEYWORD = checkKEYWORD(l);
            if (resultCheckKEYWORD != null)
                return resultCheckKEYWORD;

            return LexType._ID;
        } else if (t.get(uk) == '.') {
            l.add(t.get(inc_uk()));
            i++; //l[i++]=type[inc_uk()];
            return LexType._POINT;
        } else
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (t.get(uk) == '\'') {
                inc_uk(); // не будем включать кавычки в константу
                while ((t.get(uk) != '\'' && t.get(uk) != '#' && t.get(uk) != '\n')) {
                    if (t.get(uk) == '\n')
                        inc_lines();
                    if (i < _MAX_LEX - 1) {
                        l.add(t.get(inc_uk()));
                        i++; //l[i++]=type[inc_uk()];
                    } else
                        inc_uk();
                }
                if (t.get(uk) != '\'' || i > 1) /// || i > 1 проверка на длину константы
                {
                    printError("Неверная символьная константа", l);
                    return LexType._ERROR;
                }
                inc_uk(); // закрывающая кавычка
                return LexType._TYPE_CHAR;
            }
        if (t.get(uk) == '>') {
            l.add(t.get(inc_uk()));
            i++;

            if (t.get(uk) == '=') {
                l.add(t.get(inc_uk()));
                i++;
                return LexType._GREAT_EQUALLY;
            } else if (t.get(uk) == '>') {
                l.add(t.get(inc_uk()));
                i++;
                return LexType._SHIFT_RIGHT;
            }
            return LexType._GREAT;
        } else if (t.get(uk) == '<') {
            l.add(t.get(inc_uk()));
            i++;

            if (t.get(uk) == '=') {
                l.add(t.get(inc_uk()));
                i++;
                return LexType._LESS_EQUALLY;
            } else if (t.get(uk) == '<') {
                l.add(t.get(inc_uk()));
                i++;
                return LexType._SHIFT_LEFT;
            }
            return LexType._LESS;
        } else if (t.get(uk) == '!') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            if (t.get(uk) == '=') {
                l.add(t.get(inc_uk()));
                i++;  //l[i++]=type[inc_uk()];
                return LexType._NOT_EQUALLY;
            } else {
                printError("Неверный символ  ", l); // ошибка
                return LexType._ERROR;
            }
        } else if (t.get(uk) == '=') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            if (t.get(uk) == '=') {
                l.add(t.get(inc_uk()));
                i++;  //l[i++]=type[inc_uk()];
                return LexType._EQUALLY;
            } else
                return LexType._ASSIGN;
        } else if (t.get(uk) == '+') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._PLUS;
        } else if (t.get(uk) == '-') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._MINUS;
        } else if (t.get(uk) == '*') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._STAR;
        } else if (t.get(uk) == '%') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._PERCENT;
        } else if (t.get(uk) == '/') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._SLASH;
        } else if (t.get(uk) == ';') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._SEMICOLON;
        } else if (t.get(uk) == ',') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._COMMA;
        } else if (t.get(uk) == '(') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._PARENTHESIS_OPEN;
        } else if (t.get(uk) == ')') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._PARENTHESIS_CLOSE;
        } else if (t.get(uk) == '{') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._BRACE_OPEN;
        } else if (t.get(uk) == '}') {
            l.add(t.get(inc_uk()));
            i++;  //l[i++]=type[inc_uk()];
            return LexType._BRACE_CLOSE;
        } else {
            printError("Неверный символ", l); // ошибка
            inc_uk();
            return LexType._ERROR;
        }
    }

    // проверка на ключевое слово:
    public LexType checkKEYWORD(List<Character> l) {
        // формируем строку из текущей лексеым
        String lexem = "";
        for (int j = 0; j < l.size(); j++)
            lexem += l.get(j);
        // проверяем текущую лексему, с кллючевыми словами языка
        for (int j = 0; j < KEYWORD.size(); j++) {
            String tmp = KEYWORD.get(j).x;
            if (lexem.equals(tmp))
                return KEYWORD.get(j).y;
        }
        return null;
    }


    public void printError(String str, List<Character> l) {
        String lexem = "";
        for (int j = 0; j < l.size(); j++)
            lexem += l.get(j);
        System.out.print("Ошибка: " + str + "\t|\t Неверный символ: " + lexem);
//		System.out.print(" Строка " + (lines + 1));
//		System.out.print(" Символ " + (position_in_lines + 1));
        System.out.print(" Строка " + (lines + 1));
        System.out.print(" Символ " + (this.startLexemPositionInLine));
        System.out.print("\n");
    }

    public void printError(String str) {
        System.out.print("Ошибка: " + str + "\t\n");
    }

    public int N1(ArrayList<Character> l) {
        return 0;
    }

    public int N2(ArrayList<Character> l) {
        return 0;
    }

}

