package org.kxnvg.database.repository;

import lombok.RequiredArgsConstructor;
import org.kxnvg.database.ConnectionPool;

@RequiredArgsConstructor
public class UserRepository {

    private final ConnectionPool connectionPool;
}
