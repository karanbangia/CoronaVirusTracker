package com.webapp.coronavirustracker.models;

public class DeathStats {

	private String State;
	private String country;
	private int latestTotalCases;
	private int differenceBetweenCandP;
	@Override
	public String toString() {
		return "DeathStats [State=" + State + ", country=" + country + ", latestTotalCases=" + latestTotalCases
				+ ", differenceBetweenCandP=" + differenceBetweenCandP + "]";
	}
	public String getState() {
		return State;
	}
	public String getCountry() {
		return country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public int getDifferenceBetweenCandP() {
		return differenceBetweenCandP;
	}
	public void setState(String state) {
		State = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public void setDifferenceBetweenCandP(int differenceBetweenCandP) {
		this.differenceBetweenCandP = differenceBetweenCandP;
	}
}
