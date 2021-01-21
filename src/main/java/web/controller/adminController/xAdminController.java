//package web.controller.adminController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import web.model.Role;
//import web.model.User;
//import web.service.RoleService;
//import web.service.UserService;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private UserService userService;
//    private RoleService roleService;
//
//    @Autowired
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping(value = {"/users", "/"})
//    public String printUsers(Model model) {
//        List<User> userList = userService.listUsers();
//        model.addAttribute("users", userList);
//        //System.out.println("USERS!!!!!");
//
//        return "users";
//    }
//
//    @GetMapping("/addUser")
//    public String addUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "addUser";
//    }
//
//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    public String addUser(@ModelAttribute("user") User user,
//                          @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
//                          @RequestParam(value = "roleUser", required = false) String roleUser) {
//        Set<Role> roles = new HashSet<>();
//        if (roleAdmin != null) {
//            roles.add(roleService.findRoleByRoleName(roleAdmin));
//        }
//        if (roleUser != null) {
//            roles.add(roleService.findRoleByRoleName(roleUser));
//        }
//        user.setRoles(roles);
//        userService.add(user);
//        return "redirect:/admin/users";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        User user = userService.getById(id);
//        Set<String> roles = new HashSet<>();
//        user.getRoles().forEach(e -> roles.add(e.getRole()));
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roles);
//        return "editUser";
//    }
//
//    @PostMapping("/edit")
//    public String editUser(@ModelAttribute("user") User user,
//                           @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
//                           @RequestParam(value = "roleUser", required = false) String roleUser) {
//
//        Set<Role> roles = new HashSet<>();
//        if (roleAdmin != null) {
//            roles.add(roleService.findRoleByRoleName(roleAdmin));
//        }
//        if (roleUser != null) {
//            roles.add(roleService.findRoleByRoleName(roleUser));
//        }
//        user.setRoles(roles);
//        userService.edit(user);
//        return "redirect:/admin/users";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id){
//        userService.delete(userService.getById(id));
//
//        return "redirect:/admin/users";
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
////
////@Controller
//////@RequestMapping("/admin")
////public class AdminController {
////    private UserService userService;
////    private RoleService roleService;
////
////    @Autowired
////    public AdminController(UserService userService, RoleService roleService) {
////        this.userService = userService;
////        this.roleService = roleService;
////    }
////
////    @GetMapping(value = { "/", "/admin/users"})
////    public String printUsers(Model model) {
////        List<User> userList = userService.listUsers();
////        model.addAttribute("users", userList);
////        return "users";
////    }
////
////    @GetMapping("/admin/addUser")
////    public String addUser(Model model) {
////        User user = new User();
////        model.addAttribute("user", user);
////        return "addUser";
////    }
////
////    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
////    public String addUser(@ModelAttribute("user") User user,
////                          @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
////                          @RequestParam(value = "roleUser", required = false) String roleUser) {
////        Set<Role> roles = new HashSet<>();
////        if (roleAdmin != null) {
////            roles.add(roleService.findRoleByRoleName(roleAdmin));
////        }
////        if (roleUser != null) {
////            roles.add(roleService.findRoleByRoleName(roleUser));
////        }
////        user.setRoles(roles);
////        userService.add(user);
////        return "redirect:/admin/users";
////    }
////
////    @GetMapping("/admin/edit/{id}")
////    public String editUser(@PathVariable("id") Long id, Model model) {
////        User user = userService.getById(id);
////        Set<String> roles = new HashSet<>();
////        user.getRoles().forEach(e -> roles.add(e.getRole()));
////        model.addAttribute("user", user);
////        model.addAttribute("roles", roles);
////        return "editUser";
////    }
////
////    @PostMapping("/admin/edit")
////    public String editUser(@ModelAttribute("user") User user,
////                           @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
////                           @RequestParam(value = "roleUser", required = false) String roleUser) {
////
////        Set<Role> roles = new HashSet<>();
////        if (roleAdmin != null) {
////            roles.add(roleService.findRoleByRoleName(roleAdmin));
////        }
////        if (roleUser != null) {
////            roles.add(roleService.findRoleByRoleName(roleUser));
////        }
////        user.setRoles(roles);
////        userService.edit(user);
////        return "redirect:/admin/users";
////    }
////
////    @GetMapping("/admin/delete/{id}")
////    public String deleteUser(@PathVariable("id") Long id){
////        userService.delete(userService.getById(id));
////
////        return "redirect:/admin/users";
////    }
////}
