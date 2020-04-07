package pro.security.amg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.security.amg.jwt.UsernameAndPasswordAuthenticationRequest;

@Controller
@RequestMapping("/")
public class AuthController {

    @PostMapping("auth")
    String getToken(){
  //  String getToken(@RequestBody UsernameAndPasswordAuthenticationRequest request){
        return "login";
    }


    @GetMapping("welcome2")
    String getWelcome(){
        return "welcome";
    }
}
