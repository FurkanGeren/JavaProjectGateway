package usermanagenentmodule.usermanagement.Service.Interface;

import org.springframework.stereotype.Service;
import usermanagenentmodule.usermanagement.Entity.Role;
import usermanagenentmodule.usermanagement.Entity.User;

import java.util.List;
import java.util.Optional;

@Service  // sonradan ekledim
public interface IUserService {

    String saveUser(User user,List<Long> roleIds)throws RuntimeException;

    List<User> getAll();

    String updateUser(Long userId, User updatedUser) throws RuntimeException;

    String deleteUser(Long userId) throws RuntimeException;

    Optional<User> getUserById(Long id);

    List<User> getAllDeleted();
}
