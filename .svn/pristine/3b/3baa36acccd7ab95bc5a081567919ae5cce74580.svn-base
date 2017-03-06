package com.advancestores.standalone.ingestreports;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author PAUL
 *
 */
public class ReadBCFReport
{

	/**
	 * 
	 */
	public ReadBCFReport()
	{
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static Map<String, List<String>> readBCFValues(String filename)
	{		
		Map<String, List<String>> primarySecondaryStoresMap = new HashMap<String, List<String>>();

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
			// int numberOfSheets = workbook.getNumberOfSheets();

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
					String secondaryStore = "";
					String primaryStore = "";

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
							if(primaryStore.equalsIgnoreCase(""))
							{
								primaryStore = cell.getStringCellValue().trim();
							}
							else if(secondaryStore.equalsIgnoreCase(""))
							{
								//2nd column
								secondaryStore = cell.getStringCellValue().trim();
							}else
							{                                
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if(primaryStore.equalsIgnoreCase(""))
							{
								primaryStore = "" + cell.getNumericCellValue();
								primaryStore = primaryStore.substring(0, primaryStore.length()-2);
							}
							else if(secondaryStore.equalsIgnoreCase(""))
							{
								//2nd column
								secondaryStore = "" + cell.getNumericCellValue();
								secondaryStore = secondaryStore.substring(0, secondaryStore.length()-2);
							}else
							{                                
							}
							break;

						}
					} //end of cell iterator
					
					List<String> secondaryStores = primarySecondaryStoresMap.get(primaryStore);
					if (secondaryStores == null)
					{
						secondaryStores = new ArrayList<String>();
						primarySecondaryStoresMap.put(primaryStore, secondaryStores);
					}
					if (!secondaryStores.contains(secondaryStore) && !(primaryStore.trim().equals(secondaryStore.trim())))
					{
						secondaryStores.add(secondaryStore);
					}


				} //end of rows iterator


			} //end of sheets for loop

			//close file input stream
			fis.close();

			// Close work book
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return primarySecondaryStoresMap;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{		
		// What is in BCF but not in CCDb
		String bcfReportFilename = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\SameRouteConnectedStores.xlsx";
		Map<String, List<String>> bcfPrimarySecondaryStoresMap = readBCFValues(bcfReportFilename);

		String ccdbReportFilename = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\SameHubConnectedStores.xlsx";
		Map<String, List<String>> ccdbPrimarySecondaryStoresMap = readBCFValues(ccdbReportFilename);

		int totalNoOfCCDbStoresThatAreInPrimaryRoute = 0;
		int totalNoOfCCDbStoresThatAreNotInPrimaryRoute = 0;
		int totalNoOfBCFStoresNotInCCDbProfile = 0;

		Iterator<String> bcfIterator = bcfPrimarySecondaryStoresMap.keySet().iterator();
		System.out.print("Primary Store\t"
				+ "Number of CCDb Stores that are on the Primary Store Route (BCF)\t"
				+ "Number of CCDb Stores that are NOT on the Primary Store Route (BCF)\t"
				+ "Number of Secondary Stores on the Primary Route (BCF) that are NOT in the CCDb Stores\t"
				+ "BCF Secondary Stores\t"
				+ "CCDb Secondary Stores\n");
		while (bcfIterator.hasNext())
		{
			String primaryStore = bcfIterator.next();
			List<String> bcfSecondaryStoresList = bcfPrimarySecondaryStoresMap.get(primaryStore);
			List<String> ccdbSecondaryStoresList = ccdbPrimarySecondaryStoresMap.get(primaryStore);
			Collection<?> bcfSecondaryStores = bcfSecondaryStoresList;
			Collection<?> bcfSecondaryStoresClone1 = new ArrayList<String>(bcfSecondaryStoresList);
			Collection<?> bcfSecondaryStoresClone2 = new ArrayList<String>(bcfSecondaryStoresList);
			Collection<?> ccdbSecondaryStores = ccdbSecondaryStoresList;
			Collection<?> ccdbSecondaryStoresClone1 = new ArrayList<String>(ccdbSecondaryStoresList);
			Collection<?> ccdbSecondaryStoresClone2 = new ArrayList<String>(ccdbSecondaryStoresList);

			int numberOfCCDbStores = ccdbSecondaryStoresClone1.size();
			if ((bcfSecondaryStoresClone1 != null) && (ccdbSecondaryStoresClone1 != null))
			{
				ccdbSecondaryStoresClone1.removeAll(bcfSecondaryStoresClone1);
			}
			int noOfCCDbStoresNotInPrimaryRoute = ccdbSecondaryStoresClone1.size();			
			if ((bcfSecondaryStoresClone2 != null) && (ccdbSecondaryStoresClone2 != null))
			{
				bcfSecondaryStoresClone2.removeAll(ccdbSecondaryStoresClone2);
			}
			int noOfBCFStoresNotInCCDbProfile = bcfSecondaryStoresClone2.size();
			int noOfCCDbStoresThatAreInPrimaryRoute = numberOfCCDbStores - noOfCCDbStoresNotInPrimaryRoute;
			
			// Tabbed output
			System.out.print(primaryStore + "\t");
			System.out.print(noOfCCDbStoresThatAreInPrimaryRoute + "\t");
			System.out.print(noOfCCDbStoresNotInPrimaryRoute + "\t");			
			System.out.print(noOfBCFStoresNotInCCDbProfile + "\t");

			System.out.print(bcfSecondaryStores + "\t");			
			System.out.print(ccdbSecondaryStores + "\n");

			totalNoOfCCDbStoresThatAreInPrimaryRoute = totalNoOfCCDbStoresThatAreInPrimaryRoute + noOfCCDbStoresThatAreInPrimaryRoute;
			totalNoOfCCDbStoresThatAreNotInPrimaryRoute = totalNoOfCCDbStoresThatAreNotInPrimaryRoute + noOfCCDbStoresNotInPrimaryRoute;
			totalNoOfBCFStoresNotInCCDbProfile = totalNoOfBCFStoresNotInCCDbProfile + noOfBCFStoresNotInCCDbProfile;
		}

//		System.out.println("Total Number of CCDb Stores that are on the Primary Store Route (BCF): " + totalNoOfCCDbStoresThatAreInPrimaryRoute);
//		System.out.println("Total Number of CCDb Stores that are NOT on the Primary Store Route (BCF): " + totalNoOfCCDbStoresThatAreNotInPrimaryRoute);			
//		System.out.println("Total Number of Secondary Stores on the Primary Route (BCF) that are NOT in the CCDb Stores: " + totalNoOfBCFStoresNotInCCDbProfile);
	}

}
