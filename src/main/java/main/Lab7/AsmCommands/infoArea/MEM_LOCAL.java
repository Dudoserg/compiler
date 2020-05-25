package main.Lab7.AsmCommands.infoArea;

import main.Lab7.Register;

// Область памяти (MEM)
//  [ebp-4]
public class MEM_LOCAL extends InfoArea {
                        // dword
    Register register;  // ebp
    Integer shift;      // -4
}
