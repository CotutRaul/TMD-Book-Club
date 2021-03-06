package org.endava.tmd.TMDBookClub.controller;


import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.service.WaitListService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(params = {"userId","bookId"}, method = RequestMethod.POST)
    public void addWaitList(@RequestParam Long userId, @RequestParam Long bookId) {
        waitListService.addWaitList(userId,bookId);
    }

}
