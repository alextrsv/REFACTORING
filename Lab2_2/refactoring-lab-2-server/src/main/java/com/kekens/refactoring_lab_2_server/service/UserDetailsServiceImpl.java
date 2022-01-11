package com.kekens.refactoring_lab_2_server.service;


import com.kekens.refactoring_lab_2_server.Repositories.UserRepository;
import com.kekens.refactoring_lab_2_server.entities.SecurityUser;
import com.kekens.refactoring_lab_2_server.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserDetails userDetails = SecurityUser.fromUser(user);
        return userDetails;
    }
}
