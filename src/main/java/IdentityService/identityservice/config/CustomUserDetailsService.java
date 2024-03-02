package IdentityService.identityservice.config;

import IdentityService.identityservice.entity.User;
import IdentityService.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByName(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı adı bulunamadı: "));

        String username1 = user.getUsername();
        String password = user.getPassword();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" +role.getRoleName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(username, password, authorities);

    }
}
