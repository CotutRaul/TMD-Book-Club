package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.endava.tmd.TMDBookClub.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.WEEKS;

@Service
public class RentService {

    @Autowired
    private RentRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WaitListRepository waitListRepository;


    public List<Rent> getAll() {
        return repository.findAll();
    }

    public List<Rent> getActiveRents(){
        return repository.findActiveRents();
    }

    public void addRent(Long userId, Long bookId, int period) {
        if(!repository.findIfBookIsRented(bookId) && period>0 && period<=4) {
            Rent rent = new Rent();
            rent.setUser(userRepository.findById(userId).orElse(null));
            rent.setBook(bookRepository.findById(bookId).orElse(null));
            rent.setStartDate(LocalDate.now());
            rent.setEndDate(LocalDate.now().plusWeeks(period));
            Long waitListId = waitListRepository.findIdByIdUserAndIdBook(userId,bookId);
            if(waitListId != null)
                waitListRepository.deleteById(waitListId);
            repository.save(rent);
        }
    }

    public void extendPeriod(Long id, int period)
    {
        if(period > 0 && period <= 2)
        {
            Rent rent = repository.findById(id).orElse(null);
            if(rent.getEndDate().compareTo(LocalDate.now())>=0)
            {
                rent.setEndDate(rent.getEndDate().plusWeeks(period));
            }
            if(WEEKS.between(rent.getStartDate(),rent.getEndDate())<=6){
                repository.save(rent);
            }
        }
    }

    public void extendPeriod(Long userId, Long bookId, int period)
    {
        if(period > 0 && period <= 2)
        {
            Rent rent = repository.findRentByUserIdAndBookId(userId, bookId);
            if(rent.getEndDate().compareTo(LocalDate.now())>=0)
            {
                rent.setEndDate(rent.getEndDate().plusWeeks(period));
            }
            if(WEEKS.between(rent.getStartDate(),rent.getEndDate())<=6){
                repository.save(rent);
            }
        }
    }

    public LocalDate getLastEndDateBookWasRented(Long bookId)
    {
        return repository.findLastEndDateBookWasRented(bookId);
    }
}
