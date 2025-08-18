package com.valkyrie.authentication_service.service;

import com.valkyrie.authentication_service.config.TokenConfig;
import com.valkyrie.authentication_service.model.Store;
import com.valkyrie.authentication_service.model.User;
import com.valkyrie.authentication_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(12);

    private UserRepo repo;
    @Autowired
    private void setRepo(UserRepo repo) {this.repo = repo;}

    private AuthenticationManager authenticationManager;
    @Autowired
    private void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {this.config = config;}

    public Store<String> signIn(User user) {

        if (repo.findById(user.getUsername()).orElse(null) != null) {
            return Store.initialize(HttpStatus.OK, "Plz log in");
        }

        repo.save(user.setPassword(ENCODER.encode(user.getPassword())));
        return Store.initialize(HttpStatus.OK, "The User saved Successfully....");
    }

    public Store<String> logIn(User user) {
        String token = null;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            token = config.generateToken(user.getUsername(), authentication.getAuthorities());
            return Store.initialize(HttpStatus.OK, token);
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, "No Token Generated...");
    }

    public Store<User> getUser(String username) {
        User user = repo.findById(username).orElse(null);

        return Store.initialize(HttpStatus.OK, user);
    }
}
