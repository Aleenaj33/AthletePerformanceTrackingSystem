package com.ustcapstone.performance_analysis_service.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ustcapstone.performance_analysis_service.model.Player;

@FeignClient(name = "player-service", url = "http://localhost:8090/api/players")
public interface AthleteFeignClient {
    @GetMapping("/{playerId}")
    Player getPlayerById(@PathVariable("playerId") int playerId);
}
