package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.dto.MyBook;
import org.endava.tmd.TMDBookClub.dto.MyRented;
import org.endava.tmd.TMDBookClub.entity.Book;
import org.endava.tmd.TMDBookClub.entity.BookInfo;
import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Object getById(@RequestParam("id") Long id) {

        return userService.getUserById(id).isPresent()? userService.getUserById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(params = {"email", "password"}, method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password)
    {
        return userService.getUserByEmailAndPassword(email,password);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user)
    {
        userService.updateUser(user);
    }

    @RequestMapping(params = "id", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id") Long id)
    {
        userService.deleteUser(id);
    }

    @RequestMapping(params = "id", method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestParam("id") Long id, @RequestBody BookInfo bookInfo)
    {
        return userService.addBook(id, bookInfo);
    }

    @RequestMapping(params = "id", value = "booksReturn", method = RequestMethod.GET)
    public String getBooksReturnToOwner(@RequestParam("id") Long id)
    {
        return userService.getBooksReturnToOwner(id);
    }

    @RequestMapping(params = "id", value = "myBooks", method = RequestMethod.GET)
    public ResponseEntity<List<MyBook>> getMyBooks(@RequestParam("id") Long id)
    {
        return userService.getMyBooks(id);
    }


    @RequestMapping(params = "id", value = "giveRentedBooks", method = RequestMethod.GET)
    public String getBooksUserNeedToReturn(@RequestParam("id") Long id){
        return userService.getBooksUserNeedToReturn(id);
    }

    @RequestMapping(params = "id", value = "myRented", method = RequestMethod.GET)
    public ResponseEntity<List<MyRented>> getMyRented(@RequestParam("id") Long id) {
        return userService.getMyRented(id);
    }

}
