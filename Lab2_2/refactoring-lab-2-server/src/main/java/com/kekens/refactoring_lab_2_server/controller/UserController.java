package com.kekens.refactoring_lab_2_server.controller;

import com.kekens.refactoring_lab_2_server.Repositories.RoleRepository;
import com.kekens.refactoring_lab_2_server.Repositories.UserRepository;
import com.kekens.refactoring_lab_2_server.entities.User;
import com.kekens.refactoring_lab_2_server.entities.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Set;

@RestController
public class UserController {

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("user/login")
    public String sayHi(@RequestBody UserDTO userDTO,  HttpServletResponse response){
        BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(12);

        User user = userRepository.findByUsername(userDTO.getUserName());

        if (encoder.matches(userDTO.getPassword(), user.getPassword())) {
            response.setStatus(200);
            return "Ok";
        }
        else{
            try {
                response.sendError(404, "auth invalid");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "auth error";
    }

    @PostMapping("user/signup")
    @Transactional
    public String addNewStudent(@RequestBody UserDTO userDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        User newUser = new User(userDTO.getUserName(), encoder.encode(userDTO.getPassword()));
        newUser.setRoles(Set.of(roleRepository.findById(3).get()));

        userRepository.save(newUser);

        return "user was signed in";
    }
}