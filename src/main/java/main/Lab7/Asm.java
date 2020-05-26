package main.Lab7;

import jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants;
import main.Lab4.TreeNext.*;
import main.Lab4.Triad;
import main.Lab4.TriadsByType.*;
import main.Lab7.AsmCommands.*;
import main.Lab7.AsmCommands.infoArea.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Asm {
    public static Integer SHIFT_EBP_PARAM_VARIABLE = 12;
    public static String LEVEL_1_INDENT = "    ";   // отступ для кода асемблерного
    public static String LEVEL_2_INDENT = "        ";   // отступ для кода асемблерного

    private TreeNext treeNext;
    public List<NextNode_Triad> listTriads;

    PoolRegister poolRegister = new PoolRegister();


    private static int asm_name_counter = 0;

    private List<_AsmCommand> asmComandList = new ArrayList<>();

    private void addCommand(_AsmCommand asmCommand) throws Exception {
        this.asmComandList.add(asmCommand);
        String tmp = asmCommand.get_STRING() + "\n";
        System.out.print(tmp);
    }

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
    public void renameVariableAsm() throws Exception {
        this.renameVariableAsm_recursion(treeNext.root.right);
    }

    /**
     * Тут мы обходим дерево и назначаем новые имена и адреса для объявленныъ переменных
     *
     * @param currentNode вершина с которой начинается рекурсия
     */
    private int shift_LocalVariable = 0;
    private int shift_ParamVariable = 0;
    private boolean isLocalVariable = false;
    private boolean isParamVariable = false;

    private void renameVariableAsm_recursion(NextNode currentNode) throws Exception {
        // Если функция, то обновлляем счетчик переменных, для подсчета смещения каждой из локальных переменных
        if (currentNode.nodeBase instanceof _NextNode_Func) {
            lastFuncNodeBase = (_NextNode_Func) currentNode.nodeBase;
            lastFunc = currentNode;
            isParamVariable = true;
            isLocalVariable = false;
            shift_LocalVariable = 0;
            shift_ParamVariable = 0;
        }
        if (currentNode.nodeBase instanceof _NextNode_FuncEnd) {
            lastFuncNodeBase.asm_countLocalVariable = shift_LocalVariable;
            lastFuncNodeBase = null;
            lastFunc = null;

            isParamVariable = false;
            isLocalVariable = false;
            shift_LocalVariable = 0;
            shift_ParamVariable = 0;
        } else if(currentNode.nodeBase instanceof  _NextNode_StartLevel){
            if( isParamVariable && !isLocalVariable){
                isParamVariable = false;
                isLocalVariable = true;
            }
        }
        // Если это локальная переменная функции, то назначаем ей новое имя, и смещение( относительно верхушки стека bp)
        else if (currentNode.nodeBase instanceof _NextNode_DeclareVariable) {
            if (isLocalVariable && !isParamVariable) {
                final _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) currentNode.nodeBase;
                nodeBase.asm_len = 4;   // 4 бита, т.к. разрешено делать только один тип данных ( ну сделаю int)
                nodeBase.asm_addr = ++shift_LocalVariable * nodeBase.asm_len;   // адрес(смещение)
                nodeBase.asm_index = shift_LocalVariable;   // адрес(смещение)
                nodeBase.asm_name = nodeBase.lexem + "_" + (++Asm.asm_name_counter);    // новое название переменной

                if (this.lastFuncNodeBase != null)
                    this.lastFuncNodeBase.localVariableList.add(currentNode);
            }else if (!isLocalVariable && isParamVariable){
                final _NextNode_DeclareVariable nodeBase = (_NextNode_DeclareVariable) currentNode.nodeBase;
                nodeBase.asm_len = 4;   // 4 бита, т.к. разрешено делать только один тип данных ( ну сделаю int)
                nodeBase.asm_addr = ++shift_ParamVariable * nodeBase.asm_len;   // адрес(смещение)
                nodeBase.asm_index = shift_ParamVariable;
                nodeBase.asm_name = nodeBase.lexem + "_" + (++Asm.asm_name_counter);    // новое название переменной

                if (this.lastFuncNodeBase != null)
                    this.lastFuncNodeBase.localVariableList.add(currentNode);
            }
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
        List<Boolean> inFuncFreeLocalMemory_4byte = new ArrayList<>(); // количество "ячеек" по 4 байта
        int sumByteForLocalVariable = -1;
        for (List<NextNode_Triad> currentFuncList : functionsList) {
            for (int i = 0; i < currentFuncList.size(); i++) {
                final NextNode_Triad currentTriad = currentFuncList.get(i);

                final Triad triad = currentTriad.triad;


                if (triad.triad_base instanceof Triad_Proc) {
                    final Triad_Proc triad_base = (Triad_Proc) triad.triad_base;
                    _AsmCommand command = new AC_FuncLabel(triad_base.funcId, triad_base.node);
                    this.addCommand(command);
                    inFunc = triad_base.node;
                } else if (triad.triad_base instanceof Triad_Prolog) {
                    //  push ebp            ; сохраняем базу    +8
                    _AsmCommand push_ebp = new AC_Push(new REG(poolRegister.ebp));
                    this.addCommand(push_ebp);
                    //  push ebx            ; сохраняем регистры    +12
                    _AsmCommand push_ebx = new AC_Push(new REG(poolRegister.ebx));
                    this.addCommand(push_ebx);
                    //  push ecx            ;   +16
                    _AsmCommand push_ecx = new AC_Push(new REG(poolRegister.ecx));
                    this.addCommand(push_ecx);
                    //  push edx            ;   +20
                    _AsmCommand push_edx = new AC_Push(new REG(poolRegister.edx));
                    this.addCommand(push_edx);
                    //    mov ebp, esp        ; сохраняем указатель стека
                    InfoArea mov_to = new REG(poolRegister.ebp);
                    InfoArea mov_from = new REG(poolRegister.esp);
                    _AsmCommand mov = new AC_Mov(mov_to, mov_from);
                    this.addCommand(mov);

                    // sub  esp, XXX        ; выделяем память под локальные переменные
                    // получаем количество локальных переменных
                    final _NextNode_Func currentFuncNodeBase = (_NextNode_Func) inFunc.nodeBase;
                    final int countLocalVariable = currentFuncNodeBase.localVariableList.size();
                    // Считаем количество байт
                    sumByteForLocalVariable = 0;
                    for (NextNode nextNode : currentFuncNodeBase.localVariableList) {
                        final _NextNode_DeclareVariable decl = (_NextNode_DeclareVariable) nextNode.nodeBase;
                        sumByteForLocalVariable += decl.asm_len;
                    }
                    sumByteForLocalVariable = (sumByteForLocalVariable / 32);
                    sumByteForLocalVariable++;
                    sumByteForLocalVariable = sumByteForLocalVariable * 32;
                    _AsmCommand sub =
                            new AC_Sub(
                                    new REG(poolRegister.esp),
                                    new IMM(sumByteForLocalVariable)
                            );
                    this.addCommand(sub);
                    // помечаем, что память под локальные переменные занята
                    // остальную область будем использовать как временное хранилище под промежуточные вычисления
                    inFuncFreeLocalMemory_4byte = new ArrayList<>(Collections.nCopies(sumByteForLocalVariable / 4, true));
                    for (int w = 0; w < currentFuncNodeBase.localVariableList.size(); w++) {
                        inFuncFreeLocalMemory_4byte.set(w, false);
                    }
                } else if (triad.triad_base instanceof Triad_Epilog) {
                    //  add esp, 32   очищаем память от локальных переменных
                    InfoArea firstArea = new REG(poolRegister.esp);
                    InfoArea secondArea = new IMM(sumByteForLocalVariable);
                    _AsmCommand ac_add = new AC_Add(firstArea, secondArea, poolRegister, this.asmComandList);
                    //  pop edx
                    _AsmCommand pop_edx = new AC_Pop(new REG(poolRegister.edx));
                    this.addCommand(pop_edx);
                    //  pop ecx
                    _AsmCommand pop_ecx = new AC_Pop(new REG(poolRegister.ecx));
                    this.addCommand(pop_ecx);
                    //  pop ebx
                    _AsmCommand pop_ebx = new AC_Pop(new REG(poolRegister.ebx));
                    this.addCommand(pop_ebx);
                    //  pop ebp
                    _AsmCommand pop_ebp = new AC_Pop(new REG(poolRegister.ebp));
                    this.addCommand(pop_ebp);
                } else if (triad.triad_base instanceof Triad_Ret) {
                    //  ret;
                    _AsmCommand ret = new AC_Ret();
                    this.addCommand(ret);
                } else if (triad.triad_base instanceof Triad_Push_For_Return) {
                    final Triad_Push_For_Return triad_base = (Triad_Push_For_Return) triad.triad_base;
                    if (triad_base.node != null && triad_base.lexemStr != null &&
                            !triad_base.lexemStr.isEmpty() && triad_base.lexTypeTERMINAL != null) {
                        // Если переменная
                        System.out.print("");
                        // TODO
                    } else if (triad_base.lexemStr != null && !triad_base.lexemStr.isEmpty() && triad_base.lexTypeTERMINAL != null) {
                        // если константа
                        System.out.print("");
                        // TODO
                    } else if (triad_base.triad != null && triad_base.triad_index >= 0) {
                        // Если в стек кладем значение другой триады
                        System.out.print("");
                        // TODO
                    } else {
                        throw new Exception("это и не триада и не переменная и не константа!");
                    }
                } else if (triad.triad_base instanceof Triad_Math_Operation) {
                    final Triad_Math_Operation triad_base = (Triad_Math_Operation) triad.triad_base;
                    InfoArea firstArea = null;
                    InfoArea secondArea = null;
                    // left
                    {
                        if (triad_base.left_isNode) {
                            // Если переменная
                            // узнаем, глобальная она или локальная
                            final _NextNode_ID nodeBase_ID = (_NextNode_ID) triad_base.left_node.nodeBase;
                            final _NextNode_DeclareVariable nodeBase_Declare =
                                    (_NextNode_DeclareVariable) nodeBase_ID.nextNode.nodeBase;
                            if (nodeBase_Declare.isLocal) {
                                firstArea = new MEM_LOCAL(poolRegister.ebp, -nodeBase_Declare.asm_addr);
                            } else if (nodeBase_Declare.isParam) {
                                firstArea = new MEM_LOCAL(poolRegister.ebp, SHIFT_EBP_PARAM_VARIABLE + nodeBase_Declare.asm_addr);
                            } else if (nodeBase_Declare.isGlobal) {

                            }
                            // создаем нужный тип
                        } else if (triad_base.left_lexemStr != null && !triad_base.left_lexemStr.isEmpty() &&
                                triad_base.left_lexTypeTERMINAL != null) {
                            // если константа
                            firstArea = new IMM(triad_base.left_lexemStr);
                        } else if (triad_base.left_triad != null && triad_base.left_triad_index >= 0) {
                            // Если в стек кладем значение другой триады
                            //TODO Нужно добавить в объект триаду - ссылку на объект памяти где сейчас находится ее результат
                            firstArea = triad_base.left_triad.triad_base.result;
                        } else
                            throw new Exception("это и не триада и не переменная и не константа!");
                    }
                    // right
                    {
                        // создаем вторую область памяти (  переменная, константа или триада)
                        if (triad_base.right_isNode) {
                            // Если переменная
                            // узнаем, глобальная она или локальная
                            final _NextNode_ID nodeBase_ID = (_NextNode_ID) triad_base.right_node.nodeBase;
                            final _NextNode_DeclareVariable nodeBase_Declare =
                                    (_NextNode_DeclareVariable) nodeBase_ID.nextNode.nodeBase;
                            if (nodeBase_Declare.isLocal) {
                                secondArea = new MEM_LOCAL(poolRegister.ebp, -nodeBase_Declare.asm_addr);
                            } else if (nodeBase_Declare.isParam) {
                                secondArea = new MEM_LOCAL(poolRegister.ebp, SHIFT_EBP_PARAM_VARIABLE + nodeBase_Declare.asm_addr);
                            } else if (nodeBase_Declare.isGlobal) {

                            }
                            // создаем нужный тип
                        } else if (triad_base.right_lexemStr != null && !triad_base.right_lexemStr.isEmpty() &&
                                triad_base.right_lexTypeTERMINAL != null) {
                            // если константа
                            secondArea = new IMM(triad_base.right_lexemStr);
                        } else if (triad_base.right_triad != null && triad_base.right_triad_index >= 0) {
                            // Если в стек кладем значение другой триады
                            //TODO Нужно добавить в объект триаду - ссылку на объект памяти где сейчас находится ее результат
                            secondArea = triad_base.right_triad.triad_base.result;
                        } else
                            throw new Exception("это и не триада и не переменная и не константа!");
                    }

                    _AsmCommand asmCommand;
                    switch (triad.operation) {
                        case "+": {
                            final AC_Add ac_add = new AC_Add(firstArea, secondArea, poolRegister, this.asmComandList);
                            asmCommand = ac_add;
                            this.addCommand(asmCommand);
                            // выгружаем значение из регистра в локальную память
                            // выгружаем из результата сложения, в область памяти
                            //int shift = getShiftInFreeLocalMemory(inFuncFreeLocalMemory_4byte);
                            //AC_Mov ac_mov = new AC_Mov(new MEM_LOCAL(poolRegister.getRegister("ebp"), shift * 4), ac_add.first);
                            // запоминаем, где лежит результат
                            triad.triad_base.result = ac_add.first;

                            break;
                        }

                        case "=": {
                            System.out.println();
                            _AsmCommand mov = new AC_Mov(firstArea, secondArea);
                            this.addCommand(mov);
                        }
                        default: {
                            //throw new Exception("ты еще не реализовал данную триаду " + triad.operation);
                        }
                    }
                    // Освобождаем регист, если можем
                    if(secondArea.getType() == InfoAreaType.REG){
                        final Register register = ((REG) secondArea).register;
                        poolRegister.release(register);
                    }
                    System.out.print("");
                }else if (triad.triad_base instanceof Triad_PUSH) {
                    final Triad_PUSH triad_base = (Triad_PUSH) triad.triad_base;

                    InfoArea infoArea;
                    {
                        if (triad_base.left_isNode) {
                            // Если переменная
                            // узнаем, глобальная она или локальная
                            final _NextNode_ID nodeBase_ID = (_NextNode_ID) triad_base.left_node.nodeBase;
                            final _NextNode_DeclareVariable nodeBase_Declare =
                                    (_NextNode_DeclareVariable) nodeBase_ID.nextNode.nodeBase;
                            if (nodeBase_Declare.isLocal) {
                                firstArea = new MEM_LOCAL(poolRegister.ebp, -nodeBase_Declare.asm_addr);
                            } else if (nodeBase_Declare.isParam) {
                                firstArea = new MEM_LOCAL(poolRegister.ebp, SHIFT_EBP_PARAM_VARIABLE + nodeBase_Declare.asm_addr);
                            } else if (nodeBase_Declare.isGlobal) {

                            }
                            // создаем нужный тип
                        } else if (triad_base.left_lexemStr != null && !triad_base.left_lexemStr.isEmpty() &&
                                triad_base.left_lexTypeTERMINAL != null) {
                            // если константа
                            firstArea = new IMM(triad_base.left_lexemStr);
                        } else if (triad_base.left_triad != null && triad_base.left_triad_index >= 0) {
                            // Если в стек кладем значение другой триады
                            //TODO Нужно добавить в объект триаду - ссылку на объект памяти где сейчас находится ее результат
                            firstArea = triad_base.left_triad.triad_base.result;
                        } else
                            throw new Exception("это и не триада и не переменная и не константа!");
                    }
                }
//                tmp = this.asmComandList.get(this.asmComandList.size() - 1).get_STRING() + "\n";
//                System.out.print(tmp);
            }

        }

        System.out.print("");


        printSectionText();
    }

    private int getShiftInFreeLocalMemory(List<Boolean> inFuncFreeLocalMemory_4byte) {
        int shift = -999;
        for (int i = 0; i < inFuncFreeLocalMemory_4byte.size(); i++) {
            if (inFuncFreeLocalMemory_4byte.get(i) == true) {
                shift = i + 1;
                break;
            }
        }
        // значит памяти не хватило
        if (shift < 0) {
            // выделим еще 32 байта
            for (int i = 0; i < 32 / 4; i++) {
                inFuncFreeLocalMemory_4byte.add(true);
            }
            return getShiftInFreeLocalMemory(inFuncFreeLocalMemory_4byte);
        } else {
            return shift;
        }
    }

    private String printSectionText() throws Exception {
        String result = "";
        String tmp = "";
        for (_AsmCommand command : this.asmComandList) {
            tmp = command.get_STRING() + "\n";
            result += tmp;
            //System.out.print(tmp);
        }
        return result;
    }


}
