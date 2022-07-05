package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id)
    {
        return repository.findById(id);
    }

    public void addUser(User user)
    {
        User checkUser = repository.findUserByNameOrEmail(user.getName(),user.getEmail());
        if(checkUser == null)
            repository.save(user);
    }

    public void deleteUser(Long id)
    {
        repository.deleteById(id);
    }

    public void updateUser(User user) {
        if (repository.findById(user.getId()).isPresent())
            repository.save(user);
    }



}
