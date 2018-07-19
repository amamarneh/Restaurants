package com.am.restauarnts.repo;

import com.am.restauarnts.ui.models.User;

public interface UserRepo {
    User getCurrentUser();
    void saveUser(User user);
}
