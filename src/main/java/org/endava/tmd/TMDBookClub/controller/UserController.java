package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public User getById(@RequestParam("id") Long id) {
        return userService.getById(id);
    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void deleteById(@RequestParam Long id)
//    {
//        //Delete
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void addUser(@RequestBody User user)
//    {
//        //code
//    }
}
