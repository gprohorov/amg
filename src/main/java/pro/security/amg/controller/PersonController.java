package pro.security.amg.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

// hasRole  , hasAnyRole  , hasAuthority, hasAnyAuthority

    @RequestMapping("get/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR', 'ROLE_INTERN')")
    List<Person> showAll(){
        return persons;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR', 'ROLE_INTERN')")
    @RequestMapping("/get/{id}")
    Person show(@PathVariable("id") String id){
        return persons.stream().filter(item-> item.getId().equals(id)).findFirst().orElse(null);
    }


    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('person:write')")
    Person delete(@PathVariable("id") String id){
         System.out.println("Person deleted");
        return persons.get(0);
    }

    @PreAuthorize("hasAuthority('person:write')")
    @PostMapping()
    Person create(@RequestBody Person person){
        System.out.println("------   person created  -----------");
        persons.add(person);
        return person;
    }


}
