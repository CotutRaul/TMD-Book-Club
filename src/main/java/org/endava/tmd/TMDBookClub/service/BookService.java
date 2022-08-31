package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
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

    @Autowired
    private RentRepository rentRepository;


    public void addBook(Long userId, Long bookInfoId) {
        Book book = new Book();
        book.setOwner(userRepository.findById(userId).orElse(null));
        book.setInfo(bookInfoRepository.findById(bookInfoId).orElse(null));
        repository.save(book);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public List<Book> getAvailableBooks(Long id) {
        List<Book> books = repository.findBooksFromOtherUsers(id);
        books = books.stream()
                .filter(book -> book.getRentedBy().stream().noneMatch(rent -> rent.getEndDate().compareTo(LocalDate.now()) >= 0))
                .collect(Collectors.toList());
        return books;
    }

    public String searchForBooks(String search) {
        search = "%" + search + "%";
        List<Book> books = repository.findBooksSearched(search);
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append("Book with id = ")
                    .append(book.getId())
                    .append(", Title = ")
                    .append(book.getInfo().getTitle())
                    .append("  Author = ")
                    .append(book.getInfo().getAuthor());
            if (rentRepository.findIfBookIsRented(book.getId())) {
                result.append(" will be available in: ").append(book.getRentedBy().stream().map(Rent::getEndDate).max(Comparator.naturalOrder()).orElse(null)).append("\n");
            } else {
                result.append(" is available and can be rented from ").append(book.getOwner().getName()).append("\n");
            }
        }
        return result.toString();
    }
}
