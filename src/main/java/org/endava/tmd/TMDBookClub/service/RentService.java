package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    public List<Rent> getAll() {
        return repository.findAll();
    }

    public void addRent(Long userId, Long bookId, int period) {
        Rent rent = new Rent();
        rent.setUser(userRepository.findById(userId).orElse(null));
        rent.setBook(bookRepository.findById(bookId).orElse(null));
        rent.setStartDate(LocalDate.now());
        rent.setEndDate(LocalDate.now().plusWeeks(period));
        repository.save(rent);
    }

    public void extendPeriod(Long id, int period)
    {
        Rent rent = repository.findById(id).orElse(null);
        rent.setEndDate(rent.getEndDate().plusWeeks(period));
        repository.save(rent);
    }

    public void extendPeriod(Long userId, Long bookId, int period)
    {
        Rent rent = repository.findRentByUserIdAndBookId(userId, bookId);
        rent.setEndDate(rent.getEndDate().plusWeeks(period));
        repository.save(rent);
    }
}
