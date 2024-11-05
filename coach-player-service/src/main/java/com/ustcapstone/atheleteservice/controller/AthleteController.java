package com.ustcapstone.atheleteservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ustcapstone.atheleteservice.model.Coach;
import com.ustcapstone.atheleteservice.model.Player;
import com.ustcapstone.atheleteservice.model.Team;
import com.ustcapstone.atheleteservice.service.AthleteService;


import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

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
    
   
}
