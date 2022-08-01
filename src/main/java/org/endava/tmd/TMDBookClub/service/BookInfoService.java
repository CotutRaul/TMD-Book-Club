package org.endava.tmd.TMDBookClub.service;

import org.endava.tmd.TMDBookClub.dto.BookDTO;
import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.repository.BookInfoRepository;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookInfoService {

    @Autowired
    private BookInfoRepository repository;

    @Autowired
    private RentRepository rentRepository;


    public List<BookInfo> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<List<BookDTO>> getAllHomeBooks(){
        List<BookInfo> allBookInfos = repository.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (BookInfo allBookInfo : allBookInfos) {
            BookDTO tempBookDTO = new BookDTO();
            tempBookDTO.setInfo(allBookInfo);
            tempBookDTO.setAvailable(false);
            Long tempId=null;
            LocalDate tempDate = LocalDate.now().plusWeeks(7);
            for(Book book : allBookInfo.getBooksList()) {
                LocalDate lastDate = rentRepository.findLastEndDateBookWasRented(book.getId());
                if(lastDate == null)
                {
                    tempBookDTO.setId(book.getId());
                    tempBookDTO.setAvailable(true);
                    break;
                }
                if (lastDate.compareTo(tempDate) < 0) {
                    tempId = book.getId();
                    tempDate = lastDate;
                    if(lastDate.compareTo(LocalDate.now())<0){
                        tempBookDTO.setAvailable(true);
                        break;
                    }
                }
            }
            if(tempBookDTO.getId() == null){
                tempBookDTO.setId(tempId);
            }
            bookDTOS.add(tempBookDTO);
        }

        return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
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
