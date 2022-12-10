package com.apexonfinalproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @JsonIgnore
    private String id;

    @Column(length = 255, name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(length = 255, name = "role_description")
    private String roleDescription;

//    @ManyToMany() and change backwards the joinColumns thing
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String toString() {
        return roleName + ": " + roleDescription;
    }
}
