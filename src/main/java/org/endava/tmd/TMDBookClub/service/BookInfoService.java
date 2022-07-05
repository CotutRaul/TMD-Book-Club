package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookInfoService {

    @Autowired
    private BookInfoRepository repository;


    public List<BookInfo> getAll() {
        return repository.findAll();
    }

    public Optional<BookInfo> getById(Long id) {

        return repository.findById(id);
    }

    public void addBookInfo(BookInfo bookInfo) {
        repository.save(bookInfo);

    }

    public void deleteBookInfo(Long id) {
        repository.deleteById(id);
    }

    public void updateBookInfo(BookInfo bookInfo) {
        if (repository.findById(bookInfo.getId()).isPresent())
            repository.save(bookInfo);
    }
}
