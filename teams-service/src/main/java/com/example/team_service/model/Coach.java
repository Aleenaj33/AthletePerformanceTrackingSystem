package com.example.team_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coaches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    private int coachId;
    private String name;
    private String sport;
    private int age;
    private int email;
  
    private List<Integer> teamIds; // List of team IDs
}
