package algoritm_1and2.maga;

import java.util.ArrayList;
import java.util.List;

public class RightPart {
	List<Elem> elemList;

	public RightPart() {
		this.elemList = new ArrayList<>();
	}


	public String print(){
		String result = "";
		for(Elem elem: this.elemList){
			result += elem.print() + " ";
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
