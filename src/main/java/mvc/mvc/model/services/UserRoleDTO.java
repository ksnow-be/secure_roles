package mvc.mvc.model.services;

import mvc.mvc.model.entity.Role;
import mvc.mvc.model.entity.User;
import mvc.mvc.model.repos.RoleRepository;
import mvc.mvc.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserRoleDTO {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserRoleDTO(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void saveRole(String role_name){
        Role role = new Role();
        role.setName(role_name);
        roleRepository.save(role);
    }

    public void saveUser(String name, String pass, String role_name){
        User user = new User();
        user.setName(name);
        user.setPass(pass);
        user.setRole(roleRepository.findByName(role_name));
        userRepository.save(user);
    }
}
