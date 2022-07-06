package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByNameOrEmail(String name, String email);
    @Query("select u.booksList from User u")
    List<Book> findbooksListbyId(Long id);

    @Query("select u.booksRented from User u")
    List<Rent> findbooksRentedbyId(long id);
}