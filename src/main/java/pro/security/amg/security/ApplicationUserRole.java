package pro.security.amg.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static pro.security.amg.security.ApplicatioUserPermission.*;

public enum ApplicationUserRole {

    ADMIN_ROLE(Sets.newHashSet(PERSON_READ, PERSON_WRITE, DOCTOR_READ, DOCTOR_WRITE, INTERN_READ, INTERN_WRITE)),
    DOCTOR_ROLE(Sets.newHashSet(PERSON_READ, PERSON_WRITE, INTERN_READ)),
    INTERN_ROLE(Sets.newHashSet(PERSON_READ)),
    PERSON_ROLE(Sets.newHashSet());


    private final Set<ApplicatioUserPermission> permissions;

    ApplicationUserRole(Set<ApplicatioUserPermission> permissions) {
        this.permissions = permissions;
    }
}

