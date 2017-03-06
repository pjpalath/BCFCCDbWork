package com.advancestores.standalone.geocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

/**
 * 
 * @author PAUL
 *
 */
public class DistanceBetweenTwoAddresses 
{
	/**
	 * 
	 */
	public DistanceBetweenTwoAddresses()
	{
	}

	/**
	 * 
	 * @param address1
	 * @param address2
	 * @return
	 * @throws IOException
	 */
	public static Double getDistanceBetweenTwoAddresses(String address1, String address2) throws IOException
	{
		Double distanceBetweenTheAddresses = 0.0;
		
		String encodedAddress1 = URLEncoder.encode(address1, "UTF-8");
		String encodedAddress2 = URLEncoder.encode(address2, "UTF-8");

		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="
				+ encodedAddress1 + "&destinations=" + encodedAddress2);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		String line, outputString = "";
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		while ((line = reader.readLine()) != null)
		{
			outputString += line;
		}

		DistancePojo resultantDistanceJSon = new Gson().fromJson(outputString, DistancePojo.class);
		Rows[] rows = resultantDistanceJSon.getRows();
		for (Rows row : rows)
		{
			Elements[] arrayOfElements = row.getElements();
			for (Elements element : arrayOfElements)
			{
				if ((element != null) && (element.getDistance() != null) && (element.getDistance().getValue() != null))
				{
					distanceBetweenTheAddresses = (Double.parseDouble(element.getDistance().getValue())/1000)/1.609344;
				}
			}
		}
		
		return distanceBetweenTheAddresses;
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException 
	{
		String address1 = "12423 Stonebranch Way, 37922";
		String address2 = "9802 Christi Ridge Way, 37931";

		System.out.println(getDistanceBetweenTwoAddresses(address1, address2));
	}
}
