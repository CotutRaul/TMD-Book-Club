package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.endava.tmd.TMDBookClub.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WaitListService {

    @Autowired
    private WaitListRepository repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    public List<WaitList> getAll() {
        return repository.findAll();
    }

    public void addWaitList(Long userId, Long bookId) {
        WaitList waitList = new WaitList();
        waitList.setUser(userRepository.findById(userId).get());
        waitList.setBook(bookRepository.findById(bookId).get());
        waitList.setDate(LocalDate.now());

        repository.save(waitList);
    }
}
