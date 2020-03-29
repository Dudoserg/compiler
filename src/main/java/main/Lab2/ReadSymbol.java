package main.Lab2;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadSymbol {
    /**
     * Тип, если терминал то LLK._XXX \ иначе Scaner._
     */
    public Integer typ;

    /**
     * Лексема в виде строки, для вывода в дебаге
     */
    public String lexString;

    public ReadSymbol(Integer typ, List<Character> lexString) {
        this.typ = typ;
        this.lexString = lexString.stream().map(Object::toString).collect(Collectors.joining());
    }
}
