package com.utcn.sd_project.controller;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utcn.sd_project.exception.UserNotFoundException;
import com.utcn.sd_project.model.User;
import com.utcn.sd_project.service.UserDeviceService;
import com.utcn.sd_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDeviceService userDeviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/add-user")
    public User addUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/edit-user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setName(newUser.getName());
                    user.setRole(newUser.getRole());
                    return userService.saveUser(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/delete-user/{id}")
    String deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if (!userService.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userService.deleteUser(id);
        return "User with id " + id + " has been deleted successfully!";
    }

    @PostMapping("/link-device")
    void linkDevice(@RequestBody String data) {
        long clientID = 0L;
        long deviceID = 0L;

        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            clientID = jsonNode.get("value1").asLong();
            deviceID = jsonNode.get("value2").asLong();
        } catch (Exception e) {
            System.out.println("Eroare");
        }

        userDeviceService.linkDevice(clientID, deviceID);
    }

    @PostMapping("/login")
    Map<String, Object> login(@RequestBody String data) {
        Map<String, Object> response = new HashMap<>();

        String username = "";
        String password = "";

        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            username = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();
        } catch (Exception e) {
            System.out.println("Eroare");
        }

        User user = userService.getByUsername(username);

        response.put("login_result", userService.loginUser(username, password));
        response.put("user_role", user.getRole());
        response.put("user_id", user.getId());

        return response;
    }
}
