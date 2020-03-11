package pro.security.amg.controller;

import org.springframework.web.bind.annotation.PathVariable;
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
                    new Person("1","Ringo Star", 42),
                    new Person("2","George Harrison", 40)
            )
    );


    @RequestMapping("/list")
    List<Person> showAll(){
        return persons;
    }
    @RequestMapping("/get/{id}")
    Person show(@PathVariable("id") String id){
        return persons.get(0);
    }

     @RequestMapping("/delete/{id}")
    Person delete(@PathVariable("id") String id){
        return persons.get(0);
    }


}
