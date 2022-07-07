package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rents")
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Rent> getAll() {
        return rentService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRent(@RequestParam Long userId, @RequestParam Long bookId,@RequestParam int period)
    {
        rentService.addRent(userId,bookId,period);
    }

    @RequestMapping(params = {"id", "period"},method = RequestMethod.PUT)
    public void extendRent(@RequestParam Long id, @RequestParam int period)
    {
        rentService.extendPeriod(id, period);
    }

    @RequestMapping(params = {"userId", "bookId", "period"},method = RequestMethod.PUT)
    public void extendRent(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int period)
    {
        rentService.extendPeriod(userId, bookId, period);
    }

}
