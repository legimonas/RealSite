package com.company.Dao;

import com.company.models.User;

import java.util.Optional;

public interface UsersDao extends CrudDao<User> {
    Optional<User> find(String usermail, String password);
    Optional<User> findbymail(String usermail);
}
