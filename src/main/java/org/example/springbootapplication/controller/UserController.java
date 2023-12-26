package org.example.springbootapplication.controller;

import org.example.springbootapplication.model.User;
import org.example.springbootapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "home";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/create")
    public String createUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam("id") Long id){
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect:/";
    }

    @GetMapping("/user-update")
    public String editPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.editUser(user);
        return "redirect:/";
    }
}
