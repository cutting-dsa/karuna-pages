package com.karuna.pages.core.config;

import com.karuna.pages.user.model.AppUser;
import org.springframework.util.Assert;

public class ThreadLocalContextUtil {
    public static final ThreadLocal<AppUser> userContext = new ThreadLocal<>();

    public static void setUser(final AppUser user) {
        Assert.notNull(user, "user cannot be null");
        userContext.set(user);
    }

    public static AppUser getUser() {
        return userContext.get();
    }
}
