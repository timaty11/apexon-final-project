package com.apexonfinalproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

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

    @Override
    public String toString() {
        return roleName + ": " + roleDescription;
    }
}
