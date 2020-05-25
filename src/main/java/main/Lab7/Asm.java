package main.Lab7;

import main.Lab4.TreeNext.*;
import main.Lab4.Triad;
import main.Lab4.TriadsByType.Triad_Endp;
import main.Lab4.TriadsByType.Triad_Proc;
import main.Lab4.TriadsByType.Triad_Prolog;
import main.Lab7.AsmCommands.*;
import main.Lab7.AsmCommands.infoArea.IMM;
import main.Lab7.AsmCommands.infoArea.InfoArea;
import main.Lab7.AsmCommands.infoArea.REG;

import java.util.ArrayList;
import java.util.List;

public class Asm {
    private TreeNext treeNext;
    public List<NextNode_Triad> listTriads;

    PoolRegister poolRegister = new PoolRegister();


    private static int asm_name_counter = 0;

    private List<_AsmCommand> asmComandList = new ArrayList<>();

    public Asm(TreeNext treeNext) {
        this.treeNext = treeNext;
        this.listTriads = this.treeNext.listTriads;
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

        if (currentNode.left != null) {
            NextNode node = currentNode.left;
            if (node.nodeBase instanceof _NextNode_Func) {
                final String lexem = ((_NextNode_Func) node.nodeBase).lexem;
                if (lexem.equals("main"))
                    return;
            }
        }

        if (currentNode.right != null)
            createSectionData_recursion(currentNode.right);

    }

    private String printSectionData() {
        String result = "";
        System.out.println("section .data");
        result += "section .data" + "\n";
        for (SectionData row : this.sectionDataList) {
            String tmp = "    " + row.variable_str + ": " + row.type_str + " " + row.value_str;
            result += tmp + "\n";
            System.out.println(tmp);
        }
        result += "\n";
        System.out.println();
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private List<NextNode_Triad> getGlobalActionList() {
        List<NextNode_Triad> globalActionList = new ArrayList<>();

        boolean findFirstFunction = false;
        for (NextNode_Triad listTriad : listTriads) {
            if (listTriad.triad.triad_base instanceof Triad_Proc)
                break;
            globalActionList.add(listTriad);
        }
        return globalActionList;
    }

    private List<List<NextNode_Triad>> getFunctionList() {
        List<List<NextNode_Triad>> list = new ArrayList<>();

        boolean inFunc = false;
        for (NextNode_Triad listTriad : listTriads) {
            if (listTriad.triad.triad_base instanceof Triad_Proc) {
                inFunc = true;
                list.add(new ArrayList<>());
            }
            if (inFunc) {
                final List<NextNode_Triad> currentFuncList = list.get(list.size() - 1);
                currentFuncList.add(listTriad);
            }
            if (listTriad.triad.triad_base instanceof Triad_Endp)
                inFunc = false;
        }
        return list;
    }


    List<SectionData> sectionTextList = new ArrayList<>();

    public void createAsmFile() throws Exception {
        System.out.println();
        String tmp;
        String str = printSectionData();

        tmp = "section .text" + "\n";
        str += tmp;
        System.out.print(tmp);

        tmp = "    " + "global CMAIN" + "\n" + "\n";
        str += tmp;
        System.out.print(tmp);


        // Сначала делаем функции
        List<NextNode_Triad> globalActionList = this.getGlobalActionList();
        List<List<NextNode_Triad>> functionsList = this.getFunctionList();

        NextNode inFunc = null;
        for (List<NextNode_Triad> currentFuncList : functionsList) {
            for (int i = 0; i < currentFuncList.size(); i++) {
                final NextNode_Triad currentTriad = currentFuncList.get(i);

                final Triad triad = currentTriad.triad;

                if (triad.triad_base instanceof Triad_Proc) {
                    final Triad_Proc triad_base = (Triad_Proc) triad.triad_base;
                    _AsmCommand command = new AC_FuncLabel(triad_base.funcId, triad_base.node);
                    this.asmComandList.add(command);
                    inFunc = triad_base.node;
                }
                if (triad.triad_base instanceof Triad_Prolog) {
                    //  push ebp            ; сохраняем базу    +8
                    _AsmCommand push_ebp = new AC_Push(new REG(poolRegister.ebp));
                    this.asmComandList.add(push_ebp);
                    //  push ebx            ; сохраняем регистры    +12
                    _AsmCommand push_ebx = new AC_Push(new REG(poolRegister.ebx));
                    this.asmComandList.add(push_ebx);
                    //  push ecx            ;   +16
                    _AsmCommand push_ecx = new AC_Push(new REG(poolRegister.ecx));
                    this.asmComandList.add(push_ecx);
                    //  push edx            ;   +20
                    _AsmCommand push_edx = new AC_Push(new REG(poolRegister.edx));
                    this.asmComandList.add(push_edx);
                    //    mov ebp, esp        ; сохраняем указатель стека
                    InfoArea mov_to = new REG(poolRegister.ebp);
                    InfoArea mov_from = new REG(poolRegister.esp);
                    _AsmCommand mov = new AC_Mov(mov_to, mov_from);
                    this.asmComandList.add(mov);

                    // sub  esp, XXX        ; выделяем память под локальные переменные
                    // получаем количество локальных переменных
                    final _NextNode_Func currentFuncNodeBase = (_NextNode_Func) inFunc.nodeBase;
                    final int countLocalVariable = currentFuncNodeBase.localVariableList.size();
                    // Считаем количество байт
                    int sumByte = 0;
                    for (NextNode nextNode : currentFuncNodeBase.localVariableList) {
                        final _NextNode_DeclareVariable decl = (_NextNode_DeclareVariable) nextNode.nodeBase;
                        sumByte += decl.asm_len;
                    }
                    sumByte = (sumByte / 32);
                    sumByte++;
                    sumByte = sumByte * 32;
                    _AsmCommand sub =
                            new AC_Sub(
                                    new REG(poolRegister.esp),
                                    new IMM(sumByte)
                            );
                    this.asmComandList.add(sub);

                }
            }
        }

        System.out.print("");


        printSectionText();
    }

    private void printSectionText() throws Exception {
        String result = "";
        String tmp = "";
        for (_AsmCommand command : this.asmComandList) {
            tmp = command.get_STRING() + "\n";
            result += tmp;
            System.out.print(tmp);
        }
    }


}
