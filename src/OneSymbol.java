public class OneSymbol {
    /**
     * Терминал или нет
     */
    public boolean isTerminal;

    /**
     * Тип, если терминал то LLK._XXX \ иначе Scaner._
     */
    public Integer typ;

    /**
     * Лексема в виде строки, для вывода в дебаге
     */
    public String lexString;

    public OneSymbol(boolean isTerminal, Integer typ, String lexString) {
        this.isTerminal = isTerminal;
        this.typ = typ;
        this.lexString = lexString;
    }
}
