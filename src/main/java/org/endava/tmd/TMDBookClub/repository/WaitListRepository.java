package org.endava.tmd.TMDBookClub.repository;


import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaitListRepository extends JpaRepository<WaitList, Long> {
    @Query("select w.id from WaitList w where w.user.id = :idUser and w.book.id = :idBook")
    Long findIdByIdUserAndIdBook(Long idUser, Long idBook);

    @Query("select w from WaitList w where w.date < current_date ")
    List<WaitList> findAllByDateLessThanCurrentDate();

    List<WaitList> findWaitListByUser_Id(Long userId);
}
