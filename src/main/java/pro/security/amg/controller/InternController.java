package pro.security.amg.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.security.amg.model.Doctor;
import pro.security.amg.model.Intern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/intern")
@RestController
public class InternController {

   private List<Intern> interns = new ArrayList<>(
            Arrays.asList(
                    new Intern(1,"Lobanov"),
                    new Intern(2,"Levin")
            )
    );


    @RequestMapping("get/list")
    List<Intern> showAll(){
        return interns;
    }

    @RequestMapping("/get/{id}")
    Intern show(@PathVariable("id") String id){
        return interns.get(0);
    }

    @RequestMapping("/delete/{id}")
     Intern delete(@PathVariable("id") String id){

         System.out.println("Intern deleted");

        return interns.get(0);
    }


}
