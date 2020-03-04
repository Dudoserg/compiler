package maga;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static maga.ElemType.NOT_TERMINAL;
import static maga.ElemType.TERMINAL;

public class CreateGrammar {

	List<String> rows;
	List<Rule> rules;

	String firstGrammarName = "программа";

	public static void main(String[] args) {
		CreateGrammar createGrammar = new CreateGrammar();
	}

	public CreateGrammar() {
//		this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_text.txt");
		this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");

		this.initRules();

		List<Pair<Elem, Elem>> pairs = this.createPair();
		String result =
				pairs.stream()
						.map(elemElemPair ->  elemElemPair.getKey().print() + "\t" + elemElemPair.getValue().print() )
						.collect(Collectors.joining("\n"));

		System.out.println("Список пар = \n" + result);
	}

	private void initRules() {
		rules = new ArrayList<>();
		//      <#S#> -> a <#A#> <#S#> <#B#> | c
		for (String row : rows) {
			String[] arr = row.split("->");
			if (arr.length != 2)
				continue;
			Rule rule = new Rule();
			final List<Object> typeleft = ElemType.getType(arr[0]);
			rule.left = new Elem((String) typeleft.get(0), (ElemType) typeleft.get(1));

			String[] rightParts = arr[1].split("\\|");
			//    a <#A#> <#S#> <#B#>       ,       c
			for (String rightPart : rightParts) {
				String[] elems = rightPart.split(" ");
				RightPart rightPart_object = new RightPart();
				// a    ,   <#A#>   ,   <#S#>   ,   <#B#>
				for (String elem : elems) {
					elem = elem.trim();
					if (elem.length() == 0)
						continue;
					final List<Object> type = ElemType.getType(elem);
					Elem elem_object = new Elem((String) type.get(0), (ElemType) type.get(1));
					rightPart_object.elemList.add(elem_object);
				}
				rule.parts.add(rightPart_object);
			}
			rules.add(rule);
		}
		System.out.print("");
	}

	public List<String> readFromFile(String pathToFIle) {
		List<String> row = new ArrayList<>();
		try {
			File file = new File(pathToFIle);
			//создаем объект FileReader для объекта File
			FileReader fr = new FileReader(file);
			//создаем BufferedReader с существующего FileReader для построчного считывания
			BufferedReader reader = new BufferedReader(fr);
			// считаем сначала первую строку
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				row.add(line);
				// считываем остальные строки в цикле
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;
	}


	public List<Pair<Elem, Elem>> createPair() {
		System.out.println("=============createPair=================\n\n");
		List<Pair<Elem, Elem>> pairs = new ArrayList<>();
		pairs.add(new Pair<>(new Elem("#", TERMINAL), new Elem(this.firstGrammarName, NOT_TERMINAL)));
		pairs.add(new Pair<>(new Elem(this.firstGrammarName, NOT_TERMINAL), new Elem("#", TERMINAL)));

		String collect =
				pairs.stream()
						.map(elemElemPair -> elemElemPair.getKey().print() + "_" + elemElemPair.getValue().print())
						.collect(Collectors.joining("\t"));
		String newCollect = "";

		int countIteration = 0;
		while (true){

			pairs = this.changeTerminalToRight(pairs);
			newCollect =
					pairs.stream()
							.map(elemElemPair -> elemElemPair.getKey().print() + "_" + elemElemPair.getValue().print())
							.collect(Collectors.joining("\t"));
			if( collect.compareTo(newCollect) == 0)
				break;
			countIteration++;
			collect = newCollect;
		}
		System.out.println("countIteration = " + countIteration);
		return pairs;
	}

	public List<Pair<Elem, Elem>> changeTerminalToRight(List<Pair<Elem, Elem>> pairs) {

		List<RightPart> rightPartList = new ArrayList<>();

		for (Pair<Elem, Elem> pair : pairs) {
			List<RightPart> tmp = new ArrayList<>();

			Elem first = pair.getKey();
			Elem second = pair.getValue();

			// В этот массив пишем все замены наших терминалов на их правые части
			if (first.elementType.equals(NOT_TERMINAL)) {
				// Первый символ нетерминал, подставляем вместо него, его правые части
				Rule rule = this.findRowByLeft(first);

				// заменяем нетерминал всеми его правыми частями
				for (RightPart rightPart : rule.parts) {
					RightPart rightPart_copy = rightPart.copy();
					rightPart_copy.elemList.add(second.copy());

					tmp.add(rightPart_copy);
				}

				System.out.print("");
			}
			if (second.elementType.equals(NOT_TERMINAL)) {
				Rule rule = this.findRowByLeft(second);

				// заменяем нетерминал всеми его правыми частями
				for (RightPart rightPart : rule.parts) {
					RightPart rightPart_copy = rightPart.copy();
					rightPart_copy.elemList.add(0, first.copy());

					tmp.add(rightPart_copy);
				}

				System.out.print("");
			}
			rightPartList.addAll(tmp);
			System.out.print(first.str + "_" + second.print() + "\t->\t");
			tmp.forEach(rightPart -> System.out.print(rightPart.print() + "| "));
			System.out.println();


		}
		System.out.println("");


		// Теперь полученные правые части надо разбить на пары
		// Хотя бы один символ в такой паре должен быть нетерминальным.
		List<Pair<Elem, Elem>> result = new ArrayList<>();
		for (RightPart rightPart : rightPartList) {
			for (int i = 0; i < rightPart.elemList.size() - 1; ++i) {
				if (rightPart.elemList.get(i).elementType.equals(NOT_TERMINAL) ||
						rightPart.elemList.get(i + 1).elementType.equals(NOT_TERMINAL)) {
					result.add(new Pair<>(rightPart.elemList.get(i), rightPart.elemList.get(i + 1)));
				}
			}
		}
		result.forEach(elemElemPair -> System.out.print(elemElemPair.getKey().print() + "_" + elemElemPair.getValue().print() + "\t"));
		System.out.println("\n\n");

		result.add(0, pairs.get(1));
		result.add(0, pairs.get(0));
		// Ищем повторения
		for (int i = result.size() - 1; i >= 0; --i) {
			Pair<Elem, Elem> pair = result.get(i);
			for (int j = 0; j < i; j++) {
				if (result.get(j).getKey().str.compareTo(pair.getKey().str) == 0 &&
						result.get(j).getValue().str.compareTo(pair.getValue().str) == 0) {
					result.remove(pair);
					break;
				}
			}
		}

		System.out.print("result = { ");
		result.forEach(elemElemPair -> System.out.print(elemElemPair.getKey().print() + "_" + elemElemPair.getValue().print() + "\t\t"));
		System.out.print(" }");
		System.out.println("\n\n");

		return result;
	}

	public Rule findRowByLeft(Elem left) {
		for (int i = 0; i < this.rules.size(); i++) {
			if (this.rules.get(i).left.str.equals(left.str))
				return this.rules.get(i);
		}
		try {
			throw new Exception("Чета не то дядя {\t" + left.print() + "\t}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
