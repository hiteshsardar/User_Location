package  org.com.User_Location.User_Location.controller;


import org.com.User_Location.User_Location.models.Users;
import org.com.User_Location.User_Location.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/userDetails")
    public List<Users> getUser(){
        return this.usersService.getUsers();
    }
}
