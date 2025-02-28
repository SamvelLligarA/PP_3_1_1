package org.example.pp_3_1_1.controller;

import jakarta.validation.Valid;
import org.example.pp_3_1_1.model.User;
import org.example.pp_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }

        userService.addUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        Optional<Optional<User>> foundUser = Optional.ofNullable(userService.findById(id));

        if (foundUser.isPresent()) {
            model.addAttribute("user", foundUser.get());
            return "edituser";
        } else {
            model.addAttribute("errorMessage", "Пользователь с таким ID не найден");
            return "error";
        }
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edituser";
        }

        userService.editUser(user);
        return "redirect:/users/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }
}