package usermanagenentmodule.usermanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import usermanagenentmodule.usermanagement.Entity.Role;
import usermanagenentmodule.usermanagement.Entity.User;
import usermanagenentmodule.usermanagement.Repository.RoleRepository;
import usermanagenentmodule.usermanagement.Repository.UserRepository;
import usermanagenentmodule.usermanagement.Service.Interface.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveUser(User user,List<Long> roleIds) throws RuntimeException{
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        //newUser.setRoles(Arrays.asList(roleRepository.findByRoleName("Role_Manager")));

        List<Role> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
            roles.add(role);
        }

        newUser.setRoles(roles);

        userRepository.save(newUser);
        return "user added to the system";
    }

    @Override
    public String updateUser(Long userId, User updatedUser) throws RuntimeException{
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Güncellenmiş bilgileri atama
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        // Kullanıcıyı kaydet
        userRepository.save(existingUser);

        return "User updated successfully";
    }

    @Override
    public String deleteUser(Long userId) throws RuntimeException{
        User deletedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        deletedUser.setDeleted(true);

        userRepository.save(deletedUser);

        return "User deleted successfully";
    }

    @Override
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll(){
        return userRepository.findByDeletedFalse();
    }

    @Override
    public List<User> getAllDeleted(){
        return userRepository.findByDeletedTrue();
    }



}
