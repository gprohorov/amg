package pro.security.amg.auth;

import java.util.Optional;

public interface IApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
