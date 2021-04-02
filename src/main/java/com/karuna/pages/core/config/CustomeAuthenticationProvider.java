package com.karuna.pages.core.config;

import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AppUser user = userRepository.getUserByUsername(username);

        if(user != null && StringUtils.equals(user.getUsername(),username) && bCryptPasswordEncoder.matches(password, user.getPassword())){
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password, getUserAuthority(user.getRoles()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            ThreadLocalContextUtil.setUser(user);

            return auth;
        }else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getUserAuthority(Collection<Role> userRoles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        userRoles.forEach((role) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return grantedAuthorities;
    }
}
