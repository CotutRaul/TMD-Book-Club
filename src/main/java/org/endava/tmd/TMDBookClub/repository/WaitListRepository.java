package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaitListRepository extends JpaRepository<WaitList, Long> {
    @Query("select w.id from WaitList w where w.user.id = :idUser and w.book.id = :idBook")
    Long findIdByIdUserAndIdBook(Long idUser, Long idBook);
}
