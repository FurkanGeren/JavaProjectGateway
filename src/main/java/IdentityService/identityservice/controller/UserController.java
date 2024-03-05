package IdentityService.identityservice.controller;


import IdentityService.identityservice.dto.AuthRequest;
import IdentityService.identityservice.dto.SignInRequest;
import IdentityService.identityservice.entity.User;
import IdentityService.identityservice.repository.UserRepository;
import IdentityService.identityservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;




    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(@AuthenticationPrincipal UserDetails userDetails){

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        boolean isAdmin = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("Role_Admin"));

        if (isAdmin && !userRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        } else {
            throw new RuntimeException("Invalid access");
        }

    }

}
