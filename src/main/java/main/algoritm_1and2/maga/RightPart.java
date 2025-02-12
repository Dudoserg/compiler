package main.algoritm_1and2.maga;

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
			result += elem.getStrByType_SHARP() + " ";
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

	public boolean isContain(Elem checking){
		for (Elem elem : this.elemList) {
			if (elem.equals(checking))
				return true;
		}
		return false;
	}
}
