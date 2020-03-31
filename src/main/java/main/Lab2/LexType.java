package main.Lab2 ;

public enum LexType {
    _SSS_ ("_SSS_") ,
    _TYPE_INT_10 ("_TYPE_INT_10") ,
    _TYPE_INT_16 ("_TYPE_INT_16") ,
    _TYPE_INT_8 ("_TYPE_INT_8") ,
    _TYPE_CHAR ("_TYPE_CHAR") ,
    _ID ("_ID") ,
    _STAR ("_STAR") ,
    _SLASH ("_SLASH") ,
    _PERCENT ("_PERCENT") ,
    _PLUS ("_PLUS") ,
    _MINUS ("_MINUS") ,
    _SHIFT_LEFT ("_SHIFT_LEFT") ,              // <<
    _SHIFT_RIGHT ("_SHIFT_RIGHT") ,             // >>
    _GREAT ("_GREAT") ,
    _LESS ("_LESS") ,
    _GREAT_EQUALLY ("_GREAT_EQUALLY") ,
    _LESS_EQUALLY ("_LESS_EQUALLY") ,
    _EQUALLY ("_EQUALLY") ,
    _NOT_EQUALLY ("_NOT_EQUALLY") ,
    _ASSIGN ("_ASSIGN") ,                  // =
    _PARENTHESIS_OPEN ("_PARENTHESIS_OPEN") ,        // (
    _PARENTHESIS_CLOSE ("_PARENTHESIS_CLOSE") ,       // )
    _BRACE_OPEN ("_BRACE_OPEN") ,              // {
    _BRACE_CLOSE ("_BRACE_CLOSE") ,             // }
    _POINT ("_POINT") ,
    _COMMA ("_COMMA") ,                   //  ("") ,
    _SEMICOLON ("_SEMICOLON") ,               //  ("") ,
    _INT ("_INT") ,
    _DOUBLE ("_DOUBLE") ,
    _CONST ("_CONST") ,
    _IF ("_IF") ,
    _ELSE ("_ELSE") ,
    _MAIN ("_MAIN") ,
    _ERROR ("_ERROR") ,
    _END ("_END") ;

    LexType(String string) {
        this.string = string;
    }

    private String string;

    public String getString() {
        return string;
    }
}
