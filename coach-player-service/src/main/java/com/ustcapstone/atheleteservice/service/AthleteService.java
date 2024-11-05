package com.ustcapstone.atheleteservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcapstone.atheleteservice.interfaces.TeamFeignClient;
import com.ustcapstone.atheleteservice.model.Coach;
import com.ustcapstone.atheleteservice.model.Player;
import com.ustcapstone.atheleteservice.model.Team;
import com.ustcapstone.atheleteservice.repository.AthleteRepository;
import com.ustcapstone.atheleteservice.repository.CoachRepository;

import java.util.Collections;
import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private  TeamFeignClient teamFeignClient;

   
    public AthleteService(TeamFeignClient teamFeignClient) {
        this.teamFeignClient = teamFeignClient;
    }
    //added by me
   
        public List<Player> getPlayersByTeamId(int teamId) {
            return athleteRepository.findByTeamId(teamId);
        }
        public List<Player> getPlayersByPlayerId(int playerId) {
            // Fetch the player using the playerId
            Player player = athleteRepository.findById(playerId).orElse(null);
            if (player != null) {
                int teamId = player.getTeamId(); // Retrieve the teamId
                if (teamId != 0) { // Assuming that 0 means no team
                    // If the player has a valid teamId, fetch all players in that team
                    return athleteRepository.findByTeamId(teamId);
                }
            }
            return List.of(); // Return an empty list if no valid teamId found
        }
    
    //me
        public void updatePlayersTeamId(int teamId, List<Integer> playerIds) {
            List<Player> players = athleteRepository.findAllById(playerIds);
            for (Player player : players) {
                player.setTeamId(teamId); // Update teamId for each player
                athleteRepository.save(player); // Save updated player
            }
        }

    public List<Team> getTeamsByCoachId(int coachId) {
        return teamFeignClient.getTeamsByCoachId(coachId);
    }

    // Player CRUD Operations
    public Player createPlayer(Player player) {
        return athleteRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return athleteRepository.findAll();
    }

    public Player getPlayerById(int id) {
        return athleteRepository.findById(id).orElse(null);
    }

    public Player updatePlayer(Player player) {
        return athleteRepository.save(player);
    }

    public void deletePlayer(int id) {
        athleteRepository.deleteById(id);
    }

    // Coach CRUD Operations
    public Coach createCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach getCoachById(int id) {
        return coachRepository.findById(id).orElse(null);
    }

    public Coach updateCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public void deleteCoach(int id) {
        coachRepository.deleteById(id);
    }
}
