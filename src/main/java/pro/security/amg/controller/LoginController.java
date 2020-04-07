package pro.security.amg.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class LoginController {


    @PostMapping("login")
    String getLoginForm(){

        return "login";
    }

@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("api/page")
    String getApiPage(){

        System.out.println("CALLED API");

        return "You are welcome! Access is permited";
    }

    @GetMapping("welcome")
    String getWelcome(){
        return "welcome";
    }


}
