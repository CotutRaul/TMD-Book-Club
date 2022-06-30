package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

}
