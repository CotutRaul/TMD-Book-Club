package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.entity.Rent;
import org.endava.tmd.TMDBookClub.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("rents")
@CrossOrigin
public class RentController {

    @Autowired
    private RentService rentService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Rent> getAll() {
        return rentService.getAll();
    }

    @RequestMapping(value = "active",method = RequestMethod.GET)
    public List<Rent> getActive() {
        return rentService.getActiveRents();
    }
    @RequestMapping(params = {"userId", "bookId", "period"},method = RequestMethod.POST)
    public ResponseEntity<Rent> addRent(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int period)
    {
        return rentService.addRent(userId,bookId,period);
    }

    @RequestMapping(params = {"id", "period"},method = RequestMethod.PUT)
    public ResponseEntity<Rent> extendRent(@RequestParam Long id, @RequestParam int period)
    {
        return rentService.extendPeriod(id, period);
    }

    @RequestMapping(params = {"userId", "bookId", "period"},method = RequestMethod.PUT)
    public void extendRent(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int period)
    {
        rentService.extendPeriod(userId, bookId, period);
    }

    @RequestMapping(params = {"bookId"},method = RequestMethod.GET)
    public LocalDate getLastEndDateBookWasRented(@RequestParam Long bookId)
    {
        return rentService.getLastEndDateBookWasRented(bookId);
    }

}
