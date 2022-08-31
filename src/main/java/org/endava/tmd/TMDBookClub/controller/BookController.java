package org.endava.tmd.TMDBookClub.controller;


import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @RequestMapping(params = {"id"}, value = "/available", method = RequestMethod.GET)
    public List<Book> getAvailableBooks(@RequestParam Long id) {
        return bookService.getAvailableBooks(id);
    }

    @RequestMapping(params = {"search"}, value = "search", method = RequestMethod.GET)
    public String searchForBooks(@RequestParam String search) {
        return bookService.searchForBooks(search);
    }

    @RequestMapping(params = {"userId", "bookInfoId"}, method = RequestMethod.POST)
    public void addBook(@RequestParam Long userId, @RequestParam Long bookInfoId) {
        bookService.addBook(userId, bookInfoId);
    }
}
