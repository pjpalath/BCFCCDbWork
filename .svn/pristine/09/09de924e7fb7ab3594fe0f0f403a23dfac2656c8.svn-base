/**
 * 
 */
package com.advancestores.standalone.ingestreports;

import java.util.List;

/**
 * @author PAUL
 *
 */
public class CustomerInformation
{
	private String customerName;
	private String accountNumber;
	private Double annualSales;
	private String primaryStore;
	private List<String> secondaryStores;
	
	/**
	 * 
	 */
	public CustomerInformation()
	{
	}

	/**
	 * @param customerName
	 * @param accountNumber
	 * @param annualSales
	 * @param primaryStore
	 * @param secondaryStores
	 */
	public CustomerInformation(String customerName, String accountNumber,
			Double annualSales, String primaryStore,
			List<String> secondaryStores) {
		this.customerName = customerName;
		this.accountNumber = accountNumber;
		this.annualSales = annualSales;
		this.primaryStore = primaryStore;
		this.secondaryStores = secondaryStores;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the annualSales
	 */
	public Double getAnnualSales() {
		return annualSales;
	}

	/**
	 * @param annualSales the annualSales to set
	 */
	public void setAnnualSales(Double annualSales) {
		this.annualSales = annualSales;
	}

	/**
	 * @return the primaryStore
	 */
	public String getPrimaryStore() {
		return primaryStore;
	}

	/**
	 * @param primaryStore the primaryStore to set
	 */
	public void setPrimaryStore(String primaryStore) {
		this.primaryStore = primaryStore;
	}

	/**
	 * @return the secondaryStores
	 */
	public List<String> getSecondaryStores() {
		return secondaryStores;
	}

	/**
	 * @param secondaryStores the secondaryStores to set
	 */
	public void setSecondaryStores(List<String> secondaryStores) {
		this.secondaryStores = secondaryStores;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result
				+ ((annualSales == null) ? 0 : annualSales.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((primaryStore == null) ? 0 : primaryStore.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInformation other = (CustomerInformation) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (annualSales == null) {
			if (other.annualSales != null)
				return false;
		} else if (!annualSales.equals(other.annualSales))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (primaryStore == null) {
			if (other.primaryStore != null)
				return false;
		} else if (!primaryStore.equals(other.primaryStore))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerInformation [customerName=" + customerName
				+ ", accountNumber=" + accountNumber + ", annualSales="
				+ annualSales + ", primaryStore=" + primaryStore
				+ ", secondaryStores=" + secondaryStores + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	}

}
