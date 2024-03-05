package IdentityService.identityservice.repository;

import IdentityService.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String username);

    List<String> findRoleByName(String username);

}
