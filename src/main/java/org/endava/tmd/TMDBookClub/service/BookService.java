package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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


}
