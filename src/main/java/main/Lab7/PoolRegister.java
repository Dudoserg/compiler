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

    public PoolRegister() {
        this.poolRegister.add(eax);
        this.poolRegister.add(ebx);
        this.poolRegister.add(ecx);
        this.poolRegister.add(edx);
    }

    public Register getRegister(String name) throws Exception {
        for (int i = 0; i < poolRegister.size(); i++) {
            final Register register = poolRegister.get(i);
            if (register.name.equals(name))
                return register;
        }
        throw new Exception("Нет такого регистра, ты ошибся");
    }

    public Register getFree() throws Exception {
        if (poolRegister.size() >= 1) {
            final Register register = poolRegister.get(poolRegister.size() - 1);
            poolRegister.remove(register);
            return register;
        } else
            throw new Exception("Нет свободных регистров");
    }
    public void release(Register register){
        this.poolRegister.add(register);
    }

    public void add(Register r) {
        this.poolRegister.add(r);
    }
}
