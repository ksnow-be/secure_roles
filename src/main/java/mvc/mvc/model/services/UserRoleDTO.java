package mvc.mvc.model.services;

import mvc.mvc.model.components.MyLogger;
import mvc.mvc.model.entity.Role;
import mvc.mvc.model.entity.User;
import mvc.mvc.model.repos.RoleRepository;
import mvc.mvc.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class UserRoleDTO {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MyLogger logger;

    @Autowired
    public UserRoleDTO(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MyLogger logger) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.logger = logger;
    }

    public void saveRole(String role_name){
        if (role_name.isEmpty()){
            return;
        }
        Role role = new Role();
        role.setName("ROLE_" + role_name.toUpperCase());
        roleRepository.save(role);
        logger.addRole(role_name);
    }

    public void saveUser(String name, String pass, String role_name){
        User user = new User();
        user.setName(name);
        user.setPass(bCryptPasswordEncoder.encode(pass));
        user.setRole(roleRepository.findByName(role_name));
        userRepository.save(user);
        logger.addUser(name);
    }

    public void changeRole(String userName, String roleName){
        User user = userRepository.findByName(userName);

        if (user.getRole().getName().equals(roleName)){
            return ;
        }
        else {
            Role role = roleRepository.findByName(roleName);
            user.setRole(role);
            userRepository.save(user);
            if (user.getName().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            {
                Set<GrantedAuthority> actualAuth = new HashSet<>();
                actualAuth.add(new SimpleGrantedAuthority(user.getRole().getName()));
                Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getName(), user.getPass(), actualAuth);
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            }
            logger.updateRole(user.getName(), role.getName());
        }
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
