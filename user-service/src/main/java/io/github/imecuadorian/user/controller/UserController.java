package io.github.imecuadorian.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/list")
    public String message(){
        return "Lista de usuarios";
    }

}
