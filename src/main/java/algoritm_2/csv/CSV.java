package algoritm_2.csv;

import com.opencsv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSV {

	public static void main(String[] args) throws Exception {
		CSV csv = new CSV();
	}

	public CSV() throws Exception {
		String csvFile = "book.algoritm_2.csv";

// 		CSVReader reader = null;
//		reader = new CSVReader(new FileReader(csvFile));
//		String[] line;
//		while ((line = reader.readNext()) != null) {
//			System.out.println(Arrays.stream(line).collect(Collectors.joining("\t")));
//		}
//		System.out.println();







		List<String[]> stringArray = new ArrayList<>();

		stringArray.add(new String[]{"объявление_переменных","2","3"});
		stringArray.add(new String[]{"4","1488\n1337","6"});
		stringArray.add(new String[]{"7","8","9"});


		FileOutputStream fos =  new FileOutputStream(csvFile);
		Writer fw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
		CSVWriter writer = new CSVWriter(fw, ';');

		for (String[]array : stringArray) {
			writer.writeNext(array);
		}

		writer.close();
	}

	public List<String[]> readAll(Reader reader) throws Exception {
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(';')
				.withIgnoreQuotations(true)
				.build();

		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(0)
				.withCSVParser(parser)
				.build();
		List<String[]> list = new ArrayList<>();
		list = csvReader.readAll();
		reader.close();
		csvReader.close();
		return list;
	}

	public List<String[]> readAllExample() throws Exception {
		Reader reader = new FileReader("book.algoritm_2.csv");
		return this.readAll(reader);
	}

	public List<String[]> oneByOne(Reader reader) throws Exception {
		List<String[]> list = new ArrayList<>();
		CSVReader csvReader = new CSVReader(reader);
		String[] line;
		while ((line = csvReader.readNext()) != null) {
			list.add(line);
		}
		reader.close();
		csvReader.close();
		return list;
	}

	public List<String[]> oneByOneExample() throws Exception {
		Reader reader = new FileReader("book.algoritm_2.csv");
		return this.oneByOne(reader);
	}


}
