package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import com.lastma.data.models.Officer;

import java.util.ArrayList;
import java.util.List;

public class OfficerRepositoryImpl implements OfficerRepository {
    private List<Officer> officers = new ArrayList<Officer>();
    private int officerCount = 0;

    @Override
    public Officer save(Officer officer) {
        officers.add(officer);
        officer.setId(++officerCount);
        return officer;
    }

    @Override
    public long count() {
        return officerCount;
    }

    @Override
    public Officer findById(int id) {
        for(int counter = 0; counter < officers.size(); counter++) {
            if(officers.get(counter).getId() == id) {
                return officers.get(counter);
            }
        }
        return null;
    }

    @Override
    public List<Officer> findAll() {
        return officers;
    }

    @Override
    public void deleteById(int officerId) {
        for(int counter = 0; counter < officers.size(); counter++) {
            if(officers.get(counter).getId() == officerId) {
                officers.remove(counter);
                officerCount--;
                break;
            }
        }
    }

    @Override
    public void deleteAll() {
        officers.clear();
        officerCount = 0;

    }

    @Override
    public boolean existsById(int officerId) {
        if(findById(officerId).getId() == officerId) {
            return true;
        }
        return false;
    }
}


