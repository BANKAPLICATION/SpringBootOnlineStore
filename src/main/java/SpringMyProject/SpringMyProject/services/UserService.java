package SpringMyProject.SpringMyProject.services;

import SpringMyProject.SpringMyProject.entities.Comments;
import SpringMyProject.SpringMyProject.entities.Roles;
import SpringMyProject.SpringMyProject.entities.Users;
import SpringMyProject.SpringMyProject.repositories.RoleRepository;
import SpringMyProject.SpringMyProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(s);
        if(user!=null){
            System.out.println(user.getEmail());
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
    public Users findByEmail (String email) {
        return userRepository.findByEmail(email);
    }

    public Users getUser (Long user_id) {

        Optional<Users> opt = userRepository.findById(user_id);
        return opt.orElse(null);
    }
    public Roles getRoleByRoleName(String s) {
        return roleRepository.findByRole(s);
    }
    public Users addUser(Users user) {
        return userRepository.save(user);
    }
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }
}
