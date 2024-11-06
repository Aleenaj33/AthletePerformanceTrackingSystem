package com.ustcapstone.performance_analysis.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "teams-service", url = "http://localhost:8091/api/teams") // Adjust URL/port as needed
public interface TeamFeignClient {

   
    @GetMapping("{teamId}/player-ids")
    List<Integer> getPlayerIdsByTeamId(@PathVariable("teamId") int teamId);

}
