package IdentityService.identityservice.service;

import IdentityService.identityservice.entity.User;
import IdentityService.identityservice.repository.RoleRepository;
import IdentityService.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(User user) {
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        /*List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRoleName("Role_Kasiyer"));
        roles.add(roleRepository.findByRoleName("Role_Admin")); // Örneğin, bir tane daha rol ekleyelim

        newUser.setRoles(roles);*/
        newUser.setRoles(Arrays.asList(roleRepository.findByRoleName("Role_Admin")));

        userRepository.save(newUser);
        return "user added to the system";
    }

    public List<String> userRole(String username){
        return userRepository.findRoleByName(username);
    }

    public String generateToken(String username,String roles) {
        //List role = userRole(username);
        return jwtService.generateToken(username,roles);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);

    }
}
