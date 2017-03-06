package com.advancestores.standalone.analyse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.advancestores.standalone.geocoding.DistanceBetweenTwoAddresses;
import com.advancestores.standalone.ingestreports.CustomerInformation;
import com.advancestores.standalone.ingestreports.ReadBCFReport;
import com.advancestores.standalone.ingestreports.ReadCCDbReport;
import com.advancestores.standalone.ingestreports.ReadStoreDirectoryReport;
import com.advancestores.standalone.ingestreports.StoreInformation;
import com.advancestores.standalone.utils.CommonUtilities;

/**
 * 
 * @author PAUL
 *
 */
public class AnalyzeDifferencesInCCDbAndBCF
{
	private static final String BCF_REPORT_FILENAME = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\SameRouteConnectedStores.xlsx";
	private static final String BCF_REPORT_SAME_HUB_FILENAME = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\SameHubConnectedStores.xlsx";
	private static final String CCDB_REPORT_FILENAME = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\CCDBSecondaryStores.xlsx";
	private static final String STORE_DIRECTORY_REPORT_FILENAME = "C:\\Users\\PAUL\\Documents\\OnlyFolderToUse\\AAPWork\\BA\\FulfillmentLogic\\Store Directory.xls";

	private Map<String, List<String>> bcfPrimarySecondaryStoresMap = ReadBCFReport.readBCFValues(BCF_REPORT_FILENAME);
	private List<CustomerInformation> customerInfoSecondaryStoresMap = ReadCCDbReport.readCCDbValues(CCDB_REPORT_FILENAME);
	private Map<String, StoreInformation> mapOfStoreAndStoreInformation = ReadStoreDirectoryReport.readStoreValues(STORE_DIRECTORY_REPORT_FILENAME);

	/**
	 * 
	 */
	public AnalyzeDifferencesInCCDbAndBCF()
	{
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public void analyzeStoresInCCDbButNotInBCFAndDistanceBetweenStores() throws IOException
	{
		Map<String, List<CustomerInformation>> primaryStoreCustomersMap = new HashMap<String, List<CustomerInformation>>();

		// Go through the CCDb report and find all customers for a primary store
		for (CustomerInformation customerInformation : customerInfoSecondaryStoresMap)
		{
			if (!primaryStoreCustomersMap.containsKey(customerInformation.getPrimaryStore().trim()))
			{
				List<CustomerInformation> customerListPerPrimaryStore = new ArrayList<CustomerInformation>();
				customerListPerPrimaryStore.add(customerInformation);
				primaryStoreCustomersMap.put(customerInformation.getPrimaryStore().trim(), customerListPerPrimaryStore);
			}
			else
			{
				primaryStoreCustomersMap.get(customerInformation.getPrimaryStore().trim()).add(customerInformation);
			}
		}		

		System.out.print("Primary Store thas has customers with stores in CCDb but not on the primary route (BCF)\t"
				+ "Primary Store Address\t"
				+ "Customer Name\t "				
				+ "Customer Account Number\t"
				+ "BCF Secondary Store\t"
				+ "BCF Secondary Store Address\t"
				+ "CCDb Secondary Store\t"
				+ "CCDb Secondary Store Address\t"
				+ "Distance between Primary and Secondary Store\t"
				+ "Number of CCDb Secondary Stores not in Primary Route (BCF)\n");

		Iterator<String> primaryStoreCustomerMapIterator = primaryStoreCustomersMap.keySet().iterator();		
		while (primaryStoreCustomerMapIterator.hasNext())
		{
			String primaryStore = primaryStoreCustomerMapIterator.next();
			List<CustomerInformation> primaryStoreCustomers = primaryStoreCustomersMap.get(primaryStore);
			List<String> bcfSecondaryStoresList = bcfPrimarySecondaryStoresMap.get(primaryStore);

			if (!CommonUtilities.isListNullOrEmpty(bcfSecondaryStoresList))
			{
				for (CustomerInformation customerInformation : primaryStoreCustomers)
				{
					List<String> ccdbSecondaryStoresList = customerInformation.getSecondaryStores();					

					List<String> bcfSecondaryStoresClone1 = new ArrayList<String>(bcfSecondaryStoresList);				
					List<String> ccdbSecondaryStoresClone1 = new ArrayList<String>(ccdbSecondaryStoresList);

					if ((bcfSecondaryStoresClone1 != null) && (ccdbSecondaryStoresClone1 != null))
					{
						CommonUtilities.removeValuesFromOneListInAnother(ccdbSecondaryStoresClone1, bcfSecondaryStoresList);					
					}
					int noOfCCDbStoresNotInPrimaryRoute = ccdbSecondaryStoresClone1.size();

					if (noOfCCDbStoresNotInPrimaryRoute == 5)
					{
						String primaryStoreAddress = mapOfStoreAndStoreInformation.get(primaryStore).getAddress();
						for (String bcfSecondaryStore : bcfSecondaryStoresList)
						{
							if (mapOfStoreAndStoreInformation.get(bcfSecondaryStore.trim()) != null)
							{
								String bcfSecondaryStoreAddress = mapOfStoreAndStoreInformation.get(bcfSecondaryStore.trim()).getAddress();
								Double distanceBetweenPrimaryAndBCFStore = DistanceBetweenTwoAddresses.getDistanceBetweenTwoAddresses(
										primaryStoreAddress, bcfSecondaryStoreAddress);
								if (distanceBetweenPrimaryAndBCFStore != 0)
								{
									System.out.print(primaryStore + "\t");
									System.out.print(primaryStoreAddress + "\t");
									System.out.print(customerInformation.getCustomerName() +  "\t");
									System.out.print(customerInformation.getAccountNumber() +  "\t");
									System.out.print(bcfSecondaryStore +  "\t");
									System.out.print(bcfSecondaryStoreAddress +  "\t");
									System.out.print(" \t");
									System.out.print(" \t");
									System.out.print(distanceBetweenPrimaryAndBCFStore +  "\t");
									System.out.print(noOfCCDbStoresNotInPrimaryRoute +  "\n");
								}
							}
						}
						for (String ccdbSecondaryStore : ccdbSecondaryStoresList)
						{
							if (mapOfStoreAndStoreInformation.get(ccdbSecondaryStore.trim()) != null)
							{
								String ccdbSecondaryStoreAddress = mapOfStoreAndStoreInformation.get(ccdbSecondaryStore.trim()).getAddress();
								Double distanceBetweenPrimaryAndCCDbStore = DistanceBetweenTwoAddresses.getDistanceBetweenTwoAddresses(
										primaryStoreAddress, ccdbSecondaryStoreAddress);
								if (distanceBetweenPrimaryAndCCDbStore != 0)
								{
									System.out.print(primaryStore + "\t");
									System.out.print(primaryStoreAddress + "\t");
									System.out.print(customerInformation.getCustomerName() +  "\t");
									System.out.print(customerInformation.getAccountNumber() +  "\t");							
									System.out.print(" \t");
									System.out.print(" \t");
									System.out.print(ccdbSecondaryStore +  "\t");							
									System.out.print(ccdbSecondaryStoreAddress +  "\t");
									System.out.print(distanceBetweenPrimaryAndCCDbStore +  "\t");
									System.out.print(noOfCCDbStoresNotInPrimaryRoute +  "\n");
								}
							}
						}						
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	public void analyzeStoresInCCDbButNotInBCF()
	{
		Map<String, List<CustomerInformation>> primaryStoreCustomersMap = new HashMap<String, List<CustomerInformation>>();

		// Go through the CCDb report and find all customers for a primary store
		for (CustomerInformation customerInformation : customerInfoSecondaryStoresMap)
		{
			if (!primaryStoreCustomersMap.containsKey(customerInformation.getPrimaryStore().trim()))
			{
				List<CustomerInformation> customerListPerPrimaryStore = new ArrayList<CustomerInformation>();
				customerListPerPrimaryStore.add(customerInformation);
				primaryStoreCustomersMap.put(customerInformation.getPrimaryStore().trim(), customerListPerPrimaryStore);
			}
			else
			{
				primaryStoreCustomersMap.get(customerInformation.getPrimaryStore().trim()).add(customerInformation);
			}
		}		

		System.out.print("Primary Store thas has customers with stores in CCDb but not on the primary route (BCF)\t"
				+ "Customer Name\t "				
				+ "Customer Account Number\t"
				+ "BCF Secondary Stores\t"
				+ "CCDb Secondary Stores\t"
				+ "Number of CCDb Secondary Stores not in Primary Route (BCF)\n");

		Iterator<String> primaryStoreCustomerMapIterator = primaryStoreCustomersMap.keySet().iterator();		
		while (primaryStoreCustomerMapIterator.hasNext())
		{
			String primaryStore = primaryStoreCustomerMapIterator.next();
			List<CustomerInformation> primaryStoreCustomers = primaryStoreCustomersMap.get(primaryStore);
			List<String> bcfSecondaryStoresList = bcfPrimarySecondaryStoresMap.get(primaryStore);

			if (!CommonUtilities.isListNullOrEmpty(bcfSecondaryStoresList))
			{
				for (CustomerInformation customerInformation : primaryStoreCustomers)
				{
					List<String> ccdbSecondaryStoresList = customerInformation.getSecondaryStores();					

					List<String> bcfSecondaryStoresClone1 = new ArrayList<String>(bcfSecondaryStoresList);
					List<String> bcfSecondaryStoresClone2 = new ArrayList<String>(bcfSecondaryStoresList);				
					List<String> ccdbSecondaryStoresClone1 = new ArrayList<String>(ccdbSecondaryStoresList);
					List<String> ccdbSecondaryStoresClone2 = new ArrayList<String>(ccdbSecondaryStoresList);

					if ((bcfSecondaryStoresClone1 != null) && (ccdbSecondaryStoresClone1 != null))
					{
						CommonUtilities.removeValuesFromOneListInAnother(ccdbSecondaryStoresClone1, bcfSecondaryStoresList);					
					}
					int noOfCCDbStoresNotInPrimaryRoute = ccdbSecondaryStoresClone1.size();

					if (noOfCCDbStoresNotInPrimaryRoute > 0)
					{						
						System.out.print(primaryStore + "\t");
						System.out.print(customerInformation.getCustomerName() +  "\t");
						System.out.print(customerInformation.getAccountNumber() +  "\t");
						System.out.print(bcfSecondaryStoresClone2 +  "\t");
						System.out.print(ccdbSecondaryStoresClone2 +  "\t");
						System.out.print(noOfCCDbStoresNotInPrimaryRoute +  "\n");
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	public void analyzePrimaryStoreDiffBCFAndCCDb()
	{
		Map<String, List<CustomerInformation>> primaryStoreCustomersMap = new HashMap<String, List<CustomerInformation>>();

		// Go through the CCDb report and find all customers for a primary store
		for (CustomerInformation customerInformation : customerInfoSecondaryStoresMap)
		{
			if (!primaryStoreCustomersMap.containsKey(customerInformation.getPrimaryStore().trim()))
			{
				List<CustomerInformation> customerListPerPrimaryStore = new ArrayList<CustomerInformation>();
				customerListPerPrimaryStore.add(customerInformation);
				primaryStoreCustomersMap.put(customerInformation.getPrimaryStore().trim(), customerListPerPrimaryStore);
			}
			else
			{
				primaryStoreCustomersMap.get(customerInformation.getPrimaryStore().trim()).add(customerInformation);
			}
		}

		System.out.print("Primary Store\t"
				+ "Percentage of customers that have ALL secondary stores on the primary route (BCF) "
				+ "also in the CCDb is\t"
				+ "Percentage of customers that have missing secondary stores in the CCDb profile but "
				+ "are on the primary route (BCF) is\t"
				+ "Percentage of customers that have missing secondary stores on the primary route (BCF) "
				+ "but are in the CCDb profile is\t"
				+ "BCF Secondary Stores\t"
				+ "CCDb Secondary Stores\n");

		int totalNumOfCustomers = 0;
		int totalNumOfCustomersWithAllRouteStoresInCCDb = 0;
		int totalNumOfCustomersWithMissStoresInCCDbButInRoutes = 0;
		int totalNumOfCustomersWithMissStoresOnRouteButInCCDb = 0;
		Iterator<String> primaryStoreCustomerMapIterator = primaryStoreCustomersMap.keySet().iterator();		
		while (primaryStoreCustomerMapIterator.hasNext())
		{
			String primaryStore = primaryStoreCustomerMapIterator.next();
			List<CustomerInformation> primaryStoreCustomers = primaryStoreCustomersMap.get(primaryStore);
			List<String> bcfSecondaryStoresList = bcfPrimarySecondaryStoresMap.get(primaryStore);

			if (!CommonUtilities.isListNullOrEmpty(bcfSecondaryStoresList))
			{				
				int numOfCustomersForThisPrimaryStore = primaryStoreCustomers.size();
				int numOfCustomersWithAllRouteStoresInCCDb = 0;			
				int numOfCustomersWithMissStoresOnRouteButInCCDb = 0;
				int numOfCustomersWithMissStoresInCCDbButInRoutes = 0;

				for (CustomerInformation customerInformation : primaryStoreCustomers)
				{
					List<String> ccdbSecondaryStoresList = customerInformation.getSecondaryStores();					

					List<String> bcfSecondaryStoresClone1 = new ArrayList<String>(bcfSecondaryStoresList);
					List<String> bcfSecondaryStoresClone2 = new ArrayList<String>(bcfSecondaryStoresList);				
					List<String> ccdbSecondaryStoresClone1 = new ArrayList<String>(ccdbSecondaryStoresList);
					List<String> ccdbSecondaryStoresClone2 = new ArrayList<String>(ccdbSecondaryStoresList);

					if ((bcfSecondaryStoresClone1 != null) && (ccdbSecondaryStoresClone1 != null))
					{
						CommonUtilities.removeValuesFromOneListInAnother(ccdbSecondaryStoresClone1, bcfSecondaryStoresList);					
					}
					int noOfCCDbStoresNotInPrimaryRoute = ccdbSecondaryStoresClone1.size();			
					if ((bcfSecondaryStoresClone2 != null) && (ccdbSecondaryStoresClone2 != null))
					{
						CommonUtilities.removeValuesFromOneListInAnother(bcfSecondaryStoresClone2, ccdbSecondaryStoresClone2);
					}
					int noOfBCFStoresNotInCCDbProfile = bcfSecondaryStoresClone2.size();
					int noOfBCFStoresThatAreInCCDb = bcfSecondaryStoresList.size() - noOfBCFStoresNotInCCDbProfile;

					if (bcfSecondaryStoresList.size() == noOfBCFStoresThatAreInCCDb)
					{
						numOfCustomersWithAllRouteStoresInCCDb = numOfCustomersWithAllRouteStoresInCCDb + 1;
					}
					if (noOfBCFStoresNotInCCDbProfile > 0)
					{
						numOfCustomersWithMissStoresInCCDbButInRoutes = numOfCustomersWithMissStoresInCCDbButInRoutes + 1;
					}
					if (noOfCCDbStoresNotInPrimaryRoute > 0)
					{
						numOfCustomersWithMissStoresOnRouteButInCCDb = numOfCustomersWithMissStoresOnRouteButInCCDb + 1;
					}
				}

				totalNumOfCustomers = totalNumOfCustomers + numOfCustomersForThisPrimaryStore;
				totalNumOfCustomersWithAllRouteStoresInCCDb = totalNumOfCustomersWithAllRouteStoresInCCDb + numOfCustomersWithAllRouteStoresInCCDb;			
				totalNumOfCustomersWithMissStoresInCCDbButInRoutes = totalNumOfCustomersWithMissStoresInCCDbButInRoutes + numOfCustomersWithMissStoresInCCDbButInRoutes;
				totalNumOfCustomersWithMissStoresOnRouteButInCCDb = totalNumOfCustomersWithMissStoresOnRouteButInCCDb + numOfCustomersWithMissStoresOnRouteButInCCDb;	


				System.out.print(primaryStore + "\t");
				System.out.print((float) (numOfCustomersWithAllRouteStoresInCCDb * 100)/numOfCustomersForThisPrimaryStore +  "\t");
				System.out.print((float) (numOfCustomersWithMissStoresInCCDbButInRoutes * 100)/numOfCustomersForThisPrimaryStore +  "\t");
				System.out.print((float) (numOfCustomersWithMissStoresOnRouteButInCCDb * 100)/numOfCustomersForThisPrimaryStore +  "\n");
			}
		}

		System.out.println("Percentage of customers that have ALL secondary stores on the primary route (BCF) "
				+ "also in the CCDb is: " + ((float) (totalNumOfCustomersWithAllRouteStoresInCCDb * 100)/totalNumOfCustomers));
		System.out.println("Percentage of customers that have missing secondary stores in the CCDb profile but "
				+ "are on the primary route (BCF) is: " + ((float) (totalNumOfCustomersWithMissStoresInCCDbButInRoutes * 100)/totalNumOfCustomers));
		System.out.println("Percentage of customers that have missing secondary stores on the primary route (BCF) "
				+ "but are in the CCDb profile is: " + ((float) (totalNumOfCustomersWithMissStoresOnRouteButInCCDb * 100)/totalNumOfCustomers));
	}

	/**
	 * 
	 */
	public void analyzeCustomerDifferenceBCFAndCCDb()
	{
		int totalNoOfCCDbStoresThatAreInPrimaryRoute = 0;
		int totalNoOfCCDbStoresThatAreNotInPrimaryRoute = 0;
		int totalNoOfBCFStoresNotInCCDbProfile = 0;

		System.out.print("Customer Name\t"
				+ "CCDb Account Number\t"
				+ "Account Annual Sales\t"
				+ "Primary Store\t"
				+ "Number of CCDb Stores that are on the Primary Store Route (BCF)\t"
				+ "Number of CCDb Stores that are NOT on the Primary Store Route (BCF)\t"
				+ "Number of Secondary Stores on the Primary Route (BCF) that are NOT in the CCDb Stores\t"
				+ "BCF Secondary Stores\t"
				+ "CCDb Secondary Stores\n");
		for (CustomerInformation customerInformation : customerInfoSecondaryStoresMap)
		{	
			List<String> bcfSecondaryStoresList = bcfPrimarySecondaryStoresMap.get(customerInformation.getPrimaryStore());
			if (!CommonUtilities.isListNullOrEmpty(bcfSecondaryStoresList))
			{
				List<String> ccdbSecondaryStoresList = customerInformation.getSecondaryStores();

				List<String> bcfSecondaryStores = bcfSecondaryStoresList;
				List<String> bcfSecondaryStoresClone1 = new ArrayList<String>(bcfSecondaryStoresList);
				List<String> bcfSecondaryStoresClone2 = new ArrayList<String>(bcfSecondaryStoresList);
				List<String> ccdbSecondaryStores = ccdbSecondaryStoresList;
				List<String> ccdbSecondaryStoresClone1 = new ArrayList<String>(ccdbSecondaryStoresList);
				List<String> ccdbSecondaryStoresClone2 = new ArrayList<String>(ccdbSecondaryStoresList);

				int numberOfCCDbStores = ccdbSecondaryStoresClone1.size();
				if ((bcfSecondaryStoresClone1 != null) && (ccdbSecondaryStoresClone1 != null))
				{
					CommonUtilities.removeValuesFromOneListInAnother(ccdbSecondaryStoresClone1, bcfSecondaryStores);					
				}
				int noOfCCDbStoresNotInPrimaryRoute = ccdbSecondaryStoresClone1.size();			
				if ((bcfSecondaryStoresClone2 != null) && (ccdbSecondaryStoresClone2 != null))
				{
					CommonUtilities.removeValuesFromOneListInAnother(bcfSecondaryStoresClone2, ccdbSecondaryStoresClone2);
				}
				int noOfBCFStoresNotInCCDbProfile = bcfSecondaryStoresClone2.size();
				int noOfCCDbStoresThatAreInPrimaryRoute = numberOfCCDbStores - noOfCCDbStoresNotInPrimaryRoute;

				// Tabbed output
				System.out.print(customerInformation.getCustomerName() + "\t");
				System.out.print(customerInformation.getAccountNumber() + "\t");
				System.out.print(customerInformation.getAnnualSales() + "\t");
				System.out.print(customerInformation.getPrimaryStore() + "\t");
				System.out.print(noOfCCDbStoresThatAreInPrimaryRoute + "\t");
				System.out.print(noOfCCDbStoresNotInPrimaryRoute + "\t");			
				System.out.print(noOfBCFStoresNotInCCDbProfile + "\t");

				System.out.print(bcfSecondaryStores + "\t");			
				System.out.print(ccdbSecondaryStores + "\n");

				totalNoOfCCDbStoresThatAreInPrimaryRoute = totalNoOfCCDbStoresThatAreInPrimaryRoute + noOfCCDbStoresThatAreInPrimaryRoute;
				totalNoOfCCDbStoresThatAreNotInPrimaryRoute = totalNoOfCCDbStoresThatAreNotInPrimaryRoute + noOfCCDbStoresNotInPrimaryRoute;
				totalNoOfBCFStoresNotInCCDbProfile = totalNoOfBCFStoresNotInCCDbProfile + noOfBCFStoresNotInCCDbProfile;
			}
		}

		System.out.println("Total Number of CCDb Stores that are on the Primary Store Route (BCF): " + totalNoOfCCDbStoresThatAreInPrimaryRoute);
		System.out.println("Total Number of CCDb Stores that are NOT on the Primary Store Route (BCF): " + totalNoOfCCDbStoresThatAreNotInPrimaryRoute);			
		System.out.println("Total Number of Secondary Stores on the Primary Route (BCF) that are NOT in the CCDb Stores: " + totalNoOfBCFStoresNotInCCDbProfile);

	}

	/**
	 * 
	 */
	public void analyzeDifferencesBetweenTwoBCFRouteTypes()
	{
		Map<String, List<String>> bcfPrimarySecondaryStoresMap = ReadBCFReport.readBCFValues(BCF_REPORT_FILENAME);
		Map<String, List<String>> ccdbPrimarySecondaryStoresMap = ReadBCFReport.readBCFValues(BCF_REPORT_SAME_HUB_FILENAME);

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

		System.out.println("Total Number of CCDb Stores that are on the Primary Store Route (BCF): " + totalNoOfCCDbStoresThatAreInPrimaryRoute);
		System.out.println("Total Number of CCDb Stores that are NOT on the Primary Store Route (BCF): " + totalNoOfCCDbStoresThatAreNotInPrimaryRoute);			
		System.out.println("Total Number of Secondary Stores on the Primary Route (BCF) that are NOT in the CCDb Stores: " + totalNoOfBCFStoresNotInCCDbProfile);
	}

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main (String[] args) throws IOException
	{
		AnalyzeDifferencesInCCDbAndBCF analyzeDifferencesInCCDbAndBCF = new AnalyzeDifferencesInCCDbAndBCF();

		//		analyzeDifferencesInCCDbAndBCF.analyzeDifferencesBetweenTwoBCFRouteTypes();
		//		analyzeDifferencesInCCDbAndBCF.analyzeCustomerDifferenceBCFAndCCDb();
		//		analyzeDifferencesInCCDbAndBCF.analyzePrimaryStoreDiffBCFAndCCDb();
		//		analyzeDifferencesInCCDbAndBCF.analyzeStoresInCCDbButNotInBCF();
		analyzeDifferencesInCCDbAndBCF.analyzeStoresInCCDbButNotInBCFAndDistanceBetweenStores();
	}
}
