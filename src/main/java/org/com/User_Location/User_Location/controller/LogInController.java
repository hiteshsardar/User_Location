package  org.com.User_Location.User_Location.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping()
public class LogInController {

    @GetMapping("/home")
    public String userLogin() {
       return "Welcome Home !!";
    }

    @GetMapping("/getLoggedInUser")
    public String loggedInUser(Principal principal) {
        return principal.getName();
    }
}
