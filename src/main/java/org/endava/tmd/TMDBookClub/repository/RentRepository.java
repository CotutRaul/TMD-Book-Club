package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query("select r from Rent r where r.user.id = :userId and r.book.id = :bookId")
    Rent findRentByUserIdAndBookId(Long userId,Long bookId);

}
