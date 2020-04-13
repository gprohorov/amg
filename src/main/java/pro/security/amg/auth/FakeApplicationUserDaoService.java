package pro.security.amg.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static pro.security.amg.security.ApplicationUserRole.*;


@Repository("fake")
public class FakeApplicationUserDaoService implements IApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        System.out.println(" ---------------- FAKE DATA WAS CALLED -----------------");

        return this.getApplicationUsers().stream()
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
                        passwordEncoder.encode("admin"),
                        "admin",
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        DOCTOR.getGrantedAuthorities(),
                        passwordEncoder.encode("doctor"),
                        "doctor",
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        INTERN.getGrantedAuthorities(),
                        passwordEncoder.encode("intern"),
                        "intern",
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        PERSON.getGrantedAuthorities(),
                        passwordEncoder.encode("person"),
                        "person",
                        true,
                        true,
                        true,
                        true)
        );

        return applicationUsers;
    }
}
