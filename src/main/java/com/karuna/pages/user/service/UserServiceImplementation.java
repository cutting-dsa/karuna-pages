package com.karuna.pages.user.service;

import com.karuna.pages.core.config.ThreadLocalContextUtil;
import com.karuna.pages.core.exceptions.BadRequestException;
import com.karuna.pages.core.exceptions.ResourceNotFoundException;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.role.repository.RoleRepository;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUser(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public AppUser saveUser(AppUser user) {

        if(user == null) throw new BadRequestException("No user details to save");

        if(user.getPassword() == null) throw new BadRequestException("User must have a passwrod");

        Collection<Role> userRoles = user.getRoles();

        Collection<Role> roles = new ArrayList<>();

        for(Role r: userRoles){
            Role role = roleRepository.getRoleById(r.getId());
            roles.add(role);
        }

        user.setRoles(roles);

        if(user.getPassword() != null) user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public AppUser editUser(AppUser user, Long id)  {

        if(id == null) throw new BadRequestException("User id cannot be null");

        if(user == null) return null;

        AppUser savedUser = userRepository.getUserById(id);

        if(savedUser == null) throw new ResourceNotFoundException("User with id " + id + " not found");

        String password = null;
        if(user.getPassword() != null) {
            password = bCryptPasswordEncoder.encode(user.getPassword());
        }

        Collection<Role> newRoles = user.getRoles();
        Collection<Role> roles = new ArrayList<>();

        if(newRoles != null){
            if(newRoles.isEmpty()) throw new BadRequestException("User must have at least one role");

            for(Role role: newRoles){
                if(role.getId() == null) throw new BadRequestException("Role id cannot be null");
                Role roleE = roleRepository.getRoleById(role.getId());
                roles.add(roleE);
            }

        }

        savedUser.editUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEnabled(), password, roles);

        return userRepository.saveAndFlush(savedUser);
    }

    @Override
    public AppUser disableUser(Long id) {
        if(id == null) throw new BadRequestException("User id cannot be null");

        AppUser user =  userRepository.getUserById(id);
        if(user == null) throw new ResourceNotFoundException("User with id " + id + " not found");
        user.disable();

        return userRepository.saveAndFlush(user);
    }

    @Override
    public AppUser getLoggedInUser() {
        return ThreadLocalContextUtil.getUser();
    }

    @Override
    public AppUser getUserEnabled(String username, String password) {
        Collection<AppUser> users = userRepository.getAppUsersByUsernameAndEnabled(username, 1);
        Optional<AppUser> user = users.stream().filter(u -> bCryptPasswordEncoder.matches(password, u.getPassword())).findAny();

        if(!user.isPresent()){
            throw new BadCredentialsException("Failed to login. Please check your credentials and try again");
        }

        return user.get();
    }


}
