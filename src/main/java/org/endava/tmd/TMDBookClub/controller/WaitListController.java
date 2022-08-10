package org.endava.tmd.TMDBookClub.controller;


import org.endava.tmd.TMDBookClub.dto.BookDTO;
import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.service.WaitListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waitLists")
@CrossOrigin
public class WaitListController {
    @Autowired
    private WaitListService waitListService;

    @RequestMapping(method = RequestMethod.GET)
    public List<WaitList> getAll() {
        return waitListService.getAll();
    }

    @RequestMapping(params = {"userId"}, method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getWaitListForUser(@RequestParam Long userId) {
        return waitListService.getWaitListForUser(userId);
    }

    @RequestMapping(params = {"userId","bookId"}, method = RequestMethod.POST)
    public ResponseEntity<WaitList> addWaitList(@RequestParam Long userId, @RequestParam Long bookId) {
        return waitListService.addWaitList(userId,bookId);
    }

}
