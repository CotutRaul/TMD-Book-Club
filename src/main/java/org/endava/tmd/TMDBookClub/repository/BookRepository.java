package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.owner.id <> :id")
    List<Book> findBooksFromOtherUsers(Long id);

    @Query("SELECT b FROM Book b WHERE LOWER(b.info.title) LIKE LOWER(:search) OR LOWER(b.info.author) LIKE LOWER(:search) ")
    List<Book> findBooksSearched(String search);
}
