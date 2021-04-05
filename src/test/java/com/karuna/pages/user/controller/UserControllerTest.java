package com.karuna.pages.user.controller;

import com.karuna.pages.review.controller.ReviewController;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.service.ReviewService;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {


    @InjectMocks
    UserController userController;

    @Mock
    private UserService userService;

    private AppUser stubUser() {
        Collection<Role> roles = new ArrayList<>();
        roles.add(new Role(1L,"SUPER_ADMIN"));
        roles.add(new Role(2L,"VISITOR"));

        return new AppUser(1L,"username","firstName", "lastName",
                "$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6", 1, roles);

    }

    @Test
    void getAllTest() {
        List<AppUser> allUsers = Arrays.asList(stubUser());

        when(userService.getAllUsers()).thenReturn(allUsers);

        Collection<AppUser> result = userController.getAll();

        ArrayList<AppUser> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getUsername()).isEqualTo(stubUser().getUsername());

        verify(userService, times(1)).getAllUsers();

    }

    @Test
    void createUserTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.saveUser(any(AppUser.class))).thenReturn(stubUser());

        AppUser response = userController.createUser(stubUser());

        assertThat(response.getUsername()).isEqualTo(stubUser().getUsername());

        verify(userService, times(1)).saveUser(any(AppUser.class));
    }

    @Test
    void editUserTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.editUser(any(AppUser.class),anyLong())).thenReturn(stubUser());

        AppUser response = userController.editUser(stubUser(),1L);

        assertThat(response.getUsername()).isEqualTo(stubUser().getUsername());

        verify(userService, times(1)).editUser(any(AppUser.class),anyLong());

    }

    @Test
    void login() {
        when(userService.getUserEnabled(anyString(),anyString())).thenReturn(stubUser());

        AppUser response = userController.login("username","$2a$10$oiFjEXUH6AbAr3tpZIcpdu/kqJ/w0Gu0EVNO3vHN97a1JqON6vo/6");

        assertThat(response.getUsername()).isEqualTo(stubUser().getUsername());

        verify(userService, times(1)).getUserEnabled(anyString(),anyString());
    }

}