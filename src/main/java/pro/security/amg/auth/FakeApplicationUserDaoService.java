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
        return this.getApplicationUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                ;
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
