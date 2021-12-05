package com.crud.crud.controller;

import com.crud.crud.model.User;
import com.crud.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

    @RequestMapping("/users")
    public String findAll(Model model){
       List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users-list";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String addUserForm(User user){
        return "user-add";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(User user){
    userService.saveUser(user);
    return "redirect:/";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.GET)
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }



}
