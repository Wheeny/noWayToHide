package com.lastma.services;

import com.lastma.data.models.Officer;

public interface OfficerService {

    void register(String fullName, String email, String password);

    Officer login(String email, String password);

    Officer findOfficerById(int id);
}
