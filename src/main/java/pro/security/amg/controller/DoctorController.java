package pro.security.amg.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.security.amg.model.Doctor;
import pro.security.amg.model.Person;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/doctor")
@RestController
public class DoctorController {

   private List<Doctor> doctors = new ArrayList<>(
            Arrays.asList(
                    new Doctor(1,"Watson"),
                    new Doctor(2,"Piliulkin")
            )
    );


    @RequestMapping("/list")
    List<Doctor> showAll(){
        return doctors;
    }
    @RequestMapping("/get/{id}")
    Doctor show(@PathVariable("id") String id){
        return doctors.get(0);
    }

     @RequestMapping("/delete/{id}")
     Doctor delete(@PathVariable("id") String id){
        return doctors.get(0);
    }


}
