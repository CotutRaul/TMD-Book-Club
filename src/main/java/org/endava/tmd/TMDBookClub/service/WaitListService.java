package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitListService {

    @Autowired
    private WaitListRepository repository;


    public List<WaitList> getAll() {
        return repository.findAll();
    }
}
