package com.webapp.coronavirustracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.webapp.coronavirustracker.models.LocationStats;

@Repository
public interface LocationStatsRepository extends CrudRepository<LocationStats, String> {

}
