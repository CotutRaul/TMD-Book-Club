package org.endava.tmd.TMDBookClub.controller;

import org.endava.tmd.TMDBookClub.dto.AuthenticationResponse;
import org.endava.tmd.TMDBookClub.dto.UsernameAndPasswordAuthenticationRequest;
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
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest) throws Exception{

        return authenticationService.login(authenticationRequest);
    }
}
