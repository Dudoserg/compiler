package main.Lab7;

import main.Lab4.TreeNext.*;
import main.Lab7.AsmCommands.AsmCommand;

import java.util.ArrayList;
import java.util.List;

public class Asm {

    private List<Register> poolRegister = new ArrayList<>();

    private static int asm_name_counter = 0;

    private List<AsmCommand> asmComandList = new ArrayList<>();
    private TreeNext treeNext;

    public Asm(TreeNext treeNext) {
        this.treeNext = treeNext;
        poolRegister.add(new Register("eax"));
        poolRegister.add(new Register("ebx"));
        poolRegister.add(new Register("ecx"));
        poolRegister.add(new Register("edx"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private NextNode lastFunc = null;
    private _NextNode_Func lastFuncNodeBase = null;

    /**
     * Тут мы обходим дерево и назначаем новые имена и адреса для объявленныъ переменных
     */
    public void renameVariableAsm() {
        this.renameVariableAsm_recursion(treeNext.root.right);
    }

    /**
     * Тут мы обходим дерево и назначаем новые имена и адреса для объявленныъ переменных
     *
     * @param currentNode вершина с которой начинается рекурсия
     */
    private void renameVariableAsm_recursion(NextNode currentNode) {
        // Если функция, то обновлляем счетчик переменных, для подсчета смещения каждой из локальных переменных
        if (currentNode.nodeBase instanceof _NextNode_Func) {
            _NextNode_DeclareVariable.asm_addr_counter = 0;
            lastFuncNodeBase = (_NextNode_Func) currentNode.nodeBase;
            lastFunc = currentNode;
        }
        if (currentNode.nodeBase instanceof _NextNode_FuncEnd) {
            lastFuncNodeBase.asm_countLocalVariable = _NextNode_DeclareVariable.asm_addr_counter;
            lastFuncNodeBase = null;
            lastFunc = null;
        }
        // Если это локальная переменная функции, то назначаем ей новое имя, и смещение( относительно верхушки стека bp)
        else if (currentNode.nodeBase instanceof _NextNode_DeclareVariable) {
            final _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) currentNode.nodeBase;
            nodeBase.asm_len = 4;   // 4 бита, т.к. разрешено делать только один тип данных ( ну сделаю int)
            nodeBase.asm_addr = -((++_NextNode_DeclareVariable.asm_addr_counter) * nodeBase.asm_len);   // адрес(смещение)
            nodeBase.asm_name = nodeBase.lexem + "_" + (++Asm.asm_name_counter);    // новое название переменной

            if (this.lastFuncNodeBase != null)
                this.lastFuncNodeBase.localVariableList.add(currentNode);
        } else {

        }

        if (currentNode.left != null)
            this.renameVariableAsm_recursion(currentNode.left);
        if (currentNode.right != null)
            this.renameVariableAsm_recursion(currentNode.right);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    List<SectionData> sectionDataList = new ArrayList<>();
    /**
     * Создаем секцию с глобальными переменными
     */
    public void createSectionData() {
        createSectionData_recursion(this.treeNext.root);
    }

    private void createSectionData_recursion(NextNode currentNode) {

        // Если левый потомок присутствует
        if (currentNode.left != null) {
            // и если он объявление переменной
            NextNode node = currentNode.left;

            if (node.nodeBase instanceof _NextNode_DeclareVariable) {
                // заходим в него
                final _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) node.nodeBase;

                sectionDataList.add(new SectionData(node, nodeBase.lexem, "dd", nodeBase.currentValue));
            }
        }

        if(currentNode.left != null){
            NextNode node = currentNode.left;
            if( node.nodeBase instanceof _NextNode_Func){
                final String lexem = ((_NextNode_Func) node.nodeBase).lexem;
                if(lexem.equals("main"))
                    return;
            }
        }

        if (currentNode.right != null)
            createSectionData_recursion(currentNode.right);

    }

}
