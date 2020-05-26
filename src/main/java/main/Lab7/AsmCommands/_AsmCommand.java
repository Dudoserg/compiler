package main.Lab7.AsmCommands;

import javafx.util.Pair;
import main.Lab7.AsmCommands.infoArea.InfoArea;
import main.Lab7.AsmCommands.infoArea.InfoAreaType;

import java.util.List;

public class _AsmCommand {
    public String get_STRING() throws Exception {
        return "НЕ ПЕРЕОПРЕДЕЛИЛ";
    };

    public boolean check_OK(InfoArea first, InfoArea second, List<Pair<InfoAreaType, InfoAreaType>> rules) {
        boolean rules_OK = false;
        for (Pair<InfoAreaType, InfoAreaType> rule : rules) {
            // перебираем все правила и проверяем, чтобы наша ситуация была в доступных правилах
            final InfoAreaType firstType = rule.getKey();
            final InfoAreaType secondType = rule.getValue();

            if (first.getType().equals(firstType) && second.getType().equals(secondType)) {
                rules_OK = true;
                break;
            }
        }
        return rules_OK;
    }
    public boolean check_OK(InfoArea first, List<InfoAreaType> rules) {
        boolean rules_OK = false;

        for (InfoAreaType rule : rules) {
            // перебираем все правила и проверяем, чтобы наша ситуация была в доступных правилах
            if (first.getType().equals(rule)) {
                rules_OK = true;
                break;
            }
        }
        return rules_OK;
    }

    public void exception(String message, InfoArea first, InfoArea second) throws Exception {

        message += "\n" + first.getClass().toString() + "\t" + second.getClass().toString() + "\n\n";
        throw new Exception(message);
    }
    public void exception(String message, InfoArea first) throws Exception {

        message += "\n" + first.getClass().toString() + "\n\n";
        throw new Exception(message);
    }
}
