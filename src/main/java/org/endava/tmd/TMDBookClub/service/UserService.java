package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentRepository rentRepository;


    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getById(Long id)
    {
        return repository.findById(id);
    }

    public void addUser(User user)
    {
        User checkUser = repository.findUserByNameOrEmail(user.getName(),user.getEmail());
        if(checkUser == null)
            repository.save(user);
    }

    public void deleteUser(Long id)
    {
        repository.deleteById(id);
    }

    public void updateUser(User user) {
        if (repository.findById(user.getId()).isPresent())
            repository.save(user);
    }


    public void addBook(Long id, BookInfo bookInfo) {
        User tempUser = repository.findById(id).orElse(null);
        BookInfo tempBookInfo = bookInfoRepository.findBookInfoByTitle(bookInfo.getTitle());
        if(tempBookInfo == null)
        {
            tempBookInfo = bookInfoRepository.save(bookInfo);
        }
        Book book = new Book();
        book.setInfo(tempBookInfo);
        book.setOwner(tempUser);
        bookRepository.save(book);
    }

    public String getBooksReturnToOwner(Long id)
    {
        StringBuilder result = new StringBuilder();
        List<Rent> rents = rentRepository.findBooksReturnToOwner(id);

        for (Rent rent : rents) {
            result.append("Book with id = ")
                    .append(rent.getBook().getId()).append(", Title = ")
                    .append(rent.getBook().getInfo().getTitle())
                    .append(" will be returned at ")
                    .append(rent.getEndDate()).append(" by ")
                    .append(rent.getUser().getName()).append("\n");
        }
        return result.toString();
    }

    public String getBooksUserNeedToReturn(Long id)
    {
        StringBuilder result = new StringBuilder("You rented:\n");
        List<Book> books = rentRepository.findbooksRentedbyId(id);
        for (Book book : books) {
            result.append("Book with id = ")
                    .append(book.getId()).append(", Title = ")
                    .append(book.getInfo().getTitle())
                    .append(" from ").append(book.getOwner().getName())
                    .append(" and need to return it at ").append(book.getRentedBy().get(book.getRentedBy().size() - 1).getEndDate()).append("\n");
        }
        return result.toString();
    }
}

