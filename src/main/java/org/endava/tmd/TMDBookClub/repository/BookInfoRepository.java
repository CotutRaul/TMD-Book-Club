package org.endava.tmd.TMDBookClub.repository;

import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
    BookInfo findBookInfoByTitle(String title);
}
