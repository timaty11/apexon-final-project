package com.apexonfinalproject.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class User {

    private String id;
    private String login;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String country;
    private String city;

}
