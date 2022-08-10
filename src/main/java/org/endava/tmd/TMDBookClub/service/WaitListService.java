package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.dto.BookDTO;
import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.endava.tmd.TMDBookClub.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WaitListService {

    @Autowired
    private WaitListRepository repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentRepository rentRepository;


    public List<WaitList> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<List<BookDTO>> getWaitListForUser(Long userId) {
        List<WaitList> waitList = repository.findWaitListByUser_Id(userId);
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (WaitList wait : waitList) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(wait.getId());
            bookDTO.setInfo(wait.getBook().getInfo());
            bookDTO.setReturnDate(rentRepository.findLastEndDateBookWasRented(wait.getBook().getId()).plusDays(1));

            bookDTOS.add(bookDTO);
        }

        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
    }

    public ResponseEntity<WaitList> addWaitList(Long userId, Long bookId) {
        WaitList waitList = new WaitList();
        waitList.setUser(userRepository.findById(userId).orElse(null));
        waitList.setBook(bookRepository.findById(bookId).orElse(null));

        waitList.setDate(rentRepository.findLastEndDateBookWasRented(bookId));
        if(waitList.getUser() == null || waitList.getBook() == null
                || waitList.getDate() == null || waitList.getDate().compareTo(LocalDate.now())<0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(repository.save(waitList), HttpStatus.CREATED);
    }
}
