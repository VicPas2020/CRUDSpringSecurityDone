package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.dao.UserRepository;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserRepository userService;

    @Autowired
    public UserController(UserRepository userService) {
        this.userService = userService;
    }

    @GetMapping({"/user"})
    public String printUser(Model model) {
        User user = userService.findByUsername(getCurrentUsername());
        model.addAttribute("user", user);
        //тестовый метод
//        User byUserAgeImp = userService.findByUserAgeImp(5);
//        System.out.println(byUserAgeImp);
        return "user";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth.getName());
        return auth.getName();
    }
}
