package pro.security.amg.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static pro.security.amg.security.ApplicationUserRole.*;


@Repository("mongodb")
public class MongoApplicationUserDaoService implements IApplicationUserDao {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository repository;

    @Autowired
    public MongoApplicationUserDaoService(PasswordEncoder passwordEncoder, ApplicationUserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

//    @PostConstruct
    void init(){
   repository.saveAll(this.getApplicationUsers());
    }



    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        System.out.println(" ---------------- MONGODB DATA WAS CALLED -----------------");

        return this.repository.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                ;
    }

    @Override
    public List<ApplicationUser> getAll() {
        return null;
    }

    @Override
    public ApplicationUser create(ApplicationUser user) {
        return null;
    }

    @Override
    public ApplicationUser update(ApplicationUser user) {
        return null;
    }

    @Override
    public ApplicationUser get(String id) {
        return null;
    }

    @Override
    public ApplicationUser delete(String id) {
        return null;
    }


    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(

                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        "admin",
                        passwordEncoder.encode("admin"),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        DOCTOR.getGrantedAuthorities(),
                        "doctor",
                        passwordEncoder.encode("doctor"),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        INTERN.getGrantedAuthorities(),
                        "intern",
                        passwordEncoder.encode("intern"),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        PERSON.getGrantedAuthorities(),
                        "person",
                        passwordEncoder.encode("person"),
                        true,
                        true,
                        true,
                        true)
        );

        return applicationUsers;
    }
}
