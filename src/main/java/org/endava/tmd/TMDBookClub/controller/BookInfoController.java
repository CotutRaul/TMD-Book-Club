package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("BookInfos")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;



    @RequestMapping(method = RequestMethod.GET)
    public List<BookInfo> getAll() {
        return bookInfoService.getAll();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Object getById(@RequestParam("id") Long id) {

        return bookInfoService.getById(id).isPresent()? bookInfoService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody BookInfo bookInfo)
    {
        bookInfoService.addBookInfo(bookInfo);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody BookInfo bookInfo)
    {
        bookInfoService.updateBookInfo(bookInfo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id") Long id)
    {
        bookInfoService.deleteBookInfo(id);
    }

}
