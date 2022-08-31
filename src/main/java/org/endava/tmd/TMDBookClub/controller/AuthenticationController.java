package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.dto.AuthenticationResponse;
import org.endava.tmd.TMDBookClub.dto.UsernameAndPasswordAuthenticationRequest;
import org.endava.tmd.TMDBookClub.entity.User;
import org.endava.tmd.TMDBookClub.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authenticate")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticateLogin(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest) throws Exception {
        return authenticationService.authenticateLogin(authenticationRequest);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticateRegister(@RequestBody User user) throws Exception {
        return authenticationService.authenticateRegister(user);
    }
}
