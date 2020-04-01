package pro.security.amg.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static pro.security.amg.security.ApplicatioUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(PERSON_READ, PERSON_WRITE, DOCTOR_READ, DOCTOR_WRITE, INTERN_READ, INTERN_WRITE)),
    DOCTOR(Sets.newHashSet(PERSON_READ, PERSON_WRITE, INTERN_READ)),
    INTERN(Sets.newHashSet(PERSON_READ)),
    PERSON(Sets.newHashSet());


    private final Set<ApplicatioUserPermission> permissions;

    ApplicationUserRole(Set<ApplicatioUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicatioUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority>  getGrantedAuthorities(){

        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }


}

