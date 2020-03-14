package com.webapp.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.webapp.coronavirustracker.services.CoronaVirusTrackerService;
import com.webapp.coronavirustracker.models.DeathStats;
import com.webapp.coronavirustracker.models.LocationStats;
import com.webapp.coronavirustracker.models.RecoveredStats;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusTrackerService coronaVirusTracker;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusTracker.getAllStats();
		List<DeathStats> deathStats = coronaVirusTracker.getDeathStats();
		List<RecoveredStats> recoveredStats = coronaVirusTracker.getRecoveredStats();
		int totalCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat->stat.getDifferenceBetweenCandP()).sum();
		int totalDeathCases=deathStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewDeathCases=deathStats.stream().mapToInt(stat->stat.getDifferenceBetweenCandP()).sum();
		int totalRecoveredCases=recoveredStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewRecoveredCases=recoveredStats.stream().mapToInt(stat->stat.getDifferenceBetweenCandP()).sum();
		model.addAttribute("locationStats", coronaVirusTracker.getAllStats());
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("totalNewReportedCases",totalNewCases);
		model.addAttribute("totalDeathsReportedCases",totalDeathCases);
		model.addAttribute("totalNewDeathsReportedCases",totalNewDeathCases);
		model.addAttribute("totalRecoveredReportedCases",totalRecoveredCases);
		model.addAttribute("totalNewRecoveredReportedCases",totalNewRecoveredCases);
		return "home";

	}
}
