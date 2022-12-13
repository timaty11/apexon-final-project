package com.apexonfinalproject.services;

import com.apexonfinalproject.exceptions.UserNotFoundException;
import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class RoleService {

    private static final String ERROR_ROLE_NOT_FOUND_TEMPLATE = "Role with id: %s not found!";

    @Autowired
    private RoleRepository roleRepository;

    public void addRole(Role role) {
        log.info(role.toString());
        String id = UUID.randomUUID().toString();
        log.info("Create role with id: '{}'", id);
        role.setId(id);
        roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        log.info("Get all roles");
        return roleRepository.findAll();
    }

    public Role getRoleById(String id) {
        log.info("Get role data with id: '{}'", id);
        return roleRepository.findById(id).orElseThrow(() -> {
            log.error(String.format(ERROR_ROLE_NOT_FOUND_TEMPLATE, id));
            return new UserNotFoundException(String.format(ERROR_ROLE_NOT_FOUND_TEMPLATE, id));
        });
    }

    public void updateRole(String id, Role newRoleData) {
        log.info("Update role data with id: '{}'", id);
        if (!roleRepository.existsById(id)) {
            log.error(String.format(ERROR_ROLE_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_ROLE_NOT_FOUND_TEMPLATE, id));
        }

        Role prevRoleData = getRoleById(id);
        roleRepository.save(Role.builder()
                .id(id)
                .roleName(newRoleData.getRoleName() == null ? prevRoleData.getRoleName() : newRoleData.getRoleName())
                .roleDescription(newRoleData.getRoleDescription() == null ? prevRoleData.getRoleDescription() : newRoleData.getRoleDescription())
                .build()
        );
    }

    public void deleteRole(String id) {
        log.info("Delete role data with id: '{}'", id);
        if (!roleRepository.existsById(id)) {
            log.error(String.format(ERROR_ROLE_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_ROLE_NOT_FOUND_TEMPLATE, id));
        }
        roleRepository.deleteById(id);
    }

}
