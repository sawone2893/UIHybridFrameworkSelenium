package utililties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelDataReader {

	private static File f = null;
	private static FileInputStream finp = null;
	private static FileOutputStream fos = null;
	private static HSSFWorkbook work = null;
	private static HSSFSheet sh = null;
	private static HSSFRow row2 = null;
	private static HSSFCell cell = null;
	private static DataFormatter formatter = null;

	public static String getVariable(String testDataExcel, String sheetName, String TC_name, String name)
			throws IOException {
		// ****String name is the column name from Excel sheet

		f = new File("./TestData/" + testDataExcel + ".xls");
		formatter = new DataFormatter();
		finp = new FileInputStream(f);// input stream for reading data
		work = new HSSFWorkbook(finp);
		sh = work.getSheet(sheetName);
		HashMap<String, String> mapping = new HashMap<String, String>();
		int firstrow = sh.getFirstRowNum();
		int lastrow = sh.getLastRowNum();
		for (int i = firstrow; i <= lastrow; i++) {
			if (sh.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(TC_name)) {
				row2 = sh.getRow(i);
				int firstcell2 = row2.getFirstCellNum();
				int lastCell2 = row2.getLastCellNum();
				for (int j = firstcell2; j < lastCell2; j++) {
					Cell cell1 = sh.getRow(0).getCell(j);
					Cell cell2 = sh.getRow(i).getCell(j);
					mapping.put(formatter.formatCellValue(cell1), formatter.formatCellValue(cell2)); // [NOTE- Refer
				}
				break;
			}
		}

		String data = mapping.get(name);
		finp.close();
		return data;

	}

	public static void enterValue(String sheetName, String testCase_Name, String ProposalId) throws IOException {
		// ****String name is the column name from Excel sheet
		File f = new File("./Reports/Test Results/testdata_Proposals.xls");
		DataFormatter formatter = new DataFormatter();
		FileInputStream finp = new FileInputStream(f);// input stream for reading data
		HSSFWorkbook work = new HSSFWorkbook(finp);
		HSSFSheet sh = work.getSheet(sheetName);
		HashMap<String, String> mapping = new HashMap<String, String>();
		int firstrow = sh.getFirstRowNum();
		int lastrow = sh.getLastRowNum();
		for (int i = firstrow; i <= lastrow; i++) {
			if (sh.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testCase_Name)) {
				HSSFRow row2 = sh.getRow(i);
				int firstcell2 = row2.getFirstCellNum();
				int lastCell2 = row2.getLastCellNum();
				for (int j = firstcell2; j < lastCell2; j++) {
					Cell cell1 = sh.getRow(0).getCell(j); // column header
					Cell cell2 = sh.getRow(i).getCell(j); // value under the header
					if (cell1.getStringCellValue().equalsIgnoreCase("Proposal Id")) {
						Cell cellVal = sh.getRow(i).createCell(j);
						cellVal.setCellValue(ProposalId);
					}

				}
				break;
			}
		}

		finp.close();
		FileOutputStream foup = new FileOutputStream(f);
		work.write(foup);
		foup.close();
	}

	public static void inserRow(String outputExcelName, String sheetName, String ePRSModuleName,
			String testScenarioName, String testcaseId, String testStatus, String proposalId, String reportID,
			String userID, String date) throws InterruptedException {
		int rowCount = 0;

		try {
			f = new File("./Reports/" + outputExcelName + ".xls");

			if (f.exists()) {
				// This is to insert into the existing excel.
				finp = new FileInputStream(f);// input stream for existing file
				work = new HSSFWorkbook(finp);
				sh = work.getSheet(sheetName);
				int firstrow = sh.getFirstRowNum();
				int lastrow = sh.getLastRowNum();
				rowCount = lastrow - firstrow;
				// This is to append new row
				row2 = sh.createRow(rowCount + 1);

				cell = row2.createCell(0);
				cell.setCellValue(ePRSModuleName);

				cell = row2.createCell(1);
				cell.setCellValue(testScenarioName);

				cell = row2.createCell(2);
				cell.setCellValue(testcaseId);

				cell = row2.createCell(3);
				cell.setCellValue(testStatus);

				cell = row2.createCell(4);
				cell.setCellValue(proposalId);
				cell = row2.createCell(5);
				cell.setCellValue(reportID);

				cell = row2.createCell(6);
				cell.setCellValue(userID);

				cell = row2.createCell(7);
				cell.setCellValue(date);

				finp.close();
			} else {

				work = new HSSFWorkbook();
				sh = work.createSheet(sheetName);

				// This is to Add Headers
				row2 = sh.createRow(0);

				CellStyle style = work.createCellStyle();
				// Setting Background color
				style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				style.setFillPattern(CellStyle.BORDER_THIN);

				cell = row2.createCell(0);
				cell.setCellValue("ePRSModuleName");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue("TestScenarioName");
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("TestcaseId");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue("TestExecutionStatus");
				cell.setCellStyle(style);

				cell = row2.createCell(4);
				cell.setCellValue("ProposalId");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue("ReportId");
				cell.setCellStyle(style);

				cell = row2.createCell(6);
				cell.setCellValue("User ID");
				cell.setCellStyle(style);

				cell = row2.createCell(7);
				cell.setCellValue("Execution Date");
				cell.setCellStyle(style);

				// This is to add new row
				row2 = sh.createRow(1);

				cell = row2.createCell(0);
				cell.setCellValue(ePRSModuleName);

				cell = row2.createCell(1);
				cell.setCellValue(testScenarioName);

				cell = row2.createCell(2);
				cell.setCellValue(testcaseId);

				cell = row2.createCell(3);
				cell.setCellValue(testStatus);

				cell = row2.createCell(4);
				cell.setCellValue(proposalId);

				cell = row2.createCell(5);
				cell.setCellValue(reportID);

				cell = row2.createCell(6);
				cell.setCellValue(userID);

				cell = row2.createCell(7);
				cell.setCellValue(date);
			}

			fos = new FileOutputStream(f);
			work.write(fos);
			fos.close();
			System.out.println("Test Results for TC:" + testcaseId + " stored successfully");

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void inserRow(String outputExcelName, String sheetName, String ePRSModuleName,
			String testScenarioName, String testcaseId, String testStatus, String proposalId, String reportID,
			String userID, String reportStatus, String date) throws InterruptedException {
		int rowCount = 0;

		try {
			f = new File("./Reports/" + outputExcelName + ".xls");

			if (f.exists()) {
				// This is to insert into the existing excel.
				finp = new FileInputStream(f);// input stream for existing file
				work = new HSSFWorkbook(finp);
				sh = work.getSheet(sheetName);
				int firstrow = sh.getFirstRowNum();
				int lastrow = sh.getLastRowNum();
				rowCount = lastrow - firstrow;
				// This is to append new row
				row2 = sh.createRow(rowCount + 1);

				cell = row2.createCell(0);
				cell.setCellValue(ePRSModuleName);

				cell = row2.createCell(1);
				cell.setCellValue(testScenarioName);

				cell = row2.createCell(2);
				cell.setCellValue(testcaseId);

				cell = row2.createCell(3);
				cell.setCellValue(testStatus);

				cell = row2.createCell(4);
				cell.setCellValue(proposalId);
				cell = row2.createCell(5);
				cell.setCellValue(reportID);

				cell = row2.createCell(6);
				cell.setCellValue(userID);

				cell = row2.createCell(7);
				cell.setCellValue(reportStatus);

				cell = row2.createCell(8);
				cell.setCellValue(date);

				finp.close();
			} else {

				work = new HSSFWorkbook();
				sh = work.createSheet(sheetName);

				// This is to Add Headers
				row2 = sh.createRow(0);

				CellStyle style = work.createCellStyle();
				// Setting Background color
				style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				style.setFillPattern(CellStyle.BORDER_THIN);

				cell = row2.createCell(0);
				cell.setCellValue("ePRSModuleName");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue("TestScenarioName");
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("TestcaseId");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue("TestExecutionStatus");
				cell.setCellStyle(style);

				cell = row2.createCell(4);
				cell.setCellValue("ProposalId");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue("ReportId");
				cell.setCellStyle(style);

				cell = row2.createCell(6);
				cell.setCellValue("User ID");
				cell.setCellStyle(style);

				cell = row2.createCell(7);
				cell.setCellValue("Report Status");
				cell.setCellStyle(style);

				cell = row2.createCell(8);
				cell.setCellValue("Execution Date");
				cell.setCellStyle(style);

				// This is to add new row
				row2 = sh.createRow(1);

				cell = row2.createCell(0);
				cell.setCellValue(ePRSModuleName);

				cell = row2.createCell(1);
				cell.setCellValue(testScenarioName);

				cell = row2.createCell(2);
				cell.setCellValue(testcaseId);

				cell = row2.createCell(3);
				cell.setCellValue(testStatus);

				cell = row2.createCell(4);
				cell.setCellValue(proposalId);

				cell = row2.createCell(5);
				cell.setCellValue(reportID);

				cell = row2.createCell(6);
				cell.setCellValue(userID);

				cell = row2.createCell(7);
				cell.setCellValue(reportStatus);

				cell = row2.createCell(8);
				cell.setCellValue(date);
			}

			fos = new FileOutputStream(f);
			work.write(fos);
			fos.close();
			System.out.println("Test Results for TC:" + testcaseId + " stored successfully");

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
