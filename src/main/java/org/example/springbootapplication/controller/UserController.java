package org.example.springbootapplication.controller;

import org.example.springbootapplication.model.User;
import org.example.springbootapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }
    @GetMapping("/user-create")
    public ModelAndView createUserForm() {
        ModelAndView modelAndView = new ModelAndView("user-create");
        modelAndView.addObject("user", new User());
        return modelAndView;
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
    public ModelAndView editPage(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user-update");
        modelAndView.addObject("user", userService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.editUser(user);
        return "redirect:/";
    }
}
