package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByNameOrEmail(String name, String email);

    User findUserByEmailAndPassword(String name, String password);
    @Query("select u.booksList from User u where u.id= :id")
    List<Book> findbooksListbyId(Long id);

    User findUserByEmail(String email);
}