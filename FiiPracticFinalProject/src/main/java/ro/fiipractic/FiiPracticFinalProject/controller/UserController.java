package ro.fiipractic.FiiPracticFinalProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.service.PasswordEncryptionService;
import ro.fiipractic.FiiPracticFinalProject.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncryptionService passwordEncryptionService;

    @Autowired
    public UserController(UserService userService, PasswordEncryptionService passwordEncryptionService){
        this.userService = userService;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody User user) {
        user.setPassword(passwordEncryptionService.eencryptPassword(user.getPassword()));
        userService.registerUser(user);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
