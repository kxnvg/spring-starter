package org.kxnvg.database.repository;

import org.kxnvg.dto.PersonalInfo;
import org.kxnvg.dto.UserFilter;
import org.kxnvg.entity.Role;
import org.kxnvg.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
