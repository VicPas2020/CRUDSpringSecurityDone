package web.controller.adminController.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class EditController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public EditController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(e -> roles.add(e.getRole()));
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "editUser";
    }



    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
                           @RequestParam(value = "roleUser", required = false) String roleUser) {

        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(roleService.findRoleByRoleName(roleAdmin));
        }
        if (roleUser != null) {
            roles.add(roleService.findRoleByRoleName(roleUser));
        }
        user.setRoles(roles);
        userService.edit(user);
        return "redirect:/admin/users";
    }

}
