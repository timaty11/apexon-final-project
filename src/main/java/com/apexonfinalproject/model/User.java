package com.apexonfinalproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @JsonIgnore
    private String id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "activated")
    @Builder.Default
    private Boolean activated = false;

}
