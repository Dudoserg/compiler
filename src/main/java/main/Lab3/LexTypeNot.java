package main.Lab3;


public enum LexTypeNot {
    _программа("_программа"),
    _одно_описание("_одно_описание"),
    _объявление_переменных("_объявление_переменных"),
    _объявление_констант("_объявление_констант"),
    _функция("_функция"),
    _тип("_тип"),
    _список_переменных("_список_переменных"),
    _переменная("_переменная"),
    _список_параметров("_список_параметров"),
    _составной_оператор("_составной_оператор"),
    _составной_оператор_функции("_составной_оператор_функции"),
    _операторы_и_описания("_операторы_и_описания"),
    _один_оператор("_один_оператор"),
    _оператор("_оператор"),
    _присваивание("_присваивание"),
    _вызов_функции("_вызов_функции"),
    _список_констант("_список_констант"),
    _константа("_константа"),
    _параметры("_параметры"),
    _if("_if"),
    _else("_else"),


    _A1("_A1"),
    _A2("_A2"),
    _A3("_A3"),
    _A4("_A4"),
    _A5("_A5"),
    _A6("_A6"),
    _R1("_R1"),
    _R2("_R2"),
    _R3("_R3"),
    _R4("_R4"),
    _R5("_R5"),

    _W1("_W1"),
    _W2("_W2"),
    _W3("_W3"),
    _W4("_W4"),
    _W5("_W5"),
    _W6("_W6"),
    _W7("_W7"),
    _W8("_W8"),
    _W9("_W9"),
    _W10("_W10"),
    _W11("_W11"),
    _W12("_W12"),
    _W13("_W13"),

    _возврат("_возврат"),


    ;

    private String str;

    LexTypeNot(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
