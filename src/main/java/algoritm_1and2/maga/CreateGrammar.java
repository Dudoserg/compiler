package algoritm_1and2.maga;

import algoritm_1and2.csv.EXCEL;
import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static algoritm_1and2.maga.ElemType.NOT_TERMINAL;
import static algoritm_1and2.maga.ElemType.TERMINAL;

public class CreateGrammar {

	private static <T> List<T> reversedView(final List<T> list) {
		return new AbstractList<T>() {
			@Override
			public T get(int index) {
				return list.get(list.size() - 1 - index);
			}

			@Override
			public int size() {
				return list.size();
			}
		};
	}

	// Считанные с файла строки
	List<String> rows;
	// строки в виде объектов
	List<Rule> rules;

	List<Relations> relations = new ArrayList<>();

	//	HashMap<String, String> relations_Hashmap = new HashMap<String, String>();
	Map<MyPair<Elem, Elem>, List<Relations>> relations_Hashmap = new HashMap<MyPair<Elem, Elem>, List<Relations>>();

	// Все пары
	List<Pair<Elem, Elem>> pairs;

	public static String firstGrammarName = "программа";

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		CreateGrammar createGrammar = new CreateGrammar();
		long finish = System.currentTimeMillis();
		System.out.println("\n\n\ntime = " + (finish - start) / 1000.0 + " s.");
	}

	public CreateGrammar() throws IOException {
		int algoritm = 1;
		switch (algoritm) {
			case 1: {
				//		this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_text.txt");
//		CreateGrammar.firstGrammarName = "S";
				this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");
				CreateGrammar.firstGrammarName = "S";

				this.initRules();

				this.pairs = this.createPair();
				this.removeElemWithLastPlace(this.pairs);

//		String result =
//				pairs.stream()
//						.map(elemElemPair -> elemElemPair.getKey().print() + "\t" + elemElemPair.getValue().print())
//						.collect(Collectors.joining("\n"));
//
				String result = "";
				for (int i = 0; i < pairs.size(); i++) {
					String tmp = "";
					final Pair<Elem, Elem> pair = pairs.get(i);
					if (pair.getKey().elementType.equals(TERMINAL)) {
						tmp += pair.getKey().str;
					} else {
						tmp += "<# " + pair.getKey().str + " #>";
					}
					tmp += "\t";
					if (pair.getValue().elementType.equals(TERMINAL)) {
						tmp += pair.getValue().str;
					} else {
						tmp += "<# " + pair.getValue().str + " #>";
					}
					tmp += "\n";
					result += tmp;
				}

				System.out.println("Список пар = \n" + result);
				System.out.println("\n\n\t\tСписок пар закончился\n\n");

				System.out.println("===========================================================");
				System.out.println("Отношения Great \n");
				Great();
				System.out.println("\nОтношения Great кончились \n\n");


				System.out.println("===========================================================");
				System.out.println("Отношения Less \n");
				Less();
				System.out.println("\nОтношения Less кончились \n\n");

				System.out.println("===========================================================");
				System.out.println("Отношения Equals \n");
				Equals();
				System.out.println("\nОтношения Equals кончились \n\n");

				//System.out.println("\n\nСписок отношений = \n");
				//this.relations.forEach(elem -> System.out.println(elem.leftElem.print() + " " + elem.sign.getStr() + " " + elem.rightElem.print()));

				this.createHashMap(algoritm);
				break;
			}
			case 2: {
				this.rows = this.readFromFile(System.getProperty("user.dir") + "/grammar_change.txt");
				this.firstGrammarName = "S";

				this.initRules();

				this.pairs = this.createPair();

				this.removeElemWithLastPlace(this.pairs);


				// ALGORITM  2
				// <    >
				List<Relations> alg2 = new ArrayList<>();
				int countExceptions = 0;
				for (Pair<Elem, Elem> pair : pairs) {
					final Elem key = pair.getKey();
					final Elem value = pair.getValue();
					// a    <   <#B#>
					if (key.elementType.equals(TERMINAL) && value.elementType.equals(NOT_TERMINAL)) {
						// ищем правило
						final Rule rule = this.findRowByLeft(value);
						// перебираем правые части правила, и ищем его ПЕРВЫЙ терминал
						for (RightPart part : rule.parts) {
							Elem firstTerminal = null;
							for (Elem elem : part.elemList) {
								// Если терминал
								if (elem.elementType.equals(TERMINAL)) {
									firstTerminal = elem;
									break;
								}
							}
							// Теперь мы знаем первый нетерминал
							if (firstTerminal != null) {
								alg2.add(new Relations(key, firstTerminal, Sign.LESS));
								if (key.str.equals("}") && firstTerminal.str.equals("int"))
									System.out.println();
							}
						}
					} else
						// <#B#>    >   a
						if (key.elementType.equals(NOT_TERMINAL) && value.elementType.equals(TERMINAL)) {
							// ищем правило
							final Rule rule = this.findRowByLeft(key);
							// перебираем правые части правила, и ищем его ПОСЛЕДНИЙ терминал
							for (RightPart part : rule.parts) {
								Elem lastTerminal = null;

								for (Elem elem : reversedView(part.elemList)) {
									// Если терминал
									if (elem.elementType.equals(TERMINAL)) {
										lastTerminal = elem;
										break;
									}
								}
								// Теперь мы знаем первый нетерминал
								if (lastTerminal != null) {
									alg2.add(new Relations(lastTerminal, value, Sign.GREAT));
									if (lastTerminal.str.equals("}") && value.str.equals("int"))
										System.out.println();
								}
							}
						} else {
							countExceptions++;
							String errorMessage =
									"TERMINAL & TERMINAL || NOT_TERMINAL & NOT_TERMINAL" + "\t\t" + key.str + "\t" + value.str;
							System.out.println(errorMessage);
						}

				}

				// =
				for (Rule rule : this.rules) {
					for (RightPart part : rule.parts) {


						// делаем копию правила из правой части
						List<Elem> copy_elemList = new ArrayList<>(part.elemList);
						// удаляем все нетерминалы
						copy_elemList = copy_elemList.stream()
								.filter(elem -> elem.elementType.equals(TERMINAL))
								.collect(Collectors.toList());

						// Если хотя бы два элемента
						if (copy_elemList.size() >= 2) {
							for (int i = 0; i < copy_elemList.size() - 1; i++) {
								alg2.add(
										new Relations(
												copy_elemList.get(i),
												copy_elemList.get(i + 1),
												Sign.EQUALS
										)
								);
							}
						}

					}
				}

				alg2 = alg2.stream().distinct().collect(Collectors.toList());
				final String s = this.pairsToString(alg2);
				System.out.println("==================sqs=======================");
				System.out.println(s);
				System.out.println("=========================================");
				this.relations = alg2;

				this.createHashMap(algoritm);
			}
		}

	}


	/**
	 * Удалим из расмотрения правила bS (если b во всех правилах только на конце)
	 *
	 * @param pairs
	 */
	private void removeElemWithLastPlace(List<Pair<Elem, Elem>> pairs) {

		// Boolean  - true - значит везде последний
		List<Elem> set_list = new ArrayList<>();
		{
			Set<Elem> set = new HashSet<>();
			set.add(new Elem("#", TERMINAL));
			//
			for (int i = 0; i < this.rules.size(); i++) {
				final Rule rule = this.rules.get(i);
				set.add(rule.left);

				for (RightPart part : rule.parts) {
					for (Elem elem : part.elemList) {
						set.add(elem);
					}
				}
			}

			for (Elem elem : set) {
				if (elem.elementType.equals(TERMINAL))
					set_list.add(elem);
			}
		}

		List<Elem> elemAlwaysLast = new ArrayList<>();
		for (Elem elem : set_list) {
			if (!elem.str.equals("#"))
				elemAlwaysLast.add(elem.copy());
		}

		// Ищем ТЕРМИНАЛЫ которые всегда на последнем месте
		for (Rule rule : this.rules) {
			for (RightPart part : rule.parts) {
				Elem endELem = part.elemList.get(part.elemList.size() - 1);    // последний элемент
				Elem lastElem = endELem; // последний рассматриваемый элемент
				for (int i = part.elemList.size() - 2; i >= 0; i--) {
					Elem current = part.elemList.get(i);
					// если текущий рассматриваемый элемент не равен последнему, значит он уже точно не последний
					// A d a d , тут a != d, значит a не последний
					if (!current.str.equals(endELem.str)) {
						elemAlwaysLast.remove(current);
					} else {
						if (current.equals(lastElem)) {
							// A d a d d ,
							// current d(-2), lastElem = d(-1), endElem = d(-1)
							// тут current = endElem
							// тут current = lastElem в этом случае current последний

						} else {
							// ============
							// A d a d d ,
							// current d(-4), lastElem = a(-3), endElem = d(-1)
							// тут current = endElem
							// тут current != lastElem в этом случае current НЕ последний
							elemAlwaysLast.remove(current);
						}
					}
					lastElem = current;
				}
			}
		}

		for (int i = pairs.size() - 1; i >= 0; i--) {
			Pair<Elem, Elem> currentPair = pairs.get(i);
			Elem left = currentPair.getKey();
			Elem right = currentPair.getValue();
			// гавнокод, обход каждый раз это конечно дно, но мне впадлу делать нормально
			for (Elem lastElem : elemAlwaysLast) {
				if (lastElem.equals(left)) {
					pairs.remove(currentPair);
					break;
				}
			}
		}
		System.out.println();
	}


	private void createHashMap(int algoritm) throws IOException {
		//Map<MyPair<Elem,Elem>, List<Relations>> relations_Hashmap = new HashMap<>();
		Set<Elem> set = new HashSet<>();
		set.add(new Elem("#", TERMINAL));
		//
		for (int i = 0; i < this.rules.size(); i++) {
			final Rule rule = this.rules.get(i);
			set.add(rule.left);

			for (RightPart part : rule.parts) {
				for (Elem elem : part.elemList) {
					set.add(elem);
				}
			}
		}
		for (Elem elem : set) {
			for (Elem elem1 : set) {
				this.relations_Hashmap.put(new MyPair<>(elem, elem1), new ArrayList<>());
			}
		}

		for (int i = 0; i < this.relations.size(); ++i) {
			final Relations relations = this.relations.get(i);

			MyPair<Elem, Elem> elemElemMyPair = new MyPair<>(relations.leftElem, relations.rightElem);
			// Ищем ячейку
			List<Relations> cell = this.relations_Hashmap.get(elemElemMyPair);
			cell.add(relations);
		}

		EXCEL.printTable(set, this.relations_Hashmap, algoritm);
	}


	private void Great() {
		for (Pair<Elem, Elem> pair : this.pairs) {
			Elem first = pair.getKey();
			Elem second = pair.getValue();
			// Нетерминал - терминал
			if (first.elementType.equals(NOT_TERMINAL) && second.elementType.equals(TERMINAL)) {
				// ищем ЧЕМ ЗАКАНЧИВАЮТСЯ правые части нетерминала
				List<Elem> lastElem = new ArrayList<>();

				final Rule rule = this.findRowByLeft(first);
				for (RightPart rightPart : rule.parts) {
					if (rightPart.elemList.size() == 0) {
						try {
							throw new Exception(" rightPart.elemList.size() == 0 ");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						lastElem.add(rightPart.elemList.get(rightPart.elemList.size() - 1));
					}
				}

				System.out.print(this.printPair(pair) + ":\t{");
				for (int i = 0; i < lastElem.size(); i++) {
					Elem elem = lastElem.get(i);
					Relations relations = new Relations(elem, second, Sign.GREAT);
					this.relations.add(relations);
					if (i != lastElem.size() - 1)
						System.out.print(elem.print() + ", ");
					else
						System.out.print(elem.print());
					//System.out.println(relations.leftElem.print() + " " + relations.sign.getStr() + " " + relations.rightElem.print());
				}
				System.out.print("}\tили\t");
				for (int i = 0; i < lastElem.size(); i++) {
					Elem elem = lastElem.get(i);
					System.out.print(elem.print() + " > " + second.str);
					if (i != lastElem.size() - 1)
						System.out.print(", ");
				}

				System.out.print("\n");
			}
		}
	}


	private void Less() {
		// TODO При этом, если символ b во всех правилах грамматики, где он встречается,
		//  стоит последним, то отношение < становится бессмысленным

		for (Pair<Elem, Elem> pair : this.pairs) {
			Elem first = pair.getKey();
			Elem second = pair.getValue();
			// Нетерминал - терминал
			if (first.elementType.equals(TERMINAL) || second.elementType.equals(NOT_TERMINAL)) {
				// ищем ЧЕМ ЗАКАНЧИВАЮТСЯ правые части нетерминала
				List<Elem> firstElems = new ArrayList<>();

				final Rule rule = this.findRowByLeft(second);
				for (RightPart rightPart : rule.parts) {
					if (rightPart.elemList.size() == 0) {
						try {
							throw new Exception(" rightPart.elemList.size() == 0 ");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						firstElems.add(rightPart.elemList.get(0));
					}
				}

				System.out.print(this.printPair(pair) + ":\t{");
				for (int i = 0; i < firstElems.size(); i++) {
					Elem elem = firstElems.get(i);
					Relations relations = new Relations(first, elem, Sign.LESS);
					this.relations.add(relations);
					if (i != firstElems.size() - 1)
						System.out.print(elem.print() + ", ");
					else
						System.out.print(elem.print());
					//System.out.println(relations.leftElem.print() + " " + relations.sign.getStr() + " " + relations.rightElem.print());
				}
				System.out.print("}\tили\t");
				for (int i = 0; i < firstElems.size(); i++) {
					Elem elem = firstElems.get(i);
					System.out.print(first.str + " < " + elem.print());
					if (i != firstElems.size() - 1)
						System.out.print(", ");
				}

				System.out.print("\n");
			}
		}
	}


	private void Equals() {
		for (Rule rule : rules) {
			for (RightPart rightPart : rule.parts) {
				System.out.print(rule.left.str + " -> " + rightPart.print() + "\t\t");
				for (int i = 0; i < rightPart.elemList.size() - 1; ++i) {
					Relations relations = new Relations(
							rightPart.elemList.get(i),
							rightPart.elemList.get(i + 1),
							Sign.EQUALS
					);
					this.relations.add(relations);
					System.out.print(relations.print() + "\t");
				}
				System.out.println();
			}
		}
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
		List<Pair<Elem, Elem>> allPairs = new ArrayList<>(pairs);

		while (true) {

			pairs = this.changeTerminalToRight(pairs, allPairs, countIteration);


			newCollect =
					pairs.stream()
							.sorted((o1, o2) ->
									(o1.getKey().str + o1.getValue().str).compareTo(o2.getKey().str + o2.getValue().str))
							.map(elemElemPair -> elemElemPair.getKey().print() + "_" + elemElemPair.getValue().print())
							.collect(Collectors.joining("\t"));
			if (collect.compareTo(newCollect) == 0)
				break;
			countIteration++;
			collect = newCollect;
		}
		System.out.println("countIteration = " + countIteration);
		return pairs;
	}


	public List<Pair<Elem, Elem>> changeTerminalToRight(
			List<Pair<Elem, Elem>> oldPairs,
			List<Pair<Elem, Elem>> allPairs,
			int countIteration
	) {

		for (int i = 0; i < 100; i++)
			System.out.print("=");
		System.out.println();

		List<RightPart> rightPartList = new ArrayList<>();

		for (Pair<Elem, Elem> pair : oldPairs) {
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
			System.out.print(first.str + " " + second.print() + "    ->    ");
			tmp.forEach(rightPart -> System.out.print(rightPart.print() + "| "));
			System.out.println();


		}
		System.out.println("");


		// Теперь полученные правые части надо разбить на пары
		// Хотя бы один символ в такой паре должен быть нетерминальным.
		List<Pair<Elem, Elem>> newPairs = new ArrayList<>();

		for (RightPart rightPart : rightPartList) {
			for (int i = 0; i < rightPart.elemList.size() - 1; ++i) {
				if (rightPart.elemList.get(i).elementType.equals(NOT_TERMINAL) ||
						rightPart.elemList.get(i + 1).elementType.equals(NOT_TERMINAL)) {
					newPairs.add(new Pair<>(rightPart.elemList.get(i), rightPart.elemList.get(i + 1)));
				}
			}
		}

		newPairs.forEach(elemElemPair -> System.out.print(elemElemPair.getKey().print() + "_" + elemElemPair.getValue().print() + "\t"));
		System.out.println("\n\n");

		if( countIteration == 8)
			System.out.print("");

		// Ищем повторения
		for (int i = 0; i < newPairs.size(); i++) {
			Pair<Elem, Elem> pair = newPairs.get(i);
			for (int j = newPairs.size() - 1; j > 0; j--) {
				if (newPairs.get(j).getKey().str.compareTo(pair.getKey().str) == 0 &&
						newPairs.get(j).getValue().str.compareTo(pair.getValue().str) == 0) {
					if (i != j)
						newPairs.remove(j);
				}
			}
		}

		// Среди новых ищем старые и удаляем их
		for (int i = 0; i < allPairs.size(); ++i) {
			Pair<Elem, Elem> pair = allPairs.get(i);

			if( pair.getKey().str.equals("1_оператор") && pair.getValue().str.equals("if"))
				System.out.print("");
			for (int j = newPairs.size() - 1; j >= 0; j--) {
				if (newPairs.get(j).getKey().str.compareTo(pair.getKey().str) == 0 &&
						newPairs.get(j).getValue().str.compareTo(pair.getValue().str) == 0) {
					newPairs.remove(j);
				}
			}
		}

		// добавляем новые в общий список
		List<Pair<Elem, Elem>> tmp = new ArrayList<>();
		for (Pair<Elem, Elem> newPair : newPairs) {
			Elem newLeft = newPair.getKey();
			Elem newRight = newPair.getValue();
			boolean isfound = false;
			for (Pair<Elem, Elem> allPair : allPairs) {
				Elem oldLeft = allPair.getKey();
				Elem oldRight = allPair.getValue();
				if (oldLeft.equals(newLeft) && oldRight.equals(newRight)) {
					isfound = true;
					break;
				}
			}
			if (!isfound) {
				tmp.add(newPair);
			}
		}
		for (Pair<Elem, Elem> elemElemPair : tmp) {
			allPairs.add(elemElemPair);
		}
		System.out.println("countIteration = " + countIteration);
		System.out.print("oldPairs = { ");
		oldPairs.forEach(elemElemPair -> System.out.print(elemElemPair.getKey().print() + " " + elemElemPair.getValue().print() + "     "));
		System.out.print(" }\n");

		System.out.print("newPairs = { ");
		newPairs.forEach(elemElemPair -> System.out.print(elemElemPair.getKey().print() + " " + elemElemPair.getValue().print() + "     "));
		System.out.print(" }\n");

		System.out.print("allPairs = { ");
		allPairs.forEach(elemElemPair -> System.out.print(elemElemPair.getKey().print() + " " + elemElemPair.getValue().print() + "     "));
		System.out.print(" }");
		System.out.println("\n\n");

		return newPairs;
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


	public String printPair(Pair<Elem, Elem> pair) {
		return pair.getKey().str + " " + pair.getValue().str;
	}

	private String pairsToString(List<Relations> pairs) {
		String result = "";
		for (Relations pair : pairs) {
			String tmp = "";
			final Elem leftElem = pair.leftElem;
			final Elem rightElem = pair.rightElem;
			final Sign sign = pair.sign;
			tmp += leftElem.str + "\t" + sign.getStr() + "\t" + rightElem.str + "\n";
			result += tmp;
		}
		return result;
	}
}