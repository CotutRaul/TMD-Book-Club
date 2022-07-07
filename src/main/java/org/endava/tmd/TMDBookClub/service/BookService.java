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

        List<Book> books = repository.getBooksFromOtherUsers(id);
        books = books.stream()
                .filter(book -> book.getRentedBy().stream().noneMatch(rent -> rent.getEndDate().compareTo(LocalDate.now())>=0))
                .collect(Collectors.toList());
        return books;
    }


    public String searchForBooks(String search)
    {
        search = "%"+search+"%";
        List<Book> books = repository.getBooksSearched(search);
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append("Book with id = ")
                    .append(book.getId())
                    .append(", Title = ")
                    .append(book.getInfo().getTitle())
                    .append("  Author = ")
                    .append(book.getInfo().getAuthor());
            if(book.getRentedBy().size()==0)
            {
                result.append(" is available and can be rented from ").append(book.getOwner().getName()).append("\n");
            }
            else
                if(book.getRentedBy().get(book.getRentedBy().size()-1).getEndDate().compareTo(LocalDate.now())>=0)
                {
                    result.append(" will be available in: ").append(book.getRentedBy().get(book.getRentedBy().size()-1).getEndDate()).append("\n");
                }
                else
                {
                    result.append(" is available").append("\n").append(book.getOwner().getName()).append("\n");
                }
        }
        return result.toString();
    }


}
