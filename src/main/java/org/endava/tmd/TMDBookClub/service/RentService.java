package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentService {

    private final RentRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RentService(RentRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void addRent(Long userId, Long bookId, LocalDate startDate , LocalDate endDate) {
        Rent rent = new Rent();
        rent.setUser(userRepository.findById(userId).get());
        rent.setBook(bookRepository.findById(bookId).get());
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        repository.save(rent);
    }

    public List<Rent> getAll() {
        return repository.findAll();
    }


}
