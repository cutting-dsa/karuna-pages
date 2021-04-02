package com.karuna.pages.user.service;

import com.karuna.pages.core.config.ThreadLocalContextUtil;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.role.repository.RoleRepository;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<AppUser> getAllUsers() {
        AppUser user = ThreadLocalContextUtil.getUser();
        return userRepository.findAll();
    }

    @Override
    public AppUser getUser(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public AppUser saveUser(AppUser user) {

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
    public AppUser editUser(AppUser user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public AppUser disableUser(Long id) {
        AppUser user =  userRepository.getUserById(id);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public AppUser getLoggedInUser() {
        return ThreadLocalContextUtil.getUser();
    }


}
