package main.Lab3;

import java.util.ArrayList;
import java.util.HashMap;


public class Semantic {

    static final int TYPE_FUNC = 1;
    static final int TYPE_DOUBLE = 2;
    static final int TYPE_INTEGER = 3;
    static final int TYPE_UNKNOWN = 4;
    static final int TYPE_BLACK = 5;
    static final int TYPE_CHAR = 6;

    Tree cur ;
    Scaner scaner;
    Tree root ;

    HashMap<String, Integer> types;



    public Semantic() {
        types = new HashMap<>();
        types.put("int", TYPE_INTEGER);
        types.put("double", TYPE_DOUBLE);
        types.put("char", TYPE_CHAR);
        this.root = new Tree();
        this.root.n.dataType = Node.TYPE_BLACK;         /// Корень ЧЕРНЫЙ НИГА
        this.cur = this.root;
    }

    void etCur(Tree  a){
        // установить текущий узел дерева
        cur = a;
        if(root == null)
            root = cur;
    }

    Tree getCur(){
        // получить значение текущего узла дерева
        return cur;
    }


    Tree semInclude(ArrayList<Character> a, Container t){
        // занесение идентификатора a в таблицу с типом type

        if ( dupControl_OneLevel(cur,a) )
            scaner.printError("Повторное описание идентификатора ",a);
        Tree v;
        if ( t.type != Node.TYPE_FUNC)
        {
            // не функция
            Node b = new Node();
            b.id = new ArrayList<>(a);
            b.dataType  =  t.type;
            b.data = null;
            b.param = -1; // количество параметров функции
            b.returnType = -1;   // тип возвращаемого параметра
            cur.setLeft(b); // сделали вершину - переменную

            cur  =  cur.left;

            return cur;
        }
        else
        {
            // Функция
            Node b = new Node();
            b.id = new ArrayList<>(a);
            b.dataType = t.type;
            b.data = null;
            b.param = 0; // количество параметров функции
            cur.setLeft (b); // сделали вершину - функцию
            cur  =  cur.left;

            v = cur; // это точка возврата после выхода из функции

            b = new Node();
            b.dataType = Node.TYPE_BLACK;
            b.data = null;
            b.param = -1;
            b.returnType = -1;
            cur.setRight (b); // сделали пустую вершину
            cur  =  cur.right;
            return v;
        }
    }
    
    void semSetType(Tree addr, int t){
        // установить тип type для переменной по адресу Addr
        addr.n.dataType = t;
    }
    void SemSetParam(Tree addr, int num){
        // установить число формальных параметров n для функции по адресу Addr
        addr.n.param = num;
    }
    void SemControlParam(Tree addr, int num){
        // проверить равенство числа формальных параметров значению
        // n для функции по адресу Addr

        if (num != addr.n.param)
            scaner.printError("Неверное число параметров у функции ", addr.n.id);
    }
    Tree semGetType(ArrayList<Character> a){
        // найти в таблице переменную с именем a
        // и вернуть ссылку на соответствующий элемент дерева

        Tree v = cur.findUp(cur, a);
        if (v == null)
            scaner.printError("Отсутствует описание идентификатора ",a);
        if (v.n.dataType == Node.TYPE_FUNC)
            scaner.printError("Неверное использование вызова функции ",a);
        return v;
    }
    Tree semGetFunct(ArrayList<Character> a){
        // найти в таблице функцию с именем a
        // и вернуть ссылку на соответствующий элемент дерева.

        Tree  v = cur.findUp(cur, a);
        if (v == null)
            scaner.printError("Отсутствует описание функции ",a);
        if (v.n.dataType != Node.TYPE_FUNC)
            scaner.printError("Не является функцией идентификатор ",a);
        return v;
    }
    boolean dupControl_OneLevel(Tree addr, ArrayList<Character> a){
        // Проверка идентификатора а на повторное описание внутри блока.
        // Поиск осуществляется вверх от вершины Addr.

        if (cur.findUpOneLevel(addr, a)  ==  null)
            return false;
        return true;
    }

    boolean dupControl_Function(Tree addr, ArrayList<Character> a){
        // Проверка идентификатора а на повторное описание внутри блока.
        // Поиск осуществляется вверх от вершины Addr.

        if (cur.findUpOneLevel(addr, a)  ==  null)
            return false;
        return true;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void sem1(ArrayList<Character> lex, Container t){
        String strLex = new String();
        for(Character character: lex)
            strLex += character;

        if( types.containsKey(strLex))
            t.type = types.get(strLex);

        System.out.print("");
    }


    public Tree sem2Const(ArrayList<Character> lex, Container t){
        if ( dupControl_OneLevel(cur, lex) )
            scaner.printError("Повторное описание идентификатора ", lex);

        // не функция
        Node b = new Node();
        b.id = new ArrayList<>(lex);
        b.dataType  =  t.type;
        b.data = null;
        b.param = -1; // количество параметров функции
        b.returnType = -1;   // тип возвращаемого параметра
        b.flag_constant = 1;
        cur.setLeft(b); // сделали вершину - переменную

        cur  =  cur.left;
        System.out.print("");
        return cur;

    }
    
    public Tree sem2(ArrayList<Character> lex, Container t){
        if ( dupControl_OneLevel(cur, lex) )
            scaner.printError("Повторное описание идентификатора ", lex);

        // не функция
        Node b = new Node();
        b.id = new ArrayList<>(lex);
        b.dataType  =  t.type;
        b.data = null;
        b.param = -1; // количество параметров функции
        b.returnType = -1;   // тип возвращаемого параметра
        b.flag_constant = 0;
        cur.setLeft(b); // сделали вершину - переменную

        cur  =  cur.left;
        System.out.print("");
        return cur;

    }

    public boolean sem3(Container t, Container g, ArrayList<Character> l){
        if( t.type == Node.TYPE_UNKNOWN || g.type == Node.TYPE_UNKNOWN){
            scaner.printError("Невозможно привести типы ", l);
            g.type = Node.TYPE_UNKNOWN;
            return false;
        }

        // Впринципе не обязательно
        if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
            return true;

        if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE)
            return true;

        if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
            return true;

        if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER)
            return true;

        if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE)
            return true;

        if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR)
            return true;

        if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
            return true;

        if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE)
            return true;

        if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
            return true;
        return false;
    }


    public Tree sem21(ArrayList<Character> lex, Container t){

        if( cur.findUp(lex) != null ){
            // Если нашли совпадение
            scaner.printError("Повторное объявление идентификатора", lex);
            //return null;
        }
        Node b = new Node();
        b.id = new ArrayList<>(lex);
        b.dataType = Node.TYPE_FUNC;
        b.data = null;
        b.param = 0; // количество параметров функции
        b.returnType = t.type;
        cur.setLeft(b);
        cur = cur.left;
        Tree v = cur;

        b = new Node();
        b.id = null;
        b.dataType = Node.TYPE_BLACK;
        b.data = null;
        b.param = -1; // количество параметров функции
        b.returnType = -1;
        cur.setRight(b);
        cur = cur.right;

        return v;
    }


    public void sem17(Tree k){

        k.n.param++;
    }


    public void sem18(Tree k){

        this.cur = k;
    }


    public Tree sem4(){
        Node b = new Node();

        b.id = null;
        b.dataType = Node.TYPE_BLACK;
        b.data = null;
        b.param = -1; // количество параметров функции
        b.returnType = -1;

        cur.setLeft(b);
        cur = cur.left;

        Tree v = cur;

        b = new Node();

        b.id = null;
        b.dataType = Node.TYPE_BLACK;
        b.data = null;
        b.param = -1; // количество параметров функции
        b.returnType = -1;

        cur.setRight(b);
        cur = cur.right;

        return v;
    }


    public Tree sem5( ArrayList<Character> lex, Container type){
        Tree tmp = cur.findUp(lex);
        if( tmp == null){
            scaner.printError("Идентификатор не определен", lex);
            type.type = Node.TYPE_UNKNOWN;
            return tmp;
        }
        if(tmp.n.flag_declared != 1){
            scaner.printError("Переменная не объявлена", lex);
            type.type = Node.TYPE_UNKNOWN;
            return tmp;
        }
        type.type = tmp.n.dataType;
        return tmp;
    }


    public Tree sem5Assign( ArrayList<Character> lex, Container type){
        Tree tmp = cur.findUp(lex);
        // Проверяем, Мб такой переменной/константы вообще нет
        if( tmp == null){
            scaner.printError("Идентификатор не определен ", lex);
            type.type = Node.TYPE_UNKNOWN;
            return tmp;
        }
        if( tmp.n.dataType == Node.TYPE_FUNC){
            scaner.printError("Выражение должно быть допустимым для изменения левосторонним значением ", lex);
            type.type = Node.TYPE_UNKNOWN;
            return tmp;
        }
        // Проверяем, Если константа то все плохо, их низя ПЕРЕПРИСВАИВАТЬ))0
        if( tmp.n.flag_constant == 1){
            scaner.printError("Присваивание константе невозможно ", lex);
            type.type = Node.TYPE_UNKNOWN;
            return tmp;
        }


        type.type = tmp.n.dataType;
        return tmp;
    }


    public Tree sem5func( ArrayList<Character> lex, Container container){
        Tree tmp = cur.findUp(lex);
        if( tmp == null){
            scaner.printError("Идентификатор не определен", lex);
            container.type = Node.TYPE_UNKNOWN;
            return tmp;
        }
        if( tmp.n.dataType != Node.TYPE_FUNC){
            // Теперь пройдемся вверх, ища функцию
            Tree local = cur.findFunction(lex);
            if(local == null){
                scaner.printError("Функции с таким идентификатором не существует", lex);
                container.type = Node.TYPE_UNKNOWN;
                return local;
            }
            return local;
        }
        return tmp;
    }


    public void semParamDeclared(Tree t, Container type, ArrayList<Character> lex){
        if( type.type != Node.TYPE_UNKNOWN)
            t.n.flag_declared = 1;
        else{
            scaner.printError("Присваивание типа UNKNOWN", lex);
        }
    }


    public void semCheckType(int countParam, Tree k,Container type, ArrayList<Character> lex){
        if( k == null ){
            return;
        }
        if( countParam > k.n.param ){
            scaner.printError("Несовпадение количества параметров функции(что то их тут многовато)", lex);
        }
        else {
            // Ищем параметр # param
            //      Для этого пишем функцию getParamNum. Она будет искать num параметр от функции, шагая вниз
            Tree tmp = cur.getParamNUm(k, countParam);
            if( type.type != tmp.n.dataType){
                ////////////////////////////////////////////////////////////////////////////////////////////////////////Проверить приведение типов
                Container tmpContainer = new Container();
                tmpContainer.type = tmp.n.dataType;
                if( sem3(tmpContainer,type,lex) == false)
                    scaner.printError("Несовпадение типов параметров функции", lex);
            }
        }
    }


    public void semCheckCountParam(int countParam, Tree k, ArrayList<Character> lex){
        if( k == null ){
            return;
        }
        if(countParam != k.n.param){
            scaner.printError("Несовпадение количества параметров функции", lex);
        }

    }


    public void sem55(int typelex, Container t){
        switch (typelex){
            case Scaner._TYPE_INT_10:{
                t.type = Node.TYPE_INTEGER;
                break;
            }
            case Scaner._TYPE_INT_8:{
                t.type = Node.TYPE_INTEGER;
                break;
            }
            case Scaner._TYPE_INT_16:{
                t.type = Node.TYPE_INTEGER;
                break;
            }
            case Scaner._TYPE_CHAR:{
                t.type = Node.TYPE_CHAR;
                break;
            }
            default:{
                t.type = Node.TYPE_UNKNOWN;
                break;
            }
        }
    }


    public int sem6(Container t, Container g, int sign, ArrayList<Character> l){

        if( t.type == Node.TYPE_UNKNOWN || g.type == Node.TYPE_UNKNOWN)
            return Node.TYPE_UNKNOWN;

        if( sign == Scaner._STAR){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return  Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

        }
        if( sign == Scaner._SLASH){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return  Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE)
                return  Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return  Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;
        }
        if( sign == Scaner._PERCENT){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }
            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)   //??????????????????????????????????????????????????????????????????????????????
                return Node.TYPE_INTEGER;
        }
        if( sign == Scaner._PLUS){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;
        }
        if( sign == Scaner._MINUS){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_DOUBLE;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;
        }
        if( sign == Scaner._SHIFT_LEFT || sign == Scaner._SHIFT_RIGHT){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE){
                scaner.printError("выражение должно относиться к целочисленному типу");
                return Node.TYPE_UNKNOWN;
            }

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER;
        }
        if( sign == Scaner._GREAT || sign == Scaner._LESS ||
                sign == Scaner._GREAT_EQUALLY || sign == Scaner._LESS_EQUALLY ||
                sign == Scaner._NOT_EQUALLY || sign == Scaner._EQUALLY ){
            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_INTEGER && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_DOUBLE && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_INTEGER)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_DOUBLE)
                return Node.TYPE_INTEGER ;

            if( t.type == Node.TYPE_CHAR && g.type == Node.TYPE_CHAR)
                return Node.TYPE_INTEGER ;
        }
        return -1337;
    }


}


