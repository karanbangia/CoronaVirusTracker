package com.webapp.coronavirustracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webapp.coronavirustracker.models.RecoveredStats;

@Repository
public interface RecoveredStatsRepository extends CrudRepository<RecoveredStats, String> {

}
