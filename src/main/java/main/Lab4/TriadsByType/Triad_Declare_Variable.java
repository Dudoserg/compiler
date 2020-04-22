package main.Lab4.TriadsByType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Lab2.LexTypeTERMINAL;
import main.Node;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Triad_Declare_Variable extends _Triad_Base{
    LexTypeTERMINAL lexTypeTERMINAL;
    String variableId;
    Node node;
}
