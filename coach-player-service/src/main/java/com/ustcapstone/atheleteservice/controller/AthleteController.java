package com.ustcapstone.atheleteservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ustcapstone.atheleteservice.interfaces.GoalServiceFeignClient;
import com.ustcapstone.atheleteservice.interfaces.PerformanceFeignClient;
import com.ustcapstone.atheleteservice.interfaces.TeamFeignClient;
import com.ustcapstone.atheleteservice.interfaces.TrainingSessionFeignClient;
import com.ustcapstone.atheleteservice.model.Coach;
import com.ustcapstone.atheleteservice.model.Player;
import com.ustcapstone.atheleteservice.model.PlayerGoal;
import com.ustcapstone.atheleteservice.model.PlayerPerformance;
import com.ustcapstone.atheleteservice.model.PlayerPerformanceReport;
import com.ustcapstone.atheleteservice.model.Team;
import com.ustcapstone.atheleteservice.model.TrainingSession;
import com.ustcapstone.atheleteservice.service.AthleteService;


import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {
	
	@Autowired
    private TrainingSessionFeignClient trainingSessionFeignClient;

    @Autowired
    private AthleteService athleteService;
    @GetMapping("/teamIds/{coachId}")
    public ResponseEntity<List<Integer>> getTeamIdsByCoachId(@PathVariable int coachId) {
        List<Integer> teamIds = athleteService.getTeamIdsByCoachId(coachId);
        return ResponseEntity.ok(teamIds);
    }
    @PutMapping("/update-teams/{coachId}")
    public ResponseEntity<Void> updateCoachTeamIds(@PathVariable int coachId, @RequestBody List<Integer> teamIds) {
        athleteService.updateTeamIds(coachId, teamIds);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{coachId}/teams")
    public ResponseEntity<List<Team>> getTeamsByCoachId(@PathVariable int coachId) {
        List<Team> teams = athleteService.getTeamsByCoachId(coachId);
        return ResponseEntity.ok(teams);
    }
    @PutMapping("/update-team/{teamId}")
    public void updatePlayersTeamId(@PathVariable int teamId, @RequestBody List<Integer> playerIds) {
        athleteService.updatePlayersTeamId(teamId, playerIds);
    }
   
    
    
    //added by me
    @GetMapping("/players/{playerId}/team-members")
    public ResponseEntity<List<Player>> getTeamMembersByPlayerId(@PathVariable int playerId) {
        List<Player> players = athleteService.getPlayersByPlayerId(playerId);
        return ResponseEntity.ok(players);
    }
    
    @GetMapping("/team/{teamId}/players")
    public ResponseEntity<List<Player>> getPlayersByTeamId(@PathVariable int teamId) {
        List<Player> players = athleteService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }
    //added by me

    // Player Endpoints
    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(athleteService.createPlayer(player), HttpStatus.CREATED);
    }

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(athleteService.getAllPlayers());
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        return ResponseEntity.ok(athleteService.getPlayerById(id));
    }

    @PutMapping("/players")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        return ResponseEntity.ok(athleteService.updatePlayer(player));
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        athleteService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    // Coach Endpoints
    @PostMapping("/coaches")
    public ResponseEntity<Coach> createCoach(@RequestBody Coach coach) {
        return new ResponseEntity<>(athleteService.createCoach(coach), HttpStatus.CREATED);
    }

    @GetMapping("/coaches")
    public ResponseEntity<List<Coach>> getAllCoaches() {
        return ResponseEntity.ok(athleteService.getAllCoaches());
    }

    @GetMapping("/coaches/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable int id) {
        return ResponseEntity.ok(athleteService.getCoachById(id));
    }

    @PutMapping("/coaches")
    public ResponseEntity<Coach> updateCoach(@RequestBody Coach coach) {
        return ResponseEntity.ok(athleteService.updateCoach(coach));
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable int id) {
        athleteService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/training-sessions/player/{playerId}")
    public ResponseEntity<List<TrainingSession>> getTrainingSessionsByPlayerId(@PathVariable int playerId) {
        List<TrainingSession> sessions = athleteService.getTrainingSessionsByPlayerId(playerId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/training-sessions/coach/{coachId}")
    public ResponseEntity<List<TrainingSession>> getTrainingSessionsByCoachId(@PathVariable int coachId) {
        List<TrainingSession> sessions = athleteService.getTrainingSessionsByCoachId(coachId);
        return ResponseEntity.ok(sessions);
    }
    //*******************************************************************
    
    @Autowired
    private GoalServiceFeignClient goalServiceClient;

    // Endpoint to get all goals for a player
    @GetMapping("/player/{playerId}/goals")
    public List<PlayerGoal> getGoalsForPlayer(@PathVariable int playerId) {
        return goalServiceClient.getGoalsByPlayerId(playerId);
    }

    // Endpoint to get all goals given by a specific coach
    @GetMapping("/coach/{coachId}/goals")
    public List<PlayerGoal> getGoalsForCoach(@PathVariable int coachId) {
        return goalServiceClient.getGoalsByCoachId(coachId);
    }

    // Endpoint to get a specific goal by goalId
    @GetMapping("/goal/{goalId}")
    public PlayerGoal getGoalById(@PathVariable int goalId) {
        return goalServiceClient.getGoalById(goalId);
    }

    // Endpoint to update a goal
    @PutMapping("/goal/{goalId}")
    public PlayerGoal updateGoal(@PathVariable int goalId, @RequestBody PlayerGoal updatedGoal) {
        return goalServiceClient.updateGoal(goalId, updatedGoal);
    }
    @DeleteMapping("/goal/{goalId}")
    public String deleteGoal(@PathVariable int goalId) {
        goalServiceClient.deleteGoal(goalId);
        return "Goal with ID " + goalId + " has been deleted.";
    }
    
    //*******************************************************************
    // Player Endpoints
    @Autowired
    private PerformanceFeignClient performanceFeignClient;
    
    

    @GetMapping("/player/{playerId}/metrics")
    public List<PlayerPerformance> getPlayerMetrics(@PathVariable int playerId) {
        return performanceFeignClient.getAllMetricsByPlayerId(playerId);
    }

    @GetMapping("/player/{playerId}/reports")
    public List<PlayerPerformanceReport> getPlayerReports(@PathVariable int playerId) {
        return performanceFeignClient.getAllReportsByPlayerId(playerId);
    }

    @GetMapping("/player/{playerId}/teammates-reports")
    public List<PlayerPerformanceReport> getTeammatesReports(@PathVariable int playerId) {
        int teamId =  athleteService.getTeamIdByPlayerId(playerId);
        return performanceFeignClient.getTeamPerformanceReports(teamId);
    }

    // Coach Endpoints

    @PostMapping("/coach/player/{playerId}/metrics")
    public ResponseEntity<String> uploadMetricsForPlayer(@PathVariable int playerId, @RequestBody PlayerPerformance metrics) {
        performanceFeignClient.addPlayerMetrics(metrics);
        return ResponseEntity.ok("Metrics uploaded successfully");
    }

    @GetMapping("/coach/team/{teamId}/reports")
    public List<PlayerPerformanceReport> getTeamReports(@PathVariable int teamId) {
        return performanceFeignClient.getTeamPerformanceReports(teamId);
    }
    @GetMapping("/players/unassigned")
    public ResponseEntity<List<Player>> getUnassignedPlayers() {
        List<Player> unassignedPlayers = athleteService.getUnassignedPlayers();
        return ResponseEntity.ok(unassignedPlayers);
    }
    
    @GetMapping("/{playerId}/coach")
    public ResponseEntity<Coach> getCoachForPlayer(@PathVariable int playerId) {
        Coach coach = athleteService.getCoachForPlayer(playerId);
        return ResponseEntity.ok(coach);
    }
    
   
}
