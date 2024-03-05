package usermanagenentmodule.usermanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usermanagenentmodule.usermanagement.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    Role findByRoleName(String roleName);

}
