package pro.security.amg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.security.amg.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/person")
@RestController
public class PersonController {

   private List<Person> persons = new ArrayList<>(
            Arrays.asList(
                    new Person("Ringo Star", 42),
                    new Person("George Harrison", 40)
            )
    );


    @RequestMapping("/list")
    List<Person> showAll(){
        return persons;
    }

}
