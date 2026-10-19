package com.lastma.data.repositories;

import com.lastma.data.models.Officer;
import com.lastma.data.models.Officer;

import java.util.List;

public interface OfficerRepository {

    Officer save(Officer officer);

    long count();

    Officer findById(int id);

    Officer findByEmail(String email);

    List<Officer> findAll();

    void deleteById(int officerId);

    void deleteAll();

    boolean existsById(int officerId);

}
