package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Adds a new User entry to the repository with an encoded password.
     *
     * @param user the User entity to be added
     * @return the saved User entity
     */
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Retrieves all User entities from the repository.
     *
     * @return a list of all User objects stored in the database
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a User entity by its unique identifier.
     *
     * @param id the unique identifier of the User to retrieve
     * @return the User entity if found, or {@code null} if no entity with the given
     *         id exists
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing User entry in the repository.
     * The password will be encoded before saving.
     *
     * @param user the User entity to update
     * @return the updated User entity
     */
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Deletes a User entry from the repository.
     *
     * @param id the unique identifier of the User to delete
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
