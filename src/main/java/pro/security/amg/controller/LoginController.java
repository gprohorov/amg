package pro.security.amg.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
  //  CurrentUser currentUser;

    @CrossOrigin(origins = "*")
    @PostMapping("login")
    String getLoginForm(){
        System.out.println("---------------------------------");
        return "login";
    }


    @GetMapping("api/welcomepage")
    String getWelcome(){
        return "You have got an access to API routes";
    }
}
