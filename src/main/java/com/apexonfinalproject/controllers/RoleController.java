package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.services.RoleService;
import com.apexonfinalproject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
@Slf4j
public class RoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/")
    public String getControlPanelPage(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("listRoles", roles);
        return "roleControlPanel";
    }

    @GetMapping("/new")
    public String getRoleCreatePage(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "roleForm";
    }

    @PostMapping("/create")
    public String createRole(Role role) {
        log.info(role.toString());
        roleService.addRole(role);
        return "redirect:/roles/";
    }

    @GetMapping("/edit/{id}")
    public String getRoleEditPage(@PathVariable String id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "roleForm";
    }

    @PostMapping(value = "/update/{id}", consumes = "application/x-www-form-urlencoded")
    public String updateRole(@PathVariable String id, Role role) {
        roleService.updateRole(id, role);
        return "redirect:/roles/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return "redirect:/roles/";
    }

}
