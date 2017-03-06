/**
 * 
 */
package com.advancestores.standalone.ingestreports;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.advancestores.standalone.utils.CommonUtilities;

/**
 * @author PAUL
 *
 */
public class ReadStoreDirectoryReport
{

	/**
	 * 
	 */
	public ReadStoreDirectoryReport()
	{
	}

	public static Map<String, StoreInformation> readStoreValues(String filename)
	{
		Map<String, StoreInformation> mapOfStoreAndStoreInformation = new HashMap<String, StoreInformation>();

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
				for (int ii=0; ii<6; ii++)
				{
					rowIterator.next();
				}
				
				while (rowIterator.hasNext()) 
				{
					StoreInformation storeInformation = new StoreInformation();

					//Get the row object
					Row row = rowIterator.next();					


					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();
					int cellCounter = 0;
					while (cellIterator.hasNext()) 
					{											
						//Get the Cell object
						Cell cell = cellIterator.next();						

						DecimalFormat df = new DecimalFormat("#");
						if (cellCounter == 0)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setStoreNumber(cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								storeInformation.setStoreNumber(df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 2)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setStoreName(cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								storeInformation.setStoreName(df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 5)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setArea(cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:								
								storeInformation.setArea(df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 6)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setDivision(cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:								
								storeInformation.setDivision(df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 9)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress()) 
										? cell.getStringCellValue().trim() : storeInformation.getAddress() + ", " + cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress())
										? df.format(cell.getNumericCellValue()) : storeInformation.getAddress() + ", " + df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 10)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress()) 
										? cell.getStringCellValue().trim() : storeInformation.getAddress() + ", " + cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress())
										? df.format(cell.getNumericCellValue()) : storeInformation.getAddress() + ", " + df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 11)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress()) 
										? cell.getStringCellValue().trim() : storeInformation.getAddress() + ", " + cell.getStringCellValue().trim());
								storeInformation.setState(cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress())
										? df.format(cell.getNumericCellValue()) : storeInformation.getAddress() + ", " + df.format(cell.getNumericCellValue()));
								storeInformation.setState(df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 12)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress()) 
										? cell.getStringCellValue().trim() : storeInformation.getAddress() + ", " + cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								storeInformation.setAddress(CommonUtilities.isStringNullOrEmpty(storeInformation.getAddress())
										? df.format(cell.getNumericCellValue()) : storeInformation.getAddress() + ", " + df.format(cell.getNumericCellValue()));
								break;
							}
						}
						else if (cellCounter == 15)
						{
							//check the cell type and process accordingly
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_STRING:
								storeInformation.setCam(cell.getStringCellValue().trim());
								break;
							case Cell.CELL_TYPE_NUMERIC:								
								storeInformation.setCam(df.format(cell.getNumericCellValue()));
								break;
							}
						}

						cellCounter++;
					} //end of cell iterator

					mapOfStoreAndStoreInformation.put(storeInformation.getStoreNumber(), storeInformation);

				} //end of rows iterator


			} //end of sheets for loop

			//close file input stream
			fis.close();

			// Close work book
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mapOfStoreAndStoreInformation;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String storeDirectoryReportFileName = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\Store Directory.xls";
		Map<String, StoreInformation> mapOfStoreAndStoreInformation = readStoreValues(storeDirectoryReportFileName);

		System.out.print("Store Number\t"
				+ "Store Name\t"
				+ "Area\t"
				+ "Division\t"
				+ "Address\t"
				+ "State\t"
				+ "Cam\n");
		Iterator<String> storeIterator = mapOfStoreAndStoreInformation.keySet().iterator();
		while (storeIterator.hasNext())
		{
			String storeNumber = (String) storeIterator.next();
			StoreInformation storeInformation = mapOfStoreAndStoreInformation.get(storeNumber);
			// Tabbed output
			System.out.print(storeInformation.getStoreNumber() + "\t");
			System.out.print(storeInformation.getStoreName() + "\t");
			System.out.print(storeInformation.getArea() + "\t");			
			System.out.print(storeInformation.getDivision() + "\t");			
			System.out.print(storeInformation.getAddress() + "\t");
			System.out.print(storeInformation.getState() + "\t");
			System.out.print(storeInformation.getCam() + "\n");
		}

	}

}
