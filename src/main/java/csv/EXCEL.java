package csv;

import maga.Elem;
import maga.ElemType;
import maga.MyPair;
import maga.Relations;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EXCEL {
	public static void main(String[] args) throws IOException {
		EXCEL excel = new EXCEL();
	}

	public EXCEL() throws IOException {
		//this.writeIntoExcel("dudos.xls");
	}

	public static void printTable(Set<Elem> set, Map<MyPair<Elem, Elem>, List<Relations>> relations_Hashmap) throws IOException {
		System.out.println();
//		List<Elem> set_list = Arrays.asList(
//				new Elem("S", ElemType.NOT_TERMINAL),
//				new Elem("a", ElemType.TERMINAL),
//				new Elem("b", ElemType.TERMINAL),
//				new Elem("c", ElemType.TERMINAL),
//				new Elem("#", ElemType.TERMINAL)
//		);
		List<Elem> set_list = new ArrayList<>();
		for (Elem elem : set) {
			set_list.add(elem);
		}

		// Начинаем создавать документ
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet("Birthdays");

		// Создаем строки
		for (int i = 0; i < set_list.size() + 1; i++) {
			// Нумерация начинается с нуля
			Row row = sheet.createRow(i);
		}
		sheet.getRow(0).createCell(0).setCellValue("⬛");
		//sheet.autoSizeColumn(0);


		// шапка горизонтальная
		for (int i = 0; i < set_list.size(); i++) {
			Row row = sheet.getRow(0);
			Cell cell = row.createCell(i + 1);
			cell.setCellValue(set_list.get(i).str);

			CellStyle cellStyle = book.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cell.setCellStyle(cellStyle);
		}


		// Вертикальный столбец
		for (int i = 0; i < set_list.size(); i++) {
			Row row = sheet.getRow(i + 1);
			Cell cell = row.createCell(0);
			cell.setCellValue(set_list.get(i).str);

			CellStyle cellStyle = book.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cell.setCellStyle(cellStyle);

		}
		// Задаем ширину
		for (int i = 0; i < set.size() + 1; ++i) {
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 4 * 256);
		}

		// Теперь пройдемся по контенту
		for (int i = 0; i < set_list.size(); ++i) {
			for (int j = 0; j < set_list.size(); ++j) {
				Elem elem_first = set_list.get(i);
				Elem elem_second = set_list.get(j);

				Row row = sheet.getRow(i + 1);

				Cell cell = row.createCell(j + 1);
				List<Relations> relations = relations_Hashmap.get(new MyPair<Elem, Elem>(elem_first, elem_second));
				final String cellValue = relations.stream().map(r -> r.sign.getStr()).collect(Collectors.joining("\n"));
				cell.setCellValue(cellValue);

				CellStyle cellStyle = book.createCellStyle();
				cellStyle.setWrapText(true);
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				cell.setCellStyle(cellStyle);
			}
		}


		// Записываем всё в файл
		book.write(new FileOutputStream("book.xls"));
		book.close();
	}


	@SuppressWarnings("deprecation")
	public void writeIntoExcel(String file) throws FileNotFoundException, IOException {
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet("Birthdays");

		// Нумерация начинается с нуля
		Row row = sheet.createRow(0);

		// Мы запишем имя и дату в два столбца
		// имя будет String, а дата рождения --- Date,
		// формата dd.mm.yyyy
		Cell name = row.createCell(0);
		name.setCellValue("объявление_переменных");


		Cell birthdate = row.createCell(1);

		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		birthdate.setCellStyle(dateStyle);


		// Нумерация лет начинается с 1900-го
		birthdate.setCellValue(new Date(110, 10, 10));

		// Меняем размер столбца
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);


		// Записываем всё в файл
		book.write(new FileOutputStream(file));
		book.close();
	}
}
