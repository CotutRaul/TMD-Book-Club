package org.endava.tmd.TMDBookClub.controller;


import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.service.WaitListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("waitLists")
public class WaitListController {
    @Autowired
    private WaitListService waitListService;

    @RequestMapping(method = RequestMethod.GET)
    public List<WaitList> getAll() {
        return waitListService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addWaitList(@RequestParam Long userId, @RequestParam Long bookId) {
        waitListService.addWaitList(userId,bookId);
    }

}
