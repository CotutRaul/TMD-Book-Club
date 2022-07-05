package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
    @Autowired
    private BookRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookInfoRepository bookInfoRepository;



    public void addBook(Long userId, Long bookInfoId) {
        Book book = new Book();
        book.setOwner(userRepository.findById(userId).get());
        book.setInfo(bookInfoRepository.findById(bookInfoId).get());
        repository.save(book);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }


    public List<Book> getAvailableBooks(Long id)
    {

        List<Book> books = repository.getBooksWithNoWaitingList(id);
        books = books.stream()
                .filter(book -> book.getRentedBy().stream().noneMatch(rent -> rent.getEndDate().compareTo(LocalDate.now())>=0))
                .collect(Collectors.toList());
        return books;
    }


}
