package repository.security;


import model.Right;
import model.Role;



/**
 * Created by Alex on 11/03/2017.
 */
public interface RightsRolesRepository {

    void addRole(String role);

    void addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    Right findRightByTitle(String right);

    void addRoleRight(Long roleId, Long rightId);
    
    Role getRoleOfEmployee(Long employeeId);
}
