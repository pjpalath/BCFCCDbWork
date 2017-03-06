package com.advancestores.standalone.ingestreports;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.advancestores.standalone.utils.CommonUtilities;

/**
 * 
 * @author PAUL
 *
 */
public class ReadCCDbReport
{

	/**
	 * 
	 */
	public ReadCCDbReport()
	{
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static List<CustomerInformation> readCCDbValues(String filename)
	{
		List<CustomerInformation> customerInfoSecondaryStoresMap = new ArrayList<CustomerInformation>();

		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(filename);

			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(filename.toLowerCase().endsWith("xlsx"))
			{
				workbook = new XSSFWorkbook(fis);
			}
			else if(filename.toLowerCase().endsWith("xls"))
			{
				workbook = new HSSFWorkbook(fis);
			}

			//Get the number of sheets in the xlsx file
			//	int numberOfSheets = workbook.getNumberOfSheets();

			//loop through each of the sheets
			for(int i=0; i < 1; i++)
			{
				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);                

				//every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				if (rowIterator.hasNext())
				{
					rowIterator.next();
				}
				while (rowIterator.hasNext()) 
				{
					CustomerInformation customerInformation = new CustomerInformation();

					//Get the row object
					Row row = rowIterator.next();					

					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) 
					{											
						//Get the Cell object
						Cell cell = cellIterator.next();						

						//check the cell type and process accordingly
						switch(cell.getCellType())
						{
						case Cell.CELL_TYPE_STRING:
							if (CommonUtilities.isStringNullOrEmpty(customerInformation.getCustomerName()))
							{
								customerInformation.setCustomerName(cell.getStringCellValue().trim());
							}
							else if (CommonUtilities.isStringNullOrEmpty(customerInformation.getAccountNumber()))
							{
								customerInformation.setAccountNumber(cell.getStringCellValue().trim());
							}
							else if (CommonUtilities.isDoubleNull(customerInformation.getAnnualSales()))
							{
								customerInformation.setAnnualSales(Double.valueOf(cell.getStringCellValue().trim()));
							}
							else if (CommonUtilities.isStringNullOrEmpty(customerInformation.getPrimaryStore()))
							{
								customerInformation.setPrimaryStore(cell.getStringCellValue().trim());
							}
							else if (CommonUtilities.isListNullOrEmpty(customerInformation.getSecondaryStores()))
							{
								List<String> secondaryStores = new ArrayList<String>(Arrays.asList(cell.getStringCellValue().split(",")));								
								secondaryStores.remove(customerInformation.getPrimaryStore());
								customerInformation.setSecondaryStores(secondaryStores);
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							DecimalFormat df = new DecimalFormat("#");
							if (CommonUtilities.isStringNullOrEmpty(customerInformation.getCustomerName()))
							{
								customerInformation.setCustomerName(df.format(cell.getNumericCellValue()));
							}
							else if (CommonUtilities.isStringNullOrEmpty(customerInformation.getAccountNumber()))
							{								
								customerInformation.setAccountNumber(df.format(cell.getNumericCellValue()));
							}
							else if (CommonUtilities.isDoubleNull(customerInformation.getAnnualSales()))
							{								
								customerInformation.setAnnualSales(cell.getNumericCellValue());
							}
							else if (CommonUtilities.isStringNullOrEmpty(customerInformation.getPrimaryStore()))
							{
								customerInformation.setPrimaryStore(df.format(cell.getNumericCellValue()));
							}
							else if (CommonUtilities.isListNullOrEmpty(customerInformation.getSecondaryStores()))
							{
								List<String> secondaryStores = Arrays.asList("" + cell.getNumericCellValue());
								secondaryStores.remove(customerInformation.getPrimaryStore());
								customerInformation.setSecondaryStores(secondaryStores);
							}							
							break;						

						}
					} //end of cell iterator

					customerInfoSecondaryStoresMap.add(customerInformation);

				} //end of rows iterator


			} //end of sheets for loop

			//close file input stream
			fis.close();

			// Close work book
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return customerInfoSecondaryStoresMap;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String ccdbReportFilename = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\CCDBSecondaryStores.xlsx";
		List<CustomerInformation> customerInfoSecondaryStoresMap = readCCDbValues(ccdbReportFilename);
		
		System.out.print("Customer Name\t"
				+ "Account Number\t"
				+ "Annual Sales\t"
				+ "Primary Store\t"
				+ "Secondary Stores\n");
		for (CustomerInformation customerInformation : customerInfoSecondaryStoresMap)
		{
			// Tabbed output
			System.out.print(customerInformation.getCustomerName() + "\t");
			System.out.print(customerInformation.getAccountNumber() + "\t");
			System.out.print(customerInformation.getAnnualSales() + "\t");			
			System.out.print(customerInformation.getPrimaryStore() + "\t");			
			System.out.print(customerInformation.getSecondaryStores() + "\n");
		}
	}
}
