package com.example.team_service.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.team_service.model.Team;
import com.example.team_service.repository.TeamRepository;





@Service
public class TeamService {

    private final TeamRepository teamRepository;
    public List<Team> getTeamsByCoachId(int coachId) {
        return teamRepository.findByCoachId(coachId);
    }

 
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Save a new team
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    // Get team by ID
    public Team getTeamById(int teamId) {
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    // Update an existing team
    public Team updateTeam(int teamId, Team updatedTeam) {
        Team existingTeam = getTeamById(teamId);
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setSportCategory(updatedTeam.getSportCategory());
        existingTeam.setCoachId(updatedTeam.getCoachId());
        return teamRepository.save(existingTeam);
    }

    // Delete a team
    public void deleteTeam(int teamId) {
        Team existingTeam = getTeamById(teamId);
        teamRepository.delete(existingTeam);
    }

    // Get all teams
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Find a team by name
    public Team getTeamByName(String teamName) {
        return teamRepository.findByName(teamName)
            .orElseThrow(() -> new RuntimeException("Team not found with name: " + teamName));
    }

    // Check if a team exists
    public boolean doesTeamExist(int teamId) {
        return teamRepository.existsById(teamId);
    }

    // Count total teams
    public long countTeams() {
        return teamRepository.count();
    }

    // Find teams by sport category
    public List<Team> findTeamsBySportCategory(String sportCategory) {
        return teamRepository.findBySportCategory(sportCategory);
    }
}

