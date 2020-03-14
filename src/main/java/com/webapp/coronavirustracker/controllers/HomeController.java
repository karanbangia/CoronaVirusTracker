package com.webapp.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.webapp.coronavirustracker.services.CoronaVirusTrackerService;
import com.webapp.coronavirustracker.models.LocationStats;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusTrackerService coronaVirusTracker;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusTracker.getAllStats();
		int totalCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat->stat.getDifferenceBetweenCandP()).sum();
		model.addAttribute("locationStats", coronaVirusTracker.getAllStats());
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("totalNewReportedCases",totalNewCases);


		return "home";

	}
}
