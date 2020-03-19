package algoritm_2.maga;

import java.util.ArrayList;
import java.util.List;

public enum ElemType {
	TERMINAL,
	NOT_TERMINAL;


	public static  List<Object>  getType(String str) {
		List<Object> result = new ArrayList<>();
		str = str.trim();
		if (str.length() > 4 &&
				( str.substring(0, 2).equals("<#") && str.substring(str.length()-2, str.length()-0).equals("#>") ) ) {
			str = str.substring(2, str.length()-2);
			result.add(str);
			result.add(NOT_TERMINAL);
		} else {
			result.add(str.trim());
			result.add(TERMINAL);
		}
		return result;
	}

}
