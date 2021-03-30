package com.karuna.pages.user.service;

import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.getUserByUsername(username);
        if(appUser != null){
            return buildUserForAuthentication(appUser, getUserAuthority(appUser.getRoles()));
            //new User(appUser.getUsername(), appUser.getPassword(), buildSimpleGrantedAuthorities(appUser.getRoles()));
        }else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private List<GrantedAuthority> getUserAuthority(Collection<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Collection<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    private UserDetails buildUserForAuthentication(AppUser user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
