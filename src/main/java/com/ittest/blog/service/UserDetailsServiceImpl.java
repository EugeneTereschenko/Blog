package com.ittest.blog.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ittest.blog.models.User;
import com.ittest.blog.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ittest.blog.config.ApplicationUserRole.ADMIN;

@Service
@Slf4j
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final long serialVersionUID = 1L;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        log.info("Saving new user to the database", user.getUsername());
        System.out.println("Show password" + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Show password" + user.getPassword());
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        log.info("Get user from the database", id);
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        log.info("Get users from the database", users);
        return users;
    }

    public void deleteUser(Long id) {
        log.info("Delete user from the database", id);
        User user = userRepository.getById(id);
        userRepository.delete(user);
    }

    public User updateUser(Long id, User user) {
        log.info("Update user into the database", user);
        User userDto = userRepository.getById(id);
        userDto.setUsername(user.getUsername());
        System.out.println("Show password" + user.getPassword());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto = userRepository.save(userDto);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities = ADMIN.getGrantedAuthorities().stream().collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
