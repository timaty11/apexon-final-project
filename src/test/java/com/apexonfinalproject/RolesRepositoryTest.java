package com.apexonfinalproject;

import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.model.User;
import com.apexonfinalproject.repositories.RoleRepository;
import com.apexonfinalproject.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = ApexonFinalProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RolesRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = new Role("1", "ADMIN", "manage everything");
        Role savedRole = roleRepository.save(roleAdmin);
        Assertions.assertThat(savedRole.getId()).isNotEmpty();
    }

    @Test
    public void testCreateUsersWithDifferentRoles() {
        Role roleAdmin = new Role("1", "ADMIN", "manage everything");
        Role roleManager = new Role("2", "MANAGER", "manage products data");
        Role roleUser = new Role("3", "USER", "buy products");

        roleRepository.save(roleAdmin);
        roleRepository.save(roleManager);
        roleRepository.save(roleUser);

        Set<Role> adminSet = new HashSet<Role>();
        adminSet.add(roleAdmin);
        Set<Role> managerSet = new HashSet<Role>();
        managerSet.add(roleManager);
        Set<Role> userSet = new HashSet<Role>();
        userSet.add(roleUser);

        User userAdmin = new User(
                "530c02a1-1778-4013-a1a4-0bab689c8cab",
                "admin",
                encoder.encode("admin"),
                "admin@admin",
                "admin",
                "admin",
                "admin",
                "admin",
                adminSet,
                true
        );

        User userManager = new User(
                "c892db1b-265d-4e39-a03f-df474e3ab1c6",
                "manager",
                encoder.encode("manager"),
                "manager@manager",
                "manager",
                "manager",
                "manager",
                "manager",
                managerSet,
                true
        );

        User userUser = new User(
                "ef444972-e184-4e12-8427-3c1b364dbbf1",
                "user",
                encoder.encode("user"),
                "user@user",
                "user",
                "user",
                "user",
                "user",
                userSet,
                true
        );

        userRepository.save(userAdmin);
        userRepository.save(userManager);
        userRepository.save(userUser);
    }

}
