package main.Lab7.AsmCommands;

import javafx.util.Pair;
import main.Lab7.AsmCommands.infoArea.InfoArea;
import main.Lab7.AsmCommands.infoArea.InfoAreaType;

public class _AsmCommand {
    public String get_STRING() throws Exception {
        return "НЕ ПЕРЕОПРЕДЕЛИЛ";
    };

    public boolean check_OK(InfoArea first, InfoArea second) {
        boolean rules_OK = false;
        for (Pair<InfoAreaType, InfoAreaType> rule : AC_Add.rules) {
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
    public void exception(String message, InfoArea first, InfoArea second) throws Exception {

        message += "\n" + first.getClass().toString() + "\t" + second.getClass().toString() + "\n\n";
        throw new Exception(message);
    }
}
