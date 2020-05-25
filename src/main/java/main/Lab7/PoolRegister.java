package main.Lab7;

import java.util.ArrayList;
import java.util.List;

public class PoolRegister {
    public List<Register> poolRegister = new ArrayList<Register>();

    public Register eax = new Register("eax");
    public Register ebx = new Register("ebx");
    public Register ecx = new Register("ecx");
    public Register edx = new Register("edx");

    public Register ebp = new Register("ebp");
    public Register esp = new Register("esp");


    public Register getRegister(String name) throws Exception {
        for(int i = 0 ; i < poolRegister.size(); i++){
            final Register register = poolRegister.get(i);
            if (register.name.equals(name))
                return register;
        }
        throw new Exception("Нет такого регистра, ты ошибся");
    }

    public void add(Register r){
        this.poolRegister.add(r);
    }
}
