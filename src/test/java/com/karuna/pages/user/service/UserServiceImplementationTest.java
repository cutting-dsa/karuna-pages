package com.karuna.pages.user.service;

import com.karuna.pages.core.config.ThreadLocalContextUtil;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceImplementationTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImplementation userService;

    @Test
    void getAllUsersTest() {

        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1L,"SUPER_ADMIN"));
        roles.add(new Role(2L,"VISITOR"));

        List<AppUser> userList = new ArrayList<>();
        AppUser user1 = new AppUser(1L,"username","firstName", "lastName", "password", 1, roles);
        AppUser user2 = new AppUser(1L,"username","firstName", "lastName", "password", 1, roles);
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        Collection<AppUser> users = userService.getAllUsers();
        Assert.assertEquals(userList.size(), users.size());
        ArrayList<AppUser> actualResult = new ArrayList<>(userList);
        Assert.assertEquals(userList.get(0).getUsername(), actualResult.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserEnabledTest(){
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1L,"SUPER_ADMIN"));
        roles.add(new Role(2L,"VISITOR"));

        List<AppUser> userList = new ArrayList<>();
        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, roles);userList.add(user1);
        AppUser user2 = new AppUser(2L,"kiwa","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1Jq", 1, roles);
        userList.add(user1);
        userList.add(user2);

        when(userRepository.getAppUsersByUsernameAndEnabled("username", 1)).thenReturn(userList);

        AppUser user = userService.getUserEnabled("username", "password");
        Assert.assertEquals(userList.get(0).getUsername(), user.getUsername());
    }

    @Test
    void getUserEnabledShouldFailTest(){
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1L,"SUPER_ADMIN"));
        roles.add(new Role(2L,"VISITOR"));

        List<AppUser> userList = new ArrayList<>();
        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tp/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, roles);userList.add(user1);
        AppUser user2 = new AppUser(2L,"kiwa","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1Jq", 1, roles);
        userList.add(user1);
        userList.add(user2);

        when(userRepository.getAppUsersByUsernameAndEnabled("username", 1)).thenReturn(userList);

        try {
            AppUser user = userService.getUserEnabled("username", "password");
        }catch (Exception exception){
            if(exception.getMessage().equals("Failed to login. Please check your credentials and try again")){
                // test has passed

            }else {
                Assert.fail("BadRequestException should have been thrown");
            }
        }

    }

    @Test
    void getUserTest(){
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1L,"SUPER_ADMIN"));
        roles.add(new Role(2L,"VISITOR"));

        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, roles);

        when(userRepository.getUserById(1L)).thenReturn(user1);

        AppUser user = userService.getUser(1L);
        Assert.assertEquals(user1.getUsername(), user.getUsername());
    }

    @Test
    void disableUserTest(){
        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, new ArrayList<>());
        AppUser user2 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, new ArrayList<>());
        user2.setEnabled(0);

        when(userRepository.getUserById(1L)).thenReturn(user1);
        when(userRepository.saveAndFlush(user1)).thenReturn(user2);

        AppUser user = userService.disableUser(1L);
        Assert.assertEquals(user2.getEnabled(), user.getEnabled());
    }

    @Test
    void getLoggedInUserTest(){
        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, new ArrayList<>());

        ThreadLocalContextUtil.setUser(user1);
        AppUser user = userService.getLoggedInUser();

        Assert.assertEquals(user1, user);

    }

    @Test
    void saveUserTest(){
        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "password", 1, new ArrayList<>());
        user1.setId(null);
        user1.setEnabled(null);
        AppUser user2 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, new ArrayList<>());
        user2.setEnabled(1);

        when(userRepository.save(any(AppUser.class))).thenReturn(user2);

        AppUser user = userService.saveUser(user1);
        Assert.assertEquals(user2.getEnabled(), user.getEnabled());
    }

    @Test
    void saveUserShouldFailTest(){
        AppUser user1 = null;
        AppUser user2  = new AppUser();

        when(userRepository.save(any(AppUser.class))).thenReturn(user2);
        try {
            AppUser user = userService.saveUser(user1);
        }catch (Exception exception){
            if(exception.getMessage().equals("No user details to save")){
                // test has passed

            }else {
                Assert.fail("BadRequestException should have been thrown with message : " + "No user details to save");
            }
        }
    }

    @Test
    void editUserNoRolesShouldFailTest(){

        AppUser user1 = new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, new ArrayList<>());
        AppUser user2 = new AppUser(1L,"username","newFirstName", "lastName",
                "joker", 1, new ArrayList<>());
        user2.setId(null);
        user2.setFirstName("newFirstName");
        user2.setEnabled(1);

        AppUser user3 = new AppUser(1L,"username","newFirstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, new ArrayList<>());

        when(userRepository.getUserById(1L)).thenReturn(user1);
        when(userRepository.saveAndFlush(any(AppUser.class))).thenReturn(user3);

        try {
            AppUser user = userService.editUser(user2, 1L);
        }catch (Exception exception){
            if(exception.getMessage().equals("User must have at least one role")){
                // test has passed

            }else {
                Assert.fail("BadRequestException should have been thrown with message : " + "User must have at least one role");
            }
        }
    }
}
