/**
 * 
 */
package com.advancestores.standalone.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PAUL
 *
 */
public class CommonUtilities
{

	/**
	 * 
	 */
	public CommonUtilities()
	{
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static Boolean isStringNullOrEmpty(String string)
	{
		return string == null ? true : ((string.trim().equals("")) ? true : false);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static Boolean isListNullOrEmpty(List<?> list)
	{
		return list == null ? true : ((list.isEmpty()) ? true : false);
	}
	
	/**
	 * 
	 * @param doubleVal
	 * @return
	 */
	public static Boolean isDoubleNull(Double doubleVal)
	{
		return doubleVal == null ? true : false;
	}
	
	/**
	 * 
	 * @param listToRemoveFrom
	 * @param valuesToRemove
	 */
	public static void removeValuesFromOneListInAnother(List<?> listToRemoveFrom, List<?> valuesToRemove)
	{
		List<Object> objectsToRemove = new ArrayList<Object>();
		for (int ii = 0; ii < listToRemoveFrom.size(); ii++)
		{
			Object objectInListToRemove = listToRemoveFrom.get(ii);
			
			for (Object object : valuesToRemove)
			{				
				if (object.toString().trim().equals(objectInListToRemove.toString().trim()))
				{
					objectsToRemove.add(listToRemoveFrom.get(ii));					
				}
			}
		}
		
		for (Object object : objectsToRemove)
		{			
			listToRemoveFrom.remove(object);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	}

}
