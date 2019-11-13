package mvc.mvc.model.services;

import mvc.mvc.model.entity.Role;
import mvc.mvc.model.entity.User;
import mvc.mvc.model.repos.RoleRepository;
import mvc.mvc.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserRoleDTO {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRoleDTO(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveRole(String role_name){
        if (role_name.isEmpty()){
            System.out.println("role is emty");
            return;
        }

        Role role = new Role();
        role.setName("ROLE_" + role_name.toUpperCase());
        roleRepository.save(role);
    }

    public void saveUser(String name, String pass, String role_name){
        User user = new User();
        user.setName(name);
        user.setPass(bCryptPasswordEncoder.encode(pass));
        user.setRole(roleRepository.findByName(role_name));
        userRepository.save(user);
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
}
