package org.example.springbootapplication.service;

import jakarta.transaction.Transactional;
import org.example.springbootapplication.model.User;
import org.example.springbootapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void editUser(User user) {
        userRepository.save(user);
    }
}
