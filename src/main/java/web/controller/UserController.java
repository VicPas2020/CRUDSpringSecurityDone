package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/users", "/"})
    public String printUsers(Model model) {
        List<User> userList = userService.listUsers();

        model.addAttribute("users", userList);
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {

        model.addAttribute("user", new User());

        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("editUser", user);
        return "editUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/delete{id}")
    public String deleteUser(@PathVariable("id") int id){
        User user = userService.getById(id);
        userService.delete(user);

        return "redirect:/users";
    }
}
