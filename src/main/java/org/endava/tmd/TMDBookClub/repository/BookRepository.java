package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
