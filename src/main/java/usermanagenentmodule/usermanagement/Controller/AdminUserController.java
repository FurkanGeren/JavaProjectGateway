package usermanagenentmodule.usermanagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usermanagenentmodule.usermanagement.Entity.User;
import usermanagenentmodule.usermanagement.Service.Interface.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AdminUserController {

    @Autowired
    private IUserService iuserService;

    @PostMapping("/adduser/{id}")
    public String addNewUser(@RequestBody User user,@PathVariable List<Long> id){
        return iuserService.saveUser(user,id);
    }

    @GetMapping("/all")
    public List<User> getUser(){return iuserService.getAll();}

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return iuserService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        return iuserService.updateUser(id, updatedUser);
    }

    @PutMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return iuserService.deleteUser(id);
    }

    @GetMapping("/get-deleted")
    public List<User> getDeletedUser(){return iuserService.getAllDeleted();}
}
