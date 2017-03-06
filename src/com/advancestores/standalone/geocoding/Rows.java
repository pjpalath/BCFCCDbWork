package com.advancestores.standalone.geocoding;

import java.util.Arrays;

/**
 * 
 * @author PAUL
 *
 */
public class Rows
{
	private Elements[] elements;

	/**
	 * 
	 */
	public Rows()
	{
	}

	/**
	 * @return the elements
	 */
	public Elements[] getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(Elements[] elements) {
		this.elements = elements;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rows [elements=" + Arrays.toString(elements) + "]";
	}
}
