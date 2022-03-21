package com.example.restcrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + "was not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public void registerNewUser(User user) {
        User newUser = new User();
        newUser.setUserName(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
    }

    public void updateUser(User user, User oldUser) {
        User updatedUser = oldUser;
        if (user.getUsername().length() > 0) {
            updatedUser.setUserName(user.getUsername());
        }
        if (user.getFirstName().length() > 0) {
            updatedUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName().length() > 0) {
            updatedUser.setLastName(user.getLastName());
        }
        if (user.getEmail().length() > 0) {
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getDateOfBirth() != null) {
            updatedUser.setDateOfBirth(user.getDateOfBirth());
        }
        if (user.getPassword().length() > 0) {
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(updatedUser);
    }

}
