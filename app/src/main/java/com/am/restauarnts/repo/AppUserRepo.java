package com.am.restauarnts.repo;

import com.am.restauarnts.ui.models.User;
import com.orhanobut.hawk.Hawk;

public class AppUserRepo implements UserRepo{
    @Override
    public User getCurrentUser() {
        User user = new User();
        user.setId("u1");
        user.setName("Ala");
        user.setPhone("0592358722");
        return user;
    }

    @Override
    public void saveUser(User user) {
        Hawk.put("user",user);
    }

}
