package com.utcn.sd_project.service;

import com.utcn.sd_project.model.User;
import com.utcn.sd_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsById(Long id) { return userRepository.existsById(id); }

    public User updateUser(Long id, User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setName(userDetails.getName());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setRole(userDetails.getRole());

            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean loginUser(String username, String password) {
        return password.equals(userRepository.findByUsername(username).getPassword());
    }
}
