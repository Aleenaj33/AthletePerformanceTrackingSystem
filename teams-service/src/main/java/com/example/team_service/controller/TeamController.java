package com.example.team_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_service.model.Team;
import com.example.team_service.service.TeamService;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    
    @GetMapping("/by-coach/{coachId}")
    public ResponseEntity<List<Team>> getTeamsByCoachId(@PathVariable int coachId) {
        List<Team> teams = teamService.getTeamsByCoachId(coachId);
        return ResponseEntity.ok(teams);
    }

   
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("/getdemo")
    public String getAll() {
    	
       
        return "welcome";
       
    }

    // Create a new team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamService.saveTeam(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    // Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable int teamId) {
        Team team = teamService.getTeamById(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // Update an existing team
    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable int teamId, @RequestBody Team updatedTeam) {
        Team team = teamService.updateTeam(teamId, updatedTeam);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // Delete a team
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
    	
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
       
    }
    

    // Find a team by name
    @GetMapping("/name/{teamName}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String teamName) {
        Team team = teamService.getTeamByName(teamName);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // Count total teams
    @GetMapping("/count")
    public ResponseEntity<Long> countTeams() {
        long count = teamService.countTeams();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Find teams by sport category
    @GetMapping("/sport/{sportCategory}")
    public ResponseEntity<List<Team>> findTeamsBySportCategory(@PathVariable String sportCategory) {
        List<Team> teams = teamService.findTeamsBySportCategory(sportCategory);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}

