package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query("select r from Rent r where r.user.id = :userId and r.book.id = :bookId")
    Rent findRentByUserIdAndBookId(Long userId,Long bookId);

    @Query("select r from Rent r where r.book.owner.id = :id and r.endDate >= current_date ")
    List<Rent> findBooksReturnToOwner(Long id);

    @Query("select r.book from Rent r where r.user.id = :id and r.endDate >= current_date")
    List<Book> findbooksRentedbyId(Long id);

    @Query("select case when (count(r) > 0)  then true else false end from Rent r where r.book.id = :id and r.endDate >= current_date")
    Boolean findIfBookIsRented(Long id);

    @Query("select r from Rent r where r.endDate >= current_date ")
    List<Rent> findActiveRents();

    @Query("select max(r.endDate) from Rent r where r.book.id= :bookId")
    LocalDate findLastEndDateBookWasRented(Long bookId);
}
