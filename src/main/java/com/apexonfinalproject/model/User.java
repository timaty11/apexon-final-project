package com.apexonfinalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import java.util.Set;

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

    @Column(length = 255, name = "login", nullable = false, unique = true)
    private String login;

    @Column(length = 255, name = "password", nullable = false)
    private String password;

    @Column(length = 255, name = "email", nullable = false, unique = true)
    private String email;

    @Column(length = 255, name = "full_name", nullable = false)
    private String fullName;

    @Column(length = 255, name = "phone_number")
    private String phoneNumber;

    @Column(length = 255, name = "country")
    private String country;

    @Column(length = 255, name = "city")
    private String city;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @Column(name = "activated")
    @Builder.Default
    private Boolean activated = false;

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

}
