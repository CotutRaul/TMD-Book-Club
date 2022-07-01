package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookInfoService {

    private final BookInfoRepository repository;

    public BookInfoService(BookInfoRepository repository) {
        this.repository = repository;
    }

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
