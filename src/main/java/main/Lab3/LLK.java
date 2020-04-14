package main.Lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


/**
 * Тут кажись косяк
 * <список переменных>  -> <переменная> <W2>
 * <W2> -> E | , <список переменных> <W2>
 */
public class LLK {

	private static final int PRINTLOGS = 1;

	public static final Integer _ПРОГРАММА = 100;
	public static final Integer _A1 = 101;
	public static final Integer _A2 = 102;
	public static final Integer _A3 = 103;
	public static final Integer _A4 = 104;
	public static final Integer _A5 = 105;
	public static final Integer _A6 = 106;
	public static final Integer _ОДНО_ОПИСАНИЕ = 1007;
	public static final Integer _ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ = 108;
	public static final Integer _ОБЪЯВЛЕНИЕ_КОНСТАНТ = 109;
	public static final Integer _ФУНКЦИЯ = 110;
	public static final Integer _ТИП = 111;
	public static final Integer _СПИСОК_ПЕРЕМННЫХ = 112;
	public static final Integer _ПЕРЕМЕННАЯ = 113;
	public static final Integer _if = 114;
	public static final Integer _СПИСОК_ПАРАМЕТРОВ = 115;
	public static final Integer _СОСТАВНОЙ_ОПЕРАТОР = 116;
	public static final Integer _ОПЕРАТОРЫ_И_ОПИСАНИЯ = 117;
	public static final Integer _1_ОПЕРАТОР = 118;
	public static final Integer _ОПЕРАТОР = 119;
	public static final Integer _ПРИСВАИВАНИЕ = 120;
	public static final Integer _ВЫЗОВ_ФУНКЦИИ = 121;
	public static final Integer _СПИСОК_КОНСТАНТ = 122;
	public static final Integer _R1 = 123;
	public static final Integer _R2 = 124;
	public static final Integer _R3 = 125;
	public static final Integer _R4 = 126;
	public static final Integer _R5 = 127;
	public static final Integer _КОНСТАНТА = 128;
	public static final Integer _ПАРАМЕТРЫ = 129;
	public static final Integer _W2 = 130;
	public static final Integer _W3 = 131;
	public static final Integer _W4 = 132;
	public static final Integer _W5 = 133;
	public static final Integer _W6 = 134;
	public static final Integer _W7 = 135;
	public static final Integer _W8 = 136;
	public static final Integer _W9 = 137;
	public static final Integer _W13 = 138;
	public static final Integer _epsilon = 199;


	private HashMap<Pair<Integer, Integer>, TableCell> table;

	//private ArrayList<OneSymbol> magazin;
	private Stack<OneSymbol> magazin;

	private Scaner scaner;
	private boolean flag = true;

	private int countError = 0;


	private void putRule(Stack<OneSymbol> stack, Rule rule) {
		// Кладем все символы правила в СТЕК
		for (OneSymbol oneSymbol : rule.symbols) {
			stack.push(oneSymbol);
		}
	}

	private void printStack(Stack<OneSymbol> stack) {
		if (PRINTLOGS == 1) {
			for (int i = 0; i < 30; i++)
				System.out.print("=");
			System.out.println("\n");
			ArrayList<OneSymbol> list = new ArrayList<>();
			for (OneSymbol oneSymbol : stack) {
				list.add(0, oneSymbol);
				//System.out.println("<" + oneSymbol.lexString +">");
			}
			for (OneSymbol oneSymbol : list) {
				if (oneSymbol.isTerminal)
					System.out.println(oneSymbol.lexString);
				else
					System.out.println("<" + oneSymbol.lexString + ">");
			}
		}
	}

	private void printLex(ArrayList<Character> lex) {
		if (PRINTLOGS == 1) {
			System.out.println();
			System.out.print("lex = ");
			for (Character c : lex) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public LLK() throws JsonProcessingException {
		// Инициализируем таблицу
		this.table = TableRule.initTable();

		try {
			this.scaner = new Scaner();
		} catch (IOException e) {
			e.printStackTrace();
		}

  /*      boolean flag = true;
        while (flag){
            ArrayList<Character> lex = new ArrayList<>();
            Integer type;
            type = scaner.next(lex);

            System.out.println("lex = [ " + lex.stream().map(String::valueOf).collect(Collectors.joining()) +
                    " ]\t\ttype = [ " + type + " ]");
            System.out.println();
        }*/

		this.magazin = new Stack<>();

		// Записываем аксиому
		magazin.push(new OneSymbol(true, Scaner._END, "#"));
		magazin.push(new OneSymbol(false, LLK._ПРОГРАММА, "ПРОГРАММА"));


		this.start();
	}

	private void start() {


		ArrayList<Character> lex = new ArrayList<>();
		Integer type;
		type = scaner.next(lex);


		while (flag) {

			this.printStack(this.magazin);
			// Проверить содержимое верхушки магазина
			OneSymbol up = this.magazin.pop();
			this.printLex(lex);
			////////////////////////////////////////////////////////////////////////////////////////////////////////
			// Случай когда эпсилон
			if (up.typ.equals(LLK._epsilon))
				continue;
			////////////////////////////////////////////////////////////////////////////////////////////////////////
			// Терминал
			if (up.isTerminal == true) {
				System.out.print("");
				//System.out.println("up.typ = " + up.typ + "\t type = " + type);
				//int sukablydskay =  up.typ - type;
				// Mag == lex
				if (up.typ.equals(type)) {
					System.out.print("");
					if (type == Scaner._END)
						flag = false;
					else
						type = scaner.next(lex);
				} else {
					scaner.printError("ERR", lex);
					countError++;
					throw new NullPointerException("\n=====================================================\n\t\t\t\tОшибка!\n=====================================================");
				}
			}
			// Нетерминал
			else {
				// Анализ клетки управляющей таблицы
				System.out.print("");
				boolean f = table.containsKey(new Pair<>(up.typ, type));
				if (f == true) {
					// В ячейке не пусто
					// Заменить символ Mag на клетку (up.typ, type)

					TableCell tableCell = table.get(new Pair<>(up.typ, type));
					// Если вдруг правил больше, чем 1
					// То надо бы поглубже глянуть, так сказать
					// Всего у меня таких 4 случая
					// Нужно сначала понять, какой из этих случаев рассматривается
					if (tableCell.rules.size() > 1) {
						// То надо бы поглубже глянуть, так сказать
						// Всего у меня таких 4 случая
						// Нужно сначала понять, какой из этих случаев рассматривается

						int uk = scaner.getUk();
						SavePoint savePoint = scaner.getSavePoint();
						int tmpType;
						ArrayList<Character> tmpLex = new ArrayList<>();

						// <B> int
						// <B> double
						if (up.typ.equals(LLK._ОДНО_ОПИСАНИЕ) && (type.equals(Scaner._INT) || type.equals(Scaner._DOUBLE))) {
							//int a,
							//int a;
							//int a=
							//int a(        функция

							tmpType = scaner.next(tmpLex);      // тут должен быть айдишник
							tmpType = scaner.next(tmpLex);      // тут символ

							// удаляем верхушку
							//this.magazin.pop();

							// Ситуация когда объявляем функцию
							if (tmpType == Scaner._PARENTHESIS_OPEN) {
								this.magazin.push(new OneSymbol(false, LLK._ФУНКЦИЯ, "ФУНКЦИЯ"));
							}
							// Ситуация когда объявляем переменную без инициализации
							// Ситуация когда объявляем переменную с инициализацией
							else {
								this.magazin.push(new OneSymbol(false, LLK._ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ, "ОБЪЯВЛЕНИЕ_ПЕРЕМННЫХ"));
							}
						}

						// <A6> id
						if (up.typ.equals(LLK._A6) && type.equals(Scaner._ID)) {
							//a(        function
							//a+-*/...  все символы, кроме скобки это идентификатор

							tmpType = scaner.next(tmpLex);      // тут должен символ скобки или че другое

							//Выкинем верхушку стека
							//this.magazin.pop();

							// Ситуация когда вызов функции
							if (tmpType == Scaner._PARENTHESIS_OPEN) {
								this.magazin.push(new OneSymbol(false, LLK._ВЫЗОВ_ФУНКЦИИ, "ВЫЗОВ_ФУНКЦИИ"));
							}
							// Ситуация когда идентификатор
							else {
								this.magazin.push(new OneSymbol(true, Scaner._ID, "idd"));
							}
						}

						// <W7> else
						if (up.typ.equals(LLK._W7) && type.equals(Scaner._ELSE)) {

							//else...                   // это значит "<O> else"
							//все другое, нежели else   // это значит "e"
							//tmpType = scaner.next(tmpLex);

							//Выкинем верхушку стека
							//this.magazin.pop();
							// Ситуация когда идет блок с ELSE
							tmpType = type;
							if (tmpType == Scaner._ELSE) {
								this.magazin.push(new OneSymbol(false, LLK._ОПЕРАТОР, "ОПЕРАТОР"));
								this.magazin.push(new OneSymbol(true, Scaner._ELSE, "else"));
							}
							// Ситуация когда ИФ кончился
							else {
								this.magazin.push(new OneSymbol(false, LLK._epsilon, "_epsilon"));
							}
						}

						// <O> id
						if (up.typ == LLK._ОПЕРАТОР && type == Scaner._ID) {

							// =                  // это значит <P>
							// (   // это значит ";<Q>"
							tmpType = scaner.next(tmpLex);

							//Выкинем верхушку стека
							//this.magazin.pop();
							// Ситуация когда идет = т.е. присваивание
							if (tmpType == Scaner._ASSIGN) {
								this.magazin.push(new OneSymbol(false, LLK._ПРИСВАИВАНИЕ, "_ПРИСВАИВАНИЕ"));
							}
							// Ситуация когда ( т.е. вызов функции
							else {
								this.magazin.push(new OneSymbol(true, Scaner._SEMICOLON, ";"));
								this.magazin.push(new OneSymbol(false, LLK._ВЫЗОВ_ФУНКЦИИ, "_ВЫЗОВ_ФУНКЦИИ"));
							}
						}

						scaner.setUk(uk);
						scaner.setSavePoint(savePoint);
					}
					// Если правило одно
					else {
						// удаляем верхушку
						//this.magazin.pop();
						// Просто помещаем правило в стек
						this.putRule(this.magazin, tableCell.rules.get(0));
					}
				} else {
					// в Ячейке пусто
					System.out.print("");
					this.scaner.printError("erorina", lex);
					countError++;
					throw new NullPointerException("\n=====================================================\n\t\t\t\tОшибка!\n=====================================================");
				}
			}
		}
		System.out.println("Проверка завершена без ошибок");
		// System.out.println("Проверка завершена, Количество ошибок = " + countError);
	}


}
