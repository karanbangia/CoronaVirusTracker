package com.webapp.coronavirustracker.services;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.webapp.coronavirustracker.models.DeathStats;
import com.webapp.coronavirustracker.models.LocationStats;
import com.webapp.coronavirustracker.models.RecoveredStats;

@Service
public class CoronaVirusTrackerService {

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private static String TOTAL_DEATHS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Deaths.csv";
	private static String TOTAL_RECOVERED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv";
	private List<LocationStats> allStats = new ArrayList<>();
	private List<DeathStats> deathStats = new ArrayList<>();
	private List<RecoveredStats> recoveredStats = new ArrayList<>();

	public List<LocationStats> getAllStats() {
		return allStats;
	}

	public List<DeathStats> getDeathStats() {
		return deathStats;
	}

	public List<RecoveredStats> getRecoveredStats() {
		return recoveredStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws Exception, InterruptedException {
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		// System.out.println(httpResponse.body());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			int currentDay = Integer.parseInt(record.get(record.size() - 1));
			int previousDay = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setLatestTotalCases(currentDay);
			locationStat.setDifferenceBetweenCandP(currentDay - previousDay);
			// System.out.println(locationStat.getState() + " " + locationStat.getCountry()
			// + " "
			// + locationStat.getLatestTotalCases());
			newStats.add(locationStat);
		}

		Collections.sort(newStats, new Comparator<LocationStats>() {
			public int compare(LocationStats a, LocationStats b) {
				String x1 = a.getCountry().toLowerCase();
				String x2 = b.getCountry().toLowerCase();
				return x1.compareTo(x2);
			}

		});
		this.allStats = newStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchDeathData() throws Exception, InterruptedException {
		List<DeathStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(TOTAL_DEATHS_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		// System.out.println(httpResponse.body());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			DeathStats deathStat = new DeathStats();
			deathStat.setState(record.get("Province/State"));
			deathStat.setCountry(record.get("Country/Region"));
			int currentDay = Integer.parseInt(record.get(record.size() - 1));
			int previousDay = Integer.parseInt(record.get(record.size() - 2));
			deathStat.setLatestTotalCases(currentDay);
			deathStat.setDifferenceBetweenCandP(currentDay - previousDay);
			// System.out.println(locationStat.getState() + " " + locationStat.getCountry()
			// + " "
			// + locationStat.getLatestTotalCases());
			newStats.add(deathStat);
		}

		Collections.sort(newStats, new Comparator<DeathStats>() {
			public int compare(DeathStats a, DeathStats b) {
				String x1 = a.getCountry().toLowerCase();
				String x2 = b.getCountry().toLowerCase();
				return x1.compareTo(x2);
			}

		});
		this.deathStats = newStats;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchRecoveredData() throws Exception, InterruptedException {
		List<RecoveredStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(TOTAL_RECOVERED_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			RecoveredStats recoveredStat = new RecoveredStats();
			recoveredStat.setState(record.get("Province/State"));
			recoveredStat.setCountry(record.get("Country/Region"));
			int currentDay = Integer.parseInt(record.get(record.size() - 1));
			int previousDay = Integer.parseInt(record.get(record.size() - 2));
			recoveredStat.setLatestTotalCases(currentDay);
			recoveredStat.setDifferenceBetweenCandP(currentDay - previousDay);
			newStats.add(recoveredStat);
		}

		Collections.sort(newStats, new Comparator<RecoveredStats>() {
			public int compare(RecoveredStats a, RecoveredStats b) {
				String x1 = a.getCountry().toLowerCase();
				String x2 = b.getCountry().toLowerCase();
				return x1.compareTo(x2);
			}

		});
		this.recoveredStats = newStats;

	}
}
