package com.advancestores.standalone.geocoding;

/**
 * 
 * @author PAUL
 *
 */
public class Duration
{
	private Distance distance;
	private String status;
	
	/**
	 * 
	 */
	public Duration()
	{
	}

	/**
	 * @return the distance
	 */
	public Distance getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Duration [distance=" + distance + ", status=" + status + "]";
	}
}
