package main.algoritm_1and2.maga;

import lombok.Getter;
import lombok.Setter;
import main.Lab2.LexType;

import java.util.ArrayList;
import java.util.List;


public class RightPart {
	public List<Elem> elemList;

	public RightPart() {
		this.elemList = new ArrayList<>();
	}


	public String print(){
		String result = "";
		for(Elem elem: this.elemList){
			result += elem.getStrByType() + " ";
		}
		return result;
	}

	public RightPart copy(){
		List<Elem> tmp = new ArrayList<>();
		for(Elem elem: this.elemList){
			tmp.add(elem.copy());
		}
		RightPart result = new RightPart();
		result.elemList = tmp;
		return result;
	}
}
