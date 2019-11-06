package mvc.mvc.model.security;

import mvc.mvc.model.entity.User;
import mvc.mvc.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);
        if (user == null) throw new UsernameNotFoundException(userName + " not found");

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        System.out.println(customUserDetails.getUsername() + " " + customUserDetails.getPassword() + " " + customUserDetails.getAuthorities());

        return new CustomUserDetails(user);
    }
}
