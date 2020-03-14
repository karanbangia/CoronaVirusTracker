package com.webapp.coronavirustracker.models;

public class LocationStats {

	private String State;
	private String country;
	private int latestTotalCases;
	private int differenceBetweenCandP;

	public int getDifferenceBetweenCandP() {
		return differenceBetweenCandP;
	}

	public void setDifferenceBetweenCandP(int differenceBetweenCandP) {
		this.differenceBetweenCandP = differenceBetweenCandP;
	}

	@Override
	public String toString() {
		return "LocationStats [State=" + State + ", country=" + country + ", latestTotalCases=" + latestTotalCases
				+ "]";
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

}
