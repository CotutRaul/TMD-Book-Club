package org.endava.tmd.TMDBookClub.controller;


import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestParam Long userId,@RequestParam Long bookInfoId)
    {
        bookService.addBook(userId,bookInfoId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }
}
