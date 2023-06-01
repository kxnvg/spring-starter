package com.kxnvg.service.service;

import com.kxnvg.database.dao.UserDao;
import com.kxnvg.service.dto.UserDto;

import java.util.Optional;

public class UserService {

    private final UserDao userDao = new UserDao();

    public Optional<UserDto> getUser(Long id) {
        return userDao.findById(id)
                .map(it -> new UserDto());

    }
}
