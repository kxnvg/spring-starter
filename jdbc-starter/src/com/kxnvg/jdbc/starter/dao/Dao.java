package com.kxnvg.jdbc.starter.dao;

import com.kxnvg.jdbc.starter.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    boolean delete(K id);

    E save(E ticket);

    void update(E ticket);

    Optional<E> findById(K id);

    List<E> findAll();
}
