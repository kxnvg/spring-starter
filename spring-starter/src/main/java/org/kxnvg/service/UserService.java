package org.kxnvg.service;

import lombok.RequiredArgsConstructor;
import org.kxnvg.database.repository.CompanyRepository;
import org.kxnvg.database.repository.UserRepository;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

}
