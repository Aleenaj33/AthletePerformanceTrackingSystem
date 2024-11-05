package com.example.team_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_service.interfaces.AthleteFeignClient;
import com.example.team_service.model.Player;
import com.example.team_service.model.Team;
import com.example.team_service.repository.TeamRepository;





@Service
public class TeamService {
	@Autowired
    private AthleteFeignClient athleteFeignClient;

    public List<Player> getPlayersByTeamId(int teamId) {
        // Calls AthleteService via Feign client to fetch players for a team
        return athleteFeignClient.getPlayersByTeamId(teamId);
    }

    private final TeamRepository teamRepository;
    public List<Team> getTeamsByCoachId(int coachId) {
        return teamRepository.findByCoachId(coachId);
    }

 
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    // Get team by ID
    public Team getTeamById(int teamId) {
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found"));
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
    @Transactional
    public Team createTeam(Team team) {
        Team createdTeam = teamRepository.save(team);
        updatePlayersTeamId(createdTeam.getTeamId(), team.getPlayerIds());
        return createdTeam;
    }

    // Update an existing team
    @Transactional
    public Team updateTeam(int teamId, Team updatedTeam) {
        Team existingTeam = teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found for ID: " + teamId));

        // Update team properties
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setSportCategory(updatedTeam.getSportCategory());
        existingTeam.setCoachId(updatedTeam.getCoachId());
        existingTeam.setPlayerIds(updatedTeam.getPlayerIds()); // Update player IDs

        // Save the updated team
        teamRepository.save(existingTeam);

        // Update players' teamId
        updatePlayersTeamId(existingTeam.getTeamId(), updatedTeam.getPlayerIds());

        return existingTeam;
    }

    // Delete a team
    @Transactional
    public void deleteTeam(int teamId) {
        Team team = teamRepository.findById(teamId)
            .orElseThrow(() -> new RuntimeException("Team not found for ID: " + teamId));

        // Remove teamId from players
        athleteFeignClient.removePlayersTeamId(teamId);
        teamRepository.delete(team);
    }

    // Helper method to update players' teamId
    private void updatePlayersTeamId(int teamId, List<Integer> playerIds) {
        athleteFeignClient.updatePlayersTeamId(teamId, playerIds);
    }
}

