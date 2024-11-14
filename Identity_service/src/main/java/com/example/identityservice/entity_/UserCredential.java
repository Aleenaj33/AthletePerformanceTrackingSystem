package com.example.identityservice.entity_;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user_credential")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {

    @Id
    
    private String id;
    private String name; // this should be username to avoid errors when requesting token
    private String email;
    private String password;
}
