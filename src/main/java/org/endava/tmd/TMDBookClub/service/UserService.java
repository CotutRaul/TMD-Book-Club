package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.dto.BookDTO;
import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.BookRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentRepository rentRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id)
    {
        return repository.findById(id);
    }

    public ResponseEntity<User> getUserByEmailAndPassword(String email, String password)
    {
        User user = repository.findUserByEmail(email);
        if (user != null) {
            if(bCryptPasswordEncoder.matches(password,user.getPassword())) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<User> addUser(User user)
    {
        User checkUser = repository.findUserByNameOrEmail(user.getName(),user.getEmail());
        if(checkUser == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public void deleteUser(Long id)
    {
        repository.deleteById(id);
    }

    public void updateUser(User user) {
        if (repository.findById(user.getId()).isPresent())
            repository.save(user);
    }


    public ResponseEntity<Book> addBook(Long id, BookInfo bookInfo) {
        HttpStatus status = HttpStatus.OK;
        User tempUser = repository.findById(id).orElse(null);
        if(tempUser == null || bookInfo.getTitle().equals("") || bookInfo.getAuthor().equals("")){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        BookInfo tempBookInfo = bookInfoRepository.findBookInfoByTitle(bookInfo.getTitle());
        if(tempBookInfo == null){
            tempBookInfo = bookInfoRepository.save(bookInfo);
            status = HttpStatus.CREATED;
        }
        Book book = new Book();
        book.setInfo(tempBookInfo);
        book.setOwner(tempUser);
        return new ResponseEntity<>(bookRepository.save(book),status);
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

    public ResponseEntity<List<BookDTO>> getMyBooks(Long id)
    {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> booksList = repository.findById(id).get().getBooksList();
        for (Book book : booksList) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setInfo(book.getInfo());
            Rent rent = book.getRentedBy().stream().filter(r -> r.getEndDate().compareTo(LocalDate.now()) >= 0).findFirst().orElse(null);
            if (rent != null) {
                bookDTO.setUserName(rent.getUser().getName());
                bookDTO.setReturnDate(rent.getEndDate());
            }
            bookDTOS.add(bookDTO);
        }
        return new ResponseEntity<>(bookDTOS,HttpStatus.OK);
    }


    public String getBooksUserNeedToReturn(Long id)
    {
        StringBuilder result = new StringBuilder("You rented:\n");
        List<Book> books = rentRepository.findBooksRentedByUserId(id);
        for (Book book : books) {
            result.append("Book with id = ")
                    .append(book.getId()).append(", Title = ")
                    .append(book.getInfo().getTitle())
                    .append(" from ").append(book.getOwner().getName())
                    .append(" and need to return it at ").append(book.getRentedBy().get(book.getRentedBy().size() - 1).getEndDate()).append("\n");
        }
        return result.toString();
    }

    public ResponseEntity<List<BookDTO>> getMyRented(Long id)
    {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Rent> rents = rentRepository.findActiveRentsByUserId(id);
        for (Rent rent : rents) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(rent.getId());
            bookDTO.setInfo(rent.getBook().getInfo());
            bookDTO.setUserName(rent.getBook().getOwner().getName());
            bookDTO.setReturnDate(rent.getEndDate());
            bookDTOS.add(bookDTO);
        }
        return new ResponseEntity<>(bookDTOS,HttpStatus.OK);
    }
}

