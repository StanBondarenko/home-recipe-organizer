package com.stanbond.homerecipeorganizer.security.user;

import com.stanbond.homerecipeorganizer.DAO.entites.Role;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoUserRole;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import com.stanbond.homerecipeorganizer.repositores.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final RoleRepository roleRepository;
    private final DaoUserRole userRoleDao;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        if (userRepository.existsUsersByLogin(user.getLogin())) {
            throw new IllegalStateException("Login has already been selected by another user, please select another one!");
        }
        if (userRepository.existsUsersByEmail(user.getEmail())) {
            throw new IllegalStateException("Email has already been selected by another user, please select another one!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) {

        User user = userRepository.findByLoginOrEmail(identifier, identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<String> roles = userRoleDao.findRoleNamesByUserId(user.getUserId());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getLogin(),
                user.getPassword(),
                roles
        );
    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("User not found"));
    }
    public void setRoleToUser(long userId, String roleName){
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new NotFoundException("Role: "+roleName+" not found"));
        userRoleDao.createNewUserRole(userId,role.getRoleId());
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserByLogin(Principal principal){
            return userRepository.findByLogin(principal.getName())
                    .orElseThrow(()->new NotFoundException("No user with tis login"));
    }
    public User getUserByLoginOrEmail(Principal principal){
        return userRepository.findByLoginOrEmail(principal.getName(), principal.getName())
                .orElseThrow(()-> new NotFoundException("No user"));
    }
}
