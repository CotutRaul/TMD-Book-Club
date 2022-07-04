package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private final BookRepository repository;
    private final UserRepository userRepository;
    private final BookInfoRepository bookInfoRepository;

    public BookService(BookRepository repository, UserRepository userRepository, BookInfoRepository bookInfoRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookInfoRepository = bookInfoRepository;
    }

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
