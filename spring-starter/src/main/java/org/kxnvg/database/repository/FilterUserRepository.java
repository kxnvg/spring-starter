package org.kxnvg.database.repository;

import org.kxnvg.dto.UserFilter;
import org.kxnvg.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
