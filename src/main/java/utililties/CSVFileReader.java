package utililties;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {
	public static void updateCSVFile(String fileName, String keyNameValue) {
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bwr = null;
		Scanner scanner = null;
		String filePath = System.getProperty("user.dir") + "\\resources\\" + fileName + ".csv";
		String[] currentLineData;
		String currentLine = null;
		ArrayList<String> fileContent = new ArrayList<String>();
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			scanner = new Scanner(br);
			// Reading Data from CSV file and storing into List
			while (scanner.hasNextLine()) {
				fileContent.add(scanner.nextLine());
			}
			final File file = new File(filePath);
			file.delete();
			// Searching required header and updating it value.
			String[] keyValueSet = keyNameValue.split("~");
			for (String keyValue : keyValueSet) {
				for (int i = 0; i < fileContent.size(); i++) {
					currentLineData = fileContent.get(i).split(",");
					System.out.println("Searching for " + keyValue.split(";")[0]);
					if (currentLineData[0].equalsIgnoreCase(keyValue.split(";")[0])) {
						currentLineData[1] = keyValue.split(";")[1];
						List<String> list = Arrays.asList(currentLineData);
						currentLine = String.join(",", list);
						fileContent.remove(i);
						fileContent.add(i, currentLine);
						break;
					}
				}

			}
			scanner.close();
			fw = new FileWriter(filePath);
			bwr = new BufferedWriter(fw);
			// Generating New CSV file with updated Data
			for (String eachline : fileContent) {
				System.out.println("Added Data: " + eachline);
				bwr.append(eachline);
				bwr.append("\n");
			}
			bwr.flush();
			bwr.close();
			System.out.println("File Updated:");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
