package com.example.demomethodsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class ContactController {

    @GetMapping("/hello")
    public String hello(){return "Hello without roles";}

    @Secured("ADMIN")
    @GetMapping("/delete-user")
    public String deleteUser(){return "User was deleted";}

    @Secured({"ROLE_READ", "ROLE_ADMIN"})
    @GetMapping("/read")
    public String write(){return "Read";}

    @RolesAllowed({"ROLE_WRITE", "ROLE_ADMIN"})
    @GetMapping("/write")
    public String read(){return "Write";}

    @PreAuthorize("hasAnyRole('ROLE_READ', 'ROLE_DELETE', 'ROLE_ADMIN')")
    @GetMapping("/change")
    public String change(){return "Change for WRITE AND DELETE";}

    @PostAuthorize("#username==authentication.principal.username")
    @GetMapping("/helloUser")
    public String helloUser(@RequestParam("username") String username){
        return "Hello, " + username + " !";
    }
}
