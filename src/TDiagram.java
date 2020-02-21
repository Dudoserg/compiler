import java.io.IOException;
import java.util.ArrayList;

public class TDiagram {
    Scaner scaner ;

    Semantic semantic;
    boolean SHOW_NAME_NOT_TERMINAL = false;

    public TDiagram() throws IOException {
        scaner = new Scaner();
        if( this.programma())
            System.out.println("all is ok");
        else ;
    }

    public boolean programma(){
        ArrayList<Character> l = new ArrayList<>();
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        semantic = new Semantic();
        semantic.scaner = this.scaner;
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int t ;
        SavePoint savePoint1 ;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);        // Считали ТИП

        while (t == scaner._INT || t == scaner._DOUBLE || t == scaner._CONST){
            // Если int double
            if( t == scaner._INT || t == scaner._DOUBLE){
                t = scaner.next(l); // Считали ИДЕНТИФИКАТОР

                if( t != scaner._ID && t != scaner._MAIN){
                    scaner.printError("1Ожидался идентификатор / ключевое слово main",l);
                    return false;
                }

                t = scaner.next(l); // Считали либо '=' или';' (если объявление переменных) либо '(' (если функция)

                if(t == scaner._ASSIGN ||  t == scaner._SEMICOLON || t == scaner._COMMA){ // = ;
                    //ОБЪЯВЛЕНИЕ ПРЕМЕННЫХ
                   scaner.setSavePoint(savePoint1);
                    if( !this.declaration_of_variable())
                        return false;
                }
                else if(t == scaner._PARENTHESIS_OPEN ){
                    //ФУНКЦИЯ
                   scaner.setSavePoint(savePoint1);
                    if( !this.func())
                        return false;
                }
                else{
                    scaner.printError("2Ожидался один из символов : = ; (",l);
                    return false;
                }


            }
            else{           // Если const
                if( t == scaner._CONST){
                   scaner.setSavePoint(savePoint1);
                    if ( !declaration_of_constant())
                        return false;
                }
                else {
                    scaner.printError("3Ожидалось ключевое слово int || const",l);
                    return false;
                }
            }
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }
       scaner.setSavePoint(savePoint1);
        t = scaner.next(l);
        if( t == scaner._END)
            return true;
        else{
            scaner.printError("#Ожидось объявление функции или констант или переменных",l);
            return false;
        }

    }


    // Объявление констант
    public boolean declaration_of_constant(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("declaration_of_constant");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;
        Container containerT = new Container();
        Container containerG = new Container();

        savePoint1 = scaner.getSavePoint();///////////////////////////////////////////////////////////////////////////////////////////
        t = scaner.next(l);
        if( t != scaner._CONST){
            scaner.printError("4Ожидалось ключевое слово 'const'",l);
            return false;
        }

        t = scaner.next(l);
        if( t != scaner._INT && t != scaner._DOUBLE){
            scaner.printError("5Ожидалось ключевое слово 'int'/'double'",l);
            return false;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem1
        semantic.sem1(l, containerT);
        System.out.print("");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem1

        do{
            t = scaner.next(l);
            if( t != scaner._ID){
                scaner.printError("6Ожидался идентификато",l);
                return false;
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem2
            Tree k = semantic.sem2Const(l, containerT);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem2

            t = scaner.next(l);
            if(t != scaner._ASSIGN ){
                scaner.printError("34Ожидался символ '='   ",l);
                return false;
            }

            if( !this.expression(containerG))
                return false;

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem3
            semantic.sem3(containerT, containerG, l);
            semantic.semParamDeclared(k,containerG,l);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem3

            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
//            if( type == scaner._ASSIGN){
//                // =
//                type = scaner.next(l);
//                if( type == scaner._TYPE_INT_8 || type == scaner._TYPE_INT_10 || type == scaner._TYPE_INT_16){
//                    // Константа целая
//                }else if(type == scaner._TYPE_CHAR){
//                    // константа символьная
//                } else {
//                    scaner.printError("7Ожидалась константа символьная/числовая",l);
//                    return false;
//                }
//            }
        }while (t == scaner._COMMA );
       scaner.setSavePoint(savePoint1);
        t = scaner.next(l);
        if( t != scaner._SEMICOLON){
            scaner.printError("8ожидался символ ';'",l);
            return false;
        }
        return true;
    }


    /// / Объявление переменных
    public boolean declaration_of_variable(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("declaration_of_variable");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;
        Container containerT = new Container();
        Container containerG = new Container();

        t = scaner.next(l);
        if( t != scaner._INT && t != scaner._DOUBLE){
            scaner.printError("9Ожидалось ключевое слово 'int'/'double'",l);
            return false;
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem1
        semantic.sem1(l, containerT);
        System.out.print("");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem1
        do{
            t = scaner.next(l);
            if( t != scaner._ID){
                scaner.printError("10Ожидался идентификато",l);
                return false;
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem2
            Tree k = semantic.sem2(l,containerT);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem2
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
            //this.scaner.setUk(uk1);   scaner.setSavePoint(savePoint1);
            if( t == scaner._ASSIGN){
                // =

                if( !this.expression(containerG))
                    return false;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem3
                semantic.sem3(containerT, containerG, l);
                semantic.semParamDeclared(k, containerG, l);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////    sem3
                savePoint1 = scaner.getSavePoint();
                t = scaner.next(l);
            }
            else{
                /// Тут пошли по пустой ветке, все нормас
            }

        }while (t == scaner._COMMA );
       scaner.setSavePoint(savePoint1);
        t = scaner.next(l);
        if( t != scaner._SEMICOLON){
            scaner.printError("11ожидался символ ';'",l);
            return false;
        }
        return true;
    }


    // функция
    public boolean func(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("func");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;
        Container containerT = new Container();

        t = scaner.next(l);
        if( t != scaner._INT && t != scaner._DOUBLE){
            scaner.printError("12ожидался тип int/double",l);
            return false;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem1
        semantic.sem1(l,containerT);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem1
        t = scaner.next(l);
        if( t != scaner._ID && t != scaner._MAIN) {
            scaner.printError("13Ожидался идентификатор", l);
            return false;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem1
        Tree k = semantic.sem21(l,containerT);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem1
        t = scaner.next(l);
        // != '('
        if(t != scaner._PARENTHESIS_OPEN){
            scaner.printError("14Ожидался символ '(' ", l);
            return false;
        }

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        if( t != scaner._PARENTHESIS_CLOSE) { // != ')'
            // восстанавливаем метку до считываниея ')'
           scaner.setSavePoint(savePoint1);
            // Список параметров

            if( !this.lis_of_parametr(k))
                return false;

            t = scaner.next(l);
        }
        if( t != scaner._PARENTHESIS_CLOSE){
            scaner.printError("15Ожидался символ ')'", l);
            return false;
        }
        // Составной оператор
        if( !this.compound_operator_WITHOUT_CREATE_BLACK_VERTEX())////////////////////////////////////////////////////////////////////////////////// У функции свой собственный составной оператор
            return false;                                                                                             // который не создает две черные вершины
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem18
        semantic.sem18(k);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem18
        return true;
    }


    // Список параметров
    public boolean lis_of_parametr(Tree k){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("lis_of_parametr");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;
        Container containerT = new Container();
        do{
            t = scaner.next(l);
            if( t != scaner._DOUBLE && t != scaner._INT){
                scaner.printError("16ожидался тип int/double",l);
                return false;
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////// sem1
            semantic.sem1(l, containerT);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////// sem1
            t = scaner.next(l);
            if( t != scaner._ID){
                scaner.printError("17ожидался идентификатор",l);
                return false;
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////// sem2
            semantic.sem2(l, containerT);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////// sem2
            //////////////////////////////////////////////////////////////////////////////////////////////////////////// sem17
            semantic.sem17(k);
            //////////////////////////////////////////////////////////////////////////////////////////////////////////// sem17
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }while (t == scaner._COMMA);
        // Вернем позицию перед ликсемой != запятой
       scaner.setSavePoint(savePoint1);
        return true;
    }


    // составной оператор
    public boolean compound_operator_WITHOUT_CREATE_BLACK_VERTEX(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("compound_operator");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        t = scaner.next(l);
        if( t != scaner._BRACE_OPEN){
            scaner.printError("18Ожидался символ '{'",l);
            return false;
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);        // Считали
        //scaner.setUk(uk1);    scaner.setSavePoint(savePoint1);
        // Если int double то это объявление переменных
        // Если const то это объявление констант
        // Если идентификатор = то это оператор
        // Если { то это оператор
        // Если идентификатор то это оператор
        // Если if то это оператор
        // Если ; то это оператор
        while (t == scaner._INT || t == scaner._DOUBLE || t == scaner._CONST || t == scaner._ID || t == scaner._BRACE_OPEN ||
                t == scaner._IF || t == scaner._SEMICOLON){

            if(t == scaner._INT || t == scaner._DOUBLE  ){
                // Это объявление переменных
                scaner.setSavePoint(savePoint1);
                if( !this.declaration_of_variable())
                    return false;

            }
            else if(t == scaner._CONST){
                // Константа
                scaner.setSavePoint(savePoint1);
                if( !this.declaration_of_constant())
                    return false;
            }
            else if(t == scaner._ID || t == scaner._BRACE_OPEN ||  t == scaner._IF || t == scaner._SEMICOLON){
                // Оператор
                scaner.setSavePoint(savePoint1);
                if( !this.operator())
                    return false;
            }
            else{
                // eps Все нормас
            }
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);///////////////////////////////////////////////
            scaner.setSavePoint(savePoint1);///////////////////////////////////////////////////////////////////////////
        }
        scaner.setSavePoint(savePoint1);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        t = scaner.next(l);
        if(t != scaner._BRACE_CLOSE){
            scaner.printError("19ожидался символ '}'",l);
            return false;
        }
        return true;
    }


    // составной оператор
    public boolean compound_operator(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("compound_operator");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        t = scaner.next(l);
        if( t != scaner._BRACE_OPEN){
            scaner.printError("18Ожидался символ '{'",l);
            return false;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem4
        Tree k = semantic.sem4();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem4
        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);        // Считали
        //scaner.setUk(uk1);    scaner.setSavePoint(savePoint1);
        // Если int double то это объявление переменных
        // Если const то это объявление констант
        // Если идентификатор = то это оператор
        // Если { то это оператор
        // Если идентификатор то это оператор
        // Если if то это оператор
        // Если ; то это оператор
        while (t == scaner._INT || t == scaner._DOUBLE || t == scaner._CONST || t == scaner._ID || t == scaner._BRACE_OPEN ||
                t == scaner._IF || t == scaner._SEMICOLON){

            if(t == scaner._INT || t == scaner._DOUBLE  ){
                // Это объявление переменных
               scaner.setSavePoint(savePoint1);
                if( !this.declaration_of_variable())
                    return false;

            }
            else if(t == scaner._CONST){
                // Константа
               scaner.setSavePoint(savePoint1);
                if( !this.declaration_of_constant())
                    return false;
            }
            else if(t == scaner._ID || t == scaner._BRACE_OPEN ||  t == scaner._IF || t == scaner._SEMICOLON){
                // Оператор
               scaner.setSavePoint(savePoint1);
                if( !this.operator())
                    return false;
            }
            else{
                // eps Все нормас
            }
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);///////////////////////////////////////////////
           scaner.setSavePoint(savePoint1);///////////////////////////////////////////////////////////////////////////
        }
       scaner.setSavePoint(savePoint1);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        t = scaner.next(l);
        if(t != scaner._BRACE_CLOSE){
            scaner.printError("19ожидался символ '}'",l);
            return false;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem18
        semantic.sem18(k);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem18
        return true;
    }


    // Оператор
    public boolean operator(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("operator");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        if( t == scaner._ID){
            //scaner.setUk(uk1); scaner.setSavePoint(savePoint1);

            t = scaner.next(l);
            if( t == scaner._ASSIGN){
                // Присваивание
               scaner.setSavePoint(savePoint1);
                if( !this.assignment())
                    return false;
            }
            else if(t == scaner._PARENTHESIS_OPEN){
                // Вызов функции
               scaner.setSavePoint(savePoint1);

                if( !this.func_call(new Container()))   //////////////////////////////////??????????????????????????????????
                    return false;
                ////////////////////////////////
                //////////////проверка на ; ////
                ////////////////////////////////
                t = scaner.next(l);
                if( t != scaner._SEMICOLON){
                    scaner.printError("33ожидался символ ';'",l);
                    return false;
                }
            }else {
                /////////////////////////////////////////////////////////////////////////////////// ошибка с ele
                scaner.printError("37 Ожидался символ = или (",l);
                return false;
            }
        }
        else if(t == scaner._BRACE_OPEN){
            scaner.setSavePoint(savePoint1);
            // Составной оператор
            if ( !this.compound_operator())
                return false;
        }
        else if( t == scaner._IF){
            scaner.setSavePoint(savePoint1);
            // IF
            if( !this.func_if())
                return false;
        }
        else if( t == scaner._SEMICOLON){
            // ;

        }
        else {
            scaner.printError("20Ошибка",l);
            return false;
        }
        return true;
    }


    // присваивание
    public boolean assignment(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("assignment");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;
        Container containerT = new Container();
        Container containerG = new Container();

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        if( t != scaner._ID){
            scaner.printError("21Ожидался идентификатор",l);
            return false;
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem5
        Tree k = semantic.sem5Assign(l, containerT);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem5
        t = scaner.next(l);
        if( t != scaner._ASSIGN){
            scaner.printError("22Ожидался символ '='",l);
            return false;
        }

        if( !this.expression(containerG))  // выражение
            return false;

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem3
        // Проверяем приводимость типов
        semantic.sem3(containerT,containerG, l);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem3

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// semParamDeclared
        // Устанавливаем флаг, что переменная определена
        semantic.semParamDeclared(k,containerG, l);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// semParamDeclared

        t = scaner.next(l);
        if( t != scaner._SEMICOLON){
            scaner.printError("23Ожидался символ ';'",l);
            return false;
        }
        return true;
    }
    // вызов функции
    public boolean func_call(Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("function_call");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        Container containerG = new Container();

//        type = scaner.next(l);
//        if( type != scaner._INT && type != scaner._DOUBLE){
//            scaner.printError("24Ожидался тип int / double", l);
//            return false;
//        }


        t = scaner.next(l);
        if( t != scaner._ID){
            scaner.printError("25ожидался идентификатор", l);
            return false;
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem5
        Tree k;
        k = semantic.sem5func(l, container);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem5
        t = scaner.next(l);
        if( t != scaner._PARENTHESIS_OPEN){
            scaner.printError("26Ожидался символ '('", l);
            return false;
        }


//        type = scaner.next(l);
//        if( !this.lis_of_parametr())
//            return false;
        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        scaner.setSavePoint(savePoint1);

        int countParam = 0;
        if( t != scaner._PARENTHESIS_CLOSE){
            // Значит там выражения через запятую т.е. параметры функции
            do{
                if(  !this.expression(containerG))
                    return false;
                countParam++;
                //////////////////////////////////////////////////////////////////////////////////////////////////////// semCheckType
                semantic.semCheckType(countParam, k, containerG, l);
                //////////////////////////////////////////////////////////////////////////////////////////////////////// semCheckType
                savePoint1 = scaner.getSavePoint();
                t = scaner.next(l);

            }while (t == scaner._COMMA );
           scaner.setSavePoint(savePoint1);
        }
        else {
            // Иначе это тупо закрывающаяся скобка, т.е. вызывается функция без параметров
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////// semCheckType
        semantic.semCheckCountParam(countParam, k , l);
        //////////////////////////////////////////////////////////////////////////////////////////////////////// semCheckType
        t = scaner.next(l);
        if( t != scaner._PARENTHESIS_CLOSE){
            scaner.printError("11ожидался символ ')'",l);
            return false;
        }
        return true;
        /////////////////////////
    }
    // if
    public boolean func_if(){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("function_call");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;
        Container containerT = new Container();
        Container containerG = new Container();

        t = scaner.next(l);
        if( t != scaner._IF  ){
            scaner.printError("28Ожидалось ключевое слово if", l);
            return false;
        }

        t = scaner.next(l);
        if( t != scaner._PARENTHESIS_OPEN  ){
            scaner.printError("29Ожидался символ '('", l);
            return false;
        }

        if( !this.expression(containerG))
            return false;

        t = scaner.next(l);
        if( t != scaner._PARENTHESIS_CLOSE  ){
            scaner.printError("30Ожидался символ ')'", l);
            return false;
        }

        if( !this.operator())
            return false;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        if( t == scaner._ELSE) {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if( !this.operator())/////////////////////////////////////////////////////////////////////////////////
                return false;
            //System.out.println();
        }else{
           scaner.setSavePoint(savePoint1);
        }


        return true;
    }


    // выражение
    public boolean expression(Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("expression");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        // type = scaner.next(l);
        if( !this.A2(container))
            return false;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        while (t == scaner._NOT_EQUALLY || t == scaner._EQUALLY){
            Container containerG = new Container();
            if( !A2(containerG))
                return false;
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////// sem6
            container.type = semantic.sem6(container, containerG, t, l);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }
       scaner.setSavePoint(savePoint1);

        return true;
    }

    public boolean A2(Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("A2");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        //type = scaner.next(l);
        if( !this.A3(container))
            return false;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        while (t == scaner._GREAT|| t == scaner._LESS || t == scaner._GREAT_EQUALLY || t == scaner._LESS_EQUALLY){
            Container containerG = new Container();
            if ( !this.A3(containerG))
                return false;
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////// sem6
            container.type = semantic.sem6(container, containerG, t, l);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
              savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }
       scaner.setSavePoint(savePoint1);
        return true;
    }

    public boolean A3(Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("A3");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        //type = scaner.next(l);
        if ( !this.A4(container))
            return false;

          savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        while (t == scaner._SHIFT_LEFT|| t == scaner._SHIFT_RIGHT ){
            Container containerG = new Container();
            if( !this.A4(containerG))
                return false;
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////// sem6
            container.type = semantic.sem6(container, containerG, t, l);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }
       scaner.setSavePoint(savePoint1);
        return true;
    }
    public boolean A4( Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("A4");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        int uk1;
        SavePoint savePoint1 ;

        //type = scaner.next(l);
        if( !this.A5(container))
            return false;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        while (t == scaner._PLUS|| t == scaner._MINUS ){
            Container containerG = new Container();
            if( !this.A5(containerG))
                return false;
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////// sem6
            container.type = semantic.sem6(container, containerG, t, l);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }
       scaner.setSavePoint(savePoint1);
        return true;
    }

    public boolean A5(Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("A5");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        //type = scaner.next(l);
        if( !this.A6(container))
            return false;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);
        while (t == scaner._STAR || t == scaner._SLASH || t == scaner._PERCENT ){
            Container containerG = new Container();
            if( !this.A6(containerG))
                return false;
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////// sem6
            container.type = semantic.sem6(container, containerG, t, l);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
            savePoint1 = scaner.getSavePoint();
            t = scaner.next(l);
        }
        scaner.setSavePoint(savePoint1);

        return true;
    }

    public boolean A6( Container container){
        if(SHOW_NAME_NOT_TERMINAL) System.out.println("A6");
        ArrayList<Character> l = new ArrayList<>();
        int t ;
        SavePoint savePoint1 ;

        savePoint1 = scaner.getSavePoint();
        t = scaner.next(l);

        if( t == scaner._TYPE_CHAR  || t == scaner._TYPE_INT_8 || t == scaner._TYPE_INT_10 || t == scaner._TYPE_INT_16 ){
            // константа
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////// sem55
            semantic.sem55(t,container);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
            return true;
        }
        else if( t == scaner._PARENTHESIS_OPEN ){

            if( !this.expression(container))
                return false;
            t = scaner.next(l);
            if( t != scaner._PARENTHESIS_CLOSE){
                scaner.printError("31ожидался символ ')' ", l);
                return false;
            }
            return true;
        }
        else if (t == scaner._ID){
            // Либо вызов функции либо просто идентификатор
            SavePoint savePoint2 = scaner.getSavePoint();
            t = scaner.next(l);

            if( t == scaner._PARENTHESIS_OPEN){
                // вызов функции
               scaner.setSavePoint(savePoint1);
                if( !this.func_call(container))
                    return false;

            }
            else {
               scaner.setSavePoint(savePoint1);
               t = scaner.next(l);
                // идентификатор
                ///////////////////////////////////////////////////////////////////////////////////////////////////////// sem 5
                semantic.sem5(l, container);
                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                return true;
            }
        }
        else {
            scaner.printError("32Ожидался идентификатор / константа / символ '(' ",l);
            return false;
        }
        return true;
    }
}
