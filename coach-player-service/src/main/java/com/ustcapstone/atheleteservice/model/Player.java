package com.ustcapstone.atheleteservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;

import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
@Document(collection = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Player {
	    @Id
	    private int playerId;
	    private String name;
	    private String sport;
	    private int teamId; // Reference to Team in TeamService

<<<<<<< HEAD
=======
	
>>>>>>> ecbe57c16e4c2bc1bb20b939069fc2eaf97cbc6d

}
